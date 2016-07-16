package com.cjemison.rxjava.service.eventBus;

import com.cjemison.rxjava.controller.domain.DogPayload;
import com.cjemison.rxjava.controller.domain.HttpRequestContext;
import com.cjemison.rxjava.service.domain.AbstractEvent;

/**
 * Created by cjemison on 7/16/16.
 */
public interface GetDogIdSubscriber {

  void event(AbstractEvent<HttpRequestContext, DogPayload> event);
}
