package assignment05;

import java.util.Random;

public enum State {
	HAS_ONE_QUARTER {
		@Override
		public State insertQuarter(GumballMachine gm) {
			System.out.println("You inserted a second quarter");
			gm.setQuarterCount(2);
			return HAS_TWO_QUARTERS;
		}
		@Override
		public State ejectQuarter(GumballMachine gm) {
			System.out.println("One quarter returned");
			gm.setQuarterCount(gm.getQuarterCount() - 1);
			return NO_QUARTER;
		}
	}, HAS_TWO_QUARTERS {
		@Override
		public State insertQuarter(GumballMachine gm) {
			System.out.println("You can't insert more than two quarters");
			return this;
		}
		@Override
		public State ejectQuarter(GumballMachine gm) {
			System.out.println("One quarter returned");
			gm.setQuarterCount(gm.getQuarterCount() - 1);
			return HAS_ONE_QUARTER;
		}
		@Override
		public State turnCrank(GumballMachine gm) {
			System.out.println("You turned...");
			int winner = randomWinner.nextInt(10);
			gm.setQuarterCount(0); // Reset quarter count
			return winner == 0 ? WINNER : SOLD;
		}
	}, NO_QUARTER {
		@Override
		public State insertQuarter(GumballMachine gm) {
			System.out.println("You inserted a quarter");
			gm.setQuarterCount(1);
			return HAS_ONE_QUARTER;
		}
	}, SOLD {
		@Override
		public State dispense(GumballMachine gm, int count) {
			gm.releaseBall();
			if (gm.getCount() > 0) {
				return NO_QUARTER;
			} else {
				System.out.println("Oops, out of gumballs!");
				return SOLD_OUT;
			}
		}
	}, WINNER {
		@Override
		public State dispense(GumballMachine gm, int count) {
			gm.releaseBall();
			if (gm.getCount() > 0) gm.releaseBall();
			if (gm.getCount() > 1) {
				gm.releaseBall();
				System.out.println("YOU'RE A WINNER! You got three gumballs for your quarters");
			} else if (gm.getCount() == 1) {
				System.out.println("YOU'RE A WINNER! But only two gumballs left, so you got two gumballs");
			}
			return gm.getCount() > 0 ? NO_QUARTER : SOLD_OUT;
		}
	}, SOLD_OUT {
		@Override
		public State insertQuarter(GumballMachine gm) {
			System.out.println("You can't insert a quarter, the machine is sold out");
			return this;
		}
	};

	static Random randomWinner = new Random(System.currentTimeMillis());

	// Override other common methods as needed
	public State insertQuarter(GumballMachine gm) {
		return this;
	}
	public State ejectQuarter(GumballMachine gm) {
		return this;
	}
	public State turnCrank(GumballMachine gm) {
		return this;
	}
	public State dispense(GumballMachine gm, int count) {
		return this;
	}
	public State refill() {
		return this;
	}
}
