package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name = "Organizations")
public class Organizations {

    public Organizations() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organizations that = (Organizations) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(name);
        return result;
    }
}
