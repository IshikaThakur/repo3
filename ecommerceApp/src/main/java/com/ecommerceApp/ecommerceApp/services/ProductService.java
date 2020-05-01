package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.dtos.ProductDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.exceptions.InvalidDetailException;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotFoundException;
import com.ecommerceApp.ecommerceApp.exceptions.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    SellerService sellerService;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    ProductRepository productRepository;
    public String addProduct(Product product) {
        Seller seller = sellerService.getLoggedInSeller();
        product.setSeller(seller);
        if(product.getBrand()!=null && product.getName()!=null && product.getCategory()!=null){
            try{
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setSubject("");
                simpleMailMessage.setText("Hii Admin, \\n There is a pending task for you. Seller \" "+ seller.getFirstName() + " added a product '\" " + product.getName());
                simpleMailMessage.setTo(" please verify" + seller.getEmail());
                emailSenderService.sendEmail(simpleMailMessage);
                productRepository.save(product);
            }catch (Exception ex){
                throw  new ValidationException("mail sending failed");
            }
            return "product added successfully";
        }
else
    throw new InvalidDetailException("fields should not be null");
    }
    public Optional<Product> viewProduct(Long productId) {
        Seller seller = sellerService.getLoggedInSeller();
        Optional<Product> product = productRepository.findByIdAndSellerId(productId, seller.getId());
        if (product.get().getId() != null)
            if(!product.get().isDeleted())
                return product;
            else
                throw new ProductNotFoundException("Product has been deleted from the database");
        else
            throw new ProductNotFoundException("Product does not exist");
    }

    public List<Product> viewAllProductAsSeller() {
        Seller seller = sellerService.getLoggedInSeller();
        return productRepository.findAllBySeller(seller.getId(),
                PageRequest.of(0, 10, Sort.Direction.ASC, "product_id"));

    }
    @Transactional
    public String deleteProduct(Long productId) {
        Seller seller = sellerService.getLoggedInSeller();
        try {
            Optional<Product> product = productRepository.findByIdAndSellerId(productId, seller.getId());
            product.get().setDeleted(true);
            return "Product deleted successfully";
        } catch (Exception ex) {
            throw new ProductNotFoundException("Product does not exist");
        }
    }

    @Transactional
    public void updateProduct(Long productId, ProductDto productDto) {
        Seller seller = sellerService.getLoggedInSeller();
        Optional<Product> product = productRepository.findByIdAndSellerId(productId, seller.getId());
        if(!product.isPresent())
            throw new ProductNotFoundException("Product does not exist");

        if (productDto.getBrand() != null)
            product.get().setBrand(productDto.getBrand());
        if (productDto.getDescription() != null)
            product.get().setDescription(productDto.getDescription());
        if (productDto.getName() != null)
            product.get().setName(productDto.getName());
        if (productDto.isCancelleable())
            product.get().setCancelleable(productDto.isCancelleable());
        if (productDto.isReturnable())
            product.get().setReturnable(productDto.isReturnable());
        Product product1 = product.get();
        productRepository.save(product1);
    }
}