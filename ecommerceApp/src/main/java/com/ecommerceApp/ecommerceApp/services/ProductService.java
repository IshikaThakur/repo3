package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.criteria.ProductRepositoryCustom;
import com.ecommerceApp.ecommerceApp.dtos.categorydtos.CategoryDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductAdminDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductAdminViewDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductCustomerDto;
import com.ecommerceApp.ecommerceApp.dtos.productdto.ProductSellerDto;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.entities.category.Category;
import com.ecommerceApp.ecommerceApp.exceptions.ProductDoesNotExistsException;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotActiveException;
import com.ecommerceApp.ecommerceApp.exceptions.ProductNotFoundException;
import com.ecommerceApp.ecommerceApp.scheduler.ProductScheduler;
import com.nimbusds.jwt.util.DateUtils;
import org.modelmapper.ModelMapper;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

@Service
public class ProductService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoryService categoryService;
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
    @Autowired
    CustomerService customerService;
    @Autowired
    MessageSource messageSource;
    @Autowired
    PagingService pagingService;
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    ProductRepositoryCustom productRepositoryCustom;


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

    public CategoryDto toCategoryDto(Category category) {
        if (category == null)
            return null;
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        CategoryDto parentDto = toCategoryDto(category.getParentCategory());
        categoryDto.setParent(parentDto);
        return categoryDto;
    }

    //====================API to add a new product=================================
    public ResponseEntity<String> addProduct(String email, ProductSellerDto productSellerDto) {
        ResponseEntity<String> responseEntity;

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
        //return new ResponseEntity<>("Success", HttpStatus.OK);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-product-added", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }

    //==================API to activate a product==============
    public ResponseEntity<String> activateProductById(Long id) {
        ResponseEntity<String> responseEntity;
        Optional<Product> savedproduct = productRepository.findById(id);
        if (!savedproduct.isPresent())
            return new ResponseEntity<>("Product not present", HttpStatus.NOT_FOUND);
        Product product = savedproduct.get();
        if (product.isActive())
            return new ResponseEntity<>("Product already activated", HttpStatus.BAD_REQUEST);
        product.setActive(true);
        String email = product.getSeller().getEmail();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Product is activated");
        mailMessage.setFrom("tanu.thakur0816@gmail.com");
        productRepository.save(product);
        //return new ResponseEntity<>( HttpStatus.OK);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-product-activated", null, LocaleContextHolder.getLocale()));
        return responseEntity;


    }

    //=====================API to deactivate a product====================
    public ResponseEntity<String> deactivateproductById(Long id) {
        ResponseEntity<String> responseEntity;
        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent())
            return new ResponseEntity<>("Invalid operation", HttpStatus.BAD_REQUEST);
        Product product = savedProduct.get();
        if (!product.isActive())
            return new ResponseEntity<>("Already inactive", HttpStatus.BAD_REQUEST);
        product.setActive(false);
        productRepository.save(product);
        String email = product.getSeller().getEmail();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Product is deactivated");
        mailMessage.setFrom("tanu.thakur0816@gmail.com");
        // return new ResponseEntity<>("Success", HttpStatus.OK);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-product-deactivated", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }

    //===============API to deelte a product===========
    public ResponseEntity<String> deleteProductById(Long id, String email) {
        ResponseEntity<String> responseEntity;
        String message;
        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            return new ResponseEntity<>("Product with the given id not found", HttpStatus.NOT_FOUND);
        }
        Product product = savedProduct.get();
        if (!product.getSeller().getEmail().equalsIgnoreCase(email)) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.BAD_REQUEST);
        }
        if (product.isDeleted()) {
            return new ResponseEntity<>("Product does not exist", HttpStatus.NOT_FOUND);
        }
        //productRepository.deleteById(id);
        productRepository.deleteProductById(id);
        //return new ResponseEntity<>("deleted", HttpStatus.OK);
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-product-deleted", null, LocaleContextHolder.getLocale()));
        return responseEntity;

    }

    //===================API to view a product by admin========================
    public ResponseEntity<String> getProductById(Long id) {
        Optional<Product> savedProduct = productRepository.findById(id);
        if (!savedProduct.isPresent()) {
            throw new ProductNotFoundException("Product does not exists");
        }
        Product product = savedProduct.get();
        if (product.isDeleted()) {
            throw new ProductNotFoundException("Product does not exists");
        }
        if (!product.isActive()) {
            throw new ProductNotActiveException("Product is  not active");
        }
        ProductAdminDto productAdminDto = toProductAdminDto(product);
        productAdminDto.setCategoryDto(toCategoryDto(product.getCategory()));
        return new ResponseEntity(productAdminDto, HttpStatus.OK);


    }

    //===========================API to view all products by admin==================
    public ResponseEntity<List> getAllProducts(Long categoryId, String offset, String size, String sortByField, String order, String brand) {
        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);
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
    public ResponseEntity<String> updateProduct(Long productId, ProductSellerDto productSellerDto) {
        ResponseEntity<String> responseEntity;

        Seller seller = sellerService.getLoggedInSeller();
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent())
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
        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage
                ("message-product-updated", null, LocaleContextHolder.getLocale()));
        return responseEntity;


    }

    //============Get all products for seller===========
    public ResponseEntity getAllProductsForSeller(String offset, String size, String sortByField, String order, Long categoryId, String brand) {


        Pageable pageable = pagingService.getPageableObject(offset, size, sortByField, order);

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

        List<ProductSellerDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            ProductSellerDto dto = toProductSellerDto(product);
            dto.setCategoryDto(categoryService.toCategoryDto(product.getCategory()));
            productDtos.add(dto);
        });

        return new ResponseEntity(productDtos, HttpStatus.OK);
    }
    //=============Get list of product for admin

    public ResponseEntity getAllProductsForAdmin(Long categoryId, String offset, String size, String sortByField, String order, String brand) {

        Pageable pageable = pagingService.getPageableObject(offset, size, sortByField, order);

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


        List<ProductAdminViewDto> productDtos = new ArrayList<>();
        products.forEach(product -> {
            ProductAdminViewDto viewDto = new ProductAdminViewDto();
            ProductSellerDto dto = toProductSellerDto(product);
            dto.setCategoryDto(categoryService.toCategoryDto(product.getCategory()));
            viewDto.setProductDto(dto);
            productDtos.add(viewDto);

        });
        return new ResponseEntity(productDtos, HttpStatus.OK);
    }

    public ResponseEntity getAllProductAndSellerInfoByAdmin() throws SchedulerException
    {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        JobDetail job = JobBuilder.newJob(ProductScheduler.class)
                .withIdentity("myJob", "group1")
                .build();
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(1))
                .forJob("job1")
                .build();

        return new ResponseEntity("Your task is on progress, You may want to add some other changes???",HttpStatus.OK);
    }
    public List<Report> getReport(String offset, String size, String field,String order) {
        Integer pageNo = Integer.parseInt(offset);
        Integer pageSize = Integer.parseInt(size);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(field).ascending());
        List<Report> reports = reportRepository.findAll();
        List<Report> reports1 = new ArrayList<>();
        reports.forEach(report -> reports1.add(report));
        return reports1;
    }

}
