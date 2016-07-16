package com.cjemison.rxjava.controller.domain;

import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by cjemison on 7/16/16.
 */
public class HttpRequestContext {
  private final String url;
  private final String uri;
  private final Map<String, Enumeration<String>> headers;
  private final Map<String, String> parameters;
  private final Optional<String> requestBody;
  private final String method;


  protected HttpRequestContext(final String url,
                               final String uri,
                               final Map<String, Enumeration<String>> headers,
                               final Map<String, String> parameters,
                               final Optional<String> requestBody,
                               final String method) {
    Assert.notNull(uri);
    Assert.notNull(url);
    Assert.notNull(headers);
    Assert.notNull(parameters);
    Assert.notNull(requestBody);

    this.url = url;
    this.uri = uri;
    this.headers = headers;
    this.parameters = parameters;
    this.requestBody = requestBody;
    this.method = method;
  }

  public String getUrl() {
    return url;
  }

  public String getUri() {
    return uri;
  }

  public Map<String, Enumeration<String>> getHeaders() {
    return headers;
  }

  public Map<String, String> getParameters() {
    return parameters;
  }

  public Optional<String> getRequestBody() {
    return requestBody;
  }

  public String getMethod() {
    return method;
  }

  public static class Builder {
    private String url;
    private String uri;
    private Map<String, Enumeration<String>> headers;
    private Map<String, String> parameters;
    private Optional<String> requestBody = Optional.empty();
    private String method = HttpMethod.GET.toString();

    public Builder() {
      headers = new HashMap<>();
      parameters = new HashMap<>();
    }

    public Builder url(final String url) {
      Assert.notNull(url);
      this.url = url;
      return this;
    }

    public Builder uri(final String uri) {
      Assert.notNull(uri);
      this.uri = uri;
      return this;
    }

    public Builder headers(final Map<String, Enumeration<String>> headers) {
      Assert.notNull(headers);
      this.headers.putAll(headers);
      return this;
    }

    public Builder header(final String key, Enumeration<String> enumeration) {
      Assert.notNull(key);
      Assert.notNull(enumeration);
      headers.put(key, enumeration);
      return this;
    }

    public Builder parameters(final Map<String, String> headers) {
      Assert.notNull(headers);
      this.parameters.putAll(headers);
      return this;
    }

    public Builder parameter(final String key, final String value) {
      Assert.notNull(key);
      Assert.notNull(value);
      this.parameters.put(key, value);
      return this;
    }

    public Builder body(final String body) {
      this.requestBody = Optional.of(body);
      return this;
    }

    public Builder method(final String method) {
      Assert.notNull(method);
      this.method = method;
      return this;
    }

    public HttpRequestContext build() {
      return new HttpRequestContext(url,
            uri,
            Collections.unmodifiableMap(headers),
            Collections.unmodifiableMap(parameters),
            requestBody, method);
    }
  }
}
