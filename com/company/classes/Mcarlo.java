
package classes;

import java.util.Random;
import state_class.State;

public class Mcarlo {
	//0 - niezdecydowany
	//1 - NIE
	//2 - TAK
	public int decizion;
	public int numOfIteration;

	public Mcarlo(int n){
		numOfIteration = n;
	}

	public State[] createMcarlo(int N, int Y) {
		State[] arr = new State[N];

		for(int i=0;i<Y;i++)
			arr[i] = State.YES;
		for(int j=Y;j<N;j++)
			arr[j] = State.NO;

		return arr;
	}

	public double countProbability(State[] allAgents) {
		double probability;
		int countYes=0;

		while(!OneDecizionArray(allAgents)){
			allAgents = ChangeState(allAgents);
		}
		if(allAgents[0] == State.YES)
			countYes++;
		probability = (double)countYes/numOfIteration;
		return probability;
	}

	public boolean OneDecizionArray(State[] allAgents) {
		for(int i=1;i<allAgents.length;i++){
			if(allAgents[0] != allAgents[i])
				return false;
		}
		System.out.println(allAgents[0].toString());
		return true;
	}

	public State[] ChangeState(State[] allAgents){
		Random rand = new Random();
		int pickOneNumber = rand.nextInt(allAgents.length);
		int pickSecondNumber = rand.nextInt(allAgents.length);
		while (pickSecondNumber == pickOneNumber)
			pickSecondNumber = rand.nextInt(allAgents.length);
//jesli 1 -  Y i 2 - N lub 1 - N i 2 - Y
		if( (allAgents[pickOneNumber] == State.YES && allAgents[pickSecondNumber] == State.NO) ||  (allAgents[pickOneNumber] == State.NO && allAgents[pickSecondNumber] == State.YES) ){
			allAgents[pickOneNumber] = State.UNDECIDED;
			allAgents[pickSecondNumber] = State.UNDECIDED;
		}
//jesli 1 - Y i 2 - U lub 1 - U i 2 - Y
		if( (allAgents[pickOneNumber] == State.YES && allAgents[pickSecondNumber] == State.UNDECIDED) ||  (allAgents[pickOneNumber] == State.UNDECIDED && allAgents[pickSecondNumber] == State.YES) ){
			allAgents[pickOneNumber] = State.YES;
			allAgents[pickSecondNumber] = State.YES;
		}
//jesli 1 - N i 2 - U lub 1 - U i 2 - N
		if( (allAgents[pickOneNumber] == State.NO && allAgents[pickSecondNumber] == State.UNDECIDED) ||  (allAgents[pickOneNumber] == State.UNDECIDED && allAgents[pickSecondNumber] == State.NO) ){
			allAgents[pickOneNumber] = State.NO;
			allAgents[pickSecondNumber] = State.NO;
		}
		return allAgents;
	}


}
