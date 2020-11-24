package com.banistmo.auth.util.exportfile;

import java.util.ArrayList;
import java.util.List;

import com.banistmo.auth.jpa.layout.LayoutDataTransaction;

public class ExportFactory {

	@SuppressWarnings("unchecked")
	public static Export getlayout(List<?> list, String filepath) throws Exception {
		return new DataTransaction((ArrayList<LayoutDataTransaction>) list, filepath);
	}
}
