package com.example.votesss.web.DTO;

import com.example.votesss.app.domain.VoteValue;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaveVoteRequest {
    private UUID userId;
    private VoteValue voteValue;
}
