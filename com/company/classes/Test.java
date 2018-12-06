package classes;

import state_class.State;

public class Test {

	public void McarloTest() {
		System.out.println("zaczynam test Mcarlo.\n");
		int iloscProb = 1000000;
		int iloscAgentow = 3;
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
	}

	public void GaussTest() {
		int iloscProb = 1000;
		int iloscAgentow = 3;

		AgentMatrix am = new AgentMatrix(iloscAgentow);
		int i,l,pom;
/*
		int size = (iloscAgentow + 1) * (iloscAgentow + 2) / 2;
		int pom;
		MyMatrix<Double> matrix = new MyMatrix<Double>(Double.class,size);
		MyMatrix<Double> vectorB = new MyMatrix<Double>(Double.class,size,1);
//ustawianie wektora B
		for (pom=0;pom<size;pom++){
			vectorB.setValue(pom,0,0.0);
		}
		vectorB.setValue(size-1,0,1.0);

		*/
	}
}
