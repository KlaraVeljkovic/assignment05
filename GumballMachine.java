package assignment05;

public class GumballMachine {

	State state;
	int count = 0;
	int quarterCount = 0; // Track the number of quarters inserted

	public GumballMachine(int numberGumballsIn) {
		count = numberGumballsIn;
 		if (count > 0) {
			state = State.NO_QUARTER;
		} else {
			state = State.SOLD_OUT;
		}
	}

	public void insertQuarter() {
		state = state.insertQuarter(this);
	}

	public void ejectQuarter() {
		state = state.ejectQuarter(this);
	}

	public void turnCrank() {
		state = state.turnCrank(this);
		state = state.dispense(this, count);
	}

	void releaseBall() {
		System.out.println("A gumball comes rolling out the slot...");
		if (count > 0) {
			count = count - 1;
		}
	}

	public int getCount() {
		return count;
	}

	void refill(int count) {
		this.count += count;
		System.out.println("The gumball machine was just refilled; its new count is: " + this.count);
		state = state.refill();
	}

	// Quarter tracking
	public int getQuarterCount() 
	{
		return quarterCount;
	}
	public void setQuarterCount(int quarterCount) 
	{
		this.quarterCount = quarterCount;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("\nMighty Gumball, Inc.");
		result.append("\nJava-enabled Standing Gumball Model #2004");
		result.append("\nInventory: " + count + " gumball");
		if (count != 1) {
			result.append("s");
		}
		result.append("\n");
		result.append("Machine is " + state + "\n");
		return result.toString();
	}
}
