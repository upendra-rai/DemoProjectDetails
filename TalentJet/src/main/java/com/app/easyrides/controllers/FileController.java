package com.app.easyrides.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
	
	@GetMapping(value = "/image")
	public @ResponseBody byte[] getImage() throws IOException {
	    InputStream in =new FileInputStream(new File("C:\\Users\\Lucky RaI\\development\\TalentJet-WS\\TalentJet\\src\\main\\resources\\static\\image\\re.jpg"));
	    		//getClass()
	      //.getResourceAsStream("C:\\Users\\Lucky RaI\\development\\TalentJet-WS\\TalentJet\\src\\main\\resources\\static\\image\\re.jpg");
	    return IOUtils.toByteArray(in);
	}

}
