package com.jsp.SpringbootRestApiJPAManytoMany;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByRatingGreaterThan(double rating, Pageable pageable);
}

