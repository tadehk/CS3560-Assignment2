import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ProfileViewPanel implements Observable, Observer {
	private JFrame frame;
	private JTextField userID;
	private JTextField wudphfMessages;

	private User thisUser;
	private JButton wudphfButton;
	private JButton followButton;

	private JLabel messageLabel;
	private JLabel newsFeedLabel;
	private JLabel followingLabel;
	private JLabel creationLabel;
	private JLabel creationTime;
	private JLabel lastUpdate;
	private JLabel updateTime;

	private static List<User> users = new ArrayList<User>();

	private JTextArea followingText;
	private JTextArea newsFeedText;

	private final String[] positiveWords = new String[] { "Good", "Great", "Perfect", "Nice Job", "Excellent", "Awesome", "Amazing"};

	private static int messageCounter = 0;
	private static int positivePercent = 0;

	private ProfileViewPanel(User user) {

		this.thisUser = user;
		System.out.println(thisUser.getNewsFeed());
		makeFrame();
		initialize();
		frame.setVisible(true);

	}

	private void makeFrame() {
		frame = new JFrame(thisUser.toString());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setBounds(100, 100, 366, 521);
		frame.setResizable(false);

	}

	private void initialize() {
		newsFeedLabel = new JLabel("News Feed");
		newsFeedLabel.setBounds(10, 245, 160, 13);
		frame.getContentPane().add(newsFeedLabel);

		followingLabel = new JLabel("Following");
		followingLabel.setBounds(10, 52, 160, 13);
		frame.getContentPane().add(followingLabel);

		messageLabel = new JLabel("");
		messageLabel.setForeground(Color.BLACK);
		messageLabel.setBounds(10, 399, 330, 14);
		frame.getContentPane().add(messageLabel);

		creationLabel = new JLabel("Creation Time: ");
		creationLabel.setBounds(10, 430, 160, 13);
		frame.getContentPane().add(creationLabel);

		lastUpdate = new JLabel("Last Updated: ");
		lastUpdate.setBounds(10, 450, 160, 13);
		frame.getContentPane().add(lastUpdate);

		creationTime = new JLabel(thisUser.getCreationTime().toString());
		creationTime.setForeground(Color.BLACK);
		creationTime.setBounds(120, 430, 160, 13);
		frame.getContentPane().add(creationTime);

		updateTime = new JLabel(((SingleUser) thisUser).getUpdatedTime().toString());
		updateTime.setForeground(Color.BLACK);
		updateTime.setBounds(120, 450, 160, 13);
		frame.getContentPane().add(updateTime);

		userID = new JTextField();
		userID.setBounds(10, 11, 160, 30);
		userID.setColumns(10);
		frame.getContentPane().add(userID);

		wudphfMessages = new JTextField();
		wudphfMessages.setColumns(10);
		wudphfMessages.setBounds(10, 204, 160, 30);
		frame.getContentPane().add(wudphfMessages);

		wudphfButton = new JButton("Post Tweet");
		wudphfButton.setBounds(180, 204, 160, 30);

		followButton = new JButton("Follow User");
		followButton.setBounds(180, 11, 160, 30);

		followingText = new JTextArea();
		JScrollPane scrollFollowing = new JScrollPane(followingText);
		scrollFollowing.setBounds(10, 75, 330, 118);
		frame.getContentPane().add(scrollFollowing);

		newsFeedText = new JTextArea();
		JScrollPane scrollNews = new JScrollPane(newsFeedText);
		scrollNews.setBounds(10, 269, 330, 118);
		frame.getContentPane().add(scrollNews);

		followButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFollower();

			}

		});
		frame.getContentPane().add(followButton);

		wudphfButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				postWudphf();

			}
		});
		frame.getContentPane().add(wudphfButton);

		frame.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				users.remove(thisUser);
			}

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

		});

		//Update the panel 
		update(thisUser);
		updateTextUser();
		updateTime();
	}

	public void updateFollower(String message) {

		List<User> followers = new ArrayList<User>();
		System.out.println(thisUser.toString() + "'s followers: " + thisUser.getFollower());

		for (int i = 0; i < thisUser.getFollower().size(); ++i) {
			followers.add(thisUser.getFollower().get(i));
			System.out.println(thisUser + " followers: " + followers.get(i));
		}

		for (int i = 0; i < followers.size(); ++i) {
			followers.get(i).addToNewsFeed(thisUser.toString() + ": " + message);
			followers.get(i).getUserPanel().update(thisUser);
		}
	}

	private void addFollower() {

		String userId = userID.getText();
		User userFound = null;
		if (userId.equals("")) {
			messageLabel.setText("Please input user ID. ");
		} else {
			messageLabel.setText("");
			if (userId.equals(thisUser.toString())) {
				messageLabel.setText("Can't follow yourself");
				return;
			} else {
				List<User> list = AdminControlPanel.getInstance().getUsers();

				for (int i = 0; i < thisUser.getFollowing().size(); ++i) {
					if (thisUser.getFollowing().get(i).toString().equals(userId)) {
						messageLabel.setText("Already following " + thisUser.getFollowing().get(i).toString());
						return;
					}
				}

				for (int i = 0; i < list.size(); ++i) {
					if (list.get(i).toString().equals(userId)) {
						userFound = list.get(i);
						break;
					}
				}
			}
		}
		if (userFound != null) {
			thisUser.addFollows(userFound);
			userFound.addFollowed(thisUser);
			userID.setText("");

			updateTextUser();
			updateTime();
		}
	}

	public void postWudphf() {
		String tweet = wudphfMessages.getText();

		if (tweet.equals("")) {
			messageLabel.setText("Please enter a message");
		} else if (!tweet.equals("")) {
			thisUser.addToNewsFeed(thisUser.toString() + ": " + tweet);
			update(thisUser);
			wudphfMessages.setText("");
			updateFollower(tweet);
			updateTime();
		}
		++messageCounter;
		for (int i = 0; i < positiveWords.length; ++i) {
			if (positiveWords[i].equalsIgnoreCase(tweet)) {
				++positivePercent;
			}
		}
	}

	public void updateTime(){
		updateTime.setText(((SingleUser) thisUser).getUpdatedTime().toString());
	}

	public void updateTextUser() {
		String followingUsers = "";
		List<User> following = thisUser.getFollowing();
		for (int i = 0; i < following.size(); ++i) {
			followingUsers += following.get(i) + "\n";
		}
		followingText.setText(followingUsers);
		updateTime();
	}

	public static ProfileViewPanel getInstance(User user) {

		for (int i = 0; i < users.size(); ++i) {
			if (users.get(i).equals(user)) {
				return user.getUserPanel();
			}
		}

		user.setUserPanel(new ProfileViewPanel(user));
		users.add(user);

		return user.getUserPanel();
	}

    public void update(User user) {

		String message = "";
		List<String> newsFeed = thisUser.getNewsFeed();

		for (int i = 0; i < newsFeed.size(); ++i) {
			message += newsFeed.get(i) + "\n";
			newsFeedText.setText(message);
		}
		updateTime();
	}

	public static int getTotalMessages() {
		return messageCounter;
	}

	public static String getPositivePercentage() {

		if (positivePercent == 0 || getTotalMessages() == 0) {
			return "0%";
		}

		String result = Double.toString((double) (positivePercent) / (getTotalMessages()) * 100);
		return result + "%";
	}

    @Override
    public void update(java.util.Observable o, Object arg) {        
    }
}