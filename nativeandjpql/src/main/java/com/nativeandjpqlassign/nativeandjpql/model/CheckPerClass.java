package com.nativeandjpqlassign.nativeandjpql.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
    @Table(name = "bankcheck")
    public class CheckPerClass extends PaymentPerClass
{

        private String checknumber;

        public String getChecknumber() {
            return checknumber;
        }

        public void setChecknumber(String checknumber) {
            this.checknumber = checknumber;
        }
    }


