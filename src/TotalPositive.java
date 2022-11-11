public class TotalPositive implements Stats {
	public void accept(VisitorStats visitor) {
		visitor.visit(this);
	}
}