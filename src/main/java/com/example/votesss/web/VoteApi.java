package com.example.votesss.web;

import com.example.votesss.app.domain.Vote;
import com.example.votesss.app.domain.VoteStats;
import com.example.votesss.app.service.VoteService;
import com.example.votesss.web.DTO.SaveVoteRequest;
import com.example.votesss.web.DTO.SaveVoteResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;


@RequiredArgsConstructor
@RestController
public class VoteApi {
    private final VoteService service;

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
    public SseEmitter getStatsStream() {
        SseEmitter sseEmitter = new SseEmitter();

        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                try {
                    Thread.sleep(i * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    sseEmitter.send("""
                            {
                                "totalY": %d,
                                "totalN": 0
                            }""".formatted(i));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return sseEmitter;

    }
}

@Getter
@Builder
class GetVoteStatsResponse {

    private long totalY;

    private long totalN;
}

