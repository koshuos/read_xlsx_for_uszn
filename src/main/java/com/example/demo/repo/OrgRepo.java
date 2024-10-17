package com.example.demo.repo;

import com.example.demo.entity.Organizations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrgRepo extends JpaRepository<Organizations, Long> {

    Optional<Organizations> findByName(String name);

}
