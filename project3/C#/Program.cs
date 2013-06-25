using System;
using System.Collections.Generic;
using System.IO;
using System.Text;

namespace Cryptanalysis.Project3
{
    public class Program
    {
        const string PATH = @"C:\Data\ciphertext_sheet3.txt";
        private static IList<byte[]> PossibleKeys;
        static void Main(string[] args)
        {
            DateTime Beginning = new DateTime(1970, 1, 1, 0, 0, 0);
            DateTime Start = new DateTime(2009, 6, 22, 0, 0, 0);
            DateTime End = new DateTime(2009, 6, 28, 23, 59, 59);

            int Min = (int)(Start - Beginning).TotalSeconds;
            int Max = (int)(End - Beginning).TotalSeconds;

            PossibleKeys = GenerateKeys(Min, Max);

            byte[] CipherText = File.ReadAllBytes(PATH);


            List<string> PossiblePlaintext = new List<string>();
            foreach (var key in PossibleKeys)
            {
                string plaintext = Decrypt(CipherText, key);

                if (!containsNonprintableChars(plaintext)) //string.contains("NSA")
                {
                    PossiblePlaintext.Add(plaintext);

                }
            }

            Console.WriteLine("Found {0} readable plaintext{1}.", PossiblePlaintext.Count, PossiblePlaintext.Count > 1 ? "s" : "");

            Console.ReadKey();
        }

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

        private static IList<byte[]> GenerateKeys(int start, int end)
        {
            IList<byte[]> Keys = new List<byte[]>();

            for (int i = start; i <= end; i++)
            {
                byte[] key = GetKey(i);
                bool match = false;
                foreach (var k in Keys)
                {
                    if (compare(k, key))
                    {
                        match = true;
                        break;
                    }
                }
                if (!match)
                {
                    Keys.Add(key);
                }
            }
            Console.WriteLine("Number of unique keys: {0}", Keys.Count);
            return Keys;
        }

        private static byte[] GetKey(int init)
        {
            int value = init;
            byte[] key = new byte[16];

            for (int i = 0; i < key.Length; i++)
            {
                value = GCC(value);
                byte b = (byte)(value & 0xFF);
                key[i] = b;
            }

            return key;
        }

        public static int GCC(int seed)
        {
            const long a = 69069;
            const long c = 5;
            return (int)(((a * seed) + c) % (long)Math.Pow(2, 32));
        }

        private static String Decrypt(byte[] CipherText, byte[] key)
        {
            byte[] PlainBytes = new byte[CipherText.Length];
            for (int i = 0; i < CipherText.Length; i++)
            {
                PlainBytes[i] = Xor(CipherText[i], key[i % 16]);
            }
            return Encoding.UTF8.GetString(PlainBytes);
        }

        public static bool compare(Byte[] x, Byte[] y)
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

        public static byte Xor(byte x, byte y)
        {
            return (byte)(x ^ y);
        }
    }
}
