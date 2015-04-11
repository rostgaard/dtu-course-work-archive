Procedure Increment_Positive (Item : in Integer) with
  Pre => Item > 0,
  Post => Item = Item'Old+1;
