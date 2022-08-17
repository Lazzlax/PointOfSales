package com.example.pos.service;

import com.example.pos.model.entity.Transaction;
import com.example.pos.model.entity.TransactionDetail;
import com.example.pos.model.request.TransactionRequest;
import com.example.pos.model.response.TransactionResponse;
import com.example.pos.repository.TransactionDetailRepository;
import com.example.pos.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    TransactionRepository transactionRepository;
    TransactionDetailRepository transactionDetailRepository;
    TransactionResponse transactionResponse;

    public TransactionService(TransactionRepository transactionRepository, TransactionResponse transactionResponse,
                              TransactionDetailRepository transactionDetailRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionDetailRepository = transactionDetailRepository;
        this.transactionResponse = transactionResponse;
    }


    public List<TransactionResponse> getAllTransaction(){
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.findAll();

        for (Transaction item:transactions) {
            TransactionResponse trx = TransactionResponse.builder()
                    .id(item.getId())
                    .totalItem(item.getTotalItem())
                    .totalPrice(item.getTotalPrice())
                    .transactionDate(item.getTransactionDate())
                    .detail(item.getDetail())
                    .build();

            transactionResponses.add(trx);
        }

        return transactionResponses;
    }

    public List<TransactionResponse> getTransactionFiltered(int totalItem,
                                                            BigDecimal totalPrice,
                                                            Date startDate,
                                                            Date endDate,
                                                            int page,
                                                            int size){
        List<TransactionResponse> transactionResponses = new ArrayList<>();

        Pageable paging = PageRequest.of(page, size);

        Page<Transaction> transactions = transactionRepository
                .getTransactionFiltered
                        (totalItem,
                        totalPrice,
                        startDate,
                        endDate,
                        paging);

        for (Transaction item: transactions) {
            TransactionResponse trx = TransactionResponse.builder()
                    .id(item.getId())
                    .totalItem(item.getTotalItem())
                    .totalPrice(item.getTotalPrice())
                    .transactionDate(item.getTransactionDate())
                    .detail(transactionDetailRepository.findByHeadTransId(item.getId()))
                    .build();

            transactionResponses.add(trx);
        }
        return transactionResponses;
    }


    public TransactionResponse insertTransaction(TransactionRequest request){
        List<TransactionDetail> transactionDetails = new ArrayList<>();
        BigDecimal sumPrice = BigDecimal.ZERO;
        Date date = new Date();

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .totalItem(request.getTotalItem())
                .totalPrice(sumPrice)
                .transactionDate(date)
                .build();

        for (TransactionDetail itemDetail: request.getDetail()) {
            TransactionDetail transactionDetail = TransactionDetail.builder()
                    .id(UUID.randomUUID().toString())
                    .headTransId(transaction.getId())
                    .product(itemDetail.getProduct())
                    .quantity(itemDetail.getQuantity())
                    .amount(itemDetail.getProduct().getPrice()
                            .multiply(BigDecimal.valueOf(itemDetail.getQuantity())))
                    .build();

            sumPrice = sumPrice.add(transactionDetail.getAmount());
            transactionDetails.add(transactionDetail);

//            transactionDetailRepository.save(transactionDetail);
        }

        transaction.setTotalPrice(sumPrice);
        transactionRepository.save(transaction);
        transactionDetailRepository.saveAll(transactionDetails.subList(0,transactionDetails.size()));

        return TransactionResponse.builder()
                .id(transaction.getId())
                .totalItem(transaction.getTotalItem())
                .totalPrice(transaction.getTotalPrice())
                .transactionDate(transaction.getTransactionDate())
                .detail(transactionDetails)
                .build();
    }


}
