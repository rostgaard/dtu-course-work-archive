-- Listing 11.1
-- Single-port RAM with synchronous read
-- Modified from XST 8.1i rams_07
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
entity lc3_mem is
  generic(
    ADDR_WIDTH: integer:=12;
    DATA_WIDTH: integer:=8
    );
  port(
    clk: in std_logic;
	 reset: in std_logic;
	 rx: in std_logic;
	 tx: out std_logic;
    we, re: in std_logic; -- Write and read enable
    addr: in std_logic_vector(ADDR_WIDTH-1 downto 0);
    data: inout std_logic_vector(DATA_WIDTH-1 downto 0);
	 sw: in std_logic_vector(7 downto 0);
	 sseg_reg: out std_logic_vector(15 downto 0);
	 leds_reg: out std_logic_vector(7 downto 0);
	 btn: in std_logic_vector(4 downto 0);
      hsync, vsync: out  std_logic;
      rgb: out std_logic_vector(2 downto 0)
    );
end lc3_mem;

architecture beh_arch of lc3_mem is
  type ram_type is array (0 to 2**ADDR_WIDTH-1)
    of std_logic_vector (DATA_WIDTH-1 downto 0);
  signal ram: ram_type := (
-- Trap Vector Table             (0-255)
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0000 to 0x0007
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0008 to 0x000f
X"034b", X"034b", X"035a", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0010 to 0x0017
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0018 to 0x001f
X"035c", X"0360", X"0366", X"0373", X"0381", X"0300", X"034b", X"034b",  -- addr 0x0020 to 0x0027
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0028 to 0x002f
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0030 to 0x0037
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0038 to 0x003f
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0040 to 0x0047
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0048 to 0x004f
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0050 to 0x0057
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0058 to 0x005f
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0060 to 0x0067
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0068 to 0x006f
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0070 to 0x0077
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0078 to 0x007f
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0080 to 0x0087
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0088 to 0x008f
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0090 to 0x0097
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x0098 to 0x009f
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00a0 to 0x00a7
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00a8 to 0x00af
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00b0 to 0x00b7
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00b8 to 0x00bf
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00c0 to 0x00c7
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00c8 to 0x00cf
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00d0 to 0x00d7
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00d8 to 0x00df
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00e0 to 0x00e7
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00e8 to 0x00ef
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00f0 to 0x00f7
X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b", X"034b",  -- addr 0x00f8 to 0x00ff
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0100 to 0x0107
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0108 to 0x010f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0110 to 0x0117
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0118 to 0x011f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0120 to 0x0127
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0128 to 0x012f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0130 to 0x0137
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0138 to 0x013f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0140 to 0x0147
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0148 to 0x014f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0150 to 0x0157
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0158 to 0x015f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0160 to 0x0167
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0168 to 0x016f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0170 to 0x0177
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0178 to 0x017f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0180 to 0x0187
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0188 to 0x018f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0190 to 0x0197
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x0198 to 0x019f
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01a0 to 0x01a7
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01a8 to 0x01af
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01b0 to 0x01b7
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01b8 to 0x01bf
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01c0 to 0x01c7
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01c8 to 0x01cf
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01d0 to 0x01d7
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01d8 to 0x01df
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01e0 to 0x01e7
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01e8 to 0x01ef
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0351",  -- addr 0x01f0 to 0x01f7
X"0351", X"0351", X"0351", X"0351", X"0351", X"0351", X"0354", X"0357",  -- addr 0x01f8 to 0x01ff
X"e00b", X"48d5", X"2007", X"b003", X"2006", X"b002", X"0e27", X"fe16",  -- addr 0x0200 to 0x0207
X"fe12", X"fe00", X"00ff", X"cccc", X"000a", X"002d", X"002d", X"002d",  -- addr 0x0208 to 0x020f
X"0020", X"0057", X"0061", X"0069", X"0074", X"0069", X"006e", X"0067",  -- addr 0x0210 to 0x0217
X"0020", X"0066", X"006f", X"0072", X"0020", X"0070", X"0072", X"006f",  -- addr 0x0218 to 0x021f
X"0067", X"0072", X"0061", X"006d", X"002e", X"002e", X"002e", X"000a",  -- addr 0x0220 to 0x0227
X"0000", X"ffe5", X"ffaf", X"ffb9", X"ffab", X"ffad", X"48b1", X"23f9",  -- addr 0x0228 to 0x022f
X"1001", X"0bfc", X"48ad", X"23f6", X"1201", X"040a", X"23f4", X"1201",  -- addr 0x0230 to 0x0237
X"0411", X"23f2", X"1201", X"041b", X"23f0", X"1201", X"0477", X"0fee",  -- addr 0x0238 to 0x023f
X"e002", X"4895", X"0feb", X"0052", X"0065", X"0061", X"0064", X"0079",  -- addr 0x0240 to 0x0247
X"002e", X"0000", X"489b", X"1820", X"b1bb", X"4898", X"1a20", X"b1b8",  -- addr 0x0248 to 0x024f
X"bbb7", X"6140", X"489d", X"1b61", X"193f", X"03fa", X"0fd7", X"488e",  -- addr 0x0250 to 0x0257
X"1820", X"b1ae", X"488b", X"1a20", X"b1ab", X"4888", X"bba9", X"7140",  -- addr 0x0258 to 0x025f
X"1b61", X"193f", X"03fa", X"e002", X"4872", X"0fc8", X"000a", X"0050",  -- addr 0x0260 to 0x0267
X"0072", X"006f", X"0067", X"0072", X"0061", X"006d", X"006d", X"0069",  -- addr 0x0268 to 0x026f
X"006e", X"0067", X"0020", X"0064", X"006f", X"006e", X"0065", X"002e",  -- addr 0x0270 to 0x0277
X"000a", X"002d", X"002d", X"002d", X"0020", X"0050", X"0072", X"0065",  -- addr 0x0278 to 0x027f
X"0073", X"0073", X"0020", X"0072", X"0065", X"0073", X"0065", X"0074",  -- addr 0x0280 to 0x0287
X"0028", X"0045", X"004e", X"0054", X"0045", X"0052", X"0020", X"0070",  -- addr 0x0288 to 0x028f
X"0075", X"0073", X"0068", X"002d", X"0062", X"0075", X"0074", X"0074",  -- addr 0x0290 to 0x0297
X"006f", X"006e", X"0029", X"0020", X"006f", X"0072", X"0020", X"0070",  -- addr 0x0298 to 0x029f
X"0072", X"006f", X"0067", X"0072", X"0061", X"006d", X"0020", X"006e",  -- addr 0x02a0 to 0x02a7
X"0065", X"0078", X"0074", X"0020", X"0062", X"006c", X"006f", X"0063",  -- addr 0x02a8 to 0x02af
X"006b", X"002e", X"002e", X"002e", X"000a", X"0000", X"5020", X"b14f",  -- addr 0x02b0 to 0x02b7
X"b14f", X"e003", X"481c", X"482a", X"c000", X"000a", X"004a", X"0075",  -- addr 0x02b8 to 0x02bf
X"006d", X"0070", X"0069", X"006e", X"0067", X"0020", X"0074", X"006f",  -- addr 0x02c0 to 0x02c7
X"0020", X"0075", X"0073", X"0065", X"0072", X"0020", X"0063", X"006f",  -- addr 0x02c8 to 0x02cf
X"0064", X"0065", X"002e", X"000a", X"0000", X"fe04", X"fe06", X"1220",  -- addr 0x02d0 to 0x02d7
X"6040", X"0405", X"a5fa", X"07fe", X"b1f9", X"1261", X"0ff9", X"c1c0",  -- addr 0x02d8 to 0x02df
X"2528", X"6080", X"07fe", X"6082", X"c1c0", X"0100", X"2522", X"6080",  -- addr 0x02e0 to 0x02e7
X"07fe", X"6082", X"6280", X"07fe", X"6282", X"1018", X"1040", X"c1c0",  -- addr 0x02e8 to 0x02ef
X"a3e4", X"07fe", X"5218", X"b3e2", X"a3e0", X"07fe", X"b1df", X"c1c0",  -- addr 0x02f0 to 0x02f7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x02f8 to 0x02ff
X"3eff", X"e007", X"f022", X"a0b7", X"22b0", X"5001", X"b0b4", X"2ef8",  -- addr 0x0300 to 0x0307
X"c1c0", X"000a", X"000a", X"002d", X"002d", X"002d", X"0020", X"0068",  -- addr 0x0308 to 0x030f
X"0061", X"006c", X"0074", X"0069", X"006e", X"0067", X"0020", X"0074",  -- addr 0x0310 to 0x0317
X"0068", X"0065", X"0020", X"004c", X"0043", X"002d", X"0033", X"0020",  -- addr 0x0318 to 0x031f
X"002d", X"002d", X"002d", X"000a", X"000a", X"0000", X"000a", X"000a",  -- addr 0x0320 to 0x0327
X"002d", X"002d", X"002d", X"0020", X"0075", X"006e", X"0064", X"0065",  -- addr 0x0328 to 0x032f
X"0066", X"0069", X"006e", X"0065", X"0064", X"0020", X"0074", X"0072",  -- addr 0x0330 to 0x0337
X"0061", X"0070", X"0020", X"0065", X"0078", X"0065", X"0063", X"0075",  -- addr 0x0338 to 0x033f
X"0074", X"0065", X"0064", X"0020", X"002d", X"002d", X"002d", X"000a",  -- addr 0x0340 to 0x0347
X"000a", X"0000", X"eeee", X"21fe", X"b070", X"0fb2", X"e1d7", X"f022",  -- addr 0x0348 to 0x034f
X"0faf", X"5020", X"103d", X"0ff8", X"5020", X"103e", X"0ff5", X"5020",  -- addr 0x0350 to 0x0357
X"103f", X"0ff2", X"b062", X"c1c0", X"a05a", X"07fe", X"a059", X"c1c0",  -- addr 0x0358 to 0x035f
X"32a0", X"a257", X"07fe", X"b056", X"229c", X"c1c0", X"309d", X"329d",  -- addr 0x0360 to 0x0367
X"3e9f", X"1220", X"6040", X"0403", X"f021", X"1261", X"0ffb", X"2094",  -- addr 0x0368 to 0x036f
X"2294", X"2e96", X"c1c0", X"328e", X"3e8e", X"e02a", X"f022", X"f020",  -- addr 0x0370 to 0x0377
X"f021", X"308a", X"5020", X"102a", X"f021", X"2086", X"2283", X"2e83",  -- addr 0x0378 to 0x037f
X"c1c0", X"3082", X"3282", X"3482", X"3682", X"3e82", X"1220", X"6440",  -- addr 0x0380 to 0x0387
X"202d", X"5002", X"040f", X"f021", X"5020", X"1628", X"1000", X"14a0",  -- addr 0x0388 to 0x038f
X"0601", X"1021", X"1482", X"16ff", X"03f9", X"1020", X"0403", X"f021",  -- addr 0x0390 to 0x0397
X"1261", X"0fed", X"2069", X"2269", X"2469", X"2669", X"2e69", X"c1c0",  -- addr 0x0398 to 0x039f
X"000a", X"0049", X"006e", X"0070", X"0075", X"0074", X"0020", X"0061",  -- addr 0x03a0 to 0x03a7
X"0020", X"0063", X"0068", X"0061", X"0072", X"0061", X"0063", X"0074",  -- addr 0x03a8 to 0x03af
X"0065", X"0072", X"003e", X"0020", X"0000", X"7fff", X"00ff", X"fe00",  -- addr 0x03b0 to 0x03b7
X"fe02", X"fe04", X"fe06", X"fffe", X"fe10", X"fe12", X"0000", X"0000",  -- addr 0x03b8 to 0x03bf
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x03c0 to 0x03c7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x03c8 to 0x03cf
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x03d0 to 0x03d7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x03d8 to 0x03df
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x03e0 to 0x03e7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x03e8 to 0x03ef
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x03f0 to 0x03f7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x03f8 to 0x03ff
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0400 to 0x0407
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0408 to 0x040f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0410 to 0x0417
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0418 to 0x041f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0420 to 0x0427
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0428 to 0x042f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0430 to 0x0437
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0438 to 0x043f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0440 to 0x0447
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0448 to 0x044f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0450 to 0x0457
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0458 to 0x045f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0460 to 0x0467
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0468 to 0x046f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0470 to 0x0477
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0478 to 0x047f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0480 to 0x0487
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0488 to 0x048f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0490 to 0x0497
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x0498 to 0x049f
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04a0 to 0x04a7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04a8 to 0x04af
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04b0 to 0x04b7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04b8 to 0x04bf
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04c0 to 0x04c7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04c8 to 0x04cf
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04d0 to 0x04d7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04d8 to 0x04df
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04e0 to 0x04e7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04e8 to 0x04ef
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04f0 to 0x04f7
X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000", X"0000",  -- addr 0x04f8 to 0x04ff
X"e004", X"f022", X"2001", X"c000", X"0916", X"000a", X"0020", X"002a",  -- addr 0x0500 to 0x0507
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x0508 to 0x050f
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x0510 to 0x0517
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x0518 to 0x051f
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x0520 to 0x0527
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x0528 to 0x052f
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x0530 to 0x0537
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x0538 to 0x053f
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x0540 to 0x0547
X"002a", X"000a", X"0020", X"002a", X"0020", X"0020", X"0054", X"0068",  -- addr 0x0548 to 0x054f
X"0069", X"0073", X"0020", X"0075", X"0073", X"0065", X"0072", X"0020",  -- addr 0x0550 to 0x0557
X"0070", X"0072", X"006f", X"0067", X"0072", X"0061", X"006d", X"0020",  -- addr 0x0558 to 0x055f
X"0064", X"006f", X"0065", X"0073", X"006e", X"0027", X"0074", X"0020",  -- addr 0x0560 to 0x0567
X"0064", X"006f", X"0020", X"0061", X"006e", X"0079", X"0074", X"0068",  -- addr 0x0568 to 0x056f
X"0069", X"006e", X"0067", X"0020", X"0069", X"006e", X"0074", X"0065",  -- addr 0x0570 to 0x0577
X"0072", X"0065", X"0073", X"0074", X"0069", X"006e", X"0067", X"002e",  -- addr 0x0578 to 0x057f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0580 to 0x0587
X"0020", X"0020", X"0020", X"0020", X"002a", X"000a", X"0020", X"002a",  -- addr 0x0588 to 0x058f
X"0020", X"0020", X"0059", X"006f", X"0075", X"0020", X"0073", X"0068",  -- addr 0x0590 to 0x0597
X"006f", X"0075", X"006c", X"0064", X"0020", X"0074", X"0072", X"0079",  -- addr 0x0598 to 0x059f
X"0020", X"0074", X"006f", X"0020", X"0075", X"0070", X"006c", X"006f",  -- addr 0x05a0 to 0x05a7
X"0061", X"0064", X"0020", X"0079", X"006f", X"0075", X"0072", X"0020",  -- addr 0x05a8 to 0x05af
X"006f", X"0077", X"006e", X"0020", X"0070", X"0072", X"006f", X"0067",  -- addr 0x05b0 to 0x05b7
X"0072", X"0061", X"006d", X"003a", X"0020", X"0020", X"0020", X"0020",  -- addr 0x05b8 to 0x05bf
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x05c0 to 0x05c7
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x05c8 to 0x05cf
X"002a", X"000a", X"0020", X"002a", X"0020", X"0020", X"0020", X"0020",  -- addr 0x05d0 to 0x05d7
X"0031", X"002e", X"0020", X"0043", X"006f", X"006d", X"0070", X"0069",  -- addr 0x05d8 to 0x05df
X"006c", X"0065", X"0020", X"0079", X"006f", X"0075", X"0072", X"0020",  -- addr 0x05e0 to 0x05e7
X"0070", X"0072", X"006f", X"0067", X"0072", X"0061", X"006d", X"0020",  -- addr 0x05e8 to 0x05ef
X"0028", X"0070", X"0072", X"006f", X"0064", X"0075", X"0063", X"0065",  -- addr 0x05f0 to 0x05f7
X"0020", X"002e", X"006f", X"0062", X"006a", X"0020", X"0066", X"0069",  -- addr 0x05f8 to 0x05ff
X"006c", X"0065", X"0029", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0600 to 0x0607
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0608 to 0x060f
X"0020", X"0020", X"0020", X"0020", X"002a", X"000a", X"0020", X"002a",  -- addr 0x0610 to 0x0617
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"005b",  -- addr 0x0618 to 0x061f
X"006f", X"0070", X"0074", X"0069", X"006f", X"006e", X"0031", X"005d",  -- addr 0x0620 to 0x0627
X"0020", X"0055", X"0073", X"0065", X"0020", X"004c", X"0043", X"0033",  -- addr 0x0628 to 0x062f
X"0045", X"0064", X"0069", X"0074", X"002e", X"0065", X"0078", X"0065",  -- addr 0x0630 to 0x0637
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0638 to 0x063f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0640 to 0x0647
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0648 to 0x064f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0650 to 0x0657
X"002a", X"000a", X"0020", X"002a", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0658 to 0x065f
X"0020", X"0020", X"0020", X"005b", X"006f", X"0070", X"0074", X"0069",  -- addr 0x0660 to 0x0667
X"006f", X"006e", X"0032", X"005d", X"0020", X"0055", X"0073", X"0065",  -- addr 0x0668 to 0x066f
X"0020", X"004c", X"0043", X"0033", X"0020", X"0063", X"006f", X"006d",  -- addr 0x0670 to 0x0677
X"006d", X"0061", X"006e", X"0064", X"0020", X"006c", X"0069", X"006e",  -- addr 0x0678 to 0x067f
X"0065", X"0020", X"0061", X"0073", X"0073", X"0065", X"006d", X"0062",  -- addr 0x0680 to 0x0687
X"006c", X"0065", X"0072", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0688 to 0x068f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0690 to 0x0697
X"0020", X"0020", X"0020", X"0020", X"002a", X"000a", X"0020", X"002a",  -- addr 0x0698 to 0x069f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x06a0 to 0x06a7
X"0020", X"0020", X"006c", X"0063", X"0033", X"0061", X"0073", X"0020",  -- addr 0x06a8 to 0x06af
X"0061", X"0073", X"006d", X"005f", X"0073", X"006f", X"0075", X"0072",  -- addr 0x06b0 to 0x06b7
X"0063", X"0065", X"002e", X"0061", X"0073", X"006d", X"0020", X"0020",  -- addr 0x06b8 to 0x06bf
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x06c0 to 0x06c7
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x06c8 to 0x06cf
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x06d0 to 0x06d7
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x06d8 to 0x06df
X"002a", X"000a", X"0020", X"002a", X"0020", X"0020", X"0020", X"0020",  -- addr 0x06e0 to 0x06e7
X"0020", X"0020", X"0020", X"005b", X"006f", X"0070", X"0074", X"0069",  -- addr 0x06e8 to 0x06ef
X"006f", X"006e", X"0033", X"005d", X"0020", X"0043", X"006f", X"006d",  -- addr 0x06f0 to 0x06f7
X"0070", X"0069", X"006c", X"0065", X"0020", X"0043", X"0020", X"0073",  -- addr 0x06f8 to 0x06ff
X"006f", X"0075", X"0072", X"0063", X"0065", X"0020", X"0020", X"0020",  -- addr 0x0700 to 0x0707
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0708 to 0x070f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0710 to 0x0717
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0718 to 0x071f
X"0020", X"0020", X"0020", X"0020", X"002a", X"000a", X"0020", X"002a",  -- addr 0x0720 to 0x0727
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0728 to 0x072f
X"0020", X"0020", X"006c", X"0063", X"0063", X"0020", X"002d", X"006f",  -- addr 0x0730 to 0x0737
X"0020", X"0063", X"005f", X"0073", X"006f", X"0075", X"0072", X"0063",  -- addr 0x0738 to 0x073f
X"0065", X"002e", X"006f", X"0062", X"006a", X"0020", X"0063", X"005f",  -- addr 0x0740 to 0x0747
X"0073", X"006f", X"0075", X"0072", X"0063", X"0065", X"002e", X"0063",  -- addr 0x0748 to 0x074f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0750 to 0x0757
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0758 to 0x075f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0760 to 0x0767
X"002a", X"000a", X"0020", X"002a", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0768 to 0x076f
X"0032", X"002e", X"0020", X"0041", X"0063", X"0074", X"0069", X"0076",  -- addr 0x0770 to 0x0777
X"0061", X"0074", X"0065", X"0020", X"0070", X"0072", X"006f", X"0067",  -- addr 0x0778 to 0x077f
X"0072", X"0061", X"006d", X"006d", X"0065", X"0072", X"0020", X"006f",  -- addr 0x0780 to 0x0787
X"006e", X"0020", X"0046", X"0050", X"0047", X"0041", X"0020", X"0028",  -- addr 0x0788 to 0x078f
X"0070", X"0075", X"0073", X"0068", X"0020", X"0060", X"004c", X"0045",  -- addr 0x0790 to 0x0797
X"0046", X"0054", X"0027", X"0020", X"0070", X"0075", X"0073", X"0068",  -- addr 0x0798 to 0x079f
X"002d", X"0062", X"0075", X"0074", X"0074", X"006f", X"006e", X"0029",  -- addr 0x07a0 to 0x07a7
X"0020", X"0020", X"0020", X"0020", X"002a", X"000a", X"0020", X"002a",  -- addr 0x07a8 to 0x07af
X"0020", X"0020", X"0020", X"0020", X"0033", X"002e", X"0020", X"0052",  -- addr 0x07b0 to 0x07b7
X"0069", X"0067", X"0068", X"0074", X"0020", X"0063", X"006c", X"0069",  -- addr 0x07b8 to 0x07bf
X"0063", X"006b", X"0020", X"006f", X"006e", X"0020", X"002e", X"006f",  -- addr 0x07c0 to 0x07c7
X"0062", X"006a", X"0020", X"0066", X"0069", X"006c", X"0065", X"0020",  -- addr 0x07c8 to 0x07cf
X"0061", X"006e", X"0064", X"0020", X"0073", X"0065", X"006c", X"0065",  -- addr 0x07d0 to 0x07d7
X"0063", X"0074", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x07d8 to 0x07df
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x07e0 to 0x07e7
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x07e8 to 0x07ef
X"002a", X"000a", X"0020", X"002a", X"0020", X"0020", X"0020", X"0020",  -- addr 0x07f0 to 0x07f7
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0043", X"003a",  -- addr 0x07f8 to 0x07ff
X"005c", X"006c", X"0063", X"0033", X"005c", X"0062", X"0069", X"006e",  -- addr 0x0800 to 0x0807
X"005c", X"004c", X"0043", X"0033", X"0054", X"0065", X"0072", X"006d",  -- addr 0x0808 to 0x080f
X"0069", X"006e", X"0061", X"006c", X"002e", X"0065", X"0078", X"0065",  -- addr 0x0810 to 0x0817
X"0020", X"0069", X"006e", X"0020", X"0022", X"004f", X"0070", X"0065",  -- addr 0x0818 to 0x081f
X"006e", X"0020", X"0077", X"0069", X"0074", X"0068", X"0022", X"0020",  -- addr 0x0820 to 0x0827
X"0064", X"0069", X"0061", X"006c", X"006f", X"0067", X"0020", X"0020",  -- addr 0x0828 to 0x082f
X"0020", X"0020", X"0020", X"0020", X"002a", X"000a", X"0020", X"002a",  -- addr 0x0830 to 0x0837
X"0020", X"0020", X"0020", X"0020", X"0034", X"002e", X"0020", X"0057",  -- addr 0x0838 to 0x083f
X"0061", X"0069", X"0074", X"0020", X"0066", X"006f", X"0072", X"0020",  -- addr 0x0840 to 0x0847
X"0070", X"0072", X"006f", X"0067", X"0072", X"0061", X"006d", X"006d",  -- addr 0x0848 to 0x084f
X"0069", X"006e", X"0067", X"0020", X"0074", X"006f", X"0020", X"0066",  -- addr 0x0850 to 0x0857
X"0069", X"006e", X"0069", X"0073", X"0068", X"0020", X"0020", X"0020",  -- addr 0x0858 to 0x085f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0860 to 0x0867
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0868 to 0x086f
X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0870 to 0x0877
X"002a", X"000a", X"0020", X"002a", X"0020", X"0020", X"0020", X"0020",  -- addr 0x0878 to 0x087f
X"0020", X"0020", X"0020", X"0054", X"0068", X"0065", X"0020", X"0049",  -- addr 0x0880 to 0x0887
X"002f", X"004f", X"0020", X"0062", X"006f", X"0061", X"0072", X"0064",  -- addr 0x0888 to 0x088f
X"0020", X"006c", X"0065", X"0064", X"0073", X"0020", X"0077", X"0069",  -- addr 0x0890 to 0x0897
X"006c", X"006c", X"0020", X"0067", X"006f", X"0020", X"006f", X"0066",  -- addr 0x0898 to 0x089f
X"0066", X"0020", X"0061", X"006e", X"0064", X"0020", X"006d", X"0065",  -- addr 0x08a0 to 0x08a7
X"0073", X"0073", X"0061", X"0067", X"0065", X"0020", X"0077", X"0069",  -- addr 0x08a8 to 0x08af
X"006c", X"006c", X"0020", X"0061", X"0070", X"0070", X"0065", X"0061",  -- addr 0x08b0 to 0x08b7
X"0072", X"002e", X"0020", X"0020", X"002a", X"000a", X"0020", X"002a",  -- addr 0x08b8 to 0x08bf
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x08c0 to 0x08c7
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x08c8 to 0x08cf
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x08d0 to 0x08d7
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x08d8 to 0x08df
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x08e0 to 0x08e7
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x08e8 to 0x08ef
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x08f0 to 0x08f7
X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a", X"002a",  -- addr 0x08f8 to 0x08ff
X"002a", X"000a", X"000a", X"0000", X"004b", X"0065", X"0079", X"0020",  -- addr 0x0900 to 0x0907
X"0070", X"0072", X"0065", X"0073", X"0073", X"0065", X"0064", X"003a",  -- addr 0x0908 to 0x090f
X"0020", X"005b", X"0020", X"005d", X"000a", X"0000", X"e3ed", X"e5fe",  -- addr 0x0910 to 0x0917
X"14bc", X"f020", X"7080", X"1060", X"f022", X"0ffb",  -- addr 0x0918 to 0x091e
others => X"0000"


);
  signal addr_reg: std_logic_vector(ADDR_WIDTH-1 downto 0);

  -- Chip selector signals
  signal cs_mem: std_logic;
  signal cs_stdin_status: std_logic;
  signal uart_rd_en: std_logic;
  signal cs_stdout_status: std_logic;
  signal uart_wr_en: std_logic;
  signal cs_switch_data: std_logic;
  signal cs_btn_data: std_logic;
  signal cs_sseg: std_logic;
  signal cs_led: std_logic;
  signal cs_display: std_logic;
  
  -- uart specific signals
  signal uart_w_data: std_logic_vector(7 downto 0);
  signal uart_r_data: std_logic_vector(7 downto 0);
  signal tx_full, rx_empty: std_logic;
  
  -- Display specific signals
   signal pixel_x, pixel_y: std_logic_vector(9 downto 0);
   signal video_on, pixel_tick, scaled_clk: std_logic;
   signal rgb_reg, rgb_next: std_logic_vector(2 downto 0);
	signal video_input_reg: std_logic_vector(15 downto 0);
	
  
