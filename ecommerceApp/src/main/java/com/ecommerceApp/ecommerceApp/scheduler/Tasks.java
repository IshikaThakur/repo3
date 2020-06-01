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
       /* CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        product.join(Product_.category, JoinType.INNER);
        TypedQuery<Product> typedQuery = entityManager.createQuery(cq);
        List<Product> resultList = typedQuery.getResultList();
        for (Product e : resultList) {
            System.out.println(e.getName() + " - " + e.getBrand() +" - ");
        }
      //  Join<Product, Seller> seller = product.join(Product_.seller);*/

       productService.getRep();
       System.out.println("hiiiiiii");
    }


}
