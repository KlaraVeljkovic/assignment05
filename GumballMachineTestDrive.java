package assignment05;

public class GumballMachineTestDrive {

	public static void main(String[] args) {
		GumballMachine gumballMachine = new GumballMachine(100);

		for (int i = 0; i < 10; i++) {
			System.out.println(gumballMachine);

			// Insert two quarters
			gumballMachine.insertQuarter();
			gumballMachine.insertQuarter();

			// Turn the crank
			gumballMachine.turnCrank();
		}

		System.out.println(gumballMachine);
	}
}
