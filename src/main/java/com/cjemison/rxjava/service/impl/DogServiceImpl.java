package com.cjemison.rxjava.service.impl;

import com.cjemison.rxjava.controller.domain.DogPayload;
import com.cjemison.rxjava.service.DogService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import rx.Observable;

/**
 * Created by cjemison on 7/16/16.
 */
@Service
public class DogServiceImpl implements DogService {
  private static final Logger logger = LoggerFactory.getLogger(DogServiceImpl.class);

  @Override
  public Observable<Optional<DogPayload>> getRandomUUID() {
    return Observable.create(subscriber -> {
      logger.debug("starting to generate data");
      subscriber.onNext(Optional.of(new DogPayload(UUID.randomUUID().toString(), "ralph")));
      subscriber.onCompleted();
    });
  }
}
