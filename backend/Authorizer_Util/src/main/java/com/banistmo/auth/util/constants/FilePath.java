package com.banistmo.auth.util.constants;

public enum FilePath {
	FINACLE(1, "FILE_PATH_FINACLE", "FINACLE"), NEQUI_HOGAN(3, "FILE_PATH_NEQUI_HOGAN", "HOGAN"), CTA(3,
			"FILE_PATH_CTA", "CTA");
	private int id;
	private String parameterLable;
	private String pathAdm;

	private FilePath(int id, String parameterLable, String pathAdm) {
		this.id = id;
		this.parameterLable = parameterLable;
		this.pathAdm = pathAdm;
	}

	public int getId() {
		return id;
	}

	public String getParameterLable() {
		return parameterLable;
	}

	public String getPathAdm() {
		return pathAdm;
	}

}
