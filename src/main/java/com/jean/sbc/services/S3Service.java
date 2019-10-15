package com.jean.sbc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.jean.sbc.services.exception.FileException;

@Service
public class S3Service {

	private static final Logger log = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartFile) {
		try {
			String fileName = multipartFile.getOriginalFilename();
			InputStream in = multipartFile.getInputStream();
			String contentType = multipartFile.getContentType();
			return this.uploadFile(in, fileName, contentType);
		} catch (IOException e) {
			throw new FileException("IO error: " + e.getMessage());
		}

	}

	public URI uploadFile(InputStream in, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			log.info("Starting upload");
			s3Client.putObject(this.bucketName, fileName, in, meta);
			log.info("Uploading complete");

			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Error converting URL to URI");
		}
	}
}
