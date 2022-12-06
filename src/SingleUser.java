import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class SingleUser implements User {
	private static int userCounter = 0;
	private String id = null;
	private List<User> follows;
	private List<User> followers;
	private List<String> newsFeed;
	private long timeStamp;
	private long updateTime;

	private ProfileViewPanel userPanel;

	public SingleUser(String id) {
		timeStamp = System.currentTimeMillis();
		updateTime = System.currentTimeMillis();
		this.id = id;
		follows = new ArrayList<User>();
		followers = new ArrayList<User>();
		newsFeed = new ArrayList<String>();
		++userCounter;
	}

	public String toString() {
		return this.id;
	}

	public void addToNewsFeed(String message) {
		this.newsFeed.add(message);
		updateTime = System.currentTimeMillis();
	}

	public void addFollows(User user) {
		this.follows.add(user);
	}

	public void addFollowed(User u) {
		this.followers.add(u);
	}

	public List<String> getNewsFeed() {
		return newsFeed;
	}

	public List<User> getFollowing() {
		return follows;
	}

	public List<User> getFollower() {
		return followers;
	}

	public static String getTotalUsers() {
		String result = Integer.toString(userCounter);
		return result;
	}

	public ProfileViewPanel getUserPanel() {
		return this.userPanel;
	}

	@Override
	public String getId() {
		return this.id;
	}
	
	public void setUserPanel(ProfileViewPanel userPanel) {
		this.userPanel = userPanel;
	}
	
	@Override
	public Timestamp getCreationTime() {
		// TODO Auto-generated method stub
		return new Timestamp(timeStamp);
	}


	public Timestamp getUpdatedTime() {
		return new Timestamp(updateTime);
	}

	public long getUpdatedTimeLong() {
		return updateTime;
	}

}