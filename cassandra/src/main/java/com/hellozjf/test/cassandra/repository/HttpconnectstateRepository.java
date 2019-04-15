package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Httpconnectstate;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface HttpconnectstateRepository extends CassandraRepository<Httpconnectstate, String> {
}
