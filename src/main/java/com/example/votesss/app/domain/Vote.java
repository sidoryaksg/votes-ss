package com.example.votesss.app.domain;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table (name = "votes")

@Getter
@Setter
public class Vote {

    @Id
    private UUID userId;
    @Column (updatable = false)
    private VoteValue voteValue;
}
