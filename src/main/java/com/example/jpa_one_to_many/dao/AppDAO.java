package com.example.jpa_one_to_many.dao;

import com.example.jpa_one_to_many.entity.Instructor;
import jakarta.persistence.criteria.CriteriaBuilder;

public interface AppDAO {
    public void save(Instructor instructor);
    public Instructor findById(int id);
    public void update(Instructor instructor);
    public void deleteInstructor(int id);
}
