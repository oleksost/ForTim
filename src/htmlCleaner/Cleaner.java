package htmlCleaner;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;

public class Cleaner {

	static ArrayList<String> listFiles(String path) {
		ArrayList<String> fileNames = new ArrayList<String>();
		File dir = new File(path);
		File[] files = dir.listFiles();
		//System.out.println(files.length);

		for (int loop = 0; loop < files.length; loop++) {
			/*if (files[loop].isDirectory()) {
				listFiles(files[loop].getAbsolutePath(), fileNames);
			} else*/
				fileNames.add(files[loop].getAbsolutePath());
		}
		// Collections.shuffle(fileNames);
		return fileNames;
	}
	
	 static String readFileAsString(String filePath) throws IOException {
		File file = new File(filePath);
		byte[] buffer = new byte[(int) file.length()];
		BufferedInputStream f = null;
		try {
			f = new BufferedInputStream(new FileInputStream(filePath));
			f.read(buffer);
		} finally {
			if (f != null)
				try {
					f.close();
				} catch (IOException ignored) {
				}
		}
		return new String(buffer);
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		String path = "/Users/Alexey/git/MaschinelleSprachverarbeitung/Assignment3/mails_unlabaled_test";
		
		ArrayList<String> dataFiles = Cleaner.listFiles(path);
		
		for (int looper = 0; looper < dataFiles.size(); looper++) {
			
		String content=Cleaner.readFileAsString(dataFiles.get(looper));
		
		
		//clean here
		
		String cleanedFile = Jsoup.parse(content).text();
		
		//save to as a new file
		String filename = "file"+looper;

		File file = new File(filename+".txt");
		FileWriter f = new FileWriter(file);
		f.write(cleanedFile);
		f.flush();
		f.close();
		
		}
	}

}
