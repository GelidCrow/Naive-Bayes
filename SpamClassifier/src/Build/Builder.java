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
			List<EmailObject> emails=reader.getEmails();
			for(EmailObject email:emails){
				addToMap(email);
			}
		this.Dictionary_size=map.size();
		System.out.println("Builder trained.Total terms:"+Dictionary_size);
		}
		else
			throw new DatasetReaderException("Could not build the classifier because the DatasetReader is not initialized.");
	}
	private void addToMap(EmailObject email) {
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
}
