package com.blog.api.service.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blog.api.service.FileUploadService;

@Service
public class FileServiceImpl implements FileUploadService{

	@Override
	public String uploadImage(String path, MultipartFile multipartfile) throws IOException {
		//File Name
		String fileName=multipartfile.getOriginalFilename();
		String randomId=UUID.randomUUID().toString();
	    String newFileName=randomId +"."+ fileName.split("\\.")[1];
		
		//Full Path Nikalege
		String filePath=path+File.separator+newFileName;
		
		//create folder if not created
		File file=new File(path);
		if(!file.exists()) {
			file.mkdir();
		}
		
		//file copy
		Files.copy(multipartfile.getInputStream(),Paths.get(filePath));
		
		return newFileName;
	}

	@Override
	public InputStream getResources(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+File.separator+fileName;
		InputStream io=new FileInputStream(fullPath);
		return io;
	}

}
