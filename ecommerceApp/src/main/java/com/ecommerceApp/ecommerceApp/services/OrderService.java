package com.ecommerceApp.ecommerceApp.services;

import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.exceptions.AddressNotFoundException;
import com.ecommerceApp.ecommerceApp.exceptions.OutOfStockException;
import com.ecommerceApp.ecommerceApp.rabbitMQ.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class OrderService {
    @Autowired
    ProductVariationRepository productVariationRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderProductRepository orderProductRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    OrderStatusRepository orderStatusRepository;
    @Autowired
    RabbitMQListener rabbitMQListener;
    @Autowired
    RabbitMQProperties rabbitMQProperties;
    @Autowired
    AMQPConfig amqpConfig;
    @Autowired
    AMQPProducer amqpProducer;

    @Transactional
    public ResponseEntity placeOrder(Long productVariationId, int quantity, String paymentMethod, Long addressId, String email) {
        ResponseEntity responseEntity;

        Customer customer = customerRepository.findByEmail(email);
        Optional<ProductVariation> productVariationOptional = productVariationRepository.findById(productVariationId);
        ProductVariation productVariation = productVariationOptional.get();

        Optional<Address> addressOptional = addressRepository.findById(addressId);
        Address address = null;
        if (addressOptional.isPresent()) {
            address = addressOptional.get();
        } else {
       throw new AddressNotFoundException("Address with this id doesn't exists");
        }
         try {
             int quantity_Available = productVariation.getQuantityAvailable();
             int quantityAvailable = quantity_Available - quantity;
             if (quantityAvailable < 0) {
                 return new ResponseEntity("Few Products Left ,You need to select in the the range ", HttpStatus.BAD_REQUEST);

             }

             productVariation.setQuantityAvailable(quantityAvailable);
         }
         catch (Exception ex)
         {
             System.out.println("Exception is"+ex.getMessage());
         }
        productVariationRepository.save(productVariation);
        double amountPaid = quantity * productVariation.getPrice();
        Orders orders = new Orders();
        orders.setDateCreated(new Date());
        orders.setCustomer(customer);
        orders.setPaymentMethod(paymentMethod);
        orders.setAmountPaid(amountPaid);
        orders.setOrderAddress(addressToOrderAddress(address));

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setPrice((double) ((productVariationRepository.getPrice(productVariationId)) * quantity));
        orderProduct.setOrders(orders);
        orderProduct.setProductVariationMetaData(String.valueOf(productVariation));
        orderProduct.setProductVariation(productVariation);

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setOrderProduct(orderProduct);
        Optional<Product> productOptional = productRepository.findById(productVariationRepository.getProductId(productVariationId));
        Product product = productOptional.get();

        orderRepository.save(orders);
        productVariationRepository.save(productVariation);

       /*      if (true) {
                 throw new OutOfStockException("The quantity is not Available");
             }*/

        System.out.println(("*****sending message*****"));
        Notification msg=new Notification("to confirm order", product);
        amqpProducer.sendMessage(msg);
        System.out.println("====message send====");
        orderProductRepository.save(orderProduct);
        addressRepository.save(address);
        customerRepository.save(customer);
        //return new ResponseEntity("***********ORDER PLACED********",HttpStatus.OK);
        //responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("message-order-placed", null, LocaleContextHolder.getLocale()));
        //return responseEntity;


        responseEntity = ResponseEntity.status(HttpStatus.OK).body(messageSource.getMessage("message-order-placed", null, LocaleContextHolder.getLocale()));
        return responseEntity;
    }

    OrderAddress addressToOrderAddress(Address address) {
        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setAddressLine(address.getAddressLine());
        orderAddress.setCity(address.getCity());
        orderAddress.setCountry(address.getCountry());
        orderAddress.setLabel(address.getLabel());
        orderAddress.setState(address.getState());
        orderAddress.setZipCode(address.getZipCode());
        return orderAddress;
    }
}
