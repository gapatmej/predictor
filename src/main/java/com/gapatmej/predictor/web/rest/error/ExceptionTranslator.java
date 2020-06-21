package com.gapatmej.predictor.web.rest.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.DefaultProblem;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.Status;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.violations.ConstraintViolationProblem;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

public class ExceptionTranslator implements ProblemHandling {


  @Override
  public ResponseEntity<Problem> process(@Nullable ResponseEntity<Problem> entity, NativeWebRequest request) {
    if (entity == null) {
      return entity;
    }
    Problem problem = entity.getBody();
    if (!(problem instanceof ConstraintViolationProblem || problem instanceof DefaultProblem)) {
      return entity;
    }
    ProblemBuilder builder = Problem.builder()
      .withType(Problem.DEFAULT_TYPE.equals(problem.getType()) ? URI.create("/problem-with-message") : problem.getType())
      .withStatus(problem.getStatus())
      .withTitle(problem.getTitle())
      .with("path", request.getNativeRequest(HttpServletRequest.class).getRequestURI());

    if (problem instanceof ConstraintViolationProblem) {
      builder
        .with("violations", ((ConstraintViolationProblem) problem).getViolations())
        .with("message", "error.validation");
    } else {
      builder
        .withCause(((DefaultProblem) problem).getCause())
        .withDetail(problem.getDetail())
        .withInstance(problem.getInstance());
      problem.getParameters().forEach(builder::with);
      if (!problem.getParameters().containsKey("message") && problem.getStatus() != null) {
        builder.with("message", "error.http." + problem.getStatus().getStatusCode());
      }
    }
    return new ResponseEntity<>(builder.build(), entity.getHeaders(), entity.getStatusCode());
  }


  @ExceptionHandler
  public ResponseEntity<Problem> handleBadRequestAlertException(BadRequestAlertException ex, NativeWebRequest request) {
    return create(ex, request, HeaderUtil.createFailureAlert(ex.getEntityName(), ex.getErrorKey(), ex.getMessage()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Problem> handleException(Exception ex, NativeWebRequest request) {
    Problem problem = Problem.builder().withStatus(Status.INTERNAL_SERVER_ERROR)
      .with("message", ex.getMessage()).withInstance(URI.create("/errorInterno")).build();
    return create(ex, problem, request);
  }


}
