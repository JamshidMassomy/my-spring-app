//package com.lottery.config;
//
//import io.r2dbc.h2.H2ConnectionConfiguration;
//import io.r2dbc.h2.H2ConnectionFactory;
//import io.r2dbc.spi.ConnectionFactories;
//import io.r2dbc.spi.ConnectionFactory;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
//import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
//
//@Configuration
//@EnableR2dbcRepositories
//public class DbConfig extends AbstractR2dbcConfiguration {
//
//    @Override
//    public ConnectionFactory connectionFactory() {
//        ConnectionFactory factory = ConnectionFactories.get("r2dbc:h2:mem:///lotterydb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
//
//        see: https://github.com/spring-projects/spring-data-r2dbc/issues/269
//        return new H2ConnectionFactory(
//
//                H2ConnectionConfiguration.builder()
//                        .url("/h2-console")
//                        .inMemory("lotterydb")
//                        .file("./lotterydb")
//                        .username("user")
//                        .password("password").build()
//        );
//    }
//}
