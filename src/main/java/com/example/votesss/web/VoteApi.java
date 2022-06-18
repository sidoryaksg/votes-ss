package com.example.votesss.web;

import com.example.votesss.app.domain.Vote;
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

    @GetMapping (path = "/votes/stats")
    public GetVoteStatsResponse getStats() {
        return GetVoteStatsResponse
                .builder()
                .build();

    }
}

@Getter
@Builder
class GetVoteStatsResponse {

    @Builder.Default
    private long totalY = 0;

    @Builder.Default
    private long totalN = 0;
}