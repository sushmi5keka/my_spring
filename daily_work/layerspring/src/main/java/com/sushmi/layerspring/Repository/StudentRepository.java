package com.sushmi.layerspring.Repository;

import com.sushmi.layerspring.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findByEmail(String email);

    long countAllByEmail(String email);

}
