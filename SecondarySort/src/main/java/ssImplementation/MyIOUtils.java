package ssImplementation;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class MyIOUtils {
/*
	public static void main(String[] args) throws IOException {
		uploadInputFile("hdfs://localhost:9000/input_data");
	}
*/
	public static void uploadInputFile(String inputPath) throws IOException {
		String localPath = "src/main/resources/";
		// String[] fileNameList = { "temp1.txt" };

		// Reading file Names dynamically
		String[] fileNameList = getListOfFiles(localPath);

		Path inputHdfsPath = new Path(inputPath);
		Configuration conf = new Configuration();
		// Pointer to HDFS
		FileSystem fs = FileSystem.get(URI.create(inputPath), conf);
		fs.delete(inputHdfsPath, true);// first empty the input_folder

		for (String fileName : fileNameList) {
			InputStream in = new BufferedInputStream(new FileInputStream(localPath + fileName));
			// Create a file called fruit1.txt in hdfs and will be reference by out
			String destPath = inputPath + "/" + fileName;
			OutputStream out = fs.create(new Path(destPath));
			IOUtils.copyBytes(in, out, 4096, true);

			System.out.println("Created :" + destPath);

		}
	}

	private static String[] getListOfFiles(String path) {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		String resultFileList[] = new String[listOfFiles.length];
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				System.out.println("File " + listOfFiles[i].getName());
				resultFileList[i] = listOfFiles[i].getName();
			} else if (listOfFiles[i].isDirectory()) {
				System.out.println("Directory " + listOfFiles[i].getName());
			}
		}
		return resultFileList;
	}

	public static void readOutputFile(String output) throws IOException {

		String url = output + "/part-r-00000";
		Configuration conf = new Configuration();

		FileSystem fs = FileSystem.get(URI.create(url), conf);
		FSDataInputStream in = fs.open(new Path(url));
		System.out.println("\n\n ***********************Output*******************");
		IOUtils.copyBytes(in, System.out, 4096, false);
	}
}
