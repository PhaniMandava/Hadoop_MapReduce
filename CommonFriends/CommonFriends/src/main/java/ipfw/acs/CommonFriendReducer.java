package ipfw.acs;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CommonFriendReducer extends Reducer<Text, Text, Text, Text> {
	public CommonFriendReducer() {
		System.out.println("CommonFriendsReducer()");
	}

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		System.out.println("CommonFriends.reduce(-,-,-)");
		// Values will have always 2 elements

		List<Set<String>> friendList = new ArrayList<Set<String>>();
		for (Text friendsText : values) {
			
			friendList.add(new HashSet<String>(Arrays.asList(friendsText.toString().split(" "))));
		}
		// friendList is an ArrayList containing 2 elements and each element is in turn another list

		Set<String> commonFriendList = CommonFriendUtil.findCommonFriends(friendList);
		
		context.write(key, new Text(commonFriendList.toString()));

	}
}
