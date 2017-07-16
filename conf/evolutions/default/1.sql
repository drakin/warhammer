# --- Initiali DB schema for identity management

# --- !Ups

create table Players (
  nickname                  varchar(255) not null primary key,
  password                  varchar(255) not null
);

# --- !Downs

drop table if exists players;