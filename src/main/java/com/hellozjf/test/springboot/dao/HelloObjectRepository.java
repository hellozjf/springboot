package com.hellozjf.test.springboot.dao;

import com.hellozjf.test.springboot.dataobject.HelloObject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jingfeng Zhou
 */
public interface HelloObjectRepository extends JpaRepository<HelloObject, Long> {
}
