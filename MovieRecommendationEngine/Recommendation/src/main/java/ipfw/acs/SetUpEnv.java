package ipfw.acs;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class SetUpEnv {
	
	private static final String INPUT_DIR="hdfs://localhost:9000/input_data";
	//private static final String OUTPUT_DIR="hdfs://localhost:9000/output_data";
	
	public static void main(String[] args) throws Exception {
		
		Path inputPath = new Path(INPUT_DIR);
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(INPUT_DIR), conf);

		 boolean flag = fs.delete(inputPath,true);
		 
		 if(flag){
			 System.out.println("File Exists and so deleted");
		 }
		 else System.out.println("No File Exists...");
		 
		// given the input local path,find out the list of files
		 
		 //copy file from local to hdfs
		 /*InputStream in = new BufferedInputStream(new FileInputStream(localPath));

		 
			// Pointer to HDFS
			FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
			//Create a file called fruit1.txt in hdfs and will be reference by out
			OutputStream out = fs.create(new Path(hdfsPath));
			IOUtils.copyBytes(in, out, 4096, true);
		*/
		
		
	}
}
