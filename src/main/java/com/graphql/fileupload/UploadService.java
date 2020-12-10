package com.graphql.fileupload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql.context.CustomGraphQLContext;
import com.graphql.entity.ResponseEntity;

@Service
public class UploadService {
	@Autowired
	private ServletContext context;
	
	@Autowired
	UploadRepository uploadRepository;
	
	 public UploadService(final UploadRepository uploadRepository) {
	        this.uploadRepository = uploadRepository ;
	 }
	static Logger logger = Logger.getLogger(UploadService.class.getName());
	
	
	public ResponseEntity uploadFile(CustomGraphQLContext customContext, String requestId) {
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] uploadFile in service class starts");
		}
		ResponseEntity responseEntity = new ResponseEntity(); 
		String realPath = context.getRealPath("/");
		String filePath = realPath+"/uploads/";
		System.out.println("filePath : "+filePath);
		File uploadsDir = new File(filePath);

		if(null != customContext) {
			for(int i=0;i<customContext.getFileParts().size();i++) {
				if(!uploadsDir.exists()) {
					uploadsDir.mkdir();
				}
				Part part = customContext.getFileParts().get(i);
				InputStream inputStream = null;
				byte[] bytes = null;
				try {
					inputStream = part.getInputStream();
					bytes = this.toByteArrayUsingJava(inputStream, requestId); 
				} catch (IOException e) {
					e.printStackTrace();
				}
				// to create a file and store it in the folder.
				this.addFileTOFolder(uploadsDir +"/"+part.getSubmittedFileName(), bytes, requestId);
				responseEntity.setMessage("File Uploaaded successfully!!!");
			}
		}
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] uploadFile in service class ends");
		}
		return responseEntity;
	}
	
	public void addFileTOFolder(String fileName, byte[] byteValue, String requestId) {
		if (logger.isDebugEnabled()) {
			logger.debug("[Request Id : " + requestId + "] [addFileTOFolder] : Adding attachments inside the folder");
		}
		FileOutputStream newImage;
		try {
			newImage = new FileOutputStream(fileName);
			newImage.write(byteValue);
			newImage.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public byte[] toByteArrayUsingJava(InputStream input, String requestId)
			throws IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("[Request Id : " + requestId + "] [toByteArrayUsingJava] ");
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = input.read();
		while (reads != -1) {
			baos.write(reads);
			reads = input.read();
		}
		return baos.toByteArray();
	}

}
