package com.solution.solution_management_svc.persistence;

import com.google.cloud.firestore.*;
import com.solution.solution_management_svc.models.Solution;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

@Repository
class SolutionsDAOImpl implements SolutionsDAO
{
    private final CollectionReference firestoreCollectionReference;

    public SolutionsDAOImpl(
            @Qualifier("firestore_collection_ref")
            CollectionReference firestoreCollectionReference ){
        this.firestoreCollectionReference = firestoreCollectionReference;
    }

    @Override
    public Solution getSolutionByIssueId(String issueID) {
        final Query query = firestoreCollectionReference.whereEqualTo("providedToIssue",issueID);
        try{
            final QuerySnapshot querySnapshot = query.get().get();
            if(querySnapshot.isEmpty())
                return null;
            AtomicReference<Solution> solution = new AtomicReference<>();
            querySnapshot
                    .getDocuments()
                    .forEach(
                            queryDocumentSnapshot ->
                                    solution.set(queryDocumentSnapshot.toObject(Solution.class))
                    );
            return solution.get();
        }catch (Exception e){
            System.out.println(">>> [ SolutionsDAOImpl.getSolutionByIssueId ] " + e.getMessage());
        }
        return  null;
    }

    @Override
    public Solution getSolutionById(String solutionID) {
        try{
            return firestoreCollectionReference
                    .document(solutionID)
                    .get()
                    .get()
                    .toObject(Solution.class);
        } catch (InterruptedException | ExecutionException exception){
            System.out.println(">>> [ SolutionsDAOImpl.getSolutionById ] " + exception.getMessage());
        }
        return null;
    }

    @Override
    public Solution createNewSolution(Solution newSolutionToCreate) {
        final DocumentReference newDocRef = firestoreCollectionReference.document();
        newSolutionToCreate.set_id(newDocRef.getId());
        newSolutionToCreate.setPostedOn();
        try{
            firestoreCollectionReference
                    .document(newSolutionToCreate.get_id())
                    .set(newSolutionToCreate, SetOptions.merge())
                    .get(5000, TimeUnit.MILLISECONDS);
            return newSolutionToCreate;
        }catch (Exception exception){
            System.out.println(">>> [ SolutionsDAOImpl.createNewSolution ] " + exception.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteSolution(String solutionID) {
        try{
            final DocumentReference docRef = firestoreCollectionReference.document(solutionID);
            final DocumentSnapshot documentSnapshot = docRef.get().get();
            if(documentSnapshot.exists())
            {
                docRef.delete();
                System.out.println(">>> [ SolutionsDAOImpl.deleteSolution ] Deleted : " + solutionID);
                return true;
            }
        }catch (Exception exception){
            System.out.println(">>> [ SolutionsDAOImpl.deleteSolution ] " + exception.getMessage());
        }
        return false;
    }
}
