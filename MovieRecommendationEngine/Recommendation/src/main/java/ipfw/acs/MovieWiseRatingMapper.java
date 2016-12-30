package ipfw.acs;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MovieWiseRatingMapper extends Mapper<LongWritable, Text, Text, Text> {

	public MovieWiseRatingMapper() {
		System.out.println("MovieWiseRatingMapper()");
	}

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		//196	242	3	881250949 (userId movieId rating timestamp)
		String line = value.toString().trim();
		if (line.isEmpty() || line.startsWith("#")) {
			return;
		}
		String data[] = value.toString().split("\t");

		if (data.length != 4) {
			return;
		}

		String userId = data[0].trim(); 
		String movieId = data[1].trim(); 
		String rating = data[2].trim();
		
		context.write(new Text(movieId), new Text(userId+","+rating));
		
	}
}

