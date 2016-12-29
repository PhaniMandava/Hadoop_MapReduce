package ssImplementation;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MaxTemperaturePartitioner extends Partitioner<Text, Text> {
	public MaxTemperaturePartitioner() {
		System.out.println("MaxTemperaturePartitioner()");
	}

	@Override
	public int getPartition(Text key, Text value, int numPartitions) {
		System.out.println("MaxTemperaturePartitioner.getPartition(-,-,-)");
		// Same year-month should go to same partitioner.

		String ymd = key.toString();
		int lastComma = ymd.lastIndexOf(",");
		String yearMonth = ymd.substring(0, lastComma);
		return yearMonth.hashCode() % numPartitions;
	}
}
