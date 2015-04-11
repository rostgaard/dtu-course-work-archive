using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Numerics;
using System.Text;
using System.Threading.Tasks;

namespace Weeksheet1
{
    class XORcipher
    {
		static void Main(string[] args)
        {
            bruteForceXor();
        }
	
        public static void bruteForceXor()
        {
            //Just some random numbers.
            var knownPlain = BigInteger.Parse("1023894628934");
            var Key = BigInteger.Parse("7853749587");
            var cipher = Xor(Key, knownPlain);
            Console.WriteLine("Key: {0}, plain: {1}, cipher: {2}",Key, knownPlain, cipher);
            //The biggest number for 128 bit.
            var limit = BigInteger.Parse("0ffffffffffffffffffffffffffffffff", NumberStyles.HexNumber);
            var startTime = DateTime.Now;

            for (var guessedKey = new BigInteger(0); guessedKey < limit; guessedKey++)
            {
                var res = Xor(guessedKey, knownPlain);

                //If we fint the key.
                if(res == cipher)
                {
                    Console.WriteLine("The key is {0:#,0}", guessedKey);
                    break;
                }

                //Just to make sure, that i don't have to ask for the clock everytime.
                if (guessedKey % 100000 == 0)
                {
                    if (DateTime.Now - startTime >= TimeSpan.FromHours(1))
                    {
                        Console.WriteLine("In one hour, we tried: {0:#,0} keys", guessedKey);
                        break;
                    }
                }
            }
        }
        
        public static BigInteger Xor(BigInteger key, BigInteger plainText)
        {
            return key ^ plainText;
        }

    }
}
