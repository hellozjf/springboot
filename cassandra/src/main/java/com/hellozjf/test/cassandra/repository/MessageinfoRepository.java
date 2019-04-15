package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Messageinfo;
import com.hellozjf.test.cassandra.domain.MessageinfoKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface MessageinfoRepository extends CassandraRepository<Messageinfo, MessageinfoKey> {
}
