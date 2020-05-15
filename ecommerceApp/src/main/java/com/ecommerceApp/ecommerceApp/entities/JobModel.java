package com.ecommerceApp.ecommerceApp.entities;

public class JobModel {
    private String name;
    private String cronExpression;


public JobModel()
{

}

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
