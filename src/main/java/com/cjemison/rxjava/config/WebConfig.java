package com.cjemison.rxjava.config;

import com.google.common.eventbus.EventBus;

import com.cjemison.rxjava.service.eventBus.GetDogIdSubscriber;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cjemison on 7/16/16.
 */
@Configuration
public class WebConfig implements InitializingBean {

  @Bean
  public EventBus eventBus(final GetDogIdSubscriber getDogIdSubscriber) {
    EventBus eventBus = new EventBus();
    eventBus.register(getDogIdSubscriber);
    return eventBus;
  }

  @Override
  public void afterPropertiesSet() throws Exception {

  }
}
