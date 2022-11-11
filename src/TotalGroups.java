public class TotalGroups implements Stats {
	public void accept(VisitorStats visitor) {
		visitor.visit(this);
	}
}