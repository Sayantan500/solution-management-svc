package com.solution.solution_management_svc.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.Timestamp;

public class Solution
{
    @JsonProperty("_id")        private String _id;
    @JsonProperty("solution")   private String solution;
    @JsonProperty("issue_id")   private String providedToIssue;
    @JsonProperty("posted_on")  private Timestamp postedOn;

    public Solution(){}

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_id() {
        return _id;
    }

    public void setPostedOn() {
        this.postedOn = Timestamp.now();
    }

    public Timestamp getPostedOn() {
        return postedOn;
    }
}
