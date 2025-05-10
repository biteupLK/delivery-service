package com.deliveryservice.DeliveryService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliveryservice.DeliveryService.dto.DeliveryRequestDTO;
import com.deliveryservice.DeliveryService.dto.DeliveryResponseDTO;
import com.deliveryservice.DeliveryService.model.Delivery;
import com.deliveryservice.DeliveryService.service.DeliveryService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public List<Delivery> getAllDelivery() {
        return deliveryService.getDelivery();
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getDeliveryByEmail(@PathVariable String email) {
        Optional<Delivery> delivery = deliveryService.getDeliveryByEmail(email);
        if (delivery.isPresent()) {
            return ResponseEntity.ok(delivery.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Delivery person not found with email: " + email);
        }
    }

    @PutMapping("/{email}")
    public ResponseEntity<DeliveryResponseDTO> updateDeliveryPerson(
            @RequestBody DeliveryRequestDTO request,
            @PathVariable String email) {

        DeliveryResponseDTO response = deliveryService.updateDeliveryPerson(request, email);

        if (response.getError() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> deleteDeliveryPerson(@PathVariable String email) {
        boolean deleted = deliveryService.deleteDeliveryPerson(email);
        if (deleted) {
            return ResponseEntity.ok("Delivery person deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Delivery person with email " + email + " not found.");
        }
    }

    @GetMapping("/checkDelivery/{email}")
    public ResponseEntity<Boolean> checkIfDeliveryExists(@PathVariable String email) {
        boolean exists = deliveryService.checkIfDeliverExists(email);
        return ResponseEntity.ok(exists);
    }

}
