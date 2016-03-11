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
		
		double probability_spam = 0,probability_ham = 0;
		double number_of_ham=builder.getHAM_EMAILS();
		double number_of_spam=builder.getSPAM_EMAILS();
		long Dictionary_size=builder.getDictionarySize();
		email.resetStream();
		while(email.nextToken()){
			String token=email.getToken();
			/**
			 * Core Naive-Bayes statistical indipendece assumption
			 * with Laplace smoothing
			 */
			double temp=builder.getHamMails_from_token(token);
			double temp2=builder.getSpamMails_from_token(token);
			double a=Math.log(temp+1/(number_of_ham+Dictionary_size));
			double b=Math.log(temp2+1/(number_of_spam+Dictionary_size));
			
			probability_ham+=(a==Double.NEGATIVE_INFINITY?0:(a<0?0:a));
			probability_spam+=(b==Double.NEGATIVE_INFINITY?0:(b<0?0:b));
		}
		
		return (probability_ham>probability_spam?TYPE.HAM:TYPE.SPAM);
		
		
	}
	
	public void classifyByDatasetReader(DatasetReader reader){
		System.out.println("\nClassifying test emails..");
		List<EmailObject> e_s=reader.getSpams_emails_test();
		List<EmailObject> e_h=reader.getHams_emails_test();
		int right_spam_classified=0,right_ham_classified=0,spam_classified=0,ham_classified=0;
		for(EmailObject e: e_s){
			TYPE t=classify(e);
			if(t==TYPE.SPAM){
				spam_classified++;
				right_spam_classified++;
			}
			else
				ham_classified++;
		}
		
		for(EmailObject e: e_h){
			TYPE t=classify(e);
			if(t==TYPE.HAM){
				right_ham_classified++;
				ham_classified++;
			}
			else
				spam_classified++;
		}
		
	double spam_recall=(double)right_spam_classified/e_s.size();
	double ham_recall=(double)right_ham_classified/e_h.size();
	
	double spam_precision=(double)right_spam_classified/spam_classified;
	double ham_precision=(double)right_ham_classified/ham_classified;
	
	//Macroaveraging
	double mean_recall=(spam_recall+ham_recall)/2;
	double mean_precision=(ham_precision+spam_precision)/2;
	System.out.println("Spam recall :"+spam_recall+" Spam precision:"+spam_precision);
	System.out.println("Ham recall :"+ham_recall+" Ham precision:"+ham_precision);
	System.out.println("Macroaveraged recall:"+mean_recall+" Macroaveraged precision:"+mean_precision);
	
	
		
	}
	
}


