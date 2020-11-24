package com.banistmo.auth.web.rest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.banistmo.auth.jpa.dao.IFilesProcessor;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/files")
public class FilesController {

	private static final String INTERNAL_FILE = "doc-tutorial-c++.pdf";
	private static final String EXTERNAL_FILE_PATH = "E:/IBM/WebSphere 8.5/AppServer/profiles/AppSrv01/installedApps/tcs-PCNode06Cell/Authorizer.ear/Authorizer.war/downloads/doc-tutorial-c++.pdf";
	@Autowired
	@Qualifier("processor")
	private IFilesProcessor processor;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(
			MultipartHttpServletRequest request) throws Exception {
		Iterator<String> itrator = request.getFileNames();
		MultipartFile multiFile = request.getFile(itrator.next());
		try {
			String s = new String(multiFile.getBytes());
			InputStream is = new ByteArrayInputStream(s.getBytes());
			boolean mensaje = processor.processIncomingFile(is);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while loading the file");
		}
		return toJson("File Uploaded successfully.");
	}

	public String toJson(Object data) {
		ObjectMapper mapper = new ObjectMapper();
		StringBuilder builder = new StringBuilder();
		try {
			builder.append(mapper.writeValueAsString(data));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder.toString();
	}

	@RequestMapping(value = "/download/{type}", method = RequestMethod.POST)
	public void handleFileDownload(HttpServletResponse response,
			@PathVariable("type") String type, @RequestParam String file_path)
			throws Exception {

		File file = null;

		if (type.equalsIgnoreCase("internal")) {
			ClassLoader classloader = Thread.currentThread()
					.getContextClassLoader();
			file = new File(classloader.getResource("downloads/" + file_path)
					.getFile());
		} else {
			file = new File(file_path);
		}

		if (!file.exists()) {
			String errorMessage = "Sorry. The file you are looking for does not exist";
			System.out.println(errorMessage);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
			outputStream.close();
			return;
		}

		String mimeType = URLConnection
				.guessContentTypeFromName(file.getName());
		if (mimeType == null) {
			System.out.println("mimetype is not detectable, will take default");
			mimeType = "application/octet-stream";
		}

		System.out.println("mimetype : " + mimeType);
		response.setContentType(mimeType);

		/*
		 * "Content-Disposition : inline" will show viewable types [like
		 * images/text/pdf/anything viewable by browser] right on browser while
		 * others(zip e.g) will be directly downloaded [may provide save as
		 * popup, based on your browser setting.]
		 */
		// response.setHeader("Content-Disposition",
		// String.format("inline; filename=\"" + file.getName() +"\""));

		/*
		 * "Content-Disposition : attachment" will be directly download, may
		 * provide save as popup, based on your browser setting
		 */
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"%s\"", file.getName()));

		response.setContentLength((int) file.length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(
				file));

		// Copy bytes from source to destination(outputstream in this example),
		// closes both streams.
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

}
