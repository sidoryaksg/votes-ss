package com.example.votesss.app.repository;

import com.example.votesss.app.domain.Vote;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;
import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    boolean existsByUserId(UUID userId);

    @Query ("select voteValue as voteValue, count (*) as voteTotal from Vote group by voteValue")
    List<Tuple> getStats();

}
