package com.example.pos.controller;

import com.example.pos.model.request.ProductRequest;
import com.example.pos.model.request.TransactionRequest;
import com.example.pos.model.response.ProductResponse;
import com.example.pos.model.response.TransactionResponse;
import com.example.pos.service.TransactionService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insert")
    public TransactionResponse insertTrx(@RequestBody TransactionRequest request){
        return transactionService.insertTransaction(request);
    }

//    @PostMapping("/update")
//    public TransactionResponse updateTrx(@RequestBody TransactionRequest request){
//        return transactionService.updateTransaction(request);
//    }

}
