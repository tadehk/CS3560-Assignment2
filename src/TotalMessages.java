public class TotalMessages implements Stats {
	public void accept(VisitorStats visitor) {
		visitor.visit(this);
	}
}