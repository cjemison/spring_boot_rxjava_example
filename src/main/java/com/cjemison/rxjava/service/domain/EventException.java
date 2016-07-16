package com.cjemison.rxjava.service.domain;

/**
 * Created by cjemison on 7/16/16.
 */
public class EventException {
  private final Exception exception;
  private final Object object;

  public EventException(Exception exception) {
    this(exception, null);
  }

  public EventException(Exception exception, Object object) {
    this.exception = exception;
    this.object = object;
  }

  public Exception getException() {
    return exception;
  }

  public Object getObject() {
    return object;
  }
}
