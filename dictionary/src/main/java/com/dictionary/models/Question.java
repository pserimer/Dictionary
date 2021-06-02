package com.dictionary.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private String question;

    private String posAnsA;

    private String posAnsB;

    private String posAnsC;

    private String selected; //a, b, or c

    private String answer;

}
