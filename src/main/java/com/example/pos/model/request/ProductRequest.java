package com.example.pos.model.request;

import com.example.pos.util.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class ProductRequest extends BaseRequest {

    private String id;

    private String name;

    private BigDecimal price;

    private int quantity;
}
