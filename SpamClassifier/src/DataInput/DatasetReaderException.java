package DataInput;

public class DatasetReaderException extends NullPointerException{
	private String cause;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8739426028265070804L;
public DatasetReaderException(String s){
	super();
	this.cause=s;
}

@Override
public String toString(){
	return this.cause;
}
}
