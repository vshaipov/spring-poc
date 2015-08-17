package com.poc.db.nosql.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Grade {
    @Id
    private String id;
    private int student_id;
    private String type;
    double score;

    @PersistenceConstructor
    public Grade() {
    }

    @PersistenceConstructor
    public Grade(int student_id, String type, double score) {
	this.student_id = student_id;
	this.type = type;
	this.score = score;
    }

    public int getStudent_id() {
	return student_id;
    }

    public String getType() {
	return type;
    }

    public double getScore() {
	return score;
    }

}
