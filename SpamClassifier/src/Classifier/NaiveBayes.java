package Classifier;
import java.util.List;

import Build.Builder;
import DataInput.DatasetReader;
import DataInput.TYPE;
import DataInput.EmailObject;
public class NaiveBayes {

	private Builder builder;
	public NaiveBayes(Builder builder){
	this.builder=builder;
}
	
	public TYPE classify(String content){
		return null;
	}
	
	public void classifyByDatasetReader(DatasetReader reader){
		List<EmailObject> e;
	}
	
}


