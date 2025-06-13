package day4.sim;

@FunctionalInterface
public interface Sim {
	void call();
    
	default void msg() {
		System.out.println("New feature msg: 4 msg/day");
	}
}