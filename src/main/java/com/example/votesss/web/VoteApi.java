package com.example.votesss.web;

import com.example.votesss.app.domain.Vote;
import com.example.votesss.app.domain.VoteStats;
import com.example.votesss.app.service.VoteService;
import com.example.votesss.app.service.VoteStatsChangedEvent;
import com.example.votesss.web.DTO.SaveVoteRequest;
import com.example.votesss.web.DTO.SaveVoteResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


@RequiredArgsConstructor
@RestController
public class VoteApi {
    private static final List<SseEmitter> voteStatsStreams = new CopyOnWriteArrayList<>();

    private final VoteService service;
    private final ObjectMapper objectMapper;

    @PostMapping(path = "/votes")
    public SaveVoteResponse save(@RequestBody SaveVoteRequest request) {
        Vote vote = new Vote();
        vote.setUserId(request.getUserId());
        vote.setVoteValue(request.getVoteValue());

        boolean isSaved = service.save(vote);

        SaveVoteResponse response = new SaveVoteResponse();
        response.setSaved(isSaved);
        return response;

    }

    @GetMapping(path = "/votes/stats")
    public GetVoteStatsResponse getStats() {

        VoteStats voteStats = service.getStats();


        return GetVoteStatsResponse.builder()
                .totalY(voteStats.getTotalY())
                .totalN(voteStats.getTotalN())
                .build();

    }

    @GetMapping(path = "/votes/stream")
    public SseEmitter getStatsStream() throws JsonProcessingException {
        SseEmitter voteStatsStream = new SseEmitter();

        notifyVoteStatsStreams(List.of(voteStatsStream));

        voteStatsStreams.add(voteStatsStream);

        voteStatsStream.onError(e -> {
            voteStatsStreams.remove(voteStatsStream);
        });

        voteStatsStream.onTimeout(() -> {
            voteStatsStreams.remove(voteStatsStream);
        });

        voteStatsStream.onCompletion(() -> {
            voteStatsStreams.remove(voteStatsStream);
        });


        return voteStatsStream;

    }

    @EventListener
    public void on(VoteStatsChangedEvent event) throws JsonProcessingException {
        notifyVoteStatsStreams(voteStatsStreams);
    }

    private void notifyVoteStatsStreams(List<SseEmitter> voteStatsStreams) throws JsonProcessingException {
        VoteStats stats = service.getStats();

        String statsJson =  objectMapper.writeValueAsString(stats);


        for (SseEmitter voteStatsStream : voteStatsStreams) {
            try {
                voteStatsStream.send(statsJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

@Getter
@Builder
class GetVoteStatsResponse {

    private long totalY;

    private long totalN;
}

