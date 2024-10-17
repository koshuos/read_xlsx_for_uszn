package com.example.demo.repo;

import com.example.demo.entity.AreaEducation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AreaEnducationRepo extends JpaRepository<AreaEducation, Long> {

    Optional<AreaEducation> findByName(String name);

}
