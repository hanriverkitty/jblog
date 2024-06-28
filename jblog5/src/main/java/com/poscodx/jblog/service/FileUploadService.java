package com.poscodx.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	private static String SAVE_PATH = "/jblog-uploads";
	private static String URL_PATH = "/assets/upload-images";

	public String restore(MultipartFile file) {
		String url = null;
		try {
			File uploadDirectory = new File(SAVE_PATH);
			if (!uploadDirectory.exists()) {
				uploadDirectory.mkdirs();
			}
			if (file.isEmpty()) {
				return url;
			}
			String originFilename = file.getOriginalFilename();
			Long fileSize = file.getSize();
			System.out.println("#######" + originFilename);
			System.out.println("#######" + fileSize);

			byte[] data = file.getBytes();
			OutputStream os = new FileOutputStream(SAVE_PATH + "/" + originFilename);
			os.write(data);
			os.close();
			
			url = URL_PATH + "/" + originFilename;
			
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		return url;
	}
}
