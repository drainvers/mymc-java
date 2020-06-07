package utils;

public interface Constants {
	// PS2 Memory Card Constants
	public static final String PS2MC_MAGIC = "Sony PS2 Memory Card Format ";
	public static final int PS2MC_FAT_ALLOCATED_BIT = 0x80000000;
	public static final int PS2MC_FAT_CHAIN_END = 0xFFFFFFFF;
	public static final int PS2MC_FAT_CHAIN_END_UNALLOC = 0x7FFFFFFF;
	public static final int PS2MC_FAT_CLUSTER_MASK = 0x7FFFFFFF;
	public static final int PS2MC_MAX_INDIRECT_FAT_CLUSTERS = 32;
	public static final int PS2MC_CLUSTER_SIZE = 1024;
	public static final int PS2MC_INDIRECT_FAT_OFFSET = 0x2000;

	public static final int PS2MC_STANDARD_PAGE_SIZE = 512;
	public static final int PS2MC_STANDARD_PAGES_PER_CARD = 16384;
	public static final int PS2MC_STANDARD_PAGES_PER_ERASE_BLOCK = 16;
	
	// PS2 Directory Entry Constants
	public static final int PS2MC_DIRENT_LENGTH = 512;
	
	public static final int DF_READ = 0x0001;
	public static final int DF_WRITE = 0x0002;
	public static final int DF_EXECUTE = 0x0004;
	public static final int DF_RWX = DF_READ | DF_WRITE | DF_EXECUTE;
	public static final int DF_PROTECTED = 0x0008;
	public static final int DF_FILE = 0x0010;
	public static final int DF_DIR = 0x0020;
	public static final int DF_O_DCREAT = 0x0040;
	public static final int DF_0080 = 0x0080;
	public static final int DF_0100 = 0x0100;
	public static final int DF_O_CREAT = 0x0200;
	public static final int DF_0400 = 0x0400;
	public static final int DF_POCKETSTN = 0x0800;
	public static final int DF_PSX = 0x1000;
	public static final int DF_HIDDEN = 0x2000;
	public static final int DF_4000 = 0x4000;
	public static final int DF_EXISTS = 0x8000;
	 
}
