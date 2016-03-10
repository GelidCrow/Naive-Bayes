package DataInput;

public class DatasetReader {
	String DATASET_PATH;

public DatasetReader(){
	DATASET_PATH="datasets/";
}
public DatasetReader(String d){
	if(!d.isEmpty())
		DATASET_PATH=d;
	else
		DATASET_PATH="datasets/";
}



}
