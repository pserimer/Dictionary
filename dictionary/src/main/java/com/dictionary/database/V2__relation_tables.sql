-- Group Members:
-- 201611007 - Batuhan Bayraktar
-- 201711058 - Pelinsu Serimer
-- 201711049 - Zeynep Özdoğan

create table dictionary.user_words
(
    user_id int not null,
    word_id int not null,
    CONSTRAINT fk_user_words_user_id
        FOREIGN KEY (user_id)
            REFERENCES dictionary.users (id),
    CONSTRAINT fk_user_words_word_id
        FOREIGN KEY (word_id)
            REFERENCES dictionary.word (id)
);

create table user_quizzes
(
    user_id int not null,
    quiz_id int not null,
    CONSTRAINT fk_user_quizzes_user_id
        FOREIGN KEY (user_id)
            REFERENCES dictionary.users (id),
    CONSTRAINT fk_user_quizzes_quiz_id
        FOREIGN KEY (quiz_id)
            REFERENCES dictionary.quiz (id)
);

create table quiz_questions
(
    quiz_id     int not null,
    question_id int not null,
    CONSTRAINT fk_quiz_questions_quiz_id
        FOREIGN KEY (quiz_id)
            REFERENCES dictionary.quiz (id),
    CONSTRAINT fk_quiz_questions_question_id
        FOREIGN KEY (question_id)
            REFERENCES dictionary.question (id)
);
