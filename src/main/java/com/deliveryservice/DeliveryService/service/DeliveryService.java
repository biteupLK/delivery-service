package com.deliveryservice.DeliveryService.service;

import java.util.List;

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

    public DeliveryResponseDTO createDeliveryPerson(DeliveryRequestDTO req){
        try{
            Delivery delivery = new Delivery();
            delivery.setDeliveryName(req.getDeliveryName());
            delivery.setEmail(req.getEmail());
            delivery.setAge(req.getAge());
            delivery.setVehicleNumber(req.getVehicleNumber());
            delivery.setVehicleType(req.getVehicleType());

            Delivery saved = deliveryRepository.save(delivery);
            log.info("Delivery person created successfully: {}", saved);
            return new DeliveryResponseDTO("Delivery person created successfully", null);
        }
        catch (Exception e){
            log.error("Error creating delivery person: {}", e.getMessage());
            return new DeliveryResponseDTO(null, "Error creating delivery person");
        }
    }

    public List<Delivery> getDelivery(){
        return deliveryRepository.findAll();
    }
    
}
