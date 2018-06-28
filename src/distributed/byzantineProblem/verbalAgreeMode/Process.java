package distributed.byzantineProblem.verbalAgreeMode;

public class Process {

	private int sourcePId;

	private int aimPId;

	private Order order;

	private Path path;

	public Process(int sourcePId, int aimPId, Order order, Path path) {
		this.sourcePId = sourcePId;
		this.aimPId = aimPId;
		this.order = order;
		this.path = path;
	}

	public int getSourcePId() {
		return sourcePId;
	}

	public int getAimPId() {
		return aimPId;
	}

	public Order getOrder() {
		return order;
	}

	public Path getPath() {
		return path;
	}
	
	@Override
	public String toString(){
		return String.format("%d\t%d\t%s\t%s", sourcePId,aimPId,order.toString(),path.toString());
	}

}