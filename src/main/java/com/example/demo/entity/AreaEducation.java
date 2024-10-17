package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "AreaEducation")
public class AreaEducation {

    public AreaEducation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaEducation that = (AreaEducation) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
