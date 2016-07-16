package com.cjemison.rxjava.service;

import com.cjemison.rxjava.controller.domain.DogPayload;

import java.util.Optional;

import rx.Observable;

/**
 * Created by cjemison on 7/16/16.
 */
public interface DogService {

  Observable<Optional<DogPayload>> getRandomUUID();
}
