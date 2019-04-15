package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Customqueueinfo;
import com.hellozjf.test.cassandra.domain.CustomqueueinfoKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface CustomqueueinfoRepository extends CassandraRepository<Customqueueinfo, CustomqueueinfoKey> {
}
