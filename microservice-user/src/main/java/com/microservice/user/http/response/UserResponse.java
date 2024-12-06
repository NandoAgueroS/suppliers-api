package com.microservice.user.http.response;

import com.microservice.user.dto.SupplierDTO;
import com.microservice.user.dto.UserDTO;
import com.microservice.user.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private UserEntity user;
    private SupplierDTO supplier;
}
