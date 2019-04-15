package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Messagetemp;
import com.hellozjf.test.cassandra.domain.MessagetempKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface MessagetempRepository extends CassandraRepository<Messagetemp, MessagetempKey> {
}
