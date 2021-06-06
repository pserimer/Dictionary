-- Group Members:
-- 201611007 - Batuhan Bayraktar
-- 201711058 - Pelinsu Serimer
-- 201711049 - Zeynep Özdoğan

create table dictionary.users
(
    id                    int generated always as identity primary key,
    email                 varchar(255) not null,
    password              varchar(255) not null,
    best_score            double precision,
    num_of_searched_words int
);

create table dictionary.word
(
    id      int generated always as identity primary key,
    turkish varchar(255) not null,
    english varchar(255) not null
);

create table dictionary.quiz
(
    id        int generated always as identity primary key,
    correct   int,
    incorrect int,
    empty     int,
    score     double precision,
    taken_at  timestamp without time zone
);

create table dictionary.question
(
    id       int generated always as identity primary key,
    question varchar(255) not null,
    posAnsA  varchar(255),
    posAnsB  varchar(255),
    posAnsC  varchar(255),
    selected varchar(255),
    answer   varchar(255)
);
