// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.web;

import com.dictionary.models.Quiz;
import com.dictionary.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/quiz")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class QuizController {

    private final QuizService quizService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Quiz> generateQuiz() {
        return new ResponseEntity<>(
                quizService.generateQuiz(),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/{quizId}", method = RequestMethod.PUT)
    public ResponseEntity<Quiz> finishQuiz(@PathVariable Long quizId, @RequestBody Quiz quiz) {
        return new ResponseEntity<>(
                quizService.finishQuiz(quizId, quiz),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/{quizId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long quizId) {
        quizService.deleteQuiz(quizId);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }

}
