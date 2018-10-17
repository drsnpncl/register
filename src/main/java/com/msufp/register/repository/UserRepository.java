package com.msufp.register.repository;

import com.msufp.register.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findById(Long id);

    List<User> findByFirstName(String firstName);

    List<User> findByLastName(String lastName);

    User findByEmail(String email);

    User findByContact(String contact);

    List<User> findByCollege(String college);

    List<User> findByCity(String city);

    List<User> findByDepartment(String department);

    List<User> findByYear(int year);
}
