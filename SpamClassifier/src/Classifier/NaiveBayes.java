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
	
	private TYPE classify(EmailObject email){
		return null;
	}
	
	public void classifyByDatasetReader(DatasetReader reader){
		System.out.println("\n Classifying test emails..");
		List<EmailObject> e_s=reader.getSpams_emails_test();
		List<EmailObject> e_h=reader.getHams_emails_test();
		int total_emails=e_s.size()+e_h.size();
		int right_classified=0;
		for(EmailObject e: e_s){
			TYPE t=classify(e);
			if(t==TYPE.SPAM)
				right_classified++;
		}
		
		for(EmailObject e: e_h){
			TYPE t=classify(e);
			if(t==TYPE.HAM)
				right_classified++;
		}
	//	double precision=right_classified/total_emails;
		
	}
	
}


