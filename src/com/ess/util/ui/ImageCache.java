package com.ess.util.ui;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageCache {

	private static final String IMAGE_DIR = "C:\\Users\\Phil\\Downloads\\_games\\WMH_Vassal421\\";
	
	Map<String,Image> map = new HashMap<>();
	
	public synchronized Image getImage(String name){
		if(map.containsKey(name)){
			//System.out.println("Got "+name+" from cache.");
			return map.get(name);
		}
		Image image = null;
		try {
			//System.out.println("Got "+name+" from file.");
			image = ImageIO.read(new File(IMAGE_DIR, name));
			map.put(name, image);
			return image;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

}
