package command;

import soda_machine.SodaMachine;

public class CancelCommand extends Command{
    public CancelCommand(SodaMachine sodaMachine) {
        super(sodaMachine);
    }

    @Override
    public void execute() {
       String message = machine.cancel();
       System.out.println(message);
    }
}
