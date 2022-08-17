package com.example.pos.controller;

import com.example.pos.model.request.TransactionRequest;
import com.example.pos.model.response.TransactionResponse;
import com.example.pos.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    TransactionRequest transactionRequest;
    TransactionService transactionService;

    public TransactionController(TransactionRequest transactionRequest, TransactionService transactionService){
        this.transactionRequest = transactionRequest;
        this.transactionService = transactionService;
    }

    @GetMapping("/getTransactions")
    public List<TransactionResponse> getListTransactions(){
        return transactionService.getAllTransaction();
    }

    @GetMapping("/getTransactionByTotalItemORTotalPriceORDate")
    public List<TransactionResponse> getListFilteredTransaction(@RequestParam(required = false, defaultValue = "0")
                                                                    Integer totalItem,
                                                                @RequestParam(required = false, defaultValue = "0")
                                                                    BigDecimal totalPrice,
                                                                    Date startDate,
                                                                    Date endDate,
                                                                @RequestParam(required = false, defaultValue = "0")
                                                                    int page,
                                                                @RequestParam(required = false, defaultValue = "2")
                                                                    int size){

        return transactionService
                .getTransactionFiltered(totalItem,totalPrice,startDate,endDate, page, size);
    }

    @PostMapping("/insert")
    public TransactionResponse insertTrx(@RequestBody TransactionRequest request){
        return transactionService.insertTransaction(request);
    }

//    @PostMapping("/update")
//    public TransactionResponse updateTrx(@RequestBody TransactionRequest request){
//        return transactionService.updateTransaction(request);
//    }

}
