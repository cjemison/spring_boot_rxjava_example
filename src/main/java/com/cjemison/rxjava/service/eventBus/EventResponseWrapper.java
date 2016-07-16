package com.cjemison.rxjava.service.eventBus;

/**
 * Created by cjemison on 6/27/16.
 */
public interface EventResponseWrapper<P extends EventResponse, R> {
  void eventExecutor(P p, EventBusFunction<P, R> function);
}
