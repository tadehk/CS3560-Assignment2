import java.util.ArrayList;
import java.sql.Timestamp;
import java.sql.Timestamp;

public class UserGroup implements User {
	private String id = "";
	private ArrayList<User> usersInGroup;
	private ArrayList<User> groupsInGroup;
	private static int groupCounter = 0;
	private long timeStamp;

	public UserGroup(String id) {
		timeStamp = System.currentTimeMillis();
		this.id = id;
		usersInGroup = new ArrayList<User>();
		groupsInGroup = new ArrayList<User>();
		++groupCounter;
	}

	public void add(User user) {
		usersInGroup.add(user);
	}

	public void addGroup(UserGroup group) {
		groupsInGroup.add(group);
	}

	public static String getGroupCounter() {
		String result = Integer.toString(groupCounter);
		return result;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		return id + " (Group)";
	}

	@Override
	public ArrayList<User> getFollowing() {
		return null;
	}

	@Override
	public ArrayList<User> getFollower() {
		return null;
	}

	@Override
	public ArrayList<String> getNewsFeed() {
		return null;
	}

	@Override
	public void addFollows(User u) {
	}

	@Override
	public void addFollowed(User u) {
	}

	@Override
	public void addToNewsFeed(String s) {
	}

	public ProfileViewPanel getUserPanel() {
		return null;
	}

	public void setUserPanel(ProfileViewPanel userViewPanel) {
	}

	@Override
	public Timestamp getCreationTime() {
		// TODO Auto-generated method stub
		return new Timestamp(timeStamp);
	}
}