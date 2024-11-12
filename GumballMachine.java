package assignment05;

public class GumballMachine {
    State state;
    int count = 0;
    int quarterCount = 0;

    public GumballMachine(int numberGumballsIn) {
        count = numberGumballsIn;
        if (count > 0) {
            state = State.NO_QUARTER;
        } else {
            state = State.SOLD_OUT;
        }
    }

    public void insertQuarter() {
        if (quarterCount < 2) {
            quarterCount++;
            state = state.insertQuarter();
        } else {
            System.out.println("Already have two quarters, can't insert more");
        }
    }

    public void ejectQuarter() {
        if (quarterCount > 0) {
            quarterCount--;
            state = state.ejectQuarter();
        } else {
            System.out.println("No quarters to eject");
        }
    }

    public void turnCrank() {
        if (quarterCount == 2) {
            state = state.turnCrank();
            state = state.dispense(this, count);
            quarterCount = 0;  // reset quarters after turning crank
        } else {
            System.out.println("You need to insert two quarters to turn the crank");
        }
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
