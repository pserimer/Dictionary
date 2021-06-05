// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.models;

import com.dictionary.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(schema = "DICTIONARY", name = "QUESTION")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Question extends BaseModel {

    // Attributes of question class

    @Column(name = "QUESTION")
    private String question;

    @Column(name = "posAnsA")
    private String posAnsA;

    @Column(name = "posAnsB")
    private String posAnsB;

    @Column(name = "posAnsC")
    private String posAnsC;

    @Column(name = "SELECTED")
    private String selected;

    @Column(name = "ANSWER")
    private String answer;

    // Constructors for question class

    public Question() {
    }

    public Question(Question question) {
        this.question = question.getQuestion();
        this.posAnsA = question.getPosAnsA();
        this.posAnsB = question.getPosAnsB();
        this.posAnsC = question.getPosAnsC();
        this.selected = question.getSelected();
        this.answer = question.getAnswer();
    }

    // Getters and setters for attributes

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPosAnsA() {
        return posAnsA;
    }

    public void setPosAnsA(String posAnsA) {
        this.posAnsA = posAnsA;
    }

    public String getPosAnsB() {
        return posAnsB;
    }

    public void setPosAnsB(String posAnsB) {
        this.posAnsB = posAnsB;
    }

    public String getPosAnsC() {
        return posAnsC;
    }

    public void setPosAnsC(String posAnsC) {
        this.posAnsC = posAnsC;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
