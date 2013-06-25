using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Cryptanalysis.Project3
{
    class Program
    {
        const string PATH = @"C:\Data\ciphertext_sheet3.txt";


        static List<long> PossibleKeys;

        static void Main(string[] args)
        {
            DateTime Beginning = new DateTime(1970, 1, 1, 0, 0, 0);
            DateTime Start = new DateTime(2009, 6, 22, 0, 0, 0);
            DateTime End = new DateTime(2009, 6, 28, 23, 59, 59);

            long Min = (long)(Start - Beginning).TotalSeconds;
            long Max = (long)(End - Beginning).TotalSeconds;

            PossibleKeys = new List<long>();

       
            
        }

        private List<long> GenerateKeys()
        { 

        }
    }
}
