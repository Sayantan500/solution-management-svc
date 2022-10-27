package com.solution.solution_management_svc.controller;

import com.solution.solution_management_svc.models.Solution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/solutions")
public class Controller
{

    @GetMapping
    public ResponseEntity<Object> getSolutionBasedOnQueryParam(
            @RequestParam(name = "issue-id") String issueID
    )
    {
        //Todo : get all the solutions for the specified issue id and return as list.
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getSolutionBasedOnSolutionId(
            @PathVariable(name = "id") String solutionId
    )
    {
        //Todo : get the solution with the specified id and return the solution object.
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<Solution> createNewSolution(
            @RequestBody Solution newSolution
    )
    {
        //Todo : get the solution object and save in the database.
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSolution(
            @PathVariable(name = "id") String solutionId
    )
    {
        //Todo : delete the solution having id as solutionId and return the appropriate response.
        return null;
    }
}
