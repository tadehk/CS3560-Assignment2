import java.util.List;

public interface User {
	public void addFollows(User u);

	public void addFollowed(User u);

	public void addToNewsFeed(String s);

	public void setUserPanel(ProfileViewPanel userViewPanel);

	public ProfileViewPanel getUserPanel();

	public List<User> getFollowing();

	public List<User> getFollower();

	public List<String> getNewsFeed();

	public String getId();

	public String toString();
}