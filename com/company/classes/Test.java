package classes;

import state_class.State;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {

	public MyMatrix<Double> mCarloTest(int numberOfAgents) {
		System.out.println("zaczynam test Mcarlo.\n");
		int iloscProb = 1000000;
		int iloscAgentow = numberOfAgents;
		Mcarlo mc = new Mcarlo(iloscProb);
		int i, l;
		double prob = 0.0;
		int pom = 0;
		MyMatrix<Double> vectorX = new MyMatrix<Double>(Double.class, (iloscAgentow + 1) * (iloscAgentow + 2) / 2, 1);
		State[] s = new State[iloscAgentow];
		for (i = 0; i <= s.length; i++) {
			for (l = 0; l <= s.length - i; l++) {
				s = mc.createMcarlo(s, i, l);
				prob = 0.0;
				for (int p = 0; p < iloscProb; p++) {
					State[] cp = s.clone();

					prob = prob + mc.countProbability(cp);
				}
				vectorX.setValue(pom, 0, prob / iloscProb);
				System.out.println("\n P(" + i + "," + l + "):  " + vectorX.getValue(pom, 0));
				pom++;
			}
		}
		return vectorX;
	}

	public MyMatrix<Double> getGaussVector(int numberOfAgents) {
		int iloscAgentow = numberOfAgents;

		AgentMatrix am = new AgentMatrix(iloscAgentow);
		am.fulfillMatrix();

		MyMatrix<Double> resultVector = new MyMatrix<Double>(Double.class,(numberOfAgents+1)*(numberOfAgents+2)/2,1);
		resultVector = am.countResultVector();
		return resultVector;
	}

	public MyMatrix<Double> countSecondBGauss(int numberOfAgents) {
		int iloscAgentow = numberOfAgents;

		AgentMatrix am = new AgentMatrix(iloscAgentow);
		am.fulfillMatrix();

		MyMatrix<Double> results = new MyMatrix<Double>(Double.class,(numberOfAgents+1)*(numberOfAgents+2)/2,1);
		am.countResultVector();
		results = am.countSecondBGauss();
		return results;
	}

	public MyMatrix<Double> getGaussSeidelResult(int numberOfAgents) {
		int iloscAgentow = numberOfAgents;
		int iloscProb = 150;

		GaussSeidel gs = new GaussSeidel(iloscAgentow,iloscProb);
		MyMatrix<Double> results = new MyMatrix<Double>(Double.class,(numberOfAgents+1)*(numberOfAgents+2)/2,1);
		results = gs.countGaussSeidelVector();
		return results;
	}

	public MyMatrix<Double> countSecondBGaussSeidel(int numberOfAgents){
		int iloscAgentow = numberOfAgents;
		int iloscProb = 150;

		GaussSeidel gs = new GaussSeidel(iloscAgentow,iloscProb);
		MyMatrix<Double> results = new MyMatrix<Double>(Double.class,(numberOfAgents+1)*(numberOfAgents+2)/2,1);
		gs.countGaussSeidelVector();
		results = gs.countSecondB();
		return results;
	}

	public MyMatrix<Double> getJacobiResult(int numberOfAgents) {
		int iloscAgentow = numberOfAgents;
		int iloscProb = 200;

		Jacobi j = new Jacobi(iloscAgentow,iloscProb);
		MyMatrix<Double> results = new MyMatrix<Double>(Double.class,(numberOfAgents+1)*(numberOfAgents+2)/2,1);
		results = j.countJacobiResultVector();
		return results;
	}

	public MyMatrix<Double> countSecondBJacobi(int numberOfAgents){
		int iloscAgentow = numberOfAgents;
		int iloscProb = 200;

		Jacobi j = new Jacobi(iloscAgentow,iloscProb);
		MyMatrix<Double> results = new MyMatrix<Double>(Double.class,(numberOfAgents+1)*(numberOfAgents+2)/2,1);
		j.countJacobiResultVector();
		results = j.countSecondB();
		return results;
	}



	public void mCarloCompareToFile(String fileName, MyMatrix<Double> mCarloVector, MyMatrix<Double> gaussVector, MyMatrix<Double> jacobiVector,MyMatrix<Double> gaussSeidelVector) throws IOException {
		FileWriter fileWriter = new FileWriter(fileName);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		printWriter.print("Mcarlo;\n");
		for (int i=0;i<mCarloVector.rows;i++){
			for (int l=0;l<mCarloVector.columns;l++) {
				printWriter.print(mCarloVector.getValue(i,l ) + ";");
			}
			printWriter.println();
		}


		printWriter.print("\nGauss;\n");
		for (int i=0;i<gaussVector.rows;i++){
			for (int l=0;l<gaussVector.columns;l++) {
				printWriter.print(gaussVector.getValue(i,l ) + ";");
			}
			printWriter.println();
		}


		printWriter.print("\nJacobii;\n");
		for (int i=0;i<jacobiVector.rows;i++){
			for (int l=0;l<jacobiVector.columns;l++) {
				printWriter.print(jacobiVector.getValue(i,l ) + ";");
			}
			printWriter.println();
		}


		printWriter.print("\nGaussSeidel;\n");
		for (int i=0;i<gaussSeidelVector.rows;i++){
			for (int l=0;l<gaussSeidelVector.columns;l++) {
				printWriter.print(gaussSeidelVector.getValue(i,l ) + ";");
			}
			printWriter.println();
		}
		printWriter.close();
	}

	public void countAndWriteTimeOfExecition(String fileName, int numberOfAgents) throws IOException {
		double startTime1, endTime1, startTime2, endTime2, startTime3, endTime3, startTime4, endTime4;
		FileWriter fileWriter = new FileWriter(fileName);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		printWriter.println("Mcarlo;Gauss;Jacobi;GaussSeidel;n = " + numberOfAgents + ";");
		
		for(int q=1;q<=10;q++) {
			//MCarlo
			startTime1 = System.nanoTime();
			mCarloTest(numberOfAgents);
			endTime1 = System.nanoTime();
			//Gauss
			startTime2 = System.nanoTime();
			getGaussVector(numberOfAgents);
			countSecondBGauss(numberOfAgents);
			endTime2 = System.nanoTime();
			//Jacobi
			startTime3 = System.nanoTime();
			getJacobiResult(numberOfAgents);
			countSecondBJacobi(numberOfAgents);
			endTime3 = System.nanoTime();
			//GaussSeidel
			startTime4 = System.nanoTime();
			getGaussSeidelResult(numberOfAgents);
			countSecondBGaussSeidel(numberOfAgents);
			endTime4 = System.nanoTime();

			printWriter.println((endTime1 - startTime1) + ";" + (endTime2 - startTime2) + ";" + (endTime3 - startTime3) + ";" + (endTime4 - startTime4) + ";");
		}
		printWriter.close();
	}
}
