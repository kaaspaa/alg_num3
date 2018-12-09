import classes.*;
import state_class.State;

public class Main {

    public static void main(String[] args) {
		int n = 15;
    	MyMatrix<Double> mCarloResults = new MyMatrix<Double>(Double.class,((n+1)*(n+2))/2,1);
    	MyMatrix<Double> gaussResults = new MyMatrix<Double>(Double.class,((n+1)*(n+2))/2,1);
    	Test mc = new Test();
    	System.out.println("Mcarlo:\n");
    	mc.McarloTest(n);
		System.out.println("Gauss:\n");
    	mc.GaussTest(n);

    }
}