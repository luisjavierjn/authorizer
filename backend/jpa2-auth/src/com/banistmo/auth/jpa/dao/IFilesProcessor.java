package com.banistmo.auth.jpa.dao;

import java.io.InputStream;

public interface IFilesProcessor {

	public boolean processIncomingFile(InputStream dataIn);

}
