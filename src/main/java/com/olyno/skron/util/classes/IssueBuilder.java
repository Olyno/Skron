package com.olyno.skron.util.classes;

import org.kohsuke.github.GHMilestone;
import org.kohsuke.github.GHUser;

public class IssueBuilder {

    private String name;
    private String content;
    private GHUser assignee;
    private GHMilestone milestone;

    public IssueBuilder(String name) {
        this.name = name;
        this.content = null;
        this.assignee = null;
        this.milestone = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public GHUser getAssignee() {
        return assignee;
    }

    public void setAssignee(GHUser assignee) {
        this.assignee = assignee;
    }

    public GHMilestone getMilestone() {
        return milestone;
    }

    public void setMilestone(GHMilestone milestone) {
        this.milestone = milestone;
    }

}
