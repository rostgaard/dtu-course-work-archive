type Packet is 
   record
      CRC     : CRC_Type;
      Payload : Bytes;
   end record
     with Type_Invariant =>
        Compute_CRC (Payload) = CRC;