begin

   -- instantiate uart
   uart_unit: entity work.uart(str_arch)
      port map(clk=>clk, 
					reset=>reset, 
					rd_uart=> uart_rd_en,
               wr_uart=> uart_wr_en,
					rx=>rx, 
					w_data=>uart_w_data,
               tx_full=>tx_full, 
					rx_empty=>rx_empty,
               r_data=>uart_r_data, 
					tx=>tx);




  -- Display specific
  
	-- For use with 100Mhz system clock
   clk_scaler: entity work.mod_m_counter(arch)
      generic map(M=>2, N=>2)
      port map(clk=>clk, reset=>reset,
               q=>open, max_tick=>scaled_clk);

					
   -- instantiate VGA sync circuit
   vga_sync_unit: entity work.vga_sync
      port map(clk=>scaled_clk, reset=>reset,
               hsync=>hsync, vsync=>vsync,
               video_on=>video_on, p_tick=>pixel_tick,
               pixel_x=>pixel_x, pixel_y=>pixel_y);
   -- instantiate full-screen text generator
   text_gen_unit: entity work.text_screen_gen
      port map(clk=>clk, reset=>reset, cs=>cs_display, input_reg=>video_input_reg,
               video_on=>video_on, pixel_x=>pixel_x,
               pixel_y=>pixel_y, text_rgb=>rgb_next);
   -- rgb buffer
   process (clk)
   begin
      if (clk'event and clk='1') then
         if (pixel_tick='1') then
            rgb_reg <= rgb_next;
         end if;
      end if;
   end process;
   rgb <= rgb_reg;


  -- Display chip select
  cs_display <= '1' when addr = X"FE18" and we = '1' else '0';
  video_input_reg <= data;


  --reserved space in software memory, rest is for I/O
	cs_mem <= '1' when addr >= X"0000" and addr <= X"DFFF" else '0';
  -- cs_mem is In/Out. This is the main memory
  data <= ram(to_integer(unsigned(addr_reg))) when re = '1' and cs_mem = '1' 
    else (others => 'Z');
	 
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if (we='1') and cs_mem = '1' then
        ram(to_integer(unsigned(addr))) <= data;
      end if;
      addr_reg <= addr;
    end if;
  end process;


  -- xFE00 Stdin Status Register
  cs_stdin_status <= '1' when addr = X"FE00" and re = '1' else '0';
  data <= not rx_empty & "000" & X"000" when cs_stdin_status = '1' 
    else (others => 'Z');

  -- xFE02 Stdin Data Register, uart_rd_en acts as chip select
  uart_rd_en <= '1' when addr = X"FE02" and re = '1' else '0';
	-- cs_stdin_data  is out
  data <= uart_r_data when uart_rd_en = '1' 
    else (others => 'Z');

  -- xFE04 Stdout Status Register
  cs_stdout_status <= '1' when re = '1' and addr = X"FE04" else '0';
	-- cs_stdout_status  is out
  data <= not tx_full & "000" & X"000" when cs_stdout_status = '1' 
    else (others => 'Z');		

  -- xFE06 Stdout Data Register
  uart_wr_en <= '1' when addr = X"FE06" and we = '1' else '0';
 	-- cs_stout_data is in 
  uart_w_data <= data(7 downto 0);

  -- xFE0A Switches Data Register
  cs_switch_data <= '1' when addr = X"FE0A" and re = '1' else '0';
  --cs_switch_data  is out
  data <= sw when re = '1' and cs_switch_data = '1' 
    else (others => 'Z');		

  -- xFE0E Buttons Data Register
  cs_btn_data <= '1' when addr = X"FE0E" and re = '1' else '0';
  -- cs_btn_data  is out
  data <= X"00" & "000" & btn when cs_btn_data = '1' 
    else (others => 'Z');

  -- xFE12 7SegDisplay Data Register
  cs_sseg <= '1' when addr = X"FE12" and we='1' else '0';
	-- cs_sseg  is in 
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if cs_sseg = '1' then
        sseg_reg <= data;
      end if;
    end if;
  end process;		

  -- xFE16 Leds Data Register
  cs_led <= '1' when addr = X"FE16" and we='1' else '0';
 	-- cs_leds  is in 
  process (clk)
  begin
    if (clk'event and clk = '1') then
      if cs_led = '1' then
        leds_reg <= data (7 downto 0);
      end if;
    end if;
  end process;
      
end beh_arch;
