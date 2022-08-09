package com.example.pos.model.response;

import com.example.pos.util.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductResponse extends BaseResponse {

    private String id;

    private String name;

    private BigDecimal price;

    private int quantity;
}
