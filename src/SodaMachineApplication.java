import soda_machine.IMachine;
import soda_machine.SodaMachine;

class SodaMachineApplication{
    public static void main(String[] args) {
        long budget = 50000;
        double rateWinInit = 0.1;
        double rateIncreased = 0.5;
        System.out.println("Note: Action exit and next day use for testing product.");
        IMachine sodaMachine = new SodaMachine(rateWinInit,budget,rateIncreased);
        sodaMachine.run();
    }
}