package net.sierrainfosys.pmo.dto;

public class PmoProjectDTO {
    private String id;
    private String name;
    private String overallProjectHealth;
    private String scope;
    private String schedule;
    private String budget;
    private String riskAndIssues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverallProjectHealth() {
        return overallProjectHealth;
    }

    public void setOverallProjectHealth(String overallProjectHealth) {
        this.overallProjectHealth = overallProjectHealth;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getRiskAndIssues() {
        return riskAndIssues;
    }

    public void setRiskAndIssues(String riskAndIssues) {
        this.riskAndIssues = riskAndIssues;
    }
}
