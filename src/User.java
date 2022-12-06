import java.util.List;
import java.sql.Timestamp;

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
	
	public Timestamp getCreationTime();
}