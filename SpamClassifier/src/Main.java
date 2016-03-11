import Build.Builder;
import DataInput.DatasetReader;
import DataInput.DatasetReaderException;






class Main{
	
	// 
	public static void main(String ...args)  {
		DatasetReader reader=new DatasetReader();
		reader.readEmails();
		Builder builder=Builder.getBuilder();
		try{
		builder.buildClassifier(reader);
		}
		catch(DatasetReaderException e){
			System.out.println(e);
		}
		
	}
}