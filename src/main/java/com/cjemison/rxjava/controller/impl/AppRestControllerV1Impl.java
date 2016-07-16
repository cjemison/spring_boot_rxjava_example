package com.cjemison.rxjava.controller.impl;

import com.google.common.eventbus.EventBus;

import com.cjemison.rxjava.controller.AppRestController;
import com.cjemison.rxjava.controller.domain.DogPayload;
import com.cjemison.rxjava.controller.domain.HttpRequestContext;
import com.cjemison.rxjava.controller.service.RequestContextBuilder;
import com.cjemison.rxjava.service.domain.AbstractEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cjemison on 7/16/16.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppRestControllerV1Impl implements AppRestController {
  private static final Logger logger = LoggerFactory.getLogger(AppRestControllerV1Impl.class);
  private final RequestContextBuilder service;
  private final EventBus eventBus;

  @Autowired
  public AppRestControllerV1Impl(final RequestContextBuilder service, final EventBus eventBus) {
    Assert.notNull(service);
    Assert.notNull(eventBus);
    this.eventBus = eventBus;
    this.service = service;
  }

  @Override
  @RequestMapping(value = "/**", produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
  public DeferredResult<ResponseEntity<?>> processRequestEvent(final HttpServletRequest request)
        throws Exception {
    Assert.notNull(request);
    return processEvent(request);
  }

  private DeferredResult<ResponseEntity<?>> processEvent(HttpServletRequest request) throws
        Exception {
    HttpRequestContext requestContext = service.buildHttpRequestContext(request);
    logger.trace("HttpRequestContext: {}", requestContext);
    DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>();
    AbstractEvent<HttpRequestContext, DogPayload> event = new AbstractEvent<>(requestContext);
    eventBus.post(event);
    if (event.getEventException() == null && event.getResponse() != null) {
      event.getResponse().subscribe(c -> {
        if (c.isPresent()) {
          deferredResult.setResult(ResponseEntity.ok(c.get()));
        }
      });
    } else if (event.getEventException() != null) {
      throw event.getEventException().getException();
    } else {
      Map<String, String> map = new HashMap<>();
      map.put("URI", request.getRequestURI());
      map.put("message", "Bad Request");
      deferredResult.setErrorResult(ResponseEntity.badRequest().body(map));
    }
    return deferredResult;
  }

}
