package com.poc.db.nosql.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.poc.db.nosql.documents.Score;
import com.poc.db.nosql.documents.Student;

@Service
public class StudentDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public int findAllLowestScoreHomworksForEachStudentAndRemove() {

	List<Student> students = mongoTemplate.findAll(Student.class,
		"students");
	int counter = 0;
	for (Student student : students) {
	    List<Score> homeWorkScores = new ArrayList<>();
	    for (Score score : student.getScores()) {
		if (score.getType().equals("homework")) {
		    homeWorkScores.add(score);
		}
	    }
	    if (homeWorkScores.size() > 0) {
		Score min = Collections.min(homeWorkScores);
		student.getScores().remove(min);
	
		mongoTemplate.save(student, "students");
		counter++;
	    }
	}
	return students.size();
    }
}
