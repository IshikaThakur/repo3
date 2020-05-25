package com.ecommerceApp.ecommerceApp.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductReviewDto {
    @NotNull
    @NotEmpty
    private String review;
    @NotNull
    @NotEmpty
    private Double rating;
    @NotNull
    @NotEmpty
    private Long productId;

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}