package com.cjemison.rxjava.service.eventBus;

import java.util.Optional;

import rx.Observable;

/**
 * Created by cjemison on 7/16/16.
 */
public interface EventBusFunction<P, R> {
  Observable<Optional<R>> apply(final P p) throws Exception;
}
