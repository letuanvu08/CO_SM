package command;
import soda_machine.SodaMachine;

 public  abstract class Command{
    protected SodaMachine machine;

    public Command(SodaMachine sodaMachine) {
        this.machine = sodaMachine;
    }
    public abstract void execute();
}