package com.dictionary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    private String quizId;

    private ArrayList<Question> questions;

    private int correct = 0; //number of correct answers

    private int incorrect = 0; //number of incorrect answers

    private int empty = 0; //number of empty answers

    private Double score = 0.0;

    private ZonedDateTime takenAt;

}
