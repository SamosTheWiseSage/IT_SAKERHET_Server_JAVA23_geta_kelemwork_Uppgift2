package com.example.demo.repository;

import com.example.demo.Model.TimeCapsule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeCapsuleRepository extends JpaRepository<TimeCapsule, Long> {
    List<TimeCapsule> findByUserId(Long userId);
}