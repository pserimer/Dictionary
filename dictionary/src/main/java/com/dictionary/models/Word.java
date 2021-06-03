package com.dictionary.models;

import com.dictionary.base.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(schema = "DICTIONARY", name = "WORD")
@Data
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class Word extends BaseModel {

    // Attributes of word class

    @Column(name = "TURKISH")
    private String turkish;

    @Column(name = "ENGLISH")
    private String english;

    // Constructors for word class

    public Word() {
    }

    public Word(String tr, String en) {
        this.setUuid(UUID.randomUUID().toString());
        this.turkish = tr;
        this.english = en;
    }

    public Word(Word word) {
        this.setUuid(UUID.randomUUID().toString());
        this.turkish = word.getTurkish();
        this.english = word.getEnglish();
    }

    // Getters and setters for attributes

    public String getTurkish() {
        return turkish;
    }

    public void setTurkish(String turkish) {
        this.turkish = turkish;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

}

