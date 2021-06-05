// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.service.impl;

import com.dictionary.models.Word;
import com.dictionary.models.dao.WordDao;
import com.dictionary.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    @Autowired
    WordDao wordDao;

    @Override
    public List<Word> listAllWords() {
        return wordDao.findAll();
    }

    @Override
    public Word translateToTurkish(Word word) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbxgCdZdIad69eqprFuLdgLFtDj9SbsrRvjn1QGIbHnW6heuZxuaVREAVmzibmRdTMiR/exec" +
                "?q=" + URLEncoder.encode(word.getEnglish(), "UTF-8") +
                "&target=" + "tr" +
                "&source=" + "en";

        URL url = new URL(urlStr);
        System.out.println(url);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        word.setTurkish(response.toString());
        return wordDao.saveAndFlush(word);
    }

    @Override
    public Word translateToEnglish(Word word) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbxgCdZdIad69eqprFuLdgLFtDj9SbsrRvjn1QGIbHnW6heuZxuaVREAVmzibmRdTMiR/exec" +
                "?q=" + URLEncoder.encode(word.getTurkish(), "UTF-8") +
                "&target=" + "en" +
                "&source=" + "tr";

        URL url = new URL(urlStr);
        System.out.println(url);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        word.setEnglish(response.toString());
        return wordDao.saveAndFlush(word);
    }

    @Override
    public void deleteWord(Long wordId) {
        wordDao.deleteById(wordId);
    }
}
