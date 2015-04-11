package Schedtool.Utils;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
public class LCMutils {

  /**
   * Returns the least common multiple between two integer values.
   *
   * @param a the first integer value.
   * @param b the second integer value.
   * @return the least common multiple between a and b.
   * @throws ArithmeticException if the lcm is too large to store as an int
   * @since 1.1
   */
  public static int lcm(int a, int b) {
      return Math.abs(mulAndCheck(a / gcd(a, b), b));
  }
  /**
   * Multiply two integers, checking for overflow.
   *
   * @param x a factor
   * @param y a factor
   * @return the product <code>x*y</code>
   * @throws ArithmeticException if the result can not be represented as an
   *         int
   * @since 1.1
   */
  public static int mulAndCheck(int x, int y) {
      long m = ((long)x) * ((long)y);
      if (m < Integer.MIN_VALUE || m > Integer.MAX_VALUE) {
          throw new ArithmeticException("overflow: mul");
      }
      return (int)m;
  }

  /**
   * <p>
   * Gets the greatest common divisor of the absolute value of two numbers,
   * using the "binary gcd" method which avoids division and modulo
   * operations. See Knuth 4.5.2 algorithm B. This algorithm is due to Josef
   * Stein (1961).
   * </p>
   *
   * @param u a non-zero number
   * @param v a non-zero number
   * @return the greatest common divisor, never zero
   * @since 1.1
   */
  public static int gcd(int u, int v) {
      if (u * v == 0) {
          return (Math.abs(u) + Math.abs(v));
      }
      // keep u and v negative, as negative integers range down to
      // -2^31, while positive numbers can only be as large as 2^31-1
      // (i.e. we can't necessarily negate a negative number without
      // overflow)
      /* assert u!=0 && v!=0; */
      if (u > 0) {
          u = -u;
      } // make u negative
      if (v > 0) {
          v = -v;
      } // make v negative
      // B1. [Find power of 2]
      int k = 0;
      while ((u & 1) == 0 && (v & 1) == 0 && k < 31) { // while u and v are
                                                          // both even...
          u /= 2;
          v /= 2;
          k++; // cast out twos.
      }
      if (k == 31) {
          throw new ArithmeticException("overflow: gcd is 2^31");
      }
      // B2. Initialize: u and v have been divided by 2^k and at least
      // one is odd.
      int t = ((u & 1) == 1) ? v : -(u / 2)/* B3 */;
      // t negative: u was odd, v may be even (t replaces v)
      // t positive: u was even, v is odd (t replaces u)
      do {
          /* assert u<0 && v<0; */
          // B4/B3: cast out twos from t.
          while ((t & 1) == 0) { // while t is even..
              t /= 2; // cast out twos
          }
          // B5 [reset max(u,v)]
          if (t > 0) {
              u = -t;
          } else {
              v = t;
          }
          // B6/B3. at this point both u and v should be odd.
          t = (v - u) / 2;
          // |u| larger: t positive (replace u)
          // |v| larger: t negative (replace v)
      } while (t != 0);
      return -u * (1 << k); // gcd is u*2^k
  }
}