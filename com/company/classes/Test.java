package classes;

import state_class.State;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {

	public MyMatrix<Double> McarloTest(int numberOfAgents) {
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

	public void GaussTest(int numberOfAgents) {
//		int iloscProb = 1000;
		int iloscAgentow = numberOfAgents;

		AgentMatrix am = new AgentMatrix(iloscAgentow);
		int i,l,pom;
		am.FulfillMatrix();

		am.showMeTheMatrix();

		double avgValue = am.GetAvgValue(am.CountResultVector());

	}

	public MyMatrix<Double> GetGaussVector(int numberOfAgents) {
		int iloscAgentow = numberOfAgents;

		AgentMatrix am = new AgentMatrix(iloscAgentow);
		am.FulfillMatrix();

		MyMatrix<Double> resultVector = new MyMatrix<Double>(Double.class,(numberOfAgents+1)*(numberOfAgents+2)/2);
		resultVector = am.CountResultVector();
		return resultVector;
	}

	public void JacobiTest(int numberOfAgents) {
		int iloscAgentow = numberOfAgents;
		int iloscProb = 200;

		Jacobi j = new Jacobi(iloscAgentow,iloscProb);
		j.FulfillMatrix();

		j.showMeTheMatrix();

		j.FulfillMatrix();
	}

	public void mCarloCompareToFile(MyMatrix<Double> mCarloVector, MyMatrix<Double> gaussVector) throws IOException {
		FileWriter fileWriter = new FileWriter("mCarloPorownanie.csv");
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

		/*
		printWriter.println("\nJacobi\n");
		for (int i=0;i<jacobiVector.rows;i++){
			for (int l=0;l<jacobiVector.columns;l++) {
				printWriter.print(jacobiVector.getValue(i,l ) + ";");
			}
			printWriter.println();
		}
		printWriter.close();
		*/
	}
}
