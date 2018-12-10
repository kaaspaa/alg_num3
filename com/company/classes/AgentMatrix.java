package classes;

import state_class.State;

public class AgentMatrix {
	private int numberOfAgent;
	private int sizeOfMatrix;
	private MyMatrix<Double> agentMatrix;
	private MyMatrix<Double> vectorB;
	private State[] s;

		/*	float moreY = (float(y)/float(N)) * (float(N-y-n) / float(N-1)) + (float(N-y-n)/float(N)) * (float(y)/float(N-1));
		float moreN = (float(n)/float(N)) * (float(N-y-n) / float(N-1)) + (float(N-y-n)/float(N)) * (float(n)/float(N-1));
		float moreU = (float(y)/float(N)) * (float(n)/float(N-1)) + (float(n)/float(N)) * (float(y)/float(N-1));
		float stays = float(1) - moreY - moreU - moreN;*/

	public AgentMatrix(int N){
		numberOfAgent = N;
		sizeOfMatrix = (numberOfAgent + 1)*(numberOfAgent + 2)/2;

		agentMatrix = new MyMatrix<Double>(Double.class,sizeOfMatrix);
		vectorB = new MyMatrix<Double>(Double.class,sizeOfMatrix,1);
		//wypelnianie zerami
		agentMatrix.fillWithZero();
		vectorB.fillWithZero();
		vectorB.setValue(sizeOfMatrix-1,0, 1.0);

	}

	public int getSizeOfMatrix(){
		return sizeOfMatrix;
	}

	public void showMeTheMatrix(){
		for (int i=0;i<sizeOfMatrix;i++) {
			for (int l = 0; l < sizeOfMatrix; l++) {
				System.out.print(agentMatrix.getValue(i,l) + ", ");
			}
			System.out.println();
		}
	}

	public void FulfillMatrix() {
		int numberOfY=0,numberOfN=0;
		for(int pom=0;pom<sizeOfMatrix;pom++){
//			for(int pom2=0;pom2<sizeOfMatrix;pom2++)
				SetAgentMatrixValue(pom, numberOfY, numberOfN);
			numberOfN++;
			if(numberOfN > numberOfAgent - numberOfY){
				numberOfN=0;
				numberOfY++;
			}
		}
	}

	public void SetAgentMatrixValue(int index1, int y, int n) {
		int u = numberOfAgent - y - n;
		double moreY = ((double) y / (double) numberOfAgent) * ((Double.valueOf(numberOfAgent - y - n)) / ((double) numberOfAgent - 1.0)) + ((Double.valueOf(numberOfAgent - y - n)) / numberOfAgent) * ((double) y / (double) (numberOfAgent - 1));
		double moreN = ((double) n / (double) numberOfAgent) * ((Double.valueOf(numberOfAgent - y - n)) / ((double) numberOfAgent - 1.0)) + ((Double.valueOf(numberOfAgent - y - n)) / numberOfAgent) * ((double) n / (double) (numberOfAgent - 1));
		double moreU = ((double) y / (double) numberOfAgent) * ((Double.valueOf(n)) / ((double) numberOfAgent - 1.0)) + ((Double.valueOf(n)) / numberOfAgent) * ((double) y / (double) (numberOfAgent - 1));
		double stays = 1.0 - moreN - moreU - moreY;
//		if (stays < 0.00000000000001)
//			stays = 0;
		System.out.println("dla P(" + y + "," + n + ")");
		System.out.println("N - " + moreN + " Y - " + moreY + " U - " + moreU + " stays - " + stays);

		int index2 = 0;
		for (int i=0;i<=numberOfAgent;i++){
			for (int l = 0;l<=numberOfAgent-i;l++){
				ConditionSet(index1, index2, y, n, i, l, moreY, moreN, moreU, stays);
				index2++;
			}
		}

	}

	public void ConditionSet(int index1, int index2, int y, int n, int currY, int currN, double moreY, double moreN, double moreU, double stays){
		if(y == numberOfAgent && n == 0 && index2 == index1)
			agentMatrix.setValue(index1,index2,1.0);
		else if(y == 0 && n == numberOfAgent && index1 == index2)
			agentMatrix.setValue(index1,index2,1.0);
		else if(y == 0 && n == 0 && index1 == index2)
			agentMatrix.setValue(index1,index2,1.0);
		else if(y == currY && n == currN)
			agentMatrix.setValue(index1,index2,-1.0 + stays);
		else if(y == currY && n == currN - 1)
			agentMatrix.setValue(index1,index2, moreN);
		else if(y == currY - 1 && n == currN)
			agentMatrix.setValue(index1,index2, moreY);
		else if(y == currY + 1 && n == currN + 1)
			agentMatrix.setValue(index1,index2, moreU);
		else
			agentMatrix.setValue(index1,index2,0.0);
	}

	public MyMatrix<Double> CountResultVector(){
		MyMatrix<Double> resultVector = new MyMatrix<Double>(Double.class,sizeOfMatrix,1);
		resultVector = agentMatrix.partialChoiseGauss(agentMatrix, vectorB);

		System.out.println("Result Gauss Matrix");
		resultVector.printMatrix();

		return resultVector;
	}

	public double GetAvgValue(MyMatrix<Double> matrix){
		double avgValue=0.0;
		for(int i=0;i<matrix.columns;i++){
			for(int l=0;l<matrix.rows;l++){
				avgValue = avgValue + matrix.getValue(l,i);
			}
		}
		return avgValue;
	}
}