package com.ecommerceApp.ecommerceApp.services;
import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.entities.*;
import com.ecommerceApp.ecommerceApp.exceptions.AddressNotFoundException;
import com.ecommerceApp.ecommerceApp.exceptions.OutOfStockException;
import com.ecommerceApp.ecommerceApp.rabbitMQ.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
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
    OrderStatusRepository orderStatusRepository;
    @Autowired
    RabbitMQListener rabbitMQListener;
    @Autowired
    RabbitMQProperties rabbitMQProperties;
    @Autowired
    AMQPConfig amqpConfig;
    @Autowired
    AMQPProducer amqpProducer;


    public void placeOrder(Long productVariationId, int quantity, String paymentMethod, Long addressId, String email) {
        Customer customer = customerRepository.findByEmail(email);
        Optional<ProductVariation> productVariationOptional = productVariationRepository.findById(productVariationId);
        ProductVariation productVariation = productVariationOptional.get();
        int quantity_Available = productVariation.getQuantityAvailable();
        int quantityAvailable = quantity_Available-quantity;
        if (quantityAvailable < 0) {
           throw new OutOfStockException("Few Products Left ,You need to select in the the range "+quantity_Available);
        }
        productVariation.setQuantityAvailable(quantityAvailable);
        productVariationRepository.save(productVariation);

        Optional<Address> addressOptional = addressRepository.findById(addressId);
        Address address = null;
        if (addressOptional.isPresent()) {
            address = addressOptional.get();
        } else {
            throw new AddressNotFoundException("****************Specify Your Delievery Address*********");
        }
        Orders orders = new Orders();
       orders.setAmountPaid(productVariationRepository.getPrice(productVariationId));
        orders.setDateCreated(new Date());
        orders.setCustomer(customer);
        orders.setPaymentMethod(paymentMethod);
        orders.setOrderAddress(addressToOrderAddress(address));

    OrderProduct orderProduct = new OrderProduct();
        orderProduct.setPrice((double) ((productVariationRepository.getPrice(productVariationId))*quantity));
        orderProduct.setOrders(orders);
        orderProduct.setProductVariationMetaData(String.valueOf(productVariation));
        orderProduct.setProductVariation(productVariation);

    OrderStatus orderStatus= new OrderStatus();
        orderStatus.setOrderProduct(orderProduct);
    Optional<Product> productOptional=productRepository.findById(productVariationRepository.getProductId(productVariationId));
    Product product=productOptional.get();

        orderRepository.save(orders);
        productVariationRepository.save(productVariation);
        orderProductRepository.save(orderProduct);
        addressRepository.save(address);
        customerRepository.save(customer);
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