package com.example.pos.model.response;

import com.example.pos.model.entity.TransactionDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TransactionResponse {

    private String id;

    private int totalItem;

    private BigDecimal totalPrice;

    private Date transactionDate;

    private List<TransactionDetail> detail = new ArrayList<>();

}
