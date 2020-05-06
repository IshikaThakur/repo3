package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.CategoryRepository;
import com.ecommerceApp.ecommerceApp.Repositories.CustomerRepository;
import com.ecommerceApp.ecommerceApp.Repositories.ProductRepository;
import com.ecommerceApp.ecommerceApp.Repositories.SellerRepository;
import com.ecommerceApp.ecommerceApp.dtos.categorydtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductAdminDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductCustomerDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;
import com.ecommerceApp.ecommerceApp.entities.Product;
import com.ecommerceApp.ecommerceApp.entities.Seller;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import com.ecommerceApp.ecommerceApp.exceptions.ProductDoesNotExistsException;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotActiveException;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EmailSenderService emailSenderService;
    @Autowired
    SellerService sellerService;

    public Product toProduct(ProductSellerDto productSellerDto) {
        if (productSellerDto == null)
            return null;
        return modelMapper.map(productSellerDto, Product.class);
    }

    public ProductSellerDto toProductSellerDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductSellerDto.class);
    }

    public Product toProduct(ProductCustomerDto productCustomerDto) {
        if (productCustomerDto == null)
            return null;
        return modelMapper.map(productCustomerDto, Product.class);
    }

    public ProductCustomerDto toproductCustomerDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductCustomerDto.class);
    }

    public Product toProduct(ProductAdminDto productAdminDto) {
        if (productAdminDto == null)
            return null;
        return modelMapper.map(productAdminDto, Product.class);
    }

    public ProductAdminDto toProductAdminDto(Product product) {
        if (product == null)
            return null;
        return modelMapper.map(product, ProductAdminDto.class);
    }
    public CategoryDto toCategoryDto(Category category){
        if(category == null)
            return null;
        CategoryDto categoryDto =modelMapper.map(category,CategoryDto.class);
        CategoryDto parentDto = toCategoryDto(category.getParentCategory());
        categoryDto.setParent(parentDto);
        return categoryDto;
    }
  //====================API to add a new product=================================
    public ResponseEntity<String> addProduct(String email, ProductSellerDto productSellerDto) {

        Category category = categoryRepository.findById(productSellerDto.getCategoryId()).get();
        Seller seller = sellerRepository.findByEmail(email);
        Product product = toProduct(productSellerDto);
        product.setCategory(category);
        product.setSeller(seller);
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(seller.getEmail());
        mailMessage.setSubject("Information about the new product created");
        mailMessage.setFrom("tanu.thakur0816@gmail.com");
        mailMessage.setText("A product with following details has been created - \n" +
                "name - " + product.getName() + "\n" +
                "category - " + product.getCategory().getName() + "\n" +
                "brand - " + product.getBrand() + "\n" +
                "description - " + product.getDescription());

        emailSenderService.sendEmail(mailMessage);
        productRepository.save(product);
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }
//==================API to activate a product==============
public ResponseEntity<String> activateProductById(Long id) {
    Optional<Product> savedproduct = productRepository.findById(id);
    if (!savedproduct.isPresent())
        return new ResponseEntity<>("Product not present", HttpStatus.NOT_FOUND);
    Product product = savedproduct.get();
    if (product.isActive())
        return new ResponseEntity<>("Product already activated", HttpStatus.BAD_REQUEST);
    product.setActive(true);
    String email = product.getSeller().getEmail();
    SimpleMailMessage mailMessage=new SimpleMailMessage();
    mailMessage.setTo(email);
    mailMessage.setSubject("Product is activated");
    mailMessage.setFrom("tanu.thakur0816@gmail.com");
    productRepository.save(product);
    return new ResponseEntity<>("Success", HttpStatus.OK);

}
//=====================API to deactivate a product====================
public ResponseEntity<String> deactivateproductById(Long id) {
    Optional<Product> savedProduct = productRepository.findById(id);
    if (!savedProduct.isPresent())
        return new ResponseEntity<>("Invalid operation", HttpStatus.BAD_REQUEST);
    Product product = savedProduct.get();
    if (!product.isActive())
        return new ResponseEntity<>("Already inactive", HttpStatus.BAD_REQUEST);
    product.setActive(false);
    productRepository.save(product);
    String email = product.getSeller().getEmail();
    SimpleMailMessage mailMessage=new SimpleMailMessage();
    mailMessage.setTo(email);
    mailMessage.setSubject("Product is deactivated");
    mailMessage.setFrom("tanu.thakur0816@gmail.com");
    return new ResponseEntity<>("Success", HttpStatus.OK);

}

