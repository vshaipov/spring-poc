package com.poc.db.nosql.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.poc.db.nosql.documents.Grade;

@Service
public class GradeDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Grade> getAllGrades() {
	return mongoTemplate.findAll(Grade.class, "grades");
    }

    public int findAllLowestScoresForEachStudentAndRemove() {
	int counter = 0;
	
	List<Grade> sortedGrades = sortByStudentIDAndScore();
	if (sortedGrades.size() > 0) {
	    int prevID = Integer.MIN_VALUE;
	    for (Grade grade : sortedGrades) {
		if (grade.getStudent_id() != prevID){
		    prevID = grade.getStudent_id();
		    mongoTemplate.remove(grade, "grades");
		    counter ++;
		}
	    }
	}
	return counter;
    }

    public List<Grade> sortByStudentIDAndScore() {
	Query query = new Query();
	query.addCriteria(where("type").is("homework"));
	query.with(new Sort(new Order(ASC, "student_id")));
	query.with(new Sort(new Order(ASC, "score")));

	return mongoTemplate.find(query, Grade.class, "grades");

    }
}
