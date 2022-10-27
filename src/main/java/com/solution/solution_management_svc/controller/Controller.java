package com.solution.solution_management_svc.controller;

import com.solution.solution_management_svc.models.Solution;
import com.solution.solution_management_svc.persistence.SolutionsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/solutions")
public class Controller
{
    @Autowired
    private SolutionsDAO solutionsDAO;

    @GetMapping
    public ResponseEntity<Solution> getSolutionBasedOnQueryParam(
            @RequestParam(name = "issue-id") String issueID
    )
    {
        Solution solution = solutionsDAO.getSolutionByIssueId(issueID);
        HttpStatus httpStatus = solution!=null? HttpStatus.OK:HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(solution,httpStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Solution> getSolutionBasedOnSolutionId(
            @PathVariable(name = "id") String solutionId
    )
    {
        Solution solution = solutionsDAO.getSolutionById(solutionId);
        HttpStatus httpStatus = solution == null ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(solution,httpStatus);
    }

    @PostMapping("/new")
    public ResponseEntity<Solution> createNewSolution(
            @RequestBody Solution newSolution
    )
    {
        newSolution = solutionsDAO.createNewSolution(newSolution);
        HttpStatus httpStatus = newSolution != null ? HttpStatus.CREATED : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(newSolution, httpStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSolution(
            @PathVariable(name = "id") String solutionId
    )
    {
        HttpStatus httpStatus =
                solutionsDAO.deleteSolution(solutionId) ?
                        HttpStatus.NO_CONTENT :
                        HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(null,httpStatus);
    }
}
