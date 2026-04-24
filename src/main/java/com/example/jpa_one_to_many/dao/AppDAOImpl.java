package com.example.jpa_one_to_many.dao;

import com.example.jpa_one_to_many.entity.Course;
import com.example.jpa_one_to_many.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private final EntityManager entityManager;

    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findById(int id) {
        return entityManager.find(Instructor.class,id);
    }

    @Override
    @Transactional
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    @Transactional
    public void deleteInstructor(int id) {

        //find instructor by id
        Instructor theInstructor=entityManager.find(Instructor.class, id);

        if (theInstructor == null) {
            System.out.println("Instructor id " + id + " not found");
            return;
        }

        //break associations
        //Courses
        List<Course> theCourses=theInstructor.getCourseList();
        for(Course c:theCourses){
            c.setInstructor(null);
        }
        entityManager.remove(theInstructor);


    }

}
