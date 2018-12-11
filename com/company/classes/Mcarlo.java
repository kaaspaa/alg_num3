
package classes;

import java.util.Random;
import state_class.State;

public class Mcarlo {
	public int numOfIteration;

	public Mcarlo(int n){
		numOfIteration = n;
	}

	public State[] createMcarlo(State[] arr, int Y, int N) {

		for(int i = 0;i<Y;i++)
			arr[i] = State.YES;
		for(int j = 0;j<N;j++)
			arr[Y + j] = State.NO;
		for(int l = N + Y;l<arr.length;l++)
			arr[l] = State.UNDECIDED;

		return arr;
	}

	public double countProbability(State[] allAgents) {
		double probability;
		int countYes=0;

		while(!oneDecizionArray(allAgents)){
			allAgents = changeState(allAgents);
		}
		if(allAgents[0] == State.YES)
			countYes++;
		probability = (double)countYes;
		return probability;
	}

	public boolean oneDecizionArray(State[] allAgents) {
		for(int i=1;i<allAgents.length;i++){
			if(allAgents[0] != allAgents[i])
				return false;
		}
		return true;
	}

	public State[] changeState(State[] allAgents){
		Random rand = new Random();
		int pickOneNumber = rand.nextInt(allAgents.length);
		int pickSecondNumber = rand.nextInt(allAgents.length);
		//jesli sa takie same to losuje drugi numer jeszcze raz
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
