package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.*;



@Entity
public class Status {
    @Id
    @GeneratedValue
    private Long report_id;
    private int statusRepo;



    public Long getReport_id() {
        return report_id;
    }

    public void setReport_id(Long report_id) {
        this.report_id = report_id;
    }




    public int getStatusRepo() {
        return statusRepo;
    }

    public void setStatusRepo(int statusRepo) {
        this.statusRepo = statusRepo;
    }


}
