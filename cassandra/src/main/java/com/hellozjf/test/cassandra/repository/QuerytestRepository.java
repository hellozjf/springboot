package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Querytest;
import com.hellozjf.test.cassandra.domain.QuerytestKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface QuerytestRepository extends CassandraRepository<Querytest, QuerytestKey> {
}
