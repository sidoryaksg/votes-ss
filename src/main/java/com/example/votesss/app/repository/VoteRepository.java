package com.example.votesss.app.repository;

import com.example.votesss.app.domain.Vote;
import com.example.votesss.app.domain.VoteStats;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    boolean existsByUserId(UUID userId);

    @Query (nativeQuery = true, value = "select" +
            " count(*) filter (where vote_value = 'Y') as totalY," +
            " count(*) filter (where vote_value = 'N') as totalN" +
            " from votes")

    VoteStats getStats();

}
