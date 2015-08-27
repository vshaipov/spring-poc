package com.poc.db.nosql.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

public class Student {
    @Id
    private String id;
    private String student_id;
    private String name;
    private List<Score> scores;

    @PersistenceConstructor
    public Student() {
    }

    @PersistenceConstructor
    public Student(String student_id, String name, List<Score> scores) {
	this.student_id = student_id;
	this.name = name;
	this.scores = scores;
    }

    public String getStudent_id() {
	return student_id;
    }

    public void setStudent_id(String student_id) {
	this.student_id = student_id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Score> getScores() {
	return scores;
    }

    public void setScores(List<Score> scores) {
	this.scores = scores;
    }

}
