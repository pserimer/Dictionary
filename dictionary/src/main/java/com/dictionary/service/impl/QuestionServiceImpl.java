// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service.impl;

import com.dictionary.models.Question;
import com.dictionary.models.Word;
import com.dictionary.models.dao.QuestionDao;
import com.dictionary.service.QuestionService;
import com.dictionary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final WordService wordService;

    @Autowired
    QuestionDao questionDao;

    @Override
    public Question createQuestionForEnglish() {
        // question is in turkish, answer is in english
        List<Word> allWords = wordService.listAllWords();

        Question question = new Question();
        Random rand = new Random();

        List<Word> words = new ArrayList<>();
        // randomly select 3 words
        for (int i = 0; i < 3; i++) {
            int randomIndex = rand.nextInt(allWords.size());
            words.add(allWords.get(randomIndex));
        }

        question.setQuestion(words.get(0).getTurkish());
        question.setAnswer(words.get(0).getEnglish());

        // randomly assigning choices
        int ctrl = rand.nextInt(3);
        if (ctrl == 0) {
            question.setPosAnsA(words.get(0).getEnglish());
            question.setPosAnsB(words.get(1).getEnglish());
            question.setPosAnsC(words.get(2).getEnglish());
        }

        else if (ctrl == 1) {
            question.setPosAnsA(words.get(1).getEnglish());
            question.setPosAnsB(words.get(0).getEnglish());
            question.setPosAnsC(words.get(2).getEnglish());
        }

        else {
            question.setPosAnsA(words.get(1).getEnglish());
            question.setPosAnsB(words.get(2).getEnglish());
            question.setPosAnsC(words.get(0).getEnglish());
        }

        return question;
    }

    @Override
    public Question createQuestionForTurkish() {
        // question is in english, answer is in turkish
        List<Word> allWords = wordService.listAllWords();

        Question question = new Question();
        Random rand = new Random();

        List<Word> words = new ArrayList<>();
        // randomly select 3 words
        for (int i = 0; i < 3; i++) {
            int randomIndex = rand.nextInt(allWords.size());
            words.add(allWords.get(randomIndex));
        }
        question.setQuestion(words.get(0).getEnglish());
        question.setAnswer(words.get(0).getTurkish());

        // randomly assigning choices
        int ctrl = rand.nextInt(3);
        if (ctrl == 0) {
            question.setPosAnsA(words.get(0).getTurkish());
            question.setPosAnsB(words.get(1).getTurkish());
            question.setPosAnsC(words.get(2).getTurkish());
        }

        else if (ctrl == 1) {
            question.setPosAnsA(words.get(1).getTurkish());
            question.setPosAnsB(words.get(0).getTurkish());
            question.setPosAnsC(words.get(2).getTurkish());
        }

        else {
            question.setPosAnsA(words.get(1).getTurkish());
            question.setPosAnsB(words.get(2).getTurkish());
            question.setPosAnsC(words.get(0).getTurkish());
        }

        return question;
    }
}
