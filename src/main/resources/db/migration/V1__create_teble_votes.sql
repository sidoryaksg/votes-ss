create table votes (
    user_id varchar(36) not null primary key,
    vote_value varchar(1) not null,
    check (vote_value in ('Y', 'N'))
);