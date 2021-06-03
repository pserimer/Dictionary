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

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/word")
public class WordController {

    private final WordService wordService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Word> saveWord(@RequestBody Word word) {
        return new ResponseEntity<>(
                wordService.saveWord(word),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/translate-turkish", method = RequestMethod.POST)
    public ResponseEntity<Word> translateToTurkish(@RequestBody Word word) throws IOException {
        return new ResponseEntity<>(
                wordService.translateToTurkish(word),
                HttpStatus.OK
        );
    }

    @RequestMapping(value = "/translate-english", method = RequestMethod.POST)
    public ResponseEntity<Word> translateToEnglish(@RequestBody Word word) throws IOException {
        return new ResponseEntity<>(
                wordService.translateToEnglish(word),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/{wordId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteWord(@PathVariable String wordId) {
        wordService.deleteWord(wordId);
        return new ResponseEntity<>(
                HttpStatus.NO_CONTENT
        );
    }
}
