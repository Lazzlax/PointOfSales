package com.example.pos.repository;

import com.example.pos.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

//    @Modifying(clearAutomatically = true)
//    @Transactional
//    @Query(value = "SELECT a.tes, b.tes, a.tes FROM TES a " +
//            " JOIN TES2 b on a.tes = b.tes" +
//            "where CONDITION = :tes", nativeQuery = true)
//    List<ResponseQuery> getListFromJoinTable(String Tes);

}
