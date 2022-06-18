package com.example.votesss.app.domain;



import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table (name = "votes")
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Check(constraints = "vote_value in ('Y', 'N')")
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
