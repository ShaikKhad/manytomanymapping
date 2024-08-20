package com.jsp.SpringbootRestApiJPAManytoMany;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    Page<Actor> findByNameContaining(String name, Pageable pageable);
}

