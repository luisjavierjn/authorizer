package com.banistmo.auth.util.constants;

public enum CVSParameters {
	CVS_TEMP_PATH("CVS_TEMP_PATH", 45), CVS_TEMP_FILE("CVS_TEMP_FILE", 46);
	private String paramName;
	private int code;

	private CVSParameters(String paramName, int code) {
		this.paramName = paramName;
		this.code = code;
	}

	public String getParamName() {
		return paramName;
	}

	public int getCode() {
		return code;
	}

}
