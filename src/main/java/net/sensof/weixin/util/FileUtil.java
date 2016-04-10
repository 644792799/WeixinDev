package net.sensof.weixin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import net.sf.json.JSONObject;

public class FileUtil {

	public static <T> void jsonObjectToFile(T jsonObject, String filePath, String fileName){
		try {
			File file = new File(filePath);
			if(file.isDirectory() && !file.exists()){
				file.mkdirs();
			}
			FileOutputStream fs = new FileOutputStream(new File(filePath + File.separator + fileName));
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(jsonObject);
			os.flush(); 
			os.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static <T> T fileToObject(String fileFullPath) {
		T jsonObject = null;
		FileInputStream fs;
		try {
			File file = new File(fileFullPath);
			if(file.exists()){
				fs = new FileInputStream(new File(fileFullPath));
				ObjectInputStream is = new ObjectInputStream(fs);
				jsonObject = (T) is.readObject();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		return jsonObject;
	}
}
