package com.hellozjf.test.springboot.repository;

import com.hellozjf.test.springboot.domain.Csadstate;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
public interface CsadstateRepository extends CassandraRepository<Csadstate, String> {
    List<Csadstate> findByGroupid(String groupid);
}
