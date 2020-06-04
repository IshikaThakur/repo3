package com.ecommerceApp.ecommerceApp.scheduler;

import com.ecommerceApp.ecommerceApp.Repositories.ReportRepository;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Component
public class Tasks {
    @Autowired
    ProductService productService;
    @Autowired
    ReportRepository reportRepository;
    public void work()
    {

       productService.getRep();

       System.out.println("hiiiiiii");
    }


}
