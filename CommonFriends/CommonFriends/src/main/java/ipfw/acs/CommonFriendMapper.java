package ipfw.acs;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CommonFriendMapper extends Mapper<LongWritable, Text, Text, Text> {

	public CommonFriendMapper() {
		System.out.println("CommonFriendMapper()");
	}

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString().trim();
		if (line.isEmpty() || line.startsWith("#")) {
			return;
		}
		String data[] = value.toString().split(",");

		if (data.length != 2) {
			return;
		}

		String userId = data[0].trim();
		String friends = data[1].trim();
		
		//ArrayWritable friendList = getFriendList(friends);
		
		
		Text friendsText = new Text(friends);

		String friendsArr[] = friends.split(" ");

		for (String friend : friendsArr) {
			String userIdAndFriend;
			
			//The key should always be in ascending order like [100, 200]  and not [200,100].
			//This is to make sure that we treat [100,200] and [200,100] same.So create the keys always in ascending order.
			//if you see [200,100] change it to [100,200]
			if (Integer.parseInt(userId) <= Integer.parseInt(friend)) {
				userIdAndFriend = userId + "," + friend;
			} else {
				userIdAndFriend = friend + "," + userId;
			}
			
			
			// This generates the expected output.Run it as map only job to
			// verify the output
			//System.out.println(userIdAndFriend+"::"+friendList.toStrings());
			context.write(new Text(userIdAndFriend), friendsText);

		}

	}

}
