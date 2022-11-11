import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JScrollPane;

public class AdminControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static AdminControlPanel instance = null;
	private List<User> userList;
	private List<User> groups;

	//Button variables
	private JButton messageButton;
	private JButton percentageButton;
	private JButton addUserButton;
	private JButton addGroupButton;
	private JButton userViewButton;
	private JButton userTotalButton;
	private JButton groupTotalButton;
	private JLabel labelMessage;
	private JTextField userID;
	private JTextField groupID;
	private JFrame frame;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode currentNode;
	private DefaultMutableTreeNode addUser;
	private DefaultMutableTreeNode addGroup;

	private AdminControlPanel() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		//Frame
		frame = new JFrame("Mini-Twitter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 752, 464);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		userList = new ArrayList<User>();
		groups = new ArrayList<User>();

		//User tree and Root
		UserGroup rootGroup = new UserGroup("Root");
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootGroup);
		treeModel = new DefaultTreeModel(root);
		tree = new JTree(treeModel);
		JScrollPane scrollTree = new JScrollPane(tree);
		scrollTree.setBounds(10, 11, 381, 413);
		frame.getContentPane().add(scrollTree);

		labelMessage = new JLabel("");
		labelMessage.setForeground(Color.BLACK);
		labelMessage.setBounds(425, 218, 316, 14);
		frame.getContentPane().add(labelMessage);

		userID = new JTextField("");
		userID.setFont(new Font("Calibri", Font.PLAIN, 22));
		userID.setBounds(425, 11, 151, 40);
		userID.setColumns(10);
		frame.getContentPane().add(userID);

		groupID = new JTextField("");
		groupID.setFont(new Font("Calibri", Font.PLAIN, 22));
		groupID.setColumns(10);
		groupID.setBounds(425, 62, 151, 40);
		frame.getContentPane().add(groupID);

		userViewButton = new JButton("View User Profile");
		userViewButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		userViewButton.setBounds(425, 113, 316, 40);
		frame.getContentPane().add(userViewButton);

		percentageButton = new JButton("Show Positive %");
		percentageButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		percentageButton.setBounds(590, 383, 151, 40);
		frame.getContentPane().add(percentageButton);

		messageButton = new JButton("Show Total Messages");
		messageButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		messageButton.setBounds(425, 384, 151, 40);
		frame.getContentPane().add(messageButton);

		addUserButton = new JButton("Add User");
		addUserButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		addUserButton.setBounds(590, 11, 151, 40);

		addGroupButton = new JButton("Add Group");
		addGroupButton.setFont(new Font("Calibri", Font.PLAIN, 18));
		addGroupButton.setBounds(590, 62, 151, 40);

		groupTotalButton = new JButton("Show Total Group(s)");
		groupTotalButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		groupTotalButton.setBounds(590, 332, 151, 40);
		frame.getContentPane().add(groupTotalButton);

		userTotalButton = new JButton("Show Total User(s)");
		userTotalButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		userTotalButton.setBounds(425, 332, 151, 40);

		addUserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addUsers();
			}
		});
		frame.getContentPane().add(addUserButton);

		addGroupButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addGroups();
			}
		});
		frame.getContentPane().add(addGroupButton);

		userViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewProfile();
			}
		});
		frame.getContentPane().add(userTotalButton);

		userTotalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TotalUsers total = new TotalUsers();
				TotalVisitor tdv = new TotalVisitor();
				labelMessage.setText(tdv.visit(total));
			}
		});

		groupTotalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TotalGroups total = new TotalGroups();
				TotalVisitor tdv = new TotalVisitor();
				labelMessage.setText(tdv.visit(total));
			}
		});

		messageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TotalMessages total = new TotalMessages();
				TotalVisitor tdv = new TotalVisitor();
				labelMessage.setText(tdv.visit(total));
			}
		});

		percentageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TotalPositive total = new TotalPositive();
				TotalVisitor tdv = new TotalVisitor();
				labelMessage.setText(tdv.visit(total));
			}
		});
	}

	public void addUsers() {
		labelMessage.setText("");
		treeModel = (DefaultTreeModel) tree.getModel();
		currentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (currentNode != null) {
			if (!userID.getText().trim().equals("")) {
				if (currentNode.getUserObject() instanceof UserGroup) {
					System.out.println(groups);
					for (int i = 0; i < userList.size(); ++i) {
						if (userList.get(i).getId().equalsIgnoreCase(userID.getText())) {
							labelMessage.setText("Please input a unique ID");
							return;
						}
					}

					for (int i = 0; i < groups.size(); ++i) {
						if (groups.get(i).getId().equalsIgnoreCase(userID.getText())) {
							labelMessage.setText("Please input a unique ID");
							return;
						}
					}

					userList.add(new SingleUser(userID.getText()));
					addUser = new DefaultMutableTreeNode(userList.get(userList.size() - 1));
					treeModel.insertNodeInto(addUser, currentNode, currentNode.getChildCount());
					userID.setText("");
					treeModel.reload();
				} else if (currentNode.getUserObject() instanceof SingleUser) {
					labelMessage.setText("Can't add User.");
				}
			}
		} else {
			labelMessage.setText("Please Select Path to Add User");
		}
	}

	public void addGroups() {
		labelMessage.setText("");
		treeModel = (DefaultTreeModel) tree.getModel();
		currentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
		if (currentNode != null) {
			if (groupID.getText().trim().equals("") == false) {
				if (currentNode.getUserObject() instanceof SingleUser) {
					labelMessage.setText("Can't add group inside user.");
				} else if (currentNode.getUserObject() instanceof UserGroup) {
					for (int i = 0; i < userList.size(); ++i) {
						if (userList.get(i).toString().equalsIgnoreCase(groupID.getText())) {
							labelMessage.setText("Please input a unique ID");
							return;
						}
					}

					for (int i = 0; i < groups.size(); ++i) {
						if (groups.get(i).getId().equalsIgnoreCase(groupID.getText())) {
							labelMessage.setText("Please enter a unique ID");
							return;
						}
					}
					groups.add(new UserGroup(groupID.getText()));
					System.out.println("\n" + groups.get(groups.size() - 1)); 
					addGroup = new DefaultMutableTreeNode(groups.get(groups.size() - 1));
					treeModel.insertNodeInto(addGroup, currentNode, currentNode.getChildCount());
					groupID.setText("");
				}
			} else {
				labelMessage.setText("You must enter a Group ID");
			}
		} else {
			labelMessage.setText("You must choose a parent node to insert");
		}
	}

	public void viewProfile() {
		currentNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		if (currentNode == null) {

			labelMessage.setText("Select a user");

		} else if (currentNode.getUserObject() instanceof SingleUser) {
			User selectedUser = (SingleUser) currentNode.getUserObject();

			ProfileViewPanel.getInstance(selectedUser);
		} else if (currentNode.getUserObject() instanceof UserGroup) {
			labelMessage.setText("Select an User ID. Not a groupList");
		}
	}

	public List<User> getUsers() {
		return userList;
	}

	public List<User> getGroups() {
		return groups;
	}

	// Singleton - to get only one instance
	public static AdminControlPanel getInstance() {
		if (instance == null) {
			instance = new AdminControlPanel();
		}
		return instance;
	}
}