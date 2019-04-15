package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Cacsiresult;
import com.hellozjf.test.cassandra.domain.CacsiresultKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface CacsiresultRepository extends CassandraRepository<Cacsiresult, CacsiresultKey> {
}
