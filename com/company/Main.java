import classes.*;
import state_class.State;

public class Main {

    public static void main(String[] args) {
		int n = 12;
		String z1 = "testy1.csv";
		String z2 = "testy2.csv";
    	MyMatrix<Double> mCarloResults = new MyMatrix<Double>(Double.class,((n+1)*(n+2))/2,1);
    	MyMatrix<Double> gaussResults = new MyMatrix<Double>(Double.class,((n+1)*(n+2))/2,1);
    	MyMatrix<Double> jacobiResults = new MyMatrix<Double>(Double.class,((n+1)*(n+2))/2,1);
    	MyMatrix<Double> gaussSeidelResults = new MyMatrix<Double>(Double.class,((n+1)*(n+2))/2,1);
    	Test mc = new Test();
    	System.out.println("Mcarlo:\n");
    	//mCarloResults = mc.mCarloTest(n);
		System.out.println("Gauss:\n");
		gaussResults = mc.getGaussVector(n);
		System.out.println("Jacobi\n");
		jacobiResults = mc.getJacobiResult(n);
		System.out.println("Gauss - Seidel\n");
		gaussSeidelResults = mc.getGaussSeidelResult(n);
		System.out.println("zapis pomiaru X");
		try {
			mc.mCarloCompareToFile(z1, mCarloResults, gaussResults, jacobiResults, gaussSeidelResults);
		} catch (Exception e){
			System.out.println("Błąd przy zapisie do pliku: " + e);
		}
		jacobiResults = mc.countSecondBJacobi(n);
		gaussSeidelResults = mc.countSecondBGaussSeidel(n);
		gaussResults = mc.countSecondBGauss(n);

		System.out.println("zapis pomiaru drugiego b");//bladbezwzgledny
		try {
			mc.mCarloCompareToFile(z2, mCarloResults, gaussResults, jacobiResults, gaussSeidelResults);
		} catch (Exception e){
			System.out.println("Błąd przy zapisie do pliku: " + e);
		}

		try {
			mc.countAndWriteTimeOfExecition("Czas_dla_n6.csv",n);
		} catch (Exception e){
			System.out.println("To nie jest czas na dobre rozwiazanie" + e);
		}

    }

}