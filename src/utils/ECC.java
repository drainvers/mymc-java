package utils;

public class ECC {
	
	private byte[] parity_table;
	private byte[] column_parity_masks;
	
	private int popcount(byte a) {
		int count = 0;
		
		while (a != 0) {
			a &= a - 1;
			count += 1;
		}
		
		return count;
	}
	
	private byte parityb(byte a) {
		a = (byte) (a ^ (a >> 1));
		a = (byte) (a ^ (a >> 2));
		a = (byte) (a ^ (a >> 4));
		
		return a;
	}
	
	private void make_ecc_table() {
		byte[] cpmasks = {0x55, 0x33, 0x0F, 0x00, (byte) 0xAA, (byte) 0xCC, (byte) 0xF0};
		
		for (int b = 0; b < 256; ++b)
			parity_table[b] = parityb((byte) b);
		
		for (int b = 0; b < 256; ++b) {
			byte mask = 0;
			
			for (int i = 0; i < cpmasks.length; ++i) {
				mask |= parity_table[b & cpmasks[i]] << i;
				column_parity_masks[b] = mask;
			}
		}
	}
	
	public byte[] ecc_calculate(byte[] s) {
		/* Calculate the Hamming code for a byte array. */
		byte column_parity = 0x77;
		byte line_parity_0 = 0x7F;
		byte line_parity_1 = 0x7F;
		
		for (int i = 0; i < s.length; ++i) {
			byte b = s[i];
			column_parity ^= column_parity_masks[b];
			if (parity_table[b] != 0) {
				line_parity_0 ^= ~((byte) i);
				line_parity_1 ^= (byte) i;
			}
			
		}
		
		return new byte[]{column_parity, (byte) (line_parity_0 & 0x7F), line_parity_1};
	}
	
	public byte[] ecc_calculate(String s) {
		/* Calculate the Hamming code for a 128 byte long string. */
		return ecc_calculate(s.getBytes());
	}
	
	public ECCFlags ecc_check(byte[] s, byte[] ecc) {
		/*
		 * Detect and correct any single bit errors.
		 * 
		 * The parameters "s" and "ecc", the data and expected Hamming code
		 * repectively, must be modifiable sequences of integers and are
		 * updated with the corrected values if necessary.
		 */
		byte[] computed = ecc_calculate(s);
		
		if (computed.equals(ecc))
			return ECCFlags.ECC_CHECK_OK;
		
		// ECC mismatch
		
		byte cp_diff = (byte) ((computed[0] ^ ecc[0]) & 0x77);
		byte lp0_diff = (byte) ((computed[1] ^ ecc[1]) & 0x7F);
		byte lp1_diff = (byte) ((computed[2] ^ ecc[2]) & 0x7F);
		byte lp_comp = (byte) (lp0_diff ^ lp1_diff);
		byte cp_comp = (byte) ((cp_diff >> 4) ^ (cp_diff & 0x07));
		
		if (lp_comp == 0x7F && cp_comp == 0x07) {
			System.out.println("corrected 1");
			// correctable 1 bit error in data
			s[lp1_diff] ^= 1 << (cp_diff >> 4);
			return ECCFlags.ECC_CHECK_CORRECTED;
		}
		
		if ((cp_diff == 0 && lp0_diff == 0 && lp1_diff == 0)
		      || popcount(lp_comp) + popcount(cp_comp) == 1) {
			System.out.println("corrected 2");
			// correctable 1 bit error in ECC
			// (and/or one of the unused bits was set)
			ecc[0] = computed[0];
			ecc[1] = computed[1];
			ecc[2] = computed[2];
			return ECCFlags.ECC_CHECK_CORRECTED;
		}
		
		// Uncorrectable error
		return ECCFlags.ECC_CHECK_FAILED;
	}
	
	public byte[][] ecc_calculate_page() {
		/* Return a list of the ECC codes for a PS2 memory card page. */
		
		return new byte[1][1]; // Temporary stub
	}
	
	public ECC() {
		parity_table = new byte[256];
		column_parity_masks = new byte[256];
		make_ecc_table();
	}

}
