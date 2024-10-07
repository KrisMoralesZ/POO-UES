package org.example.persistence.repository;

import org.example.persistence.entity.StudentEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface StudentRepository extends ListCrudRepository<StudentEntity, Integer> {
    StudentEntity findByName(String name);
}
