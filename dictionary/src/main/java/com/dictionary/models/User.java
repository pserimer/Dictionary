// Group Members:
// 201611007 - Batuhan Bayraktar
// 201711058 - Pelinsu Serimer
// 201711049 - Zeynep Özdoğan

package com.dictionary.models;

import com.dictionary.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(schema = "DICTIONARY", name = "USERS")
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "DICTIONARY", name = "USER_WORDS",
            joinColumns = {@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_WORDS_USER_ID"))},
            inverseJoinColumns = {@JoinColumn(name = "WORD_ID", foreignKey = @ForeignKey(name = "FK_USER_WORDS_WORD_ID"))}
    )
    private Set<Word> words = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "DICTIONARY", name = "USER_QUIZZES",
            joinColumns = {@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_QUIZZES_USER_ID"))},
            inverseJoinColumns = {@JoinColumn(name = "QUIZ_ID", foreignKey = @ForeignKey(name = "FK_USER_QUIZZES_QUIZ_ID"))}
    )
    private Set<Quiz> quizzes = new HashSet<>();

    // Constructors for user class

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public Set<Word> getWords() {
        return words;
    }

    public void setWords(Set<Word> words) {
        this.words = words;
    }

    public Set<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(Set<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

}
