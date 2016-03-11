package DataInput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;



public class DatasetReader {
	public String DATASET_PATH;
	private List<EmailObject> emails=new LinkedList<EmailObject>();
	private String HAM="Ham/";
	private String SPAM="Spam/";
	private long HAMS_NUMBER=0;
	private long SPAMS_NUMBER=0;
	
	
	public static void main(String[] args){
	DatasetReader d=new DatasetReader();
	d.readEmails();
	}
	
	
	
	
	
	
	
public DatasetReader(){
	DATASET_PATH="datasets/";
}
public DatasetReader(String d){
	if(!d.isEmpty())
		DATASET_PATH=d;
	else
		DATASET_PATH="datasets/";
}

public void readEmails(){
	System.out.println("Loading the training set..");
	readHams();
	readSpams();
	System.out.println(this.SPAMS_NUMBER+" spam emails loaded");
	System.out.println(this.HAMS_NUMBER+" ham emails loaded");
	
}
private void readSpams() {
	File ham_folder=new File(DATASET_PATH+SPAM);
	List<File> ham_files=Arrays.asList(ham_folder.listFiles());
	for(File f:ham_files){
		TarArchiveInputStream tar_stream=null;
	try {
		GZIPInputStream gzip_stream=new GZIPInputStream(new FileInputStream(f));
		tar_stream=new TarArchiveInputStream(gzip_stream);
		TarArchiveEntry entry;
		while((entry=tar_stream.getNextTarEntry())!=null){
			
			if(!entry.isDirectory()){
				BufferedReader br=new BufferedReader(new InputStreamReader(tar_stream));
				try{
				String content=fetchContent(br,TYPE.SPAM);
				emails.add(new EmailObject(content, TYPE.SPAM));
				HAMS_NUMBER++;
				}
				catch(IOException e1){
					
				}
			}
				
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		try {
			tar_stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
	
}







private void readHams() {
	File ham_folder=new File(DATASET_PATH+HAM);
	List<File> ham_files=Arrays.asList(ham_folder.listFiles());
	for(File f:ham_files){
		TarArchiveInputStream tar_stream=null;
	try {
		GZIPInputStream gzip_stream=new GZIPInputStream(new FileInputStream(f));
		tar_stream=new TarArchiveInputStream(gzip_stream);
		TarArchiveEntry entry;
		while((entry=tar_stream.getNextTarEntry())!=null){
			
			if(!entry.isDirectory()){
				BufferedReader br=new BufferedReader(new InputStreamReader(tar_stream));
				try{
				String content=fetchContent(br,TYPE.HAM);
				emails.add(new EmailObject(content, TYPE.HAM));
				SPAMS_NUMBER++;
				}
				catch(IOException e1){
					
				}
			}
				
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		try {
			tar_stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
}
private String fetchContent(BufferedReader br,TYPE t) throws IOException {
	
	StringBuffer s = null;
	
		if(t==TYPE.HAM)
		while(!br.readLine().startsWith("X-FileName:"));
		else if(t==TYPE.SPAM)
			while(!br.readLine().startsWith("X-MimeOLE:"));
		String g;
		s=new StringBuffer();
		while((g=br.readLine()) != null){
			s.append("\r\n"+g);
		}
	return (s.toString());
}

}
