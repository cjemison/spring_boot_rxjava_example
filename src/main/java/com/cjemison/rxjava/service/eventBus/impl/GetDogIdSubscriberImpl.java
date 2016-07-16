package com.cjemison.rxjava.service.eventBus.impl;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import com.cjemison.rxjava.controller.domain.DogPayload;
import com.cjemison.rxjava.controller.domain.HttpRequestContext;
import com.cjemison.rxjava.service.DogService;
import com.cjemison.rxjava.service.domain.AbstractEvent;
import com.cjemison.rxjava.service.domain.AbstractSubscriberEventResponseWrapper;
import com.cjemison.rxjava.service.eventBus.GetDogIdSubscriber;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by cjemison on 7/16/16.
 */
@Component
public class GetDogIdSubscriberImpl
      extends AbstractSubscriberEventResponseWrapper<
      AbstractEvent<HttpRequestContext, DogPayload>, DogPayload>
      implements GetDogIdSubscriber {
  private static final Logger logger = LoggerFactory.getLogger(GetDogIdSubscriberImpl.class);
  private final DogService dogService;

  @Autowired
  public GetDogIdSubscriberImpl(final DogService dogService,
                                final EventBus eventBus) {
    Assert.notNull(dogService);
    this.dogService = dogService;
    eventBus.register(this);
  }

  @Override
  @Subscribe
  public void event(AbstractEvent<HttpRequestContext, DogPayload> event) {
    Assert.notNull(event);
    Assert.notNull(event.getPayload());
    HttpRequestContext context = event.getPayload();
    if (context.getUri().equals("/v1/dog/id")) {
      super.eventExecutor(event, event1 -> dogService.getRandomUUID());
    }
  }
}
