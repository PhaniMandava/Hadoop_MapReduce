package ssImplementation;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class MaxTemperatureReducer extends Reducer<Text, Text, Text, Text> {
	public MaxTemperatureReducer() {
		System.out.println("MaxTemperatureReducer()");
	}

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		System.out.println("MaxTemperatureReducer.reduce(-,-,-)");
		String yearMonth = key.toString().substring(0, key.toString().lastIndexOf(","));
		yearMonth = yearMonth.replaceAll(",", "-");
		StringBuilder tempBuilder = new StringBuilder();

		values.forEach(text -> {
			System.out.println();
			// Retrieve only temp from value
			String temp = text.toString().substring(text.toString().lastIndexOf(",") + 1);
			System.out.println(temp);
			tempBuilder.append(temp);
			tempBuilder.append(",");

		});

		tempBuilder.setLength(tempBuilder.length() - 1);

		context.write(new Text(yearMonth), new Text(tempBuilder.toString()));

	}
}
