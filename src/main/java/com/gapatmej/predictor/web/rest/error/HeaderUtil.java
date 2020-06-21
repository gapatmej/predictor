package com.gapatmej.predictor.web.rest.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public final class HeaderUtil {

  private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

  private static final String APPLICATION_NAME = "predictorWebApp";

  private HeaderUtil() {
  }


  public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
    log.error("Entity processing failed, {}", defaultMessage);
    HttpHeaders headers = new HttpHeaders();
    headers.add("X-" + APPLICATION_NAME + "-error", "error." + errorKey);
    headers.add("X-" + APPLICATION_NAME + "-params", entityName);
    return headers;
  }
}
