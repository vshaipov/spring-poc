package com.poc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.db.nosql.dao.StudentDao;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    ObjectMapper objectMapper;

    
    @RequestMapping(value = "/remove-low-scores", method = RequestMethod.DELETE)
    public ResponseEntity<String> findAllLowestScoreHomworksForEachStudentAndRemove()
	    throws JsonProcessingException {
	return new ResponseEntity<String>("removed " + studentDao.findAllLowestScoreHomworksForEachStudentAndRemove(), HttpStatus.OK);
    }
    
}
