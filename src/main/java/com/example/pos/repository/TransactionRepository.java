package com.example.pos.repository;

import com.example.pos.model.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface TransactionRepository extends JpaRepository<Transaction, String> {



    //    buat sebuah api yang menampilkan seluruh transaksi (dalam bentuk pagination)
    //    yang bisa di filter berdasarkan jumlah item, total harga, start date transaksi, end date transaksi
    @Query(value = "SELECT * " +
            "FROM ms_transaction AS t " +
            "WHERE " +
            " ( " +
            " :totalItem=0 " +
            " AND :totalPrice=0 " +
            " AND COALESCE(:startDate,1)=1 " +
            " AND COALESCE(:endDate,1)=1 " +
            " ) " +
            " OR " +
            " ( " +
            " t.total_item = :totalItem " +
            " OR t.total_price = :totalPrice " +
            " OR t.transaction_date BETWEEN :startDate AND :endDate " +
            " ) " +
            "ORDER BY t.id ASC"
            , nativeQuery = true)
    Page<Transaction> getTransactionFiltered(@Param("totalItem") int totalItem,
                                             @Param("totalPrice") BigDecimal totalPrice,
                                             @Param("startDate") Date startDate,
                                             @Param("endDate") Date endDate,
                                             Pageable pageable);
}
