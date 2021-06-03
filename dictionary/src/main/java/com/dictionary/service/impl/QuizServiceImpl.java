package com.dictionary.service.impl;

import com.dictionary.models.Question;
import com.dictionary.models.Quiz;
import com.dictionary.service.QuestionService;
import com.dictionary.service.QuizService;
import com.dictionary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final UserService userRepository;

    private final QuestionService questionRepository;

    @Override
    public Quiz generateQuiz() {
        Quiz quiz = new Quiz();
        quiz.setUuid(UUID.randomUUID().toString());
        quiz.setTakenAt(ZonedDateTime.now());

        Random rand = new Random();
        int ctrl;

        for (int i = 0; i < 5 ; i++) {
            ctrl = rand.nextInt(2);

            if (ctrl == 1)
                quiz.getQuestions().add(questionRepository.createQuestionForEnglish());
            else
                quiz.getQuestions().add(questionRepository.createQuestionForTurkish());
        }

        return quiz;
    }

    @Override
    public Quiz findQuiz(String quizId) {
        //TODO: find quiz from database

        return null;
    }

    @Override
    public Quiz finishQuiz(String quizId, Quiz quiz) {
        Quiz retrievedQuiz = findQuiz(quizId);

        for (Question question : quiz.getQuestions()) {
            if ("a".equals(question.getSelected())) {
                if (question.getPosAnsA().equals(question.getAnswer()))
                    quiz.setCorrect(quiz.getCorrect() + 1);
                else
                    quiz.setIncorrect(quiz.getIncorrect() + 1);
            }

            else if ("b".equals(question.getSelected())) {
                if (question.getPosAnsB().equals(question.getAnswer()))
                    quiz.setCorrect(quiz.getCorrect() + 1);
                else
                    quiz.setIncorrect(quiz.getIncorrect() + 1);
            }

            else if ("c".equals(question.getSelected())) {
                if (question.getPosAnsC().equals(question.getAnswer()))
                    quiz.setCorrect(quiz.getCorrect() + 1);
                else
                    quiz.setIncorrect(quiz.getIncorrect() + 1);
            }
            else
                quiz.setEmpty(quiz.getEmpty() + 1);
        }

        retrievedQuiz.setCorrect(quiz.getCorrect());
        retrievedQuiz.setIncorrect(quiz.getIncorrect());
        retrievedQuiz.setEmpty(quiz.getEmpty());
        retrievedQuiz.setScore((quiz.getCorrect() * 2.0));

        //TODO: update database with retrievedQuiz

        //User currentUser = userRepository.findUserByEmail(/*Principal principal.getName()*/))
        /*if (currentUser.getScore < quiz.getScore())
            user.setBestScore(quiz.getScore());
        userRepository.updateUser(currentUser.getEmail(), user);*/

        return retrievedQuiz;
    }

    @Override
    public void deleteQuiz(String quizId) {

    }
}
