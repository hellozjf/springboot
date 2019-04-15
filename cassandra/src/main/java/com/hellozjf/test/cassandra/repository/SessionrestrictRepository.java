package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Sessionrestrict;
import com.hellozjf.test.cassandra.domain.SessionrestrictKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface SessionrestrictRepository extends CassandraRepository<Sessionrestrict, SessionrestrictKey> {
}
