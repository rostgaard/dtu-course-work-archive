using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Text;

namespace Cryptanalysis.Project3
{
    public class Program
    {
        const string PATH = @"C:\Data\ciphertext_sheet3.txt";
        static void Main(string[] args)
        {
            //The start of Unix time or POSIX time
            DateTime Beginning = new DateTime(1970, 1, 1, 0, 0, 0);
            
            //Dates the key was generated in.
            DateTime Start = new DateTime(2009, 6, 22, 0, 0, 0);
            DateTime End = new DateTime(2009, 6, 28, 23, 59, 59);

            //Interval for dates in seconds.
            int Min = (int)(Start - Beginning).TotalSeconds;
            int Max = (int)(End - Beginning).TotalSeconds;

            IList<byte[]> possibleKeys = GenerateKeys(Min, Max);

            byte[] CipherText = File.ReadAllBytes(PATH);

            //Decryptes the text with every unique key,
            foreach (var key in possibleKeys)
            {
                string plaintext = Decrypt(CipherText, key);

                //If the text only contains readable characters.
                if (!containsNonprintableChars(plaintext))
                {
                    Console.WriteLine(plaintext);
                    
                    //Transforms the key into one 128bit decimal number.
                    var keyLong = BitConverter.ToUInt64(key, 0);
                    //Prints the decrypted text to file.
                    File.WriteAllText(PATH + "_possiblePlaintext " + keyLong + ".txt", plaintext);
                }
            }
            Console.Write("\n\nDone - Press any key to exit");
            Console.ReadKey();
        }

        /// <summary>
        /// Checks if the text contains non printable characters.
        /// </summary>
        /// <param name="text"></param>
        /// <returns></returns>
        private static bool containsNonprintableChars(string text)
        {
            foreach (var c in text)
            {
                if (!char.IsLetter(c) &&
                    !char.IsPunctuation(c) &&
                    !char.IsNumber(c) &&
                    !char.IsSeparator(c) &&
                    !char.IsWhiteSpace(c) &&
                    !char.IsSymbol(c))
                {
                    return true;
                }
            }
            return false;
        }

        /// <summary>
        /// Generate every possible unique keys.
        /// </summary>
        /// <param name="start"></param>
        /// <param name="end"></param>
        /// <returns></returns>
        private static IList<byte[]> GenerateKeys(int start, int end)
        {
            IList<byte[]> Keys = new List<byte[]>();

            for (int i = start; i <= end; i++)
            {
                byte[] key = GetKey(i);
                
                //Test if the key is one we have seen before.
                bool match = false;
                foreach (var k in Keys)
                {
                    if (IsEqual(k, key))
                    {
                        match = true;
                        break;
                    }
                }

                //If the key is unique then append it to the list.
                if (!match)
                {
                    Keys.Add(key);
                }
            }
            Debug.WriteLine("Number of unique keys: {0}", Keys.Count);
            return Keys;
        }

        /// <summary>
        /// Generate a key, with the given seed
        /// </summary>
        /// <param name="seed"></param>
        /// <returns></returns>
        private static byte[] GetKey(int seed)
        {
            int value = seed;
            byte[] key = new byte[16];

            for (int i = 0; i < key.Length; i++)
            {
                //Generate a new pseudo random number
                value = GccRandomNumber(value);
                //Takes the least significat byte, and  append it to the key.
                byte b = (byte)(value & 0xFF);
                key[i] = b;
            }

            return key;
        }

        public static int GccRandomNumber(int seed)
        {
            const long a = 69069;
            const long c = 5;
            return (int)(((a * seed) + c) % (long)Math.Pow(2, 32));
        }

        /// <summary>
        /// Decryptes the ciphertext with the given key.
        /// </summary>
        /// <param name="CipherText"></param>
        /// <param name="key"></param>
        /// <returns></returns>
        private static String Decrypt(byte[] CipherText, byte[] key)
        {
            byte[] PlainBytes = new byte[CipherText.Length];
            for (int i = 0; i < CipherText.Length; i++)
            {
                PlainBytes[i] = Xor(CipherText[i], key[i % 16]);
            }
            return Encoding.UTF8.GetString(PlainBytes);
        }

        /// <summary>
        /// Makes a deep equality test.
        /// </summary>
        /// <param name="x"></param>
        /// <param name="y"></param>
        /// <returns></returns>
        public static bool IsEqual(Byte[] x, Byte[] y)
        {
            if (x.Length != y.Length)
            {
                return false;
            }

            for (int i = 0; i < x.Length; i++)
            {
                if (x[i] != y[i])
                {
                    return false;
                }
            }
            return true;
        }

        /// <summary>
        /// A simple xor of two bytes.
        /// </summary>
        /// <param name="x"></param>
        /// <param name="y"></param>
        /// <returns></returns>
        public static byte Xor(byte x, byte y)
        {
            return (byte)(x ^ y);
        }
    }
}
