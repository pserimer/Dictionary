// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service;

import com.dictionary.models.Word;

import java.io.IOException;
import java.util.List;

public interface WordService {

    List<Word> listAllWords();

    Word translateToTurkish(Word word) throws IOException;

    Word translateToEnglish(Word word) throws IOException;

    void deleteWord(Long wordId);
}
