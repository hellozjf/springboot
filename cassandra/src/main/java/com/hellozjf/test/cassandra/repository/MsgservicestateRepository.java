package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Msgservicestate;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface MsgservicestateRepository extends CassandraRepository<Msgservicestate, String> {
}
