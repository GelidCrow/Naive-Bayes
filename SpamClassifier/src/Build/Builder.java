package Build;

import DataInput.DatasetReader;
import DataInput.DatasetReaderException;
import DataInput.EmailObject;
import DataInput.TYPE;

import java.util.HashMap;
import java.util.List;

public  class Builder {

	private static Builder b=null;
	private static HashMap<String, Couple> map;
	private long Dictionary_size=0;
	private long SPAM_EMAILS=0;
	private long HAM_EMAILS=0;
	
	
	
	private Builder(){
		map=new HashMap<String,Couple>();
	}
	public static Builder getBuilder(){
		if(b==null)
			b=new Builder();
		return b;
		
	}
	
	public void buildClassifier(DatasetReader reader) throws DatasetReaderException{
		
		if(reader!=null){
			List<EmailObject> emails=reader.getTrainingEmails();
			for(EmailObject email:emails){
				addToMap(email);
			}
		
		this.Dictionary_size=map.size();
		System.out.println("Builder trained on "+emails.size()+" emails."+"Total terms:"+Dictionary_size);
		}
		else
			throw new DatasetReaderException("Could not build the classifier because the DatasetReader is not initialized.");
	}
	private void addToMap(EmailObject email) {
		
		if(email.getType()==TYPE.HAM)
			this.setHAM_EMAILS(this.getHAM_EMAILS() + 1);
		else if(email.getType()==TYPE.SPAM)
			this.setSPAM_EMAILS(this.getSPAM_EMAILS() + 1);
		email.resetStream();
		while(email.nextToken()){
			String token=email.getToken();
			if(map.containsKey(token)){
				
				Couple c=map.get(token);
				if(email.getType()==TYPE.HAM)
				map.put(token, new Couple(c.spams, c.hams+1));
				else if(email.getType()==TYPE.SPAM)
					map.put(token, new Couple(c.spams+1, c.hams));
			}
			else{
				if(email.getType()==TYPE.HAM)
				map.put(token, new Couple(0, 1));
				else if(email.getType()==TYPE.SPAM)
					map.put(token, new Couple(1,0));	
			}
		}
		
	}
	public long getSPAM_EMAILS() {
		return SPAM_EMAILS;
	}
	public void setSPAM_EMAILS(long sPAM_EMAILS) {
		SPAM_EMAILS = sPAM_EMAILS;
	}
	public long getHAM_EMAILS() {
		return HAM_EMAILS;
	}
	public void setHAM_EMAILS(long hAM_EMAILS) {
		HAM_EMAILS = hAM_EMAILS;
	}
	
	public long getSpamMails_from_token(String t){
		return(map.containsKey(t)?map.get(t).spams:0);
	}
	public long getHamMails_from_token(String t){
		return(map.containsKey(t)?map.get(t).hams:0);
	}
	public long getDictionarySize(){
		return this.Dictionary_size;
	}
	
}
