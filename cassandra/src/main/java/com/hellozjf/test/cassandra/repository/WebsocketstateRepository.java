package com.hellozjf.test.cassandra.repository;

import com.hellozjf.test.cassandra.domain.Websocketstate;
import com.hellozjf.test.cassandra.domain.WebsocketstateKey;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * @author Jingfeng Zhou
 */
public interface WebsocketstateRepository extends CassandraRepository<Websocketstate, WebsocketstateKey> {
}
