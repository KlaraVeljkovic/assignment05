package assignment05;

import java.util.Random;

public enum State {
    HAS_TWO_QUARTERS {
        @Override
        public State insertQuarter() {
            System.out.println("Already have two quarters");
            return this;
        }

        @Override
        public State ejectQuarter() {
            System.out.println("One quarter returned");
            return HAS_QUARTER;
        }

        @Override
        public State turnCrank() {
            System.out.println("You turned...");
            int winner = randomWinner.nextInt(10);
            if (winner == 0) {
                return WINNER;
            } else {
                return SOLD;
            }
        }

        @Override
        public State dispense(GumballMachine gm, int count) {
            System.out.println("No gumball dispensed");
            return this;
        }

        public String toString() {
            return "waiting for turn of crank";
        }
    },
    HAS_QUARTER {
        @Override
        public State insertQuarter() {
            System.out.println("You inserted a quarter");
            return HAS_TWO_QUARTERS;
        }

        @Override
        public State ejectQuarter() {
            System.out.println("Quarter returned");
            return NO_QUARTER;
        }

        @Override
        public State turnCrank() {
            System.out.println("You turned, but you need two quarters");
            return this;
        }

        @Override
        public State dispense(GumballMachine gm, int count) {
            System.out.println("You need to pay two quarters");
            return this;
        }

        public String toString() {
            return "waiting for second quarter";
        }
    },
    NO_QUARTER {
        @Override
        public State insertQuarter() {
            System.out.println("You inserted a quarter");
            return HAS_QUARTER;
        }

        @Override
        public State ejectQuarter() {
            System.out.println("You haven't inserted a quarter");
            return this;
        }

        @Override
        public State turnCrank() {
            System.out.println("You turned, but there's no quarter");
            return this;
        }

        @Override
        public State dispense(GumballMachine gm, int count) {
            System.out.println("You need to pay first");
            return this;
        }

        public String toString() {
            return "waiting for quarter";
        }
    },
    SOLD_OUT {
        @Override
        public State insertQuarter() {
            System.out.println("You can't insert a quarter, the machine is sold out");
            return this;
        }

        @Override
        public State ejectQuarter() {
            System.out.println("You can't eject, you haven't inserted a quarter yet");
            return this;
        }

        @Override
        public State turnCrank() {
            System.out.println("You turned, but there are no gumballs");
            return this;
        }

        @Override
        public State dispense(GumballMachine gm, int count) {
            System.out.println("No gumball dispensed");
            return this;
        }

        @Override
        public State refill() {
            return NO_QUARTER;
        }

        public String toString() {
            return "sold out";
        }
    },
    SOLD {
        @Override
        public State insertQuarter() {
            System.out.println("Please wait, we're already giving you a gumball");
            return this;
        }

        @Override
        public State ejectQuarter() {
            System.out.println("Sorry, you already turned the crank");
            return this;
        }

        @Override
        public State turnCrank() {
            System.out.println("Turning twice doesn't get you another gumball!");
            return this;
        }

        @Override
        public State dispense(GumballMachine gm, int count) {
            gm.releaseBall();
            if (gm.getCount() == 0) {
                System.out.println("Oops, out of gumballs!");
                return SOLD_OUT;
            } else {
                return NO_QUARTER;
            }
        }

        public String toString() {
            return "dispensing a gumball";
        }
    },
    WINNER {
        @Override
        public State insertQuarter() {
            System.out.println("Please wait, we're already giving you a gumball");
            return this;
        }

        @Override
        public State ejectQuarter() {
            System.out.println("Sorry, you already turned the crank");
            return this;
        }

        @Override
        public State turnCrank() {
            System.out.println("Turning twice doesn't get you another gumball!");
            return this;
        }

        @Override
        public State dispense(GumballMachine gm, int count) {
            gm.releaseBall();
            if (gm.getCount() > 0) gm.releaseBall();
            if (gm.getCount() > 0) {
                System.out.println("YOU'RE A WINNER! You got three gumballs for your two quarters");
                return NO_QUARTER;
            } else {
                System.out.println("Oops, out of gumballs!");
                return SOLD_OUT;
            }
        }

        public String toString() {
            return "dispensing three gumballs, because YOU'RE A WINNER!";
        }
    };

    static Random randomWinner = new Random(System.currentTimeMillis());

    public State insertQuarter() {
        return this;
    }

    public State ejectQuarter() {
        return this;
    }

    public State turnCrank() {
        return this;
    }

    public State dispense(GumballMachine gmIn, int count) {
        return this;
    }

    public State refill() {
        return this;
    }
}
