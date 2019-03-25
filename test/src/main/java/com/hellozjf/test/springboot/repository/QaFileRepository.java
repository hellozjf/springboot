package com.hellozjf.test.springboot.repository;

import com.hellozjf.test.springboot.entity.QaFile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Jingfeng Zhou
 */
public interface QaFileRepository extends JpaRepository<QaFile, String> {

}
