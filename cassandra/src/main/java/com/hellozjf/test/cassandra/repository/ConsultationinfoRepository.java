package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Consultationinfo;
import com.hellozjf.test.cassandra.domain.ConsultationinfoKey;
import com.hellozjf.test.cassandra.domain.Csadstate;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface ConsultationinfoRepository extends CassandraRepository<Consultationinfo, ConsultationinfoKey> {
}
