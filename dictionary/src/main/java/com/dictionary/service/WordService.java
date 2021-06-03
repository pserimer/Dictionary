package com.dictionary.service;


import com.dictionary.models.Word;

import java.io.IOException;
import java.util.List;

public interface WordService {

    List<Word> listAllWords();

    Word translateToTurkish(Word word) throws IOException;

    Word translateToEnglish(Word word) throws IOException;

    void deleteWord(String wordId);
}
