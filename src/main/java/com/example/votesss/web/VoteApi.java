package com.example.votesss.web;

import com.example.votesss.app.domain.Vote;
import com.example.votesss.app.domain.VoteValue;
import com.example.votesss.app.service.VoteService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
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
}

@Getter
@Setter
class SaveVoteRequest {
    private UUID userId;
    private VoteValue voteValue;
}

@Getter
@Setter
class SaveVoteResponse {
    private boolean isSaved;

}