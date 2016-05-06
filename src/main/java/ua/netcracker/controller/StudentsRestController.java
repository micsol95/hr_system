package ua.netcracker.controller;

/**
 * Created by Серый on 02.05.2016.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.netcracker.model.entity.Candidate;
import ua.netcracker.model.service.CandidateService;

import java.util.List;


@RestController
public class StudentsRestController {

    @Autowired
    private CandidateService candidate;


    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    public ResponseEntity<List<Candidate>> listAllStudents() {

        List<Candidate> students = candidate.getAllProfiles();

        if(students.isEmpty()){
            return new ResponseEntity<List<Candidate>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Candidate>>(students, HttpStatus.OK);
    }

}