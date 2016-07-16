package com.cjemison.rxjava.service.domain;

/**
 * Created by cjemison on 7/16/16.
 */

import com.cjemison.rxjava.service.eventBus.EventResponse;

import org.springframework.util.Assert;

import java.util.Optional;

import rx.Observable;

/**
 * Created by cjemison on 6/27/16.
 */
public class AbstractEvent<P, R> implements EventResponse<R> {
  private final P payload;
  private Observable<Optional<R>> response;
  private EventException eventException;

  public AbstractEvent(final P payload) {
    this.payload = payload;
  }

  public P getPayload() {
    return payload;
  }

  public Observable<Optional<R>> getResponse() {
    return response;
  }

  public void setResponse(Observable<Optional<R>> response) {
    Assert.notNull(response);
    this.response = response;
  }

  public EventException getEventException() {
    return eventException;
  }

  public void setEventException(EventException eventException) {
    Assert.notNull(eventException);
    this.eventException = eventException;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AbstractEvent<?, ?> that = (AbstractEvent<?, ?>) o;

    return payload != null ? payload.equals(that.payload) : that.payload == null;

  }

  @Override
  public int hashCode() {
    return payload != null ? payload.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "AbstractEvent{" +
          "payload=" + payload +
          ", response=" + response +
          ", eventException=" + eventException +
          '}';
  }
}
