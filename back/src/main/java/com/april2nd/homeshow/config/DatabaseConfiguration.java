package com.april2nd.homeshow.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(
		basePackages = "com.april2nd.homeshow.model.mapper"
)
public class DatabaseConfiguration {}