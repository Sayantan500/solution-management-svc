package com.solution.solution_management_svc.persistence;

import com.solution.solution_management_svc.models.Solution;
import org.springframework.stereotype.Repository;

@Repository
public interface SolutionsDAO
{
    Solution getSolutionByIssueId(String issueID);
    Solution getSolutionById(String solutionID);
    Solution createNewSolution(Solution newSolutionToCreate);
    boolean deleteSolution(String issueID);
}
