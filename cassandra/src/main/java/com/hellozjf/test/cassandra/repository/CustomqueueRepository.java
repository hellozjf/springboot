package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Customqueue;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface CustomqueueRepository extends CassandraRepository<Customqueue, String> {
}
