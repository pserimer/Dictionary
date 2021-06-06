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
import javax.persistence.ManyToMany;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ForeignKey;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "DICTIONARY", name = "QUIZ")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Quiz extends BaseModel {

    // Attributes of quiz class

    @Column(name = "CORRECT")
    private int correct;

    @Column(name = "INCORRECT")
    private int incorrect;

    @Column(name = "EMPTY")
    private int empty;

    @Column(name = "SCORE")
    private Double score;

    @Column(name = "TAKEN_AT")
    private ZonedDateTime takenAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "DICTIONARY", name = "QUIZ_QUESTIONS",
            joinColumns = {@JoinColumn(name = "QUIZ_ID", foreignKey = @ForeignKey(name = "FK_QUIZ_QUESTIONS_QUIZ_ID"))},
            inverseJoinColumns = {@JoinColumn(name = "QUESTION_ID", foreignKey = @ForeignKey(name = "FK_QUIZ_QUESTIONS_QUESTION_ID"))}
    )
    private List<Question> questions = new ArrayList<>();

    // Constructors for word class

    public Quiz() {
    }

    public Quiz(int correct, int incorrect, int empty, double score) {
        this.correct = correct;
        this.incorrect = incorrect;
        this.empty = empty;
        this.score = score;
        this.takenAt = ZonedDateTime.now();
    }

    public Quiz(Quiz quiz) {
        this.correct = quiz.getCorrect();
        this.incorrect = quiz.getIncorrect();
        this.empty = quiz.getEmpty();
        this.score = quiz.getScore();
        this.takenAt = quiz.getTakenAt();
    }

    // Getters and setters for attributes

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    public int getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(int incorrect) {
        this.incorrect = incorrect;
    }

    public int getEmpty() {
        return empty;
    }

    public void setEmpty(int empty) {
        this.empty = empty;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public ZonedDateTime getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(ZonedDateTime takenAt) {
        this.takenAt = takenAt;
    }
}
