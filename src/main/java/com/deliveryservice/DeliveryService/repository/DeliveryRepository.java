package com.deliveryservice.DeliveryService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deliveryservice.DeliveryService.model.Delivery;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {

}
