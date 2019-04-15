package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Customservice;
import com.hellozjf.test.cassandra.domain.CustomserviceKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface CustomserviceRepository extends CassandraRepository<Customservice, CustomserviceKey> {
}
