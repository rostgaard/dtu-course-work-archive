package body rainbow_table is


   procedure Generate_Rainbow_Table is
      Length : Natural := 2**10;
      Rows   : Natural := 2**18;
   begin

      Rainbow.Rows = Rows;
      Rainbow.ChainLength = Length;

		long lastTime = System.currentTimeMillis();
		for (int i = 0; i < rows; i++) {
			long startValue = ran.nextInt() % Utilities.bit28;
			long accumilator = startValue;
			for (int j = 0; j < length; j++) {
				long cipher = Utilities.MD5_Hash(accumilator);

				long reducedCipher = Utilities.reductionFunction(cipher, j, Utilities.bit28 + 1);
				accumilator = reducedCipher;
			}
			rainbow.put(accumilator, startValue);
		}

		long currentTime = System.currentTimeMillis();
		System.out.println("Generated the table in: "
				+ (currentTime - lastTime)/1000);

		return rainbow;
	}
