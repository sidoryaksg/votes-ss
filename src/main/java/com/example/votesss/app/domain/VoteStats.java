package com.example.votesss.app.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class VoteStats {

    private final long totalY;

    private final long totalN;
}