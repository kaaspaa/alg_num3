import classes.*;
import state_class.State;

public class Main {

    public static void main(String[] args) {
		int n = 12;
    	MyMatrix<Double> mCarloResults = new MyMatrix<Double>(Double.class,((n+1)*(n+2))/2,1);
    	MyMatrix<Double> gaussResults = new MyMatrix<Double>(Double.class,((n+1)*(n+2))/2,1);
    	MyMatrix<Double> jacobiResults = new MyMatrix<Double>(Double.class,((n+1)*(n+2))/2,1);
    	Test mc = new Test();
    	System.out.println("Mcarlo:\n");
    	mCarloResults = mc.McarloTest(n);
		System.out.println("Gauss:\n");
		gaussResults = mc.GetGaussVector(n);
		System.out.println("Jacobi\n");
		jacobiResults = mc.GetJacobiResult(n);
		try {
			mc.mCarloCompareToFile(mCarloResults,gaussResults,jacobiResults);
		} catch (Exception e){
			System.out.println("Błąd przy zapisie do pliku: " + e);
		}

    }
}