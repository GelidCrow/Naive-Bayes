package DataInput;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class EmailObject {
private TYPE t;
private EnglishAnalyzer analyzer;
private TokenStream stream;
private CharTermAttribute att;
public EmailObject(String c,TYPE t) {
	analyzer=new EnglishAnalyzer();
	try {
		stream=analyzer.tokenStream("corpus", c);
		att=stream.addAttribute(CharTermAttribute.class);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.t=t;
}



public TYPE getType(){return this.t;}

public void resetStream(){
	try {
	stream.reset();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

public boolean nextToken(){
	boolean r=false;
	try {
		r=stream.incrementToken();
		if(r==false){
			stream.end();
			stream.close();
			
			analyzer.close();
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	return r;
}

public String getToken(){
	return this.att.toString();
}

}
