class testmain {
        public static void main(String[] args) { 
                int i;
                int j;
                j=1;
                i=j;
                System.out.println(j);
                System.out.println("Hello World");

        }
}

class Super {
        
}
class TestClass1 {
        String data;
        
        public static boolean method(int arg1,int arg2,TestClass2 arg3){
                int[] array;
                boolean l;
                int result=0;
                l=(arg1<100 && arg2==3);
                //test
                if (l==false){
                        array=new int[arg1];
                        array[arg2]=3;
                        result=array[arg2];
                }
                return !(result==0);
        }
        
        public void setData(String d){
                data=d;
                return;
        }
        public String toString(){
                return data;
        }
}

class TestClass2 extends Super {
        
        boolean condition=true;
        
        void method(){
                int a=0;
                {
                        int a;
                        a=1;
                        System.out.println(a);
                }
                return;
        }
        
        public int method2(TestClass1 data) {
                int N=10;
                int product = 1;
                int j=1;
                // test
                if (!condition){
                        data.method(10,5,this);
                } else {
                        data.method(2,1,new TestClass1());
                }
                /* line comment 
                 * that
                 * goes
                 * over 
                 * several
                 * lines
                 * */
                while(j<N+1) {
                    product =product*j;
                    j=j+1;
                }
                if (!(product < 100)) {
                        product=product-100;
                }
                return product;
        }
}
