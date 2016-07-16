package com.cjemison.rxjava.service.eventBus;

import com.cjemison.rxjava.service.domain.EventException;

import java.util.Optional;

import rx.Observable;

/**
 * Created by cjemison on 7/16/16.
 */
public interface EventResponse<R> {

  void setEventException(final EventException exception);

  Observable<Optional<R>> getResponse();

  void setResponse(final Observable<Optional<R>> response);
}
