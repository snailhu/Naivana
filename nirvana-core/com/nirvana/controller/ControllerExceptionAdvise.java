package com.nirvana.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nirvana.erp.exception.WebServiceException;
import com.nirvana.exception.BindNumExitedException;
import com.nirvana.exception.BindNumUnexistException;
import com.nirvana.exception.CloseNotNullAgentAreaException;
import com.nirvana.exception.DataIntegrityException;
import com.nirvana.exception.InventoryException;
import com.nirvana.exception.RecordAcessDeniedException;
import com.nirvana.exception.RecordExistedException;
import com.nirvana.exception.RecordNotFoundException;
import com.nirvana.exception.RelationNotMatchingException;
import com.nirvana.exception.ResourceAccessException;
import com.nirvana.exception.UnexpectedStateException;
import com.nirvana.exception.UserExistedException;
import com.nirvana.exception.UserNotFoundException;
import com.nirvana.exception.UserNotLoginException;
import com.nirvana.exception.ValidateTooFrequentlyException;
import com.nirvana.exception.WrongCaptchaException;
import com.nirvana.pojo4json.message.ErrorCode;
import com.nirvana.pojo4json.message.JsonResponseBean;

/**
 * “Ï≥£¥¶¿Ì
 * 
 */
@ControllerAdvice
@RequestMapping(produces = "application/json;charset=utf-8")
public class ControllerExceptionAdvise {

	@ExceptionHandler(RecordNotFoundException.class)
	@ResponseBody
	public String handlerRecordNotFoundException(RecordNotFoundException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.DATA_NOT_FOUND, ex.getMessage()).toJson();
	}

	@ExceptionHandler(RelationNotMatchingException.class)
	@ResponseBody
	public String handlerRelationNotMatchingException(RelationNotMatchingException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ILLEGAL_ARGUMENT, ex.getMessage()).toJson();
	}

	@ExceptionHandler(UserNotLoginException.class)
	@ResponseBody
	public String handlerUserNotLoginException(UserNotLoginException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.NOT_LOGIN, ex.getMessage()).toJson();
	}

	@ExceptionHandler(RecordAcessDeniedException.class)
	@ResponseBody
	public String handlerRecordAcessDeniedException(RecordAcessDeniedException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ACCESS_DENIED, ex.getMessage()).toJson();
	}

	@ExceptionHandler(IOException.class)
	@ResponseBody
	public String handlerIOException(IOException ex) {
		ex.printStackTrace();
		return JsonResponseBean.getErrorResponse(ErrorCode.SERVER_ERROR, ex.getMessage()).toJson();
	}

	@ExceptionHandler(UnexpectedStateException.class)
	@ResponseBody
	public String handlerUnexpectedStateException(UnexpectedStateException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ERROR_STATE, ex.getMessage()).toJson();
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseBody
	public String handlerArgNonCorrectException(IllegalArgumentException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ILLEGAL_ARGUMENT, ex.getMessage()).toJson();
	}

	@ExceptionHandler(UserExistedException.class)
	@ResponseBody
	public String handlerUserExistedException(UserExistedException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ALLREADY_EXISTS, ex.getMessage()).toJson();
	}

	@ExceptionHandler(UserNotFoundException.class)
	@ResponseBody
	public String handlerUserNotFoundException(UserNotFoundException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.NOT_FOUND, ex.getMessage()).toJson();
	}

	@ExceptionHandler(CloseNotNullAgentAreaException.class)
	@ResponseBody
	public String handlerCloseNotNullAgentAreaException(CloseNotNullAgentAreaException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.NOT_EMPTY, ex.getMessage()).toJson();
	}

	@ExceptionHandler(BindNumExitedException.class)
	@ResponseBody
	public String handlerBindNumExitedException(BindNumExitedException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ALLREADY_EXISTS, ex.getMessage()).toJson();
	}

	@ExceptionHandler(BindNumUnexistException.class)
	@ResponseBody
	public String handlerBindNumUnexistException(BindNumUnexistException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.NOT_FOUND, ex.getMessage()).toJson();
	}

	@ExceptionHandler(WrongCaptchaException.class)
	@ResponseBody
	public String handlerWrongCaptchaException(WrongCaptchaException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ILLEGAL_ARGUMENT, ex.getMessage()).toJson();
	}

	@ExceptionHandler(ResourceAccessException.class)
	@ResponseBody
	public String handlerResourceAccessException(ResourceAccessException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ACCESS_DENIED, ex.getMessage()).toJson();
	}

	@ExceptionHandler(DataIntegrityException.class)
	@ResponseBody
	public String handlerDataIntegrityException(DataIntegrityException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ERROR_STATE, ex.getMessage()).toJson();
	}

	@ExceptionHandler(RecordExistedException.class)
	@ResponseBody
	public String handlerRecordExistedException(RecordExistedException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ALLREADY_EXISTS, ex.getMessage()).toJson();
	}

	@ExceptionHandler(ValidateTooFrequentlyException.class)
	@ResponseBody
	public String handlerValidateTooFrequentlyException(ValidateTooFrequentlyException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ERROR_STATE, ex.getMessage()).toJson();
	}

	@ExceptionHandler(InventoryException.class)
	@ResponseBody
	public String handlerInventoryException(InventoryException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.ERROR_STATE, ex.getMessage()).toJson();
	}

	@ExceptionHandler(WebServiceException.class)
	@ResponseBody
	public String handlerWebServiceException(WebServiceException ex) {
		return JsonResponseBean.getErrorResponse(ErrorCode.SERVER_ERROR, ex.getMessage()).toJson();
	}

}
