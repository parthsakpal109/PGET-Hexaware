package java8;

public class Main {

	public static void main(String[] args) {
//		Old Code
//		Sum s = new Sum();
//		int k = s.cal(20,30);
		
		int a = 10, b = 5;
		calculator sum = (x,y) -> x+y;
		System.out.println(sum.cal(a, b));
		
		calculator sub = (x, y) -> x - y;
		System.out.println(sub.cal(a, b));
	}
}