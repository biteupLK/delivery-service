package com.deliveryservice.DeliveryService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryservice.DeliveryService.dto.DeliveryRequestDTO;
import com.deliveryservice.DeliveryService.dto.DeliveryResponseDTO;
import com.deliveryservice.DeliveryService.model.Delivery;
import com.deliveryservice.DeliveryService.service.DeliveryService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/create")
    public ResponseEntity<DeliveryResponseDTO> createDeliveryPerson(@RequestBody DeliveryRequestDTO requestDTO) {
        DeliveryResponseDTO response = deliveryService.createDeliveryPerson(requestDTO);

        if (response.getMessage() != null) {
            return ResponseEntity.ok(response); 
        } else {
            return ResponseEntity.status(500).body(response); 
        }
    }

    @GetMapping
    public List<Delivery> getAllDelivery(){
        return deliveryService.getDelivery();
    }
    
    

}
