package com.deliveryservice.DeliveryService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deliveryservice.DeliveryService.dto.DeliveryRequestDTO;
import com.deliveryservice.DeliveryService.dto.DeliveryResponseDTO;
import com.deliveryservice.DeliveryService.model.Delivery;
import com.deliveryservice.DeliveryService.repository.DeliveryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public DeliveryResponseDTO createDeliveryPerson(DeliveryRequestDTO req) {
        try {
            Delivery delivery = new Delivery();
            delivery.setDeliveryName(req.getDeliveryName());
            delivery.setEmail(req.getEmail());
            delivery.setAge(req.getAge());
            delivery.setVehicleNumber(req.getVehicleNumber());
            delivery.setVehicleType(req.getVehicleType());
            delivery.setContactNumber(req.getContactNumber());

            Delivery saved = deliveryRepository.save(delivery);
            log.info("Delivery person created successfully: {}", saved);
            return new DeliveryResponseDTO("Delivery person created successfully", null);
        } catch (Exception e) {
            log.error("Error creating delivery person: {}", e.getMessage());
            return new DeliveryResponseDTO(null, "Error creating delivery person");
        }
    }

    public List<Delivery> getDelivery() {
        return deliveryRepository.findAll();
    }

    public Optional<Delivery> getDeliveryByEmail(String email) {
        return deliveryRepository.findByEmail(email);
    }

    public DeliveryResponseDTO updateDeliveryPerson(DeliveryRequestDTO req, String email) {
        try {
            Optional<Delivery> optionalDelivery = deliveryRepository.findByEmail(email);

            if (!optionalDelivery.isPresent()) {
                return DeliveryResponseDTO.builder()
                        .error("Restaurant Not Found" + email)
                        .build();
            }

            Delivery delivery = optionalDelivery.get();
            delivery.setDeliveryName(req.getDeliveryName());
            delivery.setEmail(email);
            delivery.setAge(req.getAge());
            delivery.setVehicleNumber(req.getVehicleNumber());
            delivery.setVehicleType(req.getVehicleType());
            delivery.setContactNumber(req.getContactNumber());

            deliveryRepository.save(delivery);

            return DeliveryResponseDTO.builder()
                    .message("Update successful")
                    .build();
        } catch (Exception e) {
            log.error("Error updating delivery person: {}", e.getMessage());
            return DeliveryResponseDTO.builder()
                    .error("Error updating delivery person")
                    .build();
        }
    }

    public boolean deleteDeliveryPerson(String email) {
        Optional<Delivery> optionalDelivery = deliveryRepository.findByEmail(email);
        if (optionalDelivery.isPresent()) {
            deliveryRepository.delete(optionalDelivery.get());
            return true;
        }
        return false;
    }

    public boolean checkIfDeliverExists(String email) {
        return deliveryRepository.findByEmail(email).isPresent();
    }

}
