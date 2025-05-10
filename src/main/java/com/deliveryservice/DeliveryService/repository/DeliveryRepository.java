package com.deliveryservice.DeliveryService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.deliveryservice.DeliveryService.model.Delivery;
import java.util.Optional;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {
    Optional<Delivery> findByEmail(String email);
}
