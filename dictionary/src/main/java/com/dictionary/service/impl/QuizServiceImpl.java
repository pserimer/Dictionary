// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service.impl;

import com.dictionary.models.Question;
import com.dictionary.models.Quiz;
import com.dictionary.models.User;
import com.dictionary.models.dao.QuestionDao;
import com.dictionary.models.dao.QuizDao;
import com.dictionary.service.QuestionService;
import com.dictionary.service.QuizService;
import com.dictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.Random;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {

    private final UserService userService;

    private final QuestionService questionService;

    private Random rand;

    public QuizServiceImpl(UserService userService, QuestionService questionService) {
        rand = new Random();
        this.userService = userService;
        this.questionService = questionService;
    }

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    @Override
    public Quiz generateQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTakenAt(ZonedDateTime.now());

        int ctrl = rand.nextInt(2);

        if (ctrl == 1)
            quiz.setQuestions(questionService.createQuestionsForEnglish());
        else
            quiz.setQuestions(questionService.createQuestionsForTurkish());

        return quiz;
    }

    @Override
    public Quiz findQuiz(Long quizId) {
        return quizDao.findById(quizId).orElseThrow(() -> new EntityNotFoundException("Quiz not found"));
    }

    @Override
    public Quiz finishQuiz(Quiz quiz, String email) {
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

        quiz.setCorrect(quiz.getCorrect());
        quiz.setIncorrect(quiz.getIncorrect());
        quiz.setEmpty(quiz.getEmpty());
        quiz.setScore((quiz.getCorrect() * 2.0));

        for (Question question : quiz.getQuestions()) {
            questionDao.saveAndFlush(question);
        }

        User currentUser = userService.findUserByEmail(email);

        if (currentUser.getBestScore() < quiz.getScore())
            currentUser.setBestScore(quiz.getScore());

        userService.updateUser(currentUser.getEmail(), currentUser);

        return quizDao.saveAndFlush(quiz);
    }

    @Override
    public void deleteQuiz(Long quizId) {
        Quiz quiz = quizDao.findById(quizId).orElseThrow(() -> new EntityNotFoundException("Quiz not found"));

        for (Question question : quiz.getQuestions()) {
            questionDao.delete(question);
        }

        quizDao.deleteById(quizId);
    }
}
