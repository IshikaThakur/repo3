package com.nativeandjpqlassign.nativeandjpql.model;

import javax.persistence.*;

@Entity
    @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    public abstract class PaymentPerClass {
        @Id
        int id;
        double amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }


