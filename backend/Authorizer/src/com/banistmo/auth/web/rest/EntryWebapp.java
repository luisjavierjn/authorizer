package com.banistmo.auth.web.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;

import org.springframework.boot.autoconfigure.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Configuration
@ComponentScan({"com.banistmo.auth.web.rest","com.banistmo.auth.jpa.dao.impl"})
@SpringBootApplication(exclude = MessageSourceAutoConfiguration.class)
public class EntryWebapp extends org.springframework.boot.context.web.SpringBootServletInitializer {
	
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } catch (IOException e) {
	    	e.printStackTrace();
	    	System.out.println(e.toString());
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	
	@Override
	public void onStartup(ServletContext servletContext) {
				
		System.out.println(servletContext.getRealPath("/"));
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String env = "";		
		String localFile = "";
		String firstChar = "";
		
    	try {
    		//File sourceFile = new File("c:\\tmp\\env.json");
    		//System.out.println("/data_bdg/WRKDIRR/ADMAUTHDIRR/conf/env.json");
    		//System.out.println(servletContext.getRealPath("/") + "/assets/env.json");
    		firstChar = servletContext.getRealPath("/").substring(0, 1).toUpperCase();
    		if(abc.indexOf(firstChar) < 0) {
    			localFile = "/data_bdg/WRKDIRR/ADMAUTHDIRR/conf/env.json";
    		} else {
    			localFile = "C:\\data_bdg\\WRKDIRR\\ADMAUTHDIRR\\conf\\env.json";
    		}
    		
        	File sourceFile = new File(localFile);
        	File destFile = new File(servletContext.getRealPath("/") + "/assets/env.json");
			copyFileUsingStream(sourceFile, destFile);
			
			JSONParser parser = new JSONParser();	
			Object obj = parser.parse(new FileReader(localFile));
			JSONObject jsonObject = (JSONObject) obj;
			env = (String) jsonObject.get("env");			
		} catch (IOException e) {
			e.printStackTrace();			
			System.out.println(e.toString());
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println(e.toString());
		}
    	
    	if(env.equals("localhost")) {
        	try {
        		//File sourceFile = new File("C:\\tmp\\localhost.json");
        		if(abc.indexOf(firstChar) < 0) {
        			localFile = "/data_bdg/WRKDIRR/ADMAUTHDIRR/conf/localhost.json";
        		} else {
        			localFile = "C:\\data_bdg\\WRKDIRR\\ADMAUTHDIRR\\conf\\localhost.json";
        		}
        		
        		File sourceFile = new File(localFile);
            	File destFile = new File(servletContext.getRealPath("/") + "/assets/localhost.json");
    			copyFileUsingStream(sourceFile, destFile);
    		} catch (IOException e) {}    		
    	}
    	
    	if(env.equals("development")) {    	
	    	try {
	    		//File sourceFile = new File("C:\\tmp\\development.json");
	        	File sourceFile = new File("/data_bdg/WRKDIRR/ADMAUTHDIRR/conf/development.json");    		
	        	File destFile = new File(servletContext.getRealPath("/") + "/assets/development.json");
				copyFileUsingStream(sourceFile, destFile);
			} catch (IOException e) {}
    	}
    	
    	if(env.equals("certification")) {    	
	    	try {
	    		//File sourceFile = new File("C:\\tmp\\certification.json");
	        	File sourceFile = new File("/data_bdg/WRKDIRR/ADMAUTHDIRR/conf/certification.json");    		
	        	File destFile = new File(servletContext.getRealPath("/") + "/assets/certification.json");
				copyFileUsingStream(sourceFile, destFile);
			} catch (IOException e) {}
    	}

    	if(env.equals("production")) {
	    	try {
	    		//File sourceFile = new File("C:\\tmp\\production.json");
	        	File sourceFile = new File("/data_bdg/WRKDIRR/ADMAUTHDIRR/conf/production.json");    		
	        	File destFile = new File(servletContext.getRealPath("/") + "/assets/production.json");
				copyFileUsingStream(sourceFile, destFile);
			} catch (IOException e) {}
    	}				
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EntryWebapp.class);
    }    
}
