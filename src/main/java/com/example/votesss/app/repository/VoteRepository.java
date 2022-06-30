package com.example.votesss.app.repository;

import com.example.votesss.app.domain.Vote;
import com.example.votesss.app.domain.VoteStats;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    boolean existsByUserId(UUID userId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "insert into votes (user_id, vote_value)" +
            " select " +
            "    user_id, vote_value" +
            " from" +
            " (select " +
            "    cast(:#{#vote.userId} as varchar)  as user_id, " +
            "    cast(:#{#vote.voteValue.name} as varchar) as vote_value)" +
            " where " +
            "     not exists (select vote_value from votes where user_id = cast(:#{#vote.userId} as varchar))")
    int saveOnce(Vote vote);

    @Query(nativeQuery = true, value = "select" +
            " count(*) filter (where vote_value = 'Y') as totalY," +
            " count(*) filter (where vote_value = 'N') as totalN" +
            " from votes")
    VoteStats getStats();

}
