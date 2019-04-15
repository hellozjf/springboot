package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Servicelog;
import com.hellozjf.test.cassandra.domain.ServicelogKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface ServicelogRepository extends CassandraRepository<Servicelog, ServicelogKey> {
}
