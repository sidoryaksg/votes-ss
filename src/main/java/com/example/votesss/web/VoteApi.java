package com.example.votesss.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class VoteApi {

    @PostMapping (path="/votes")
    SaveVoteResponse save (SaveVoteRequest request) {

    }
}

class SaveVoteRequest {
    private UUID userId;
    private VoteValue voteValue;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public VoteValue getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(VoteValue voteValue) {
        this.voteValue = voteValue;
    }
}

class SaveVoteResponse {
    private boolean isSaved;
}