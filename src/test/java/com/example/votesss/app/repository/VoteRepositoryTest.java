package com.example.votesss.app.repository;

import com.example.votesss.app.domain.VoteStats;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VoteRepositoryTest {

    @Autowired
    private VoteRepository repository;

    @Test
    void getStats() {
        VoteStats stats = repository.getStats();
        Assertions.assertEquals(2, stats.getTotalY());
        Assertions.assertEquals(2, stats.getTotalN());
    }


    @Test
    @Sql(statements = "delete from votes")
    void getStatsWhenNoVotes () {
        VoteStats stats = repository.getStats();

        Assertions.assertEquals(0, stats.getTotalY());
        Assertions.assertEquals(0, stats.getTotalN());



    }
}