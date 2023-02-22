package com.app.easyrides.exception;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(NON_EMPTY)
@Getter
@Setter
public class ApiError {

  private String time;
  private HttpStatus status;
  private String message;
  private List<Object> errors;

  public ApiError() {
    this.time = LocalDateTime.now().toString();
  }

  @SuppressWarnings("unchecked")
  public ApiError(final HttpStatus status, final String message, final Object error) {
    this.status = status;
    this.message = message;
    if (error instanceof List) {
      errors = (List<Object>) error;
    } else {
      errors = Arrays.asList(error);
    }
    this.time = LocalDateTime.now().toString();
  }

  public void setError(final Object error) {
    errors = Arrays.asList(error);
  }

}
