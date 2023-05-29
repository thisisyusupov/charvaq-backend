package com.example.demo.repository;

import com.example.demo.dto.Desc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DescRepository extends JpaRepository<Desc,Integer> {
}
