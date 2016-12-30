package ipfw.acs;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MovieWiseRatingDriver {
	public static void main(String[] args)  {
		try{
		Path input_dir=new Path("hdfs://localhost:9000/input_data/");
	    Path output_dir=new Path("hdfs://localhost:9000/output_data/");
				
		Job job = Job.getInstance();

		job.setJarByClass(MovieWiseRatingDriver.class);
		job.setJobName("Movie Recommendation");
		job.setMapperClass(MovieWiseRatingMapper.class);
		
		job.setNumReduceTasks(1);
		job.setReducerClass(MovieWiseRatingReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		output_dir.getFileSystem(job.getConfiguration()).delete(output_dir,true);
		FileInputFormat.addInputPath(job, input_dir);
		FileOutputFormat.setOutputPath(job, output_dir);	
		boolean isCompleted = job.waitForCompletion(true);
		
		if(isCompleted){
			System.out.println("Program Terminated successfully");
			//System.exit(0);
		}
		else{
			System.out.println("Program terminated abnormally..");
			//System.exit(1);
		}
		}catch(Exception e){
			System.out.println("Exception :"+e);
		}
		
		
	}
}
