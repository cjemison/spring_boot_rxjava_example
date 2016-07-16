package com.cjemison.rxjava.controller.service;

import com.cjemison.rxjava.controller.domain.HttpRequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cjemison on 7/16/16.
 */
public interface RequestContextBuilder {

  HttpRequestContext buildHttpRequestContext(HttpServletRequest request) throws Exception;
}
