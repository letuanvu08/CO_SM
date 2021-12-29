package command;

import soda_machine.SodaMachine;

public class NextDayCommand extends Command {
    public NextDayCommand(SodaMachine sodaMachine) {
        super(sodaMachine);
    }
    @Override
    public void execute() {
        String message  = machine.nextDay();
        System.out.println(message);
    }
}
