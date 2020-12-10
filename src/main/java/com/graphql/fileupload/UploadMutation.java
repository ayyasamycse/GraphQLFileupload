package com.graphql.fileupload;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphql.context.CustomGraphQLContext;
import com.graphql.entity.ResponseEntity;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UploadMutation implements GraphQLMutationResolver{
	static Logger logger = Logger.getLogger(UploadMutation.class.getName());
	@Autowired
	private UploadService uploadService;
	
	public ResponseEntity uploadFile(String fileDescription, DataFetchingEnvironment environment){
		String requestId = UUID.randomUUID().toString(); 
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"]  uploadFile in UploadMutation starts");
		}
		CustomGraphQLContext customContext = environment.getContext();
		if (logger.isInfoEnabled()) {
			logger.info("[Request Id : "+requestId+"] uploadFile in UploadMutation ends");
		}
		ResponseEntity entity = uploadService.uploadFile(customContext, requestId);
		return entity;
		
	}
	
	

}
