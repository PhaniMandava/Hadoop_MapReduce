package ssImplementation;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class MyGroupComparator extends WritableComparator {
	protected MyGroupComparator() {
		super(Text.class, true);// second parameter will start the buffer,if you
								// dont provide this value,it throws
								// NullPOinterException
		//System.out.println("MyGroupComparator()");
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		//System.out.println("MyGroupComparator.compare(-,-)::" + a + " : " + b);
		// 2000,12,10  yyyy,mm,temp
		// 2000,10,10
		// should fall on same group.ie only year should be used.
		String yearMonthTemp1[] = ((Text) a).toString().split(",");
		String yearMonthTemp2[] = ((Text) b).toString().split(",");

		int year1 = Integer.parseInt(yearMonthTemp1[0]);
		int year2 = Integer.parseInt(yearMonthTemp2[0]);
		
		int month1 = Integer.parseInt(yearMonthTemp1[1]);
		int month2 = Integer.parseInt(yearMonthTemp2[1]);
		
		
		if(year1!=year2){
			// +ve means heavier
			// -ve means lighter
			return year1-year2; // 1901-1905
		}
		else{
			return month1-month2; //if years are equal then compare the month.
		}
	}
}
