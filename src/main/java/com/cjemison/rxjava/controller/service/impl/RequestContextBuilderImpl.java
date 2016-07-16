package com.cjemison.rxjava.controller.service.impl;

import com.cjemison.rxjava.controller.domain.HttpRequestContext;
import com.cjemison.rxjava.controller.service.RequestContextBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cjemison on 7/16/16.
 */
@Service
public class RequestContextBuilderImpl implements RequestContextBuilder {
  private final Logger logger = LoggerFactory.getLogger(RequestContextBuilderImpl.class);

  @Override
  public HttpRequestContext buildHttpRequestContext(final HttpServletRequest request) throws
        Exception {
    HttpRequestContext.Builder builder = new HttpRequestContext.Builder()
          .url(request.getRequestURL().toString())
          .uri(request.getRequestURI());

    Collections.list(request.getHeaderNames()).forEach(c -> builder.header(c, request.getHeaders
          (c)));

    Collections.list(request.getParameterNames()).forEach(c -> builder.parameter(c, request
          .getParameter(c)));
    builder.method(request.getMethod());


    StringBuffer jb = new StringBuffer();
    String line;
    BufferedReader reader = request.getReader();
    while ((line = reader.readLine()) != null)
      jb.append(line);
    builder.body(jb.toString());

    HttpRequestContext context = builder.build();
    logger.trace("HttpRequestContext: {}", context);
    return builder.build();
  }
}
