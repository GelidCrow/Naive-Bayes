package DataInput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;



public class DatasetReader {
	public String DATASET_PATH;
	private List<EmailObject> hams_emails_training=new LinkedList<EmailObject>();
	private List<EmailObject> spams_emails_training=new LinkedList<EmailObject>();
	private List<EmailObject> hams_emails_test=new LinkedList<EmailObject>();
	private List<EmailObject> spams_emails_test=new LinkedList<EmailObject>();
	
	private String HAM="Ham/";
	private String SPAM="Spam/";

	
	
	
	
	
	
	
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
	List<EmailObject> hams_emails=readHams();
	List<EmailObject> spams_emails=readSpams();
	System.out.println(spams_emails.size()+" spam emails loaded");
	System.out.println(hams_emails.size()+" ham emails loaded");
	System.out.println("Splitting dataset in training and test");
	split_dataset(hams_emails,spams_emails);
	System.out.println("Training spam emails:"+spams_emails_training.size()+" - "+"Training hams emails:"+hams_emails_training.size());
	
}
private void split_dataset(List<EmailObject> hams_emails,List<EmailObject> spams_emails) {
	long hamsize=hams_emails.size();
	long spamsize=spams_emails.size();
	//Take the 80% of Hams for training
	this.setHams_emails_training(hams_emails.subList(0, (int)((hamsize/100)*80)));
	this.setHams_emails_test(hams_emails.subList((int)((hamsize/100)*80),hams_emails.size()));
	
		//Take the 80% of Spams for training
	this.setSpams_emails_training(spams_emails.subList(0, (int)((spamsize/100)*80)));
	this.setSpams_emails_test(spams_emails.subList( (int)((spamsize/100)*80), spams_emails.size()));
	
	
}







private List<EmailObject> readSpams() {
	File spam_folder=new File(DATASET_PATH+SPAM);
	List<File> spam_files=Arrays.asList(spam_folder.listFiles());
	List<EmailObject> emails=new LinkedList<EmailObject>();
	for(File f:spam_files){
		TarArchiveInputStream tar_stream=null;
		System.out.println(f.getName());
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
	return emails;
	
}

private List<EmailObject> readHams() {
	File ham_folder=new File(DATASET_PATH+HAM);
	List<File> ham_files=Arrays.asList(ham_folder.listFiles());
	List<EmailObject> emails=new LinkedList<EmailObject>();
	for(File f:ham_files){
		System.out.println(f.getName());
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
return emails;	
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


public List<EmailObject> getTrainingEmails(){
List<EmailObject> trainingset=new LinkedList<>(this.hams_emails_training);
trainingset.addAll(this.spams_emails_training);
return trainingset;
}







public List<EmailObject> getHams_emails_training() {
	return hams_emails_training;
}







public void setHams_emails_training(List<EmailObject> hams_emails_training) {
	this.hams_emails_training = hams_emails_training;
}







public List<EmailObject> getSpams_emails_training() {
	return spams_emails_training;
}







public void setSpams_emails_training(List<EmailObject> spams_emails_training) {
	this.spams_emails_training = spams_emails_training;
}







public List<EmailObject> getHams_emails_test() {
	return hams_emails_test;
}







public void setHams_emails_test(List<EmailObject> hams_emails_test) {
	this.hams_emails_test = hams_emails_test;
}







public List<EmailObject> getSpams_emails_test() {
	return spams_emails_test;
}







public void setSpams_emails_test(List<EmailObject> spams_emails_test) {
	this.spams_emails_test = spams_emails_test;
}

}
