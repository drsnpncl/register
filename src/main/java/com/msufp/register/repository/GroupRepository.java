package com.msufp.register.repository;

import com.msufp.register.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long>{
    List<Group> findByMembers(Long memberId);
    Optional<Group> findById(Long id);
}
