package com.hellozjf.test.springboot.repository;

import com.hellozjf.test.springboot.domain.MyCacheEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jingfeng Zhou
 */
public interface MyCacheRepository extends JpaRepository<MyCacheEntity, String> {
}
