using System;
using System.Collections.Generic;
using System.Numerics;
using System.IO;
using System.Text.RegularExpressions;
using System.Text;

namespace Cryptanalysis.Project3
{
    class Program
    {
        const string PATH = @"C:\Data\ciphertext_sheet3.txt";
        private static List<byte[]> PossibleKeys;

        static void Main(string[] args)
        {
            DateTime Beginning = new DateTime(1970, 1, 1, 0, 0, 0);
            DateTime Start = new DateTime(2009, 6, 22, 0, 0, 0);
            DateTime End = new DateTime(2009, 6, 28, 23, 59, 59);

            long Min = (long)(Start - Beginning).TotalSeconds;
            long Max = (long)(End - Beginning).TotalSeconds;

            PossibleKeys = GenerateKeys(Min, Max);

            byte[] CipherText = File.ReadAllBytes(PATH);
            

            List<string> PossiblePlaintext = new List<string>();


            for(int i = 0; i < PossibleKeys.Count; i++)
            {
                string plaintext = Decrypt(CipherText, PossibleKeys[i]);
                if (plaintext.Contains("NSA") || (LetterRatio(plaintext) > 0.1F))
                    PossiblePlaintext.Add(plaintext);

            }

            Console.WriteLine("Found " + PossiblePlaintext.Count + " plaintexts, containing \"NSA\".");
            

        }

        private static double LetterRatio(string input)
        {
            int counter = 0;
            foreach (char c in input)
            {
                if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
                    counter++;
            }
            double result = counter / input.Length;
            return result;
        }

        private static List<byte[]> GenerateKeys(long start, long end)
        {
            List<byte[]> Keys = new List<byte[]>();

            for (long i = start; i <= end; i++)
            {
                Keys.Add(GetKey(i));
            }
            return Keys;
        }

        private static byte[] GetKey(long init)
        {
            long value = init;
            byte[] key = new byte[16];

            for (int i = 0; i < key.Length; i++)
            {
                long r = (69096 * init + 5) % (long)Math.Pow(2, 32);
                byte  b = (byte)(r & 0xFF);
                key[i] = b;
            }

            return key;
        }

        private static String Decrypt(byte[] CipherText, byte[] key)
        {
            byte[] PlainBytes = new byte[CipherText.Length];
            for (int i = 0; i < CipherText.Length; i++)
            {
                PlainBytes[i] = (byte)(CipherText[i] ^ key[i % 16]);
            }
            return Encoding.UTF8.GetString(PlainBytes);
        }
    }
}
