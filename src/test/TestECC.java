package test;

import java.util.Arrays;

import utils.ECCFlags;
import utils.ECC;

public class TestECC {
	
	private byte[] data_;
	private byte[] ecc_;
	private byte[] page_data_;
	private byte[][] page_ecc_;
	private ECC ps2mc_ecc;
	private static TestECC instance;
	
	private TestECC() {
		data_ = new byte[]{(byte) 0x36, (byte) 0x76, (byte) 0x11, (byte) 0x24, (byte) 0xb1, (byte) 0xd2, (byte) 0xd2, (byte) 0x81, (byte) 0xd5, (byte) 0x47, (byte) 0x15, (byte) 0x41, (byte) 0xc9, (byte) 0x47, (byte) 0xb7, (byte) 0xf2,
		                   (byte) 0x6b, (byte) 0x00, (byte) 0x25, (byte) 0x34, (byte) 0x48, (byte) 0x1b, (byte) 0xbc, (byte) 0xcd, (byte) 0x07, (byte) 0x28, (byte) 0x9a, (byte) 0x88, (byte) 0x9c, (byte) 0xd3, (byte) 0x69, (byte) 0xda,
		                   (byte) 0x25, (byte) 0xa8, (byte) 0x39, (byte) 0x62, (byte) 0xd4, (byte) 0x8c, (byte) 0xc0, (byte) 0x25, (byte) 0x43, (byte) 0x04, (byte) 0x65, (byte) 0x22, (byte) 0xa8, (byte) 0xef, (byte) 0x44, (byte) 0x5f,
		                   (byte) 0x03, (byte) 0x91, (byte) 0xda, (byte) 0x23, (byte) 0x8c, (byte) 0x17, (byte) 0x24, (byte) 0xf5, (byte) 0x14, (byte) 0xf0, (byte) 0xd6, (byte) 0x5a, (byte) 0xc2, (byte) 0xe0, (byte) 0x5a, (byte) 0xb6,
		                   (byte) 0xbe, (byte) 0x6b, (byte) 0x4d, (byte) 0xb1, (byte) 0x2e, (byte) 0x20, (byte) 0x0d, (byte) 0x8a, (byte) 0x35, (byte) 0x19, (byte) 0x28, (byte) 0x7a, (byte) 0xc1, (byte) 0x8e, (byte) 0x3f, (byte) 0x1d,
		                   (byte) 0x87, (byte) 0xa9, (byte) 0x53, (byte) 0x96, (byte) 0x13, (byte) 0xe6, (byte) 0x4c, (byte) 0x16, (byte) 0x1b, (byte) 0x49, (byte) 0x0a, (byte) 0xdb, (byte) 0x88, (byte) 0xc5, (byte) 0xe6, (byte) 0xb1,
		                   (byte) 0x44, (byte) 0xdc, (byte) 0x35, (byte) 0xbd, (byte) 0x92, (byte) 0xcf, (byte) 0x53, (byte) 0x91, (byte) 0x81, (byte) 0xed, (byte) 0x70, (byte) 0xf0, (byte) 0xc0, (byte) 0xab, (byte) 0x41, (byte) 0xa8,
		                   (byte) 0xdd, (byte) 0xf2, (byte) 0x7d, (byte) 0xa4, (byte) 0x83, (byte) 0xa3, (byte) 0xb0, (byte) 0x4f, (byte) 0x77, (byte) 0xe0, (byte) 0x80, (byte) 0xba, (byte) 0xca, (byte) 0x5e, (byte) 0xf2, (byte) 0x01};
		
		page_data_ = new byte[]{(byte) 0x79, (byte) 0x4d, (byte) 0xe0, (byte) 0xa9, (byte) 0x2d, (byte) 0xc8, (byte) 0x46, (byte) 0x25, (byte) 0xec, (byte) 0xa2, (byte) 0xf4, (byte) 0xdb, (byte) 0x34, (byte) 0x19, (byte) 0xd3, (byte) 0x12,
		                    	(byte) 0xd4, (byte) 0xe6, (byte) 0xfc, (byte) 0x1b, (byte) 0xf3, (byte) 0xdd, (byte) 0x65, (byte) 0x7c, (byte) 0x08, (byte) 0x1c, (byte) 0x8b, (byte) 0x3f, (byte) 0xb4, (byte) 0x0d, (byte) 0x20, (byte) 0xb9,
		                    	(byte) 0x8a, (byte) 0x2a, (byte) 0x6b, (byte) 0xde, (byte) 0xe1, (byte) 0x67, (byte) 0xc3, (byte) 0xbd, (byte) 0x6e, (byte) 0xbf, (byte) 0xc1, (byte) 0xbd, (byte) 0xac, (byte) 0x6b, (byte) 0x39, (byte) 0x2a,
		                    	(byte) 0xc6, (byte) 0xd7, (byte) 0x1f, (byte) 0xab, (byte) 0xad, (byte) 0xa9, (byte) 0xa3, (byte) 0x41, (byte) 0xcb, (byte) 0x7e, (byte) 0x8d, (byte) 0x88, (byte) 0xee, (byte) 0x7f, (byte) 0x08, (byte) 0xe0,
		                    	(byte) 0x4e, (byte) 0x98, (byte) 0x36, (byte) 0x3d, (byte) 0xf8, (byte) 0xed, (byte) 0x9e, (byte) 0x1f, (byte) 0x59, (byte) 0xbd, (byte) 0x15, (byte) 0x52, (byte) 0x72, (byte) 0x23, (byte) 0xa0, (byte) 0xe2,
		                    	(byte) 0xc7, (byte) 0xa5, (byte) 0xe1, (byte) 0x60, (byte) 0xff, (byte) 0x0b, (byte) 0x75, (byte) 0xc4, (byte) 0x70, (byte) 0x0c, (byte) 0x73, (byte) 0x68, (byte) 0x39, (byte) 0xa8, (byte) 0x68, (byte) 0x7a,
		                    	(byte) 0x90, (byte) 0x70, (byte) 0x96, (byte) 0x65, (byte) 0x6f, (byte) 0x68, (byte) 0x54, (byte) 0x03, (byte) 0x30, (byte) 0x11, (byte) 0x3b, (byte) 0xed, (byte) 0x3a, (byte) 0x7f, (byte) 0x12, (byte) 0x81,
		                    	(byte) 0x2d, (byte) 0x99, (byte) 0x5a, (byte) 0x3c, (byte) 0x99, (byte) 0x67, (byte) 0x32, (byte) 0x67, (byte) 0x35, (byte) 0x3b, (byte) 0x03, (byte) 0xb8, (byte) 0x22, (byte) 0x0f, (byte) 0x68, (byte) 0x90,
		                    	(byte) 0x36, (byte) 0x20, (byte) 0xe2, (byte) 0x9c, (byte) 0x52, (byte) 0xbb, (byte) 0x03, (byte) 0x53, (byte) 0x49, (byte) 0x21, (byte) 0xcc, (byte) 0x67, (byte) 0xc0, (byte) 0x9c, (byte) 0x86, (byte) 0xad,
		                    	(byte) 0x97, (byte) 0xa4, (byte) 0xe5, (byte) 0x08, (byte) 0xa6, (byte) 0x6b, (byte) 0x63, (byte) 0x39, (byte) 0x68, (byte) 0x96, (byte) 0xdb, (byte) 0xe9, (byte) 0xda, (byte) 0x34, (byte) 0x38, (byte) 0xce,
		                    	(byte) 0xc7, (byte) 0x56, (byte) 0xaa, (byte) 0xca, (byte) 0x7c, (byte) 0xc3, (byte) 0xbc, (byte) 0x6b, (byte) 0xe8, (byte) 0x2d, (byte) 0x4a, (byte) 0x8c, (byte) 0x1d, (byte) 0xd4, (byte) 0x38, (byte) 0x28,
		                    	(byte) 0xe2, (byte) 0xf0, (byte) 0x4c, (byte) 0x52, (byte) 0xa5, (byte) 0xc6, (byte) 0x35, (byte) 0xc3, (byte) 0x6a, (byte) 0x5f, (byte) 0x69, (byte) 0x98, (byte) 0xfa, (byte) 0x7e, (byte) 0xee, (byte) 0xef,
		                    	(byte) 0x9c, (byte) 0x6a, (byte) 0x19, (byte) 0x24, (byte) 0x3e, (byte) 0xa2, (byte) 0xa1, (byte) 0x38, (byte) 0x4a, (byte) 0xd3, (byte) 0x45, (byte) 0x24, (byte) 0xf1, (byte) 0x17, (byte) 0xac, (byte) 0xa9,
		                    	(byte) 0x5f, (byte) 0x66, (byte) 0xf0, (byte) 0x50, (byte) 0x11, (byte) 0x45, (byte) 0x84, (byte) 0x7a, (byte) 0xfd, (byte) 0x8b, (byte) 0x41, (byte) 0xf3, (byte) 0xd9, (byte) 0x8f, (byte) 0x94, (byte) 0xe4,
		                    	(byte) 0xc9, (byte) 0xe0, (byte) 0x7f, (byte) 0xaf, (byte) 0xfb, (byte) 0x54, (byte) 0xbb, (byte) 0xa6, (byte) 0x86, (byte) 0x09, (byte) 0x62, (byte) 0xab, (byte) 0x96, (byte) 0x91, (byte) 0x24, (byte) 0x9b,
		                    	(byte) 0xd2, (byte) 0xa4, (byte) 0x60, (byte) 0x10, (byte) 0x8e, (byte) 0x68, (byte) 0x1c, (byte) 0x0e, (byte) 0x56, (byte) 0xe5, (byte) 0x8c, (byte) 0xa8, (byte) 0x6b, (byte) 0xe7, (byte) 0x43, (byte) 0x14};
		
		ecc_ = new byte[]{97, 19, 108};
		
		page_ecc_ = new byte[][]{{68, 100, 100}, {85, 100, 100}};
		
		ps2mc_ecc = ECC.getInstance();
	}
	
