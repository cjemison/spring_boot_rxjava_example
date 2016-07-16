package com.cjemison.rxjava.controller.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by cjemison on 7/16/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DogPayload {
  private final String dogId;
  private final String name;

  @JsonCreator
  public DogPayload(final String dogId, String name) {
    this.dogId = dogId;
    this.name = name;
  }

  public String getDogId() {
    return dogId;
  }

  public String getName() {
    return name;
  }
}
