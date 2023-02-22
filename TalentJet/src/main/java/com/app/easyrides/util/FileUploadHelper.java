package com.app.easyrides.util;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	
	public final String UPLOAD_DIR="C:\\Users\\Lucky RaI\\development\\TalentJet-WS\\TalentJet\\src\\main\\resources\\static\\image";
	
	public boolean uploadFile(MultipartFile file) {
		boolean f=false;
		
		try {
			InputStream inputStream=file.getInputStream();
			byte data[]= new byte [inputStream.available()];
			
			//FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+"\\"+File.separator+file.getOriginalFilename(), StandardCopyOption.REPLACE_EXISTING);
			//fos.write(data);
			//fos.flush();
			
			Files.copy(file.getInputStream(),Paths.get(UPLOAD_DIR+"\\"+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			f=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return f;
	}

}
