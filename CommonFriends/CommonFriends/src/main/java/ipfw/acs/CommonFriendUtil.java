package ipfw.acs;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CommonFriendUtil {// user1friends = {A1, A2, ..., Am}
	// user2friends = {B1, B2, ..., Bn}
	
	public static Set<String> findCommonFriends(List<Set<String>> friendList){
		Set<String> user1FriendList = friendList.get(0);
		
		Set<String> user2FriendList =friendList.get(1);
		
		return intersection(user1FriendList,user2FriendList);
		
	}
	
	private static Set<String> intersection(Set<String> user1friends, Set<String> user2friends) {
		if ((user1friends == null) || (user1friends.isEmpty())) {
			return null;
		}

		if ((user2friends == null) || (user2friends.isEmpty())) {
			return null;
		}

		// both sets are non-null
		if (user1friends.size() < user2friends.size()) {
			return intersect(user1friends, user2friends);
		} else {
			return intersect(user2friends, user1friends);
		}
	}

	private static Set<String> intersect(Set<String> smallSet, Set<String> largeSet) {
		Set<String> result = new TreeSet<String>();
		// iterate on small set to improve performance
		for (String x : smallSet) {
			if (largeSet.contains(x)) {
				result.add(x);
			}
		}
		return result;
	}
}