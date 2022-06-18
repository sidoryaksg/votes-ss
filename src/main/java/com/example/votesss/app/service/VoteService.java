package com.example.votesss.app.service;

import com.example.votesss.app.domain.Vote;
import com.example.votesss.app.domain.VoteStats;
import com.example.votesss.app.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class VoteService {

    private final VoteRepository repository;

    public boolean save (Vote vote) {
        if (repository.existsByUserId(vote.getUserId())) {
            return false;
        }
        repository.save(vote);

        return true;
    }


    public VoteStats getStats() {
        return VoteStats.builder()
                .totalY(0)
                .totalN(0)
                .build();
    }
}
