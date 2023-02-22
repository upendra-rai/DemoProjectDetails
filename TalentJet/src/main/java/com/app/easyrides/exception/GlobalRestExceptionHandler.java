package com.app.easyrides.exception;

import static com.app.easyrides.exception.ErrorCode.SERVER_ERROR;
import static com.app.easyrides.exception.ErrorUtils.prepareErrorResponse;
import static java.util.Objects.nonNull;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNSUPPORTED_MEDIA_TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

  // 400

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    final ApiError apiError = new ApiError(BAD_REQUEST, "Request Validation Failed.",
        prepareErrorResponse(ex.getBindingResult()));
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(final BindException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    final ApiError apiError = new ApiError(BAD_REQUEST, "Request Validation Failed.",
        prepareErrorResponse(ex.getBindingResult()));
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final String error = ex.getValue() + " value for " + ex.getPropertyName()
        + " should be of type " + ex.getRequiredType();

    final ApiError apiError = new ApiError(BAD_REQUEST, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(
      final MissingServletRequestPartException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final String error = ex.getRequestPartName() + " part is missing";
    final ApiError apiError = new ApiError(BAD_REQUEST, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      final MissingServletRequestParameterException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final String error = ex.getParameterName() + " parameter is missing";
    final ApiError apiError = new ApiError(BAD_REQUEST, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    logger.info(ex.getClass().getName());
    ApiError apiError = null;
    if (ex.getCause() instanceof InvalidFormatException) {
      InvalidFormatException ife = (InvalidFormatException) ex.getCause();
      Object[] enumValues = ife.getTargetType().getEnumConstants();
      Reference fieldReference = isNotEmpty(ife.getPath()) ? ife.getPath().get(0) : null;
      if (nonNull(enumValues)) {
        apiError = new ApiError(BAD_REQUEST, "Request Validation Failed.",
            prepareErrorResponse(enumValues, fieldReference, ife.getValue()));
      }
    }
    return new ResponseEntity<>(nonNull(apiError) ? apiError
        : new ApiError(BAD_REQUEST, ex.getLocalizedMessage(),
            ex.getMostSpecificCause().getMessage()),
        new HttpHeaders(), BAD_REQUEST);
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
      final MethodArgumentTypeMismatchException ex, final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

    final ApiError apiError = new ApiError(BAD_REQUEST, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final List<String> errors = new ArrayList<>();
    for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
          + violation.getMessage());
    }

    final ApiError apiError = new ApiError(BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  // 404

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

    final ApiError apiError = new ApiError(NOT_FOUND, ex.getLocalizedMessage(), error);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  // 405

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getMethod());
    builder.append(" method is not supported for this request.");
    if (Objects.nonNull(ex.getSupportedHttpMethods())) {
      builder.append(" Supported methods are ");
      ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
    }

    final ApiError apiError =
        new ApiError(METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  // 415

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      final HttpMediaTypeNotSupportedException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    //
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

    final ApiError apiError = new ApiError(UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(),
        builder.substring(0, builder.length() - 2));
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  // ServiceException

  @ExceptionHandler({ServiceException.class})
  public ResponseEntity<Object> handleAll(final ServiceException se, final WebRequest request) {
    logger.info(se.getClass().getName());
    logger.error("error", se);
    final ApiError apiError = prepareErrorResponse(se);
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  // 500

  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    final ApiError apiError = prepareErrorResponse(SERVER_ERROR, ex.getMessage());
    return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
  }

}
