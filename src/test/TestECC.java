package test;

import java.util.Arrays;

import utils.ECC;
import utils.ECCFlags;

public class TestECC {
	
	private byte[] data_;
	private byte[] ecc_;
	private ECC ps2mc_ecc;
	
	private void print_byte_array(byte[] arr) {
		System.out.print("[");
		
		for (int i = 0; i < arr.length; ++i) {
			if (i != 0)
				System.out.print(", ");
			System.out.print(String.format("%02x", arr[i]));
		}
		
		System.out.println("]");
	}
	
	public void test_ecc_calculate() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		
		assert Arrays.equals(ps2mc_ecc.ecc_calculate(s), ecc_);
		
		System.out.println("ECC calculation successful.");
	}
	
	public void test_ecc_check_ok() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		byte[] ecc = Arrays.copyOf(ecc_, ecc_.length);
		
		ECCFlags res = ps2mc_ecc.ecc_check(s, ecc);
		
		assert res == ECCFlags.ECC_CHECK_OK;
		assert Arrays.equals(s, data_);
		assert Arrays.equals(ecc, ecc_);
		
		System.out.println("ECC check returned ECC_CHECK_OK.");
	}
	
	public void test_ecc_check_correct_data() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		byte[] ecc = Arrays.copyOf(ecc_, ecc_.length);
		
		s[42] ^= 1;
		
		ECCFlags res = ps2mc_ecc.ecc_check(s, ecc);
		
		assert res == ECCFlags.ECC_CHECK_CORRECTED;
		assert Arrays.equals(s, data_);
		assert Arrays.equals(ecc, ecc_);
		
		System.out.println("ECC check returned ECC_CHECK_CORRECTED");
	}
	
	public void test_ecc_check_correct_ecc() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		byte[] ecc = Arrays.copyOf(ecc_, ecc_.length);
		
		ecc[0] ^= 1;
		
		ECCFlags res = ps2mc_ecc.ecc_check(s, ecc);
		
		assert res == ECCFlags.ECC_CHECK_CORRECTED;
		assert Arrays.equals(s, data_);
		assert Arrays.equals(ecc, ecc_);
		
		System.out.println("ECC check returned ECC_CHECK_CORRECTED");
	}
	
	public void test_ecc_check_fail() {
		byte[] s = Arrays.copyOf(data_, data_.length);
		byte[] ecc = Arrays.copyOf(ecc_, ecc_.length);
		
		s[42] ^= 3;
		
		ECCFlags res = ps2mc_ecc.ecc_check(s, ecc);
		
		assert res == ECCFlags.ECC_CHECK_FAILED;
		System.out.println("ECC check returned ECC_CHECK_FAILED");
	}
	
	public TestECC() {
		data_ = new byte[]{ (byte) 0x36, (byte) 0x76, (byte) 0x11, (byte) 0x24, (byte) 0xb1, (byte) 0xd2, (byte) 0xd2, (byte) 0x81, (byte) 0xd5, (byte) 0x47, (byte) 0x15, (byte) 0x41, (byte) 0xc9, (byte) 0x47, (byte) 0xb7, (byte) 0xf2,
							(byte) 0x6b, (byte) 0x00, (byte) 0x25, (byte) 0x34, (byte) 0x48, (byte) 0x1b, (byte) 0xbc, (byte) 0xcd, (byte) 0x07, (byte) 0x28, (byte) 0x9a, (byte) 0x88, (byte) 0x9c, (byte) 0xd3, (byte) 0x69, (byte) 0xda,
							(byte) 0x25, (byte) 0xa8, (byte) 0x39, (byte) 0x62, (byte) 0xd4, (byte) 0x8c, (byte) 0xc0, (byte) 0x25, (byte) 0x43, (byte) 0x04, (byte) 0x65, (byte) 0x22, (byte) 0xa8, (byte) 0xef, (byte) 0x44, (byte) 0x5f,
							(byte) 0x03, (byte) 0x91, (byte) 0xda, (byte) 0x23, (byte) 0x8c, (byte) 0x17, (byte) 0x24, (byte) 0xf5, (byte) 0x14, (byte) 0xf0, (byte) 0xd6, (byte) 0x5a, (byte) 0xc2, (byte) 0xe0, (byte) 0x5a, (byte) 0xb6,
							(byte) 0xbe, (byte) 0x6b, (byte) 0x4d, (byte) 0xb1, (byte) 0x2e, (byte) 0x20, (byte) 0x0d, (byte) 0x8a, (byte) 0x35, (byte) 0x19, (byte) 0x28, (byte) 0x7a, (byte) 0xc1, (byte) 0x8e, (byte) 0x3f, (byte) 0x1d,
							(byte) 0x87, (byte) 0xa9, (byte) 0x53, (byte) 0x96, (byte) 0x13, (byte) 0xe6, (byte) 0x4c, (byte) 0x16, (byte) 0x1b, (byte) 0x49, (byte) 0x0a, (byte) 0xdb, (byte) 0x88, (byte) 0xc5, (byte) 0xe6, (byte) 0xb1,
							(byte) 0x44, (byte) 0xdc, (byte) 0x35, (byte) 0xbd, (byte) 0x92, (byte) 0xcf, (byte) 0x53, (byte) 0x91, (byte) 0x81, (byte) 0xed, (byte) 0x70, (byte) 0xf0, (byte) 0xc0, (byte) 0xab, (byte) 0x41, (byte) 0xa8,
							(byte) 0xdd, (byte) 0xf2, (byte) 0x7d, (byte) 0xa4, (byte) 0x83, (byte) 0xa3, (byte) 0xb0, (byte) 0x4f, (byte) 0x77, (byte) 0xe0, (byte) 0x80, (byte) 0xba, (byte) 0xca, (byte) 0x5e, (byte) 0xf2, (byte) 0x01};
		ecc_ = new byte[]{97, 19, 108};
		ps2mc_ecc = new ECC();
		print_byte_array(data_);
		print_byte_array(ecc_);
	}
}
