class mainClass {

    public static void main(String[] args) {
	int i;
	test2 t2;
	t2 = new test2();
	t2.test(2);
    }
}

class test2 {
    String hej = "Hej";
    int i = 0;

    public test2() {
    }

    public String getString() {
	return hej;
    }
	
    public void test(int i) {
	this.i = i;
    }
	
    public void x() {
	int i = 0;
	if(i == 0) {
	    i= 2;
	}
	else {
	    i=3;
	}
    }
    
}