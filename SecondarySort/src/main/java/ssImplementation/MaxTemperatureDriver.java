package ssImplementation;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;




public class MaxTemperatureDriver {
	private static final String INPUT_DIR="hdfs://localhost:9000/input_data";
	private static final String OUTPUT_DIR="hdfs://localhost:9000/output_data/";
	
	public static void main(String[] args) throws Exception {
		
		Path input_dir=new Path(INPUT_DIR);
	    Path output_dir=new Path(OUTPUT_DIR);
	    MyIOUtils.uploadInputFile(INPUT_DIR);
		Job job = Job.getInstance();
		System.out.println("Default Grouping Comparator: "+job.getGroupingComparator());

		job.setJarByClass(MaxTemperatureDriver.class);
		job.setJobName("Max temperature");
		job.setMapperClass(MaxTemperatureMapper.class);
		job.setPartitionerClass(MaxTemperaturePartitioner.class);
		job.setSortComparatorClass(MyKeyComparator.class);
		job.setGroupingComparatorClass(MyGroupComparator.class);
		job.setNumReduceTasks(1);
		job.setReducerClass(MaxTemperatureReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		output_dir.getFileSystem(job.getConfiguration()).delete(output_dir,true);
		FileInputFormat.addInputPath(job, input_dir);
		FileOutputFormat.setOutputPath(job, output_dir);	
		boolean isCompleted = job.waitForCompletion(true);
		
		if(isCompleted){
			MyIOUtils.readOutputFile(OUTPUT_DIR);
			System.out.println("Program Terminated successfully");
			System.exit(0);
		}
		else{
			System.out.println("Program terminated abnormally..");
			System.exit(1);
		}
		
		
	}
}
