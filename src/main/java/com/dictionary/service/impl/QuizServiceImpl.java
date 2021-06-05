// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service.impl;

import com.dictionary.models.Question;
import com.dictionary.models.Quiz;
import com.dictionary.models.dao.QuizDao;
import com.dictionary.service.QuestionService;
import com.dictionary.service.QuizService;
import com.dictionary.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.ZonedDateTime;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final UserService userRepository;

    private final QuestionService questionRepository;

    @Autowired
    QuizDao quizDao;

    @Override
    public Quiz generateQuiz() {
        Quiz quiz = new Quiz();
        quiz.setTakenAt(ZonedDateTime.now());

        Random rand = new Random();
        int ctrl = rand.nextInt(2);

        if (ctrl == 1)
            quiz.setQuestions(questionRepository.createQuestionsForEnglish());
        else
            quiz.setQuestions(questionRepository.createQuestionsForTurkish());

        return quiz;
    }

    @Override
    public Quiz findQuiz(Long quizId) {
        return quizDao.findById(quizId).orElseThrow(() -> new EntityNotFoundException("Quiz not found"));
    }

    @Override
    public Quiz finishQuiz(Long quizId, Quiz quiz) {
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

        return quizDao.saveAndFlush(retrievedQuiz);

        //User currentUser = userRepository.findUserByEmail(/*Principal principal.getName()*/))
        /*if (currentUser.getScore < quiz.getScore())
            user.setBestScore(quiz.getScore());
        userRepository.updateUser(currentUser.getEmail(), user);*/

    }

    @Override
    public void deleteQuiz(Long quizId) {
        quizDao.deleteById(quizId);
    }
}
