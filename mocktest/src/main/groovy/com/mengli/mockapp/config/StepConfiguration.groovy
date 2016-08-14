package com.mengli.mockapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer


/**
 * Created by mlhuang on 8/10/16.
 */

@Configuration
@PropertySource('classpath:application.properties')
class StepConfiguration {
    @Bean
    static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        new PropertySourcesPlaceholderConfigurer()
    }
}
