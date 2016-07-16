package com.cjemison.rxjava.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cjemison on 7/16/16.
 */
public interface AppRestController {
  DeferredResult<ResponseEntity<?>> request(final HttpServletRequest request)
        throws Exception;
}
