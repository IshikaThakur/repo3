package com.nativeandjpqlassign.nativeandjpql.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Salary {
    int basicsalary;
    int bonussalary;
    int taxamount;
    int specialallowancesalary;

    public int getBasicsalary() {
        return basicsalary;
    }

    public void setBasicsalary(int basicsalary) {
        this.basicsalary = basicsalary;
    }

    public int getBonussalary() {
        return bonussalary;
    }

    public void setBonussalary(int bonussalary) {
        this.bonussalary = bonussalary;
    }

    public int getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(int taxamount) {
        this.taxamount = taxamount;
    }

    public int getSpecialallowancesalary() {
        return specialallowancesalary;
    }

    public void setSpecialallowancesalary(int specialallowancesalary) {
        this.specialallowancesalary = specialallowancesalary;
    }
}