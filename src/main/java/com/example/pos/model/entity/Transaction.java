package com.example.pos.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ms_transaction")
public class Transaction {

    @Id
    private String id;

    private int totalItem;

    private BigDecimal totalPrice;

    private Date transactionDate;

    @OneToMany
    private List<TransactionDetail> detail = new ArrayList<>();

}
