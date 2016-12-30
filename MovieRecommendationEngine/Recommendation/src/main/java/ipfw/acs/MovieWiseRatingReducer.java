package ipfw.acs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MovieWiseRatingReducer extends Reducer<Text, Text, Text, Text> {
	public MovieWiseRatingReducer() {
		System.out.println("MovieWiseRatingReducer()");
	}

	@Override
	public void reduce(Text movieId, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		System.out.println("MovieWiseRatingReducer.reduce(-,-,-)");
		
		List<String> valueList=new ArrayList<String>();
		for(Text value:values){
			valueList.add(value.toString());	
		}
		
		for(String data:valueList){
			String dataArr[]=data.split(",");
			String user	=dataArr[0];
			String rating	=dataArr[1];
			
			context.write(new Text(user), new Text(movieId.toString()+","+rating+","+valueList.size()));
			
			}

	}
}
