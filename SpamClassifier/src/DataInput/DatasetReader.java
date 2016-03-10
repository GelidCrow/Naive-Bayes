package DataInput;

import java.util.LinkedList;
import java.util.List;

import org.omg.IOP.TAG_ALTERNATE_IIOP_ADDRESS;

public class DatasetReader {
	public String DATASET_PATH;
	private List<EmailObject> emails=new LinkedList<EmailObject>();
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

public boolean readEmails(){
	
}

}
