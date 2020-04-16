package com.registration.registeruser.entity;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table
public class Order_Status {
    @Id
    @GeneratedValue
    private Long orderProductId;
    private String fromStatus;
    private String toStatus;
    private String transitionNotesComments;


    @OneToMany(mappedBy = "order_status", cascade = CascadeType.ALL)
    private Set<Order_Product> order_product;

    public Set<Order_Product> getOrder_product() {
        return order_product;
    }

    public void setOrder_product(Set<Order_Product> order_product) {
        this.order_product = order_product;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public void setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
    }

    public String getToStatus() {
        return toStatus;
    }

    public void setToStatus(String toStatus) {
        this.toStatus = toStatus;
    }

    public String getTransitionNotesComments() {
        return transitionNotesComments;
    }

    public void setTransitionNotesComments(String transitionNotesComments) {
        this.transitionNotesComments = transitionNotesComments;
    }
}