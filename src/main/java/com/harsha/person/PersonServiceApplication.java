package com.harsha.person;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.harsha.person.entity.Person;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class PersonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonServiceApplication.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<?, ?> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	
//	@Bean
//	JedisConnectionFactory jedisConnectionFactory() {
//	    JedisConnectionFactory jedisConFactory
//	      = new JedisConnectionFactory();
//	    jedisConFactory.setHostName("localhost");
//	    jedisConFactory.setPort(6379);
//	    return jedisConFactory;
//	}
}
