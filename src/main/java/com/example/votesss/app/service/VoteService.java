package com.example.votesss.app.service;

import com.example.votesss.app.domain.Vote;
import com.example.votesss.app.domain.VoteStats;
import com.example.votesss.app.domain.VoteValue;
import com.example.votesss.app.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
        return repository.getStats();
    }
}
