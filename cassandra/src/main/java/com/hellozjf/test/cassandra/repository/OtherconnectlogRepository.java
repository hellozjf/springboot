package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Otherconnectlog;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface OtherconnectlogRepository extends CassandraRepository<Otherconnectlog, String> {
}
