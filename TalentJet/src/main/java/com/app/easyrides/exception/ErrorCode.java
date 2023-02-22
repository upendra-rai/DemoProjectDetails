package com.app.easyrides.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {

  SERVER_ERROR("error.internal.server", "Internal server error occured. Server Message : {0}",
      INTERNAL_SERVER_ERROR),

  ACCESS_DENIED("error.access.denied", "You are not authorized to access this API.", FORBIDDEN),

  USER_NOT_AUTHENTICATED("error.user.not.authenticated",
      "You have not logged in to Application, please login and try again.", UNAUTHORIZED),

  INVALID_PAGE("error.invalid.page",
      "Provided Page is Invalid : {0}. Valid page should be greater than 0.", BAD_REQUEST),

  INVALID_PAGE_SIZE("error.invalid.page.size",
      "Provided Page Size is Invalid : {0}. Valid Page Size is in range {1} to {2}", BAD_REQUEST),

  UNKNOWN_PREDICATE("error.unknown.predicate", "Provided predicate is not valid for search!",
      INTERNAL_SERVER_ERROR),

  INVALID_DATE_TIME_FORMAT("error.invalid.date.time.format",
      "Provided date time is invalid. Valid format is : {0}", BAD_REQUEST),



  FILE_DATA_FILE_UPLOAD_FORM_EMPTY("NotEmpty.fileUploadRequest.data",
      "File data is required, either provide base64Data or multipart file!", BAD_REQUEST),

  FILE_NAME_FILE_UPLOAD_FORM_EMPTY("NotBlank.fileUploadRequest.fileName", "File Name is required.",
      BAD_REQUEST),

  IMAGE_FILE_UPLOAD_REQUEST_INVALID("NotValid.fileUploadRequest.imageFile",
      "Provided File data is not a valid Image. Valid formats are : {0}", BAD_REQUEST),

  AUDIO_FILE_UPLOAD_REQUEST_INVALID("NotValid.fileUploadRequest.audioFile",
      "Provided File data is not a valid Audio. Valid formats are : {0}", BAD_REQUEST),

  VIDEO_FILE_UPLOAD_REQUEST_INVALID("NotValid.fileUploadRequest.videoFile",
      "Provided File data is not a valid Video. Valid formats are : {0}", BAD_REQUEST),

  IMAGE_FILE_UPLOAD_REQUEST_LARGE("Large.fileUploadRequest.imageFile",
      "Provided Image file is too large. Allowed Image Size is : {0}", BAD_REQUEST),

  AUDIO_FILE_UPLOAD_REQUEST_LARGE("Large.fileUploadRequest.audioFile",
      "Provided Audio file is too large. Allowed Audio Size is : {0}", BAD_REQUEST),

  VIDEO_FILE_UPLOAD_REQUEST_LARGE("Large.fileUploadRequest.videoFile",
      "Provided Video file is too large. Allowed Video Size is : {0}", BAD_REQUEST),

  DOC_FILE_UPLOAD_REQUEST_LARGE("Large.fileUploadRequest.docFile",
      "Provided Document file is too large. Allowed Document Size is : {0}", BAD_REQUEST),

  FILE_TYPE_FILE_UPLOAD_FORM_NULL("NotNull.fileUploadRequest.fileType", "File Type is required.",
      BAD_REQUEST),

  // Client Error Code

  CREATE_CLIENT_DUPLICATE("error.create.Client.duplicate.name",
      "Provided Client Name is already taken, please provide unique value!", CONFLICT),

  DELETE_CLIENT_ERROR("error.client.delete",
      "Client is associated with other entities, hence can't be deleted", BAD_REQUEST),

  CLIENT_NOT_FOUND("error.client.not.found", "No client found by ID or Alias : {0}", NOT_FOUND),

  CLIENT_NAME_CLIENT_REQUEST_EMPTY("NotBlank.clientRequestDto.name", "Client Name is required.",
      BAD_REQUEST),

  CLIENT_NAME_CLIENT_REQUEST_LARGE("Size.clientRequestDto.name",
      "Client Name can be of maximum {0} characters long.", BAD_REQUEST),

  STREET_CLIENT_REQUEST_EMPTY("NotBlank.clientRequestDto.street", "Street is required.",
      BAD_REQUEST),

  STREET_CLIENT_REQUEST_LARGE("Size.clientRequestDto.street",
      "Street can be of maximum {0} characters long.", BAD_REQUEST),

  CITY_CLIENT_REQUEST_EMPTY("NotBlank.clientRequestDto.city", "City is required.", BAD_REQUEST),

  CITY_CLIENT_REQUEST_LARGE("Size.clientRequestDto.city",
      "City can be of maximum {0} characters long.", BAD_REQUEST),

  STATUS_CLIENT_REQUEST_NULL("NotNull.clientRequestDto.status", "Status is required", BAD_REQUEST),

  STATE_CLIENT_REQUEST_EMPTY("NotBlank.clientRequestDto.state", "State is required.", BAD_REQUEST),

  COUNTRY_CLIENT_REQUEST_EMPTY("NotBlank.clientRequestDto.country", "Country is required.",
      BAD_REQUEST),

  ZIPCODE_CLIENT_REQUEST_EMPTY("NotBlank.clientRequestDto.zipcode", "Zipcode is required.",
      BAD_REQUEST),

  ZIPCODE_CLIENT_REQUEST_LARGE("Size.clientRequestDto.zipcode",
      "Zipcode can be of maximum {0} characters long.", BAD_REQUEST),

  TIME_ZONE_CLIENT_REQUEST_EMPTY("NotBlank.clientRequestDto.timeZone", "Time Zone is required.",
      BAD_REQUEST),

  TIME_ZONE_CLIENT_REQUEST_VALID("NotValid.clientRequestDto.timeZone", "Time Zone is Not Correct.",
      BAD_REQUEST),

  TIME_ZONE_CLIENT_REQUEST_LARGE("Size.clientRequestDto.timeZone",
      "Time Zone can be of maximum {0} characters long.", BAD_REQUEST),

  WEBSITE_CLIENT_REQUEST_LARGE("Size.clientRequestDto.website",
      "Website can be of maximum {0} characters long.", BAD_REQUEST),

  ALIAS_CLIENT_REQUEST_LARGE("Size.clientRequestDto.alias",
      "Alias can be of maximum {0} characters long.", BAD_REQUEST),

  REMARK_CLIENT_REQUEST_LARGE("Size.clientRequestDto.remark",
      "Remark can be of maximum {0} characters long.", BAD_REQUEST),

  API_SECRET_CLIENT_REQUEST_LARGE("Size.clientRequestDto.apiSecret",
      "ApiSecret can be of maximum {0} characters long.", BAD_REQUEST),

  COUNTRY_NOT_FOUND("error.country.not.found", "Country not found by code : {0}", NOT_FOUND),

  STATE_NOT_FOUND("error.state.not.found", "State not found by code : {0}", NOT_FOUND),

  // Car Owner
  CAR_OWNER_NOT_FOUND("error.car.owner.not.found", "No CarOwner found by ID or Alias : {0}", NOT_FOUND),
  
  ADDRESS_NOT_FOUND("error.address.not.found", "No Address found by ID or Alias : {0}", NOT_FOUND),
 
  CUSTOMER_NOT_FOUND("error.customer.not.found", "No Cusotmer found by ID or Alias : {0}", NOT_FOUND),
  
  CAR_NOT_FOUND("error.car.not.found", "No Car found by ID or Alias : {0}", NOT_FOUND),

  PAYMENT_NOT_FOUND("error.payment.not.found", "No Payment found by ID or Alias : {0}", NOT_FOUND),

  BOOKING_NOT_FOUND("error.car.not.found", "No Booking found by ID or Alias : {0}", NOT_FOUND),
  
  TOKEN_NOT_INVALID("error.user.token.invalid", "User Token is Invalid : {0}", NOT_FOUND),

  VALIDATION_ERROR("", "", BAD_REQUEST);

  private String code;
  private String message;
  private HttpStatus httpStatus;

  ErrorCode(String code, String message, HttpStatus httpStatus) {
    this.code = code;
    this.message = message;
    this.httpStatus = httpStatus;
  }
}
