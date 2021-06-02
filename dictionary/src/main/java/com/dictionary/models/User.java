package com.dictionary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String username;

    private String password;

    private Double bestScore;

    private String noOfWordsSearched;

    private ArrayList<Word> words = new ArrayList<>();

}
