import classes.*;
import state_class.State;

public class Main {

    public static void main(String[] args) {
    	Mcarlo mc = new Mcarlo(1000);
    	double prob = 0.0;
		State[] s = mc.createMcarlo(20,10);
    	for(int i=0;i<1000;i++) {
    		State[] cp = s.clone();

    		prob = prob + mc.countProbability(cp);
		}
		System.out.println("\n prawdopodobienstwo wyszlo mi takie: " + prob);
    }
}