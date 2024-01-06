package com.nerzon.course.repository;

import com.nerzon.course.entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nerzon
 */
@Repository
public interface CatRepo extends JpaRepository<Cat, Integer> {
}
