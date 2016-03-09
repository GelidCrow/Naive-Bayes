package Build;



public  class Builder {

	private static Builder b=null;
	
	
	private Builder(){
		
	}
	public static Builder getBuilder(){
		if(b==null)
			b=new Builder();
		return b;
		
	}
	
	public void buildClassifier(){
		
	}
}
