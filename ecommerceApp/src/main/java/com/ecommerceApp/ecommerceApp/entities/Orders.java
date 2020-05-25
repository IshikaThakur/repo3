package com.ecommerceApp.ecommerceApp.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Orders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ids;
    private Long amountPaid;
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    private String paymentMethod;

    @Embedded
    OrderAddress orderAddress;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
     Customer customer;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private Set<OrderProduct> order_product;

    public OrderAddress getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(OrderAddress orderAddress) {
        this.orderAddress = orderAddress;
    }

    public Long getId() {
        return ids;
    }

    public void setId(Long id) {
        this.ids = id;
    }

    public Long getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Long amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Set<OrderProduct> getOrder_product() {
        return order_product;
    }

    public void setOrder_product(Set<OrderProduct> order_product) {
        this.order_product = order_product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

