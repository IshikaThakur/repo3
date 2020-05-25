package com.ecommerceApp.ecommerceApp.services;

import ch.qos.logback.core.status.Status;
import com.ecommerceApp.ecommerceApp.Repositories.*;
import com.ecommerceApp.ecommerceApp.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Notification;
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

    public void placeOrder(Long productVariationId, int quantity, String paymentMethod, Long addressId, String email) {
        Customer customer = customerRepository.findByEmail(email);
        Optional<ProductVariation> productVariationOptional = productVariationRepository.findById(productVariationId);
        ProductVariation productVariation = productVariationOptional.get();
       /* if (productVariation.getActive() == false) {
            throw new NullPointerException("this item is not available");
        }*/
        int quantity_Available = productVariation.getQuantityAvailable();
        int quantityAvailable = quantity_Available - quantity;
        if (quantityAvailable < 0) {
            throw new NullPointerException("only " + quantity_Available + " are in stock please select in this range");
        }
        productVariation.setQuantityAvailable(quantityAvailable);
        productVariationRepository.save(productVariation);

        Optional<Address> addressOptional = addressRepository.findById(addressId);
        Address address = null;
        if (addressOptional.isPresent()) {
            address = addressOptional.get();
        } else {
            throw new NullPointerException("add an address to place order");
        }
        Orders orders = new Orders();
        orders.setAmountPaid((productVariationRepository.getPrice(productVariationId)) * quantity);
        orders.setCustomer(customer);
        orders.setPaymentMethod(paymentMethod);
        orders.setOrderAddress(addressToOrderAddress(address));

    OrderProduct orderProduct = new OrderProduct();
        orderProduct.setPrice((double) ((productVariationRepository.getPrice(productVariationId))*quantity));
        orderProduct.setOrders(orders);
       // orderProduct.setProductVariationMetadata(productVariation.getInfoJson());
        orderProduct.setProductVariationMetaData(String.valueOf(productVariation));
        orderProduct.setProductVariation(productVariation);

    OrderStatus orderStatus= new OrderStatus();
        //orderStatus.
        orderStatus.setOrderProduct(orderProduct);
    Optional<Product> productOptional=productRepository.findById(productVariationRepository.getProductId(productVariationId));
    Product product=productOptional.get();

        System.out.println(("*****sending message*****"));
   // Notification msg=new Notification("to confirm order", product);
   //     amqpProducer.sendMessage(msg);
        System.out.println("====message send====");

        orderRepository.save(orders);
        productVariationRepository.save(productVariation);
        orderProductRepository.save(orderProduct);
        addressRepository.save(address);
        orderStatusRepository.save(orderStatus);
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