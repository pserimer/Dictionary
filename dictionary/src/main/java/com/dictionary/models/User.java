package com.dictionary.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(schema = "DICTIONARY", name = "users")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class User extends BaseModel {

    // Attributes of user class

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "BEST_SCORE")
    private Double bestScore;

    @Column(name = "NUM_OF_SEARCHED_WORDS")
    private Integer noOfWordsSearched;

    //TODO: new table for relation
    private ArrayList<Word> words = new ArrayList<>();

    //TODO: new table for relation
    private ArrayList<Quiz> quizzes = new ArrayList<>();

    // Constructors for user class

    public User() {
    }

    public User(String username, String password, String email) {
        this.setUuid(UUID.randomUUID().toString());
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(User user) {
        this.setUuid(UUID.randomUUID().toString());
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.bestScore = user.getBestScore();
        this.noOfWordsSearched = user.getNoOfWordsSearched();
    }

    // Getters and setters for attributes

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBestScore() {
        return bestScore;
    }

    public void setBestScore(Double bestScore) {
        this.bestScore = bestScore;
    }

    public Integer getNoOfWordsSearched() {
        return noOfWordsSearched;
    }

    public void setNoOfWordsSearched(Integer noOfWordsSearched) {
        this.noOfWordsSearched = noOfWordsSearched;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

}
