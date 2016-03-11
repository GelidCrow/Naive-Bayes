import Build.Builder;
import Classifier.NaiveBayes;
import DataInput.DatasetReader;
import DataInput.DatasetReaderException;
import DataInput.EmailObject;
import DataInput.TYPE;






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
		NaiveBayes nb=new NaiveBayes(builder);
		nb.classifyByDatasetReader(reader);
		
		
		EmailObject myemail=new EmailObject("Dear recipient,\r\nAvangar Technologies announces the beginning of a new unprecendented global employment campaign. \r\nreviser yeller winers butchery twenties\r\nDue to company's exploding growth Avangar is expanding business to the European region.\r\nDuring last employment campaign over 1500 people worldwide took part in Avangar's business\r\nand more than half of them are currently employed by the company. And now we are offering you\r\none more opportunity to earn extra money working with Avangar Technologies.\r\ndruggists blame classy gentry Aladdin\r\n\r\nWe are looking for honest, responsible, hard-working people that can dedicate 2-4 hours of their\r\ntime per day and earn extra Â£300-500 weekly. All offered positions are currently part-time\r\nand give you a chance to work mainly from home.\r\nlovelies hockey Malton meager reordered\r\n\r\nPlease visit Avangar's corporate web site (http://www.avangar.com/sta/home/0077.htm) for more details regarding these vacancies.\r\n\r\n\r\nbespeak plur\r\n\r\nThe Avangar page describes a process that looks remarkably similar to the Money Laundering jobs described in emails that we catch every day: From the website -\r\n\r\n1. Our customer (located in your area) informs us about his wish to buy specified products or services.. \r\n2. We supply our client with your contact details and he transfers funds directly to you (cheque sent to postal address specified by you or via bank transfer to your bank account). You inform us the moment the funds arrive. \r\n3. We immediately give an order to ship products to the customer. In most cases this will allow us to ship client's order on the same or next business day. \r\n4. You then transfer the client’s funds to our bank account (or follow other transferring methods as will be stated in your instructions).\r\n\r\nThe website referred to appears to have been created only a few days before the sending of the emails.", TYPE.UNKNOWN);
		System.out.println(nb.classify(myemail));
	}
}