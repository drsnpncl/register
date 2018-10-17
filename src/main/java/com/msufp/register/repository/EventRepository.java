package com.msufp.register.repository;

import com.msufp.register.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByName(String name);

    Optional<Event> findById(Long id);

    List<Event> findByParentEvent(String parentEvent);

    List<Event> findByEventType(String eventType);

    List<Event> findByStatus(String status);
}
