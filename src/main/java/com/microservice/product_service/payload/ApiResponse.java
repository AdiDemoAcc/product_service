package com.microservice.product_service.payload;

public class ApiResponse {
	
	private Object respObject;
	
	private String errorCode;
	
	private String errorMsg;
	
	private boolean flag;

	public ApiResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ApiResponse(Object respObject, String errorCode, String errorMsg, boolean flag) {
		super();
		this.respObject = respObject;
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.flag = flag;
	}

	public Object getRespObject() {
		return respObject;
	}

	public void setRespObject(Object respObject) {
		this.respObject = respObject;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "ApiResponse [respObject=" + respObject + ", errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", flag=" + flag + "]";
	}
	
	

}