	public static TestECC getInstance() {
		if (instance == null)
			return new TestECC();
		return instance;
	}
	
	public void run_tests() {
		test_ecc_calculate();
		test_ecc_check_ok();
		test_ecc_check_correct_data();
		test_ecc_check_correct_ecc();
		test_ecc_check_fail();
		
		test_ecc_calculate_page();
	}
	
	public void test_ecc_calculate() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		
		assert Arrays.equals(ps2mc_ecc.ecc_calculate(s), ecc_) : "ECC calculation failed.";
		
		System.out.println("ECC calculation successful.");
	}
	
	public void test_ecc_check_ok() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		byte[] ecc = Arrays.copyOf(ecc_, ecc_.length);
		
		ECCFlags res = ps2mc_ecc.ecc_check(s, ecc);
		
		assert res == ECCFlags.ECC_CHECK_OK : "ECC check returned different flag.";
		
		System.out.println("ECC check returned ECC_CHECK_OK.");
	}
	
	public void test_ecc_check_correct_data() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		byte[] ecc = Arrays.copyOf(ecc_, ecc_.length);
		
		s[42] ^= 1;
		
		ECCFlags res = ps2mc_ecc.ecc_check(s, ecc);
		
		assert res == ECCFlags.ECC_CHECK_CORRECTED : "ECC check returned different flag.";

		assert Arrays.equals(s, data_);
		assert Arrays.equals(ecc, ecc_);
		
		System.out.println("ECC check returned ECC_CHECK_CORRECTED.");
	}
	
	public void test_ecc_check_correct_ecc() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		byte[] ecc = Arrays.copyOf(ecc_, ecc_.length);
		
		ecc[0] ^= 1;
		
		ECCFlags res = ps2mc_ecc.ecc_check(s, ecc);
		
		assert res == ECCFlags.ECC_CHECK_CORRECTED : "ECC check returned different flag.";

		assert Arrays.equals(s, data_);
		assert Arrays.equals(ecc, ecc_);
		
		System.out.println("ECC check returned ECC_CHECK_CORRECTED.");
	}
	
	public void test_ecc_check_fail() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		byte[] ecc = Arrays.copyOf(ecc_, ecc_.length);
		
		s[42] ^= 3;
		
		ECCFlags res = ps2mc_ecc.ecc_check(s, ecc);
		
		assert res == ECCFlags.ECC_CHECK_FAILED : "ECC check returned different flag.";
		
		System.out.println("ECC check returned ECC_CHECK_FAILED.");
	}
	
	public void test_ecc_calculate_page() {
		byte[] s = Arrays.copyOf(page_data_, page_data_.length);
		
		assert Arrays.deepEquals(ps2mc_ecc.ecc_calculate_page(s), page_ecc_) : "ECC calculation of memory page failed.";
		
		System.out.println("ECC calculation of memory page successful.");
	}
	
}
