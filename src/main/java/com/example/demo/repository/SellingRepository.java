package com.example.demo.repository;

import com.example.demo.entity.Selling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SellingRepository extends JpaRepository<Selling,Long> {

    @Query(value = "select sum(value) from db_selling",nativeQuery = true)
    double sumValues();

    @Query(value = "select count(*) from db_selling",nativeQuery = true)
    int countById();
}
