package com.cjemison.rxjava.service.domain;

import com.cjemison.rxjava.service.eventBus.EventBusFunction;
import com.cjemison.rxjava.service.eventBus.EventResponse;
import com.cjemison.rxjava.service.eventBus.EventResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Created by cjemison on 7/16/16.
 */
public abstract class AbstractSubscriberEventResponseWrapper<P extends EventResponse, R>
      implements EventResponseWrapper<P, R> {
  private static final Logger logger = LoggerFactory.getLogger
        (AbstractSubscriberEventResponseWrapper.class);

  @Override
  @SuppressWarnings("unchecked")
  public void eventExecutor(P p, EventBusFunction<P, R> function) {
    Assert.notNull(p);
    Assert.notNull(function);
    logger.debug("Payload: {}", p);
    try {
      p.setResponse(function.apply(p));
    } catch (Exception e) {
      logger.warn("### Event Error", e);
      p.setEventException(new EventException(e, p));
    }
  }
}