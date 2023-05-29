package com.example.demo.repository;

import com.example.demo.dto.TitleLast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleLastRepository extends JpaRepository<TitleLast,Integer> {
}
