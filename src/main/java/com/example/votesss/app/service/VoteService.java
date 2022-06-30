package com.example.votesss.app.service;

import com.example.votesss.app.domain.Vote;
import com.example.votesss.app.domain.VoteStats;
import com.example.votesss.app.domain.VoteValue;
import com.example.votesss.app.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RequiredArgsConstructor
@Service
public class VoteService {
    private static final String VOTE_STATS_CACHE_NAME = "vote-stats";

    private final VoteRepository repository;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    public boolean save (Vote vote) {
        if (repository.saveOnce(vote) == 1){
            cacheManager.getCache(VOTE_STATS_CACHE_NAME).clear();

            applicationEventPublisher.publishEvent(new VoteStatsChangedEvent("votes stats changed"));
            return true;
        }

        return false;
    }

    @Cacheable("vote-stats")
    public VoteStats getStats() {
        return repository.getStats();
    }
}
