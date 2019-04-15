package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Csadstate;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

/**
 * @author Jingfeng Zhou
 */
public interface CsadstateRepository extends CassandraRepository<Csadstate, String> {
    List<Csadstate> findByGroupid(String groupid);
}
