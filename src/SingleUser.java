import java.util.ArrayList;
import java.util.List;

public class SingleUser implements User {
	private static int userCounter = 0;
	private String id = null;
	private List<User> follows;
	private List<User> followers;
	private List<String> newsFeed;

	private ProfileViewPanel userPanel;

	public SingleUser(String id) {
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

}