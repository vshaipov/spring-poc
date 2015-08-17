package com.poc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.db.nosql.dao.GradeDao;

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private GradeDao gradeDao;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    public ResponseEntity<String> getAllGrades() throws JsonProcessingException {
	return new ResponseEntity<String>(objectMapper
		.writerWithDefaultPrettyPrinter().writeValueAsString(
			gradeDao.getAllGrades()), HttpStatus.OK);
    }

    @RequestMapping(value = "/low-scores", method = RequestMethod.GET)
    public ResponseEntity<String> findAllLowestScoresForEachStudent()
	    throws JsonProcessingException {
	return new ResponseEntity<String>(objectMapper
		.writerWithDefaultPrettyPrinter().writeValueAsString(
			gradeDao.sortByStudentIDAndScore()),
		HttpStatus.OK);
    }

    @RequestMapping(value = "/remove-low-scores", method = RequestMethod.DELETE)
    public ResponseEntity<String> findAllLowestScoresForEachStudentAndRemove()
	    throws JsonProcessingException {
	return new ResponseEntity<String>("removed " + gradeDao.findAllLowestScoresForEachStudentAndRemove(), HttpStatus.OK);
    }
}
