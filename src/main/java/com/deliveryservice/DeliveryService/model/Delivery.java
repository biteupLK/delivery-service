package com.deliveryservice.DeliveryService.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "delivery")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Delivery {

    @Id
    private String id;

    private String deliveryName;
    private String email;
    private String age;
    private String vehicleNumber;
    private String vehicleType;
    private String contactNumber;

}
