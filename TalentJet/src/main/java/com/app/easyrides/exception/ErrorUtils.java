package com.app.easyrides.exception;

import static com.app.easyrides.exception.ErrorCode.VALIDATION_ERROR;
import static com.app.easyrides.exception.ErrorCode.values;
import static java.util.Objects.nonNull;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.uncapitalize;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class ErrorUtils {

  private static final String MESSAGE_PARAM_TOKEN = "\\(::\\)";

  private static final String PARAM_TOKEN = "\\[::\\]";

  public static List<ErrorModel> prepareErrorResponse(BindingResult result) {
    List<ErrorModel> errorMessages = new ArrayList<>();
    for (ObjectError error : result.getAllErrors()) {
      errorMessages.add(prepareErrorMessage(error));
    }
    return errorMessages;
  }

  public static ApiError prepareErrorResponse(ServiceException exception) {
    ApiError apiError = new ApiError();
    if (isNotEmpty(exception.getErrors())) {
      Error error = exception.getErrors().get(0);
      ErrorModel errorModel = new ErrorModel();
      errorModel.setCode(error.getCode().getCode());
      errorModel
          .setMessage(MessageFormat.format(error.getCode().getMessage(), error.getParameters()));
      errorModel.setParams(error.getParameters());
      apiError.setStatus(error.getCode().getHttpStatus());
      apiError.setError(errorModel);
    }
    return apiError;
  }

  public static ApiError prepareErrorResponse(ErrorCode errorCode, Object... params) {
    ApiError apiError = new ApiError();
    ErrorModel error = new ErrorModel();
    error.setCode(errorCode.getCode());
    error.setMessage(MessageFormat.format(errorCode.getMessage(), params));
    error.setParams(params);
    apiError.setErrors(List.of(error));
    apiError.setStatus(errorCode.getHttpStatus());
    return apiError;
  }

  public static List<ErrorModel> prepareErrorResponse(Object[] enumValues, Reference fieldReference,
      Object value) {
    String fieldName = nonNull(fieldReference) ? fieldReference.getFieldName() : "field";
    String formName =
        nonNull(fieldReference) ? uncapitalize(fieldReference.getFrom().getClass().getSimpleName())
            : "request";
    ErrorModel errorModel = new ErrorModel();
    errorModel.setCode(String.format("NotValid.%s.%s", formName, fieldName));
    errorModel.setParams(enumValues);
    errorModel.setMessage(String.format("%s value : %s is not valid. Allowed values are : %s",
        Utils.humanize(fieldName), value, List.of(enumValues)));
    return List.of(errorModel);
  }

  private static ErrorModel prepareErrorMessage(ObjectError error) {
    ErrorModel errorModel = new ErrorModel();
    if (Objects.nonNull(error.getDefaultMessage())) {
      String[] tokens = error.getDefaultMessage().split(MESSAGE_PARAM_TOKEN);
      ErrorCode errorCode = getErrorCodeByCode(tokens[0]);
      errorModel.setCode(errorCode.getCode());
      if (tokens.length > 1) {
        errorModel.setParams(getParams(tokens[1]));
        errorModel.setMessage(MessageFormat.format(errorCode.getMessage(), errorModel.getParams()));
      } else {
        errorModel.setMessage(errorCode.getMessage());
      }
    }
    return errorModel;

  }

  private static Object[] getParams(String paramString) {
    Object[] objectParams = null;
    if (isNotBlank(paramString)) {
      String[] params = paramString.split(PARAM_TOKEN);
      int i = 0;
      objectParams = new Object[params.length];
      for (String param : params) {
        objectParams[i++] = param;
      }
    }
    return objectParams;
  }

  private static ErrorCode getErrorCodeByCode(String code) {
    ErrorCode errorCode = VALIDATION_ERROR;
    for (ErrorCode err : values()) {
      if (err.getCode().equals(code)) {
        errorCode = err;
        break;
      }
    }
    return errorCode;
  }

}
