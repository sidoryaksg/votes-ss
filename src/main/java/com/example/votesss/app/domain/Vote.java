package com.example.votesss.app.domain;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name = "votes")
@Check(constraints = "vote_value in ('Y', 'N')")
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Vote {

    @EqualsAndHashCode.Include
    @Id
    @Type(type = "uuid-char")
    @Column (length = 36)
    private UUID userId;

    @Column (length = 1, updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private VoteValue voteValue;
}
