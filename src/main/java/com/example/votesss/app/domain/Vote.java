package com.example.votesss.app.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table (name = "votes")


public class Vote {

    @Id
    private UUID user_id;
    @Column (updatable = false)
    private VoteValue voteValue;

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public VoteValue getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(VoteValue voteValue) {
        this.voteValue = voteValue;
    }
}
