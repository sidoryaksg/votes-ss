package com.example.votesss.app.service;

import com.example.votesss.app.domain.Vote;
import com.example.votesss.app.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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



}
