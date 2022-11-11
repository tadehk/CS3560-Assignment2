public interface VisitorStats {
	String visit(TotalUsers users);

	String visit(TotalGroups groups);

	String visit(TotalMessages messages);

	String visit(TotalPositive positive);
}