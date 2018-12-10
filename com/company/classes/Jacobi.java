package classes;

public class Jacobi {
	private MyMatrix<Double> matrixA;
	private MyMatrix<Double> matrixM;
	private MyMatrix<Double> resultVector;
	private double N[];
	private double b[];
	private double x[];
	private int sizeOfMatrix;
	private int numberOfAgents;
	private int iterCount;

	public Jacobi(int liczbaAgentow, int liczbaPowtorzen){
		iterCount = liczbaPowtorzen;
		numberOfAgents = liczbaAgentow;
		sizeOfMatrix = ((numberOfAgents+1)*(numberOfAgents+2))/2;

		matrixA = new MyMatrix<Double>(Double.class,sizeOfMatrix);
		matrixM = new MyMatrix<Double>(Double.class,sizeOfMatrix);
		resultVector = new MyMatrix<Double>(Double.class,sizeOfMatrix,1);

		N = new double[sizeOfMatrix];
		b = new double[sizeOfMatrix];
		x = new double[sizeOfMatrix];
	}

	public void showMeTheMatrix(){
		for (int i=0;i<sizeOfMatrix;i++) {
			for (int l = 0; l < sizeOfMatrix; l++) {
				System.out.print(matrixA.getValue(i,l) + ", ");
			}
			System.out.println();
		}
	}

	public void FulfillMatrix() {
		int numberOfY=0,numberOfN=0;
		for(int pom=0;pom<sizeOfMatrix;pom++){
			SetAgentMatrixValue(pom, numberOfY, numberOfN);
			numberOfN++;
			if(numberOfN > numberOfAgents - numberOfY){
				numberOfN=0;
				numberOfY++;
			}
		}


	}

	public void SetAgentMatrixValue(int index1, int y, int n) {
		int u = numberOfAgents - y - n;
		double moreY = ((double) y / (double) numberOfAgents) * ((Double.valueOf(numberOfAgents - y - n)) / ((double) numberOfAgents - 1.0)) + ((Double.valueOf(numberOfAgents - y - n)) / numberOfAgents) * ((double) y / (double) (numberOfAgents - 1));
		double moreN = ((double) n / (double) numberOfAgents) * ((Double.valueOf(numberOfAgents - y - n)) / ((double) numberOfAgents - 1.0)) + ((Double.valueOf(numberOfAgents - y - n)) / numberOfAgents) * ((double) n / (double) (numberOfAgents - 1));
		double moreU = ((double) y / (double) numberOfAgents) * ((Double.valueOf(n)) / ((double) numberOfAgents - 1.0)) + ((Double.valueOf(n)) / numberOfAgents) * ((double) y / (double) (numberOfAgents - 1));
		double stays = 1.0 - moreN - moreU - moreY;
//		if (stays < 0.00000000000001)
//			stays = 0;
		System.out.println("dla P(" + y + "," + n + ")");
		System.out.println("N - " + moreN + " Y - " + moreY + " U - " + moreU + " stays - " + stays);

		int index2 = 0;
		for (int i=0;i<=numberOfAgents;i++){
			for (int l = 0;l<=numberOfAgents - i;l++){
				ConditionSet(index1, index2, y, n, i, l, moreY, moreN, moreU, stays);
				index2++;
			}
		}

	}

	public void ConditionSet(int index1, int index2, int y, int n, int currY, int currN, double moreY, double moreN, double moreU, double stays){
		if(y == numberOfAgents && n == 0 && index2 == index1)
			matrixA.setValue(index1,index2,1.0);
		else if(y == 0 && n == numberOfAgents && index1 == index2)
			matrixA.setValue(index1,index2,1.0);
		else if(y == 0 && n == 0 && index1 == index2)
			matrixA.setValue(index1,index2,1.0);
		else if(y == currY && n == currN)
			matrixA.setValue(index1,index2,-1.0 + stays);
		else if(y == currY && n == currN - 1)
			matrixA.setValue(index1,index2, moreN);
		else if(y == currY - 1 && n == currN)
			matrixA.setValue(index1,index2, moreY);
		else if(y == currY + 1 && n == currN + 1)
			matrixA.setValue(index1,index2, moreU);
		else
			matrixA.setValue(index1,index2,0.0);
	}

	public void getValuesOfB() {
		for(int i=0;i<sizeOfMatrix-1;i++){
			b[i] = 0.0;
		}
		b[sizeOfMatrix-1] = 1.0;
	}

	public void calculateN() {
		for (int i=0;i<sizeOfMatrix;i++)
			N[i] = 1/matrixA.getValue(i,i);
	}

	public void calculateM() {
		for(int i=0;i<sizeOfMatrix;i++){
			for(int l=0;l<sizeOfMatrix;l++){
				if(i==l)
					matrixM.setValue(l,i,0.0);
				else
					matrixM.setValue(l,i,-1.0 * (matrixA.getValue(l,i) * N[i]));
			}
		}
	}

	public void countResultVector() {
		int pom;
		//x1:
		for(pom=0;pom<sizeOfMatrix;pom++)
			resultVector.setValue(pom,0,0.0);
		//x2:
		for(int q=0;q<iterCount;q++){
			for(pom=0;pom<sizeOfMatrix;pom++){
				x[pom] = N[pom]*b[pom];
				for(int j=0;j<sizeOfMatrix;j++){
					x[pom] = x[pom] + matrixM.getValue(j,pom) * resultVector.getValue(j,0);
				}
			}
			for (int j=0;j<sizeOfMatrix;j++){
				resultVector.setValue(j,0,x[j]);
			}
		}
	}

	public MyMatrix<Double> getResultVector(){
		return resultVector;
	}
}
