package com.ecommerceApp.ecommerceApp.entities;

import com.ecommerceApp.ecommerceApp.services.HashMapConverter;
import com.ecommerceApp.ecommerceApp.services.HashMapConverter2;

import javax.persistence.*;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer status;

    @Convert(converter = HashMapConverter2.class)
    private Map<String, String> attributes2;
    public Reports()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, String> getAttributes2() {
        return attributes2;
    }

    public void setAttributes2(Map<String, String> attributes2) {
        this.attributes2 = attributes2;
    }
}
