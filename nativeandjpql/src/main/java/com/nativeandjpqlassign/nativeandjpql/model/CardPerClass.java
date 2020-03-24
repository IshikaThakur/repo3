package com.nativeandjpqlassign.nativeandjpql.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
    @Table(name = "cardpay")
    public class CardPerClass extends PaymentPerClass
    {
        private String cardnumber;

        public String getCardnumber() {
            return cardnumber;
        }

        public void setCardnumber(String cardnumber) {
            this.cardnumber = cardnumber;
        }
    }


