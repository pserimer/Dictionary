// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.web;

import com.dictionary.service.WordService;
import com.dictionary.models.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/word")
public class WordController {

    private final WordService wordService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Word>> generateQuiz() {
        return new ResponseEntity<>(
                wordService.listAllWords(),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/translate-turkish/{email}", method = RequestMethod.POST)
    public ResponseEntity<Word> translateToTurkish(@PathVariable String email,
                                                   @RequestBody Word word) throws IOException {
        return new ResponseEntity<>(
                wordService.translateToTurkish(word, email),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/translate-english/{email}", method = RequestMethod.POST)
    public ResponseEntity<Word> translateToEnglish(@PathVariable String email,
                                                   @RequestBody Word word) throws IOException {
        return new ResponseEntity<>(
                wordService.translateToEnglish(word, email),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/{wordId}/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteWord(@PathVariable Long wordId, @PathVariable String email) {
        wordService.deleteWord(wordId, email);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }
}
