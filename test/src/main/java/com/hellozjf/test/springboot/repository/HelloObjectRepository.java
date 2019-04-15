package com.hellozjf.test.springboot.repository;

import com.hellozjf.test.springboot.domain.HelloObject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jingfeng Zhou
 */
public interface HelloObjectRepository extends JpaRepository<HelloObject, Long> {
}
