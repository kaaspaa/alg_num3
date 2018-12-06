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
		vectorB.setValue(sizeOfMatrix,0, 1.0);

	}

	public void FulfillMatrix(MyMatrix<T> matrix) {
		for(int i=0;i<=numberOfAgent;i++) {
			for(int l=0;l<=numberOfAgent-i;l++){
				//TODO wymyslec cos co mi policzy i wstawi

			}
		}
	}

	public void SetAgentMatrixValue(){

	}



}