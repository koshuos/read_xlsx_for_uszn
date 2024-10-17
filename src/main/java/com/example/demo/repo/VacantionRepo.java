package com.example.demo.repo;

import com.example.demo.entity.Vacations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacantionRepo extends JpaRepository<Vacations, Long> {
}
