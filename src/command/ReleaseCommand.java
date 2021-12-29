package command;

import soda_machine.SodaMachine;

public class ReleaseCommand extends Command {
    public ReleaseCommand(SodaMachine sodaMachine) {
        super(sodaMachine);
    }

    @Override
    public void execute() {
        String message = machine.release();
        System.out.println(message);
    }
}
