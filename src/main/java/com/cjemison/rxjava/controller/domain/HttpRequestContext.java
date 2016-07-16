package com.cjemison.rxjava.controller.domain;

import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cjemison on 7/16/16.
 */
public class HttpRequestContext {
  private final HttpServletRequest request;
  private final Map<String, Object> payloadMap;
  private final Map<String, Object> pathVariablesMap;

  public HttpRequestContext(final HttpServletRequest request) {
    Assert.notNull(request);
    this.request = request;
    this.payloadMap = Collections.EMPTY_MAP;
    this.pathVariablesMap = Collections.EMPTY_MAP;
  }

  public HttpRequestContext(final HttpServletRequest request,
                            final Map<String, Object> pathVariablesMap) {
    Assert.notNull(request);
    Assert.notNull(pathVariablesMap);
    this.request = request;
    this.payloadMap = Collections.EMPTY_MAP;
    this.pathVariablesMap = pathVariablesMap;
  }

  public HttpRequestContext(final HttpServletRequest request,
                            final Map<String, Object> payloadMap,
                            final Map<String, Object> pathVariablesMap) {
    Assert.notNull(request);
    Assert.notNull(payloadMap);
    Assert.notNull(pathVariablesMap);
    this.request = request;
    this.payloadMap = payloadMap;
    this.pathVariablesMap = pathVariablesMap;
  }

  public HttpServletRequest getRequest() {
    return request;
  }

  public Map<String, Object> getPayload() {
    return payloadMap;
  }

  @SuppressWarnings("unchecked")
  public <T> Optional<T> getPayload(final String key, final Class<T> klass) {
    return Optional.of((T) payloadMap.get(key));
  }

  @SuppressWarnings("unchecked")
  public <T> Optional<T> getPathVariable(final String key, final Class<T> klass) {
    return Optional.of((T) payloadMap.get(key));
  }

  @SuppressWarnings("unchecked")
  private <T> Optional<T> getMapObject(String key,
                                       Class<T> klass,
                                       Map<String, Object> map) {
    Assert.notNull(key);
    Assert.notNull(klass);
    return Optional.of((T) map.get(key));
  }
}
