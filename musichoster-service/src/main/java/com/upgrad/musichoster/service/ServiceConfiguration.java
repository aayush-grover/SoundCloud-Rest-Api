package com.upgrad.musichoster.service;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.upgrad.musichoster.service")
@EntityScan("com.upgrad.musichoster.service.entity")
public class ServiceConfiguration {
}
