package com.ecommerceApp.ecommerceApp.criteria;

import com.ecommerceApp.ecommerceApp.entities.Address;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Report;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProductRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public ProductRepositoryCustom(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void deleteAddressById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Address> criteriaQuery = criteriaBuilder.createCriteriaDelete(Address.class);
        Root root = criteriaQuery.from(Address.class);
        Predicate predicateId = criteriaBuilder.equal(root.get("id"), id);
        entityManager.createQuery(criteriaQuery.where(predicateId)).executeUpdate();
    }
    public List<Report> createReport()
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Report> query = builder.createQuery(Report.class);
        Root<Report> root = query.from(Report.class);
        query.select(root);
        Query<Report> q= (Query<Report>) entityManager.createQuery(query);
        List<Report> reports=q.getResultList();
        for (Report report : reports) {
            System.out.println(report.getBrand()+" "+report.getProductname()+" "+report.getSellername());
        }
        return reports;
    }

}