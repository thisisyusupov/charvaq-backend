package com.example.demo.repository.auth;


import com.example.demo.entity.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(value = "select * from db_user where login = :login ",nativeQuery = true)
    User findByLogin(@Param("login") String login);

    boolean existsByLogin(String login);

}
