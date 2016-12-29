package ssImplementation;
import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, Text> {

	public MaxTemperatureMapper() {
		System.out.println("MaxTemperatureMapper()");
	}

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString().trim();
		if (line.isEmpty() || line.startsWith("#")) {
			return;
		}
		String data[] = value.toString().split(",");

		if (data.length != 4) {
			return;
		}

		String year = data[0];
		String month = data[1];
		String temp = data[3];

		Text output = new Text(year + "," + month + "," + temp);

		context.write(output, output);// We need to emit year month temp both as key as well as value.
	}
}
