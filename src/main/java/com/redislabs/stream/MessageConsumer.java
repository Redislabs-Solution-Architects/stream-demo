package com.redislabs.stream;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer implements StreamListener<String, MapRecord<String, String, String>>, InitializingBean {

    @Autowired
    private ReactiveRedisTemplate<String, String> redisTemplate;
    @Autowired
    private MeterRegistry meterRegistry;
    private Counter counter;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.counter = Counter.builder("events.consumed").description("Total number of messages consumed").register(meterRegistry);
    }

    @Override
    @SneakyThrows
    public void onMessage(MapRecord<String, String, String> record) {
        counter.increment();
    }

}