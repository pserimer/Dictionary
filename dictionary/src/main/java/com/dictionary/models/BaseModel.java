package com.dictionary.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@MappedSuperclass
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(callSuper = true, of = {"id"})
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "UUID")
    private String uuid;

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

}
