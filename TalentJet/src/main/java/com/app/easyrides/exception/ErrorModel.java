package com.app.easyrides.exception;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(NON_EMPTY)
@Getter
@Setter
@ToString
public class ErrorModel {
  private String code;
  private String message;
  private Object[] params;
}