//===============API to deelte a product===========
public ResponseEntity<String> deleteProductById(Long id, String email) {
    String message;
    Optional<Product> savedProduct = productRepository.findById(id);
    if(!savedProduct.isPresent()){
        return new ResponseEntity<>("Product with the given id not found", HttpStatus.NOT_FOUND);
    }
    Product product = savedProduct.get();
    if(!product.getSeller().getEmail().equalsIgnoreCase(email)){
        return new ResponseEntity<>("Unauthorized", HttpStatus.BAD_REQUEST);
    }
    if(product.isDeleted()){
        return new ResponseEntity<>("Product does not exist", HttpStatus.NOT_FOUND);
    }
    productRepository.deleteById(id);
    return new ResponseEntity<>("deleted", HttpStatus.OK);
}
//===================API to view a product by admin========================
public ResponseEntity<String>getProductById(Long id)
{

    Optional<Product>savedProduct=productRepository.findById(id);
    if(!savedProduct.isPresent())
    {
        throw new ProductNotFoundException("Product does not exists");
    }
    Product product=savedProduct.get();
    if (product.isDeleted())
    {
        throw new ProductNotFoundException("Product does not exists");
    }
    if (!product.isActive())
    {
        throw new ProductNotActiveException("Product is  not active");
    }
    ProductAdminDto productAdminDto=toProductAdminDto(product);
    productAdminDto.setCategoryDto(toCategoryDto(product.getCategory()));
    return new ResponseEntity(productAdminDto,HttpStatus.OK);

}
//===========================API to view all products by admin==================
    public ResponseEntity<List> getAllProducts(Long categoryId, String offset, String size, String sortByField, String order, String brand)
    {
        Integer pageNo=Integer.parseInt(offset);
        Integer pageSize=Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(String.valueOf(sortByField)).ascending());
        List<Product> products;
        if (categoryId != null && brand != null) {
            products = productRepository.findByBrandAndCategoryId(brand, categoryId, pageable);
        } else if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId, pageable);
        } else if (brand != null) {
            products = productRepository.findByBrand(brand, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }
        List<ProductAdminDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            ProductAdminDto productAdminDto = toProductAdminDto(product);
           productAdminDto.setCategoryDto(toCategoryDto(product.getCategory()));
            productDtos.add(productAdminDto);
        });
        return new ResponseEntity(productDtos, HttpStatus.OK);

    }
    //===============View Product for seller==========
    public ProductSellerDto getProductByIdForSeller(Long id, String email) {
        String message;

        Optional<Product> savedProduct = productRepository.findById(id);
        Product product = savedProduct.get();
        if (!product.getSeller().getEmail().equalsIgnoreCase(email)) {
            throw new ProductDoesNotExistsException("Product does not exist");
        }
        if (product.isDeleted()) {
            throw new ProductDoesNotExistsException("product has already been deleted");
        }

        ProductSellerDto productSellerDto = toProductSellerDto(product);
        productSellerDto.setCategoryDto(toCategoryDto(product.getCategory()));
        return productSellerDto;
    }
//===================API to update a product=====================
    public void updateProduct(Long productId, ProductSellerDto productSellerDto) {
        Seller seller = sellerService.getLoggedInSeller();
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent())
            throw new ProductNotFoundException("Product does not exist");

        if (productSellerDto.getBrand() != null)
            product.get().setBrand(productSellerDto.getBrand());
        if (productSellerDto.getDescription() != null)
            product.get().setDescription(productSellerDto.getDescription());
        if (productSellerDto.getName() != null)
            product.get().setName(productSellerDto.getName());
        Product product1 = product.get();
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(seller.getEmail());
        mailMessage.setSubject("Product Updated");
        mailMessage.setFrom("tanu.thakur0816@gmail.com");
        emailSenderService.sendEmail(mailMessage);
        productRepository.save(product1);


    }
}
