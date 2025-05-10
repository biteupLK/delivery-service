package com.deliveryservice.DeliveryService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryRequestDTO {

    private String deliveryName;
    private String email;
    private String age;
    private String vehicleNumber;
    private String vehicleType;
    private String contactNumber;
    
}
