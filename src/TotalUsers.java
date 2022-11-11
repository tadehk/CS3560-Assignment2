public class TotalUsers implements Stats {
	public void accept(VisitorStats visitor) {
		visitor.visit(this);
	}

}