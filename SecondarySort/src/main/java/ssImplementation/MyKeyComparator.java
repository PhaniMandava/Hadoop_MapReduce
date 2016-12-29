package ssImplementation;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyKeyComparator extends 	WritableComparator
{
	protected MyKeyComparator() {
		super(Text.class,true);//second parameter will start the buffer,if you dont provide this value,it throws NullPOinterException
		//System.out.println("MyKeyComparator()");
	}
	
	
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		// 2000,12,10  yyyy,mm,temp
		// 2000,10,10
		//System.out.println("MyKeyComparator.compare(-,-) ::"+a+": "+b);
		String yearMonthTemp1[]=((Text)a).toString().split(",");
		String yearMonthTemp2[]=((Text)b).toString().split(",");
		
		int year1=Integer.parseInt(yearMonthTemp1[0]);
		int month1=Integer.parseInt(yearMonthTemp1[1]);
		int temp1=Integer.parseInt(yearMonthTemp1[2]);
		
		
		int year2=Integer.parseInt(yearMonthTemp2[0]);
		int month2=Integer.parseInt(yearMonthTemp2[1]);
		int temp2=Integer.parseInt(yearMonthTemp2[2]);
		
		int cmp=year1-year2;
		//If years are not same send +ve or -ve.
		//If years are same,then check for months.
		//If months are not same send +ve or -ve
		//If months are same,then check for temperature
		//If temperatures are not same,then send +ve or -ve  
		//else send 0
		 if(cmp!=0){
			 return cmp;
		 }
		 else{
			 cmp=month1-month2;
			 if(cmp!=0){
				 return cmp;
			 }
			 else{
				 return -(temp1-temp2);// temperature in ascending order
			 }
		 }
		
		
	//Text
	}
}
