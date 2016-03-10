package DataInput;

import java.io.IOException;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class EmailObject {
private TYPE t;
private EnglishAnalyzer analyzer;
private TokenStream stream;

EmailObject(String c,TYPE t) {
	analyzer=new EnglishAnalyzer();
	try {
		stream=analyzer.tokenStream("corpus", c);
		stream.addAttribute(CharTermAttribute.class);
		stream.reset();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	this.t=t;
}

public TYPE getType(){return this.t;}
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
	return stream.getAttribute(CharTermAttribute.class).toString();
}

}
