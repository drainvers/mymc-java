package utils;

public class Round {
	
	public static int div_round_up(int a, int b) {
		return (a + b - 1) / b;
	}

	public static int round_up(int a, int b) {
		return (a + b - 1) / b * b;
	}

	public static int round_down(int a, int b){
		return a / b * b;
	}
	
}
