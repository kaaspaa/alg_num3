package classes;

import state_class.State;

public class Test {

	public MyMatrix<Double> McarloTest(int numberOfAgents) {
		System.out.println("zaczynam test Mcarlo.\n");
		int iloscProb = 10000000;
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
}
