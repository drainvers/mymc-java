package test;

public class TestMain {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestECC test_ecc = new TestECC();
		test_ecc.test_ecc_calculate();
		test_ecc.test_ecc_check_ok();
		test_ecc.test_ecc_check_correct_data();
		test_ecc.test_ecc_check_correct_ecc();
		test_ecc.test_ecc_check_fail();
	}

}
