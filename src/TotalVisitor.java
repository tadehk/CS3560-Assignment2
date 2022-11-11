public class TotalVisitor implements VisitorStats {
	@Override
	public String visit(TotalUsers users) {
		return "Total Users: " + AdminControlPanel.getInstance().getUsers().size();
	}

	@Override
	public String visit(TotalGroups groups) {
		return "Total Groups: " + (AdminControlPanel.getInstance().getGroups().size() + 1);
	}

	@Override
	public String visit(TotalMessages messages) {
		return "Total Messages: " + Integer.toString(ProfileViewPanel.getTotalMessages());
	}

	@Override
	public String visit(TotalPositive positive) {
		return "Positive Percentage: " + ProfileViewPanel.getPositivePercentage();
	}

}