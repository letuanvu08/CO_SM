package command;

import Coin.Coin;
import soda_machine.SodaMachine;
import utils.Input;

import java.util.List;

public class PushCoinCommand extends Command {
    private final List<Coin> coins;

    public PushCoinCommand(SodaMachine sodaMachine) {
        super(sodaMachine);
        coins = Coin.getListCoin();
    }

    @Override
    public void execute() {
        System.out.println("Please Choose coin push: ");
        for (int i = 1; i <= coins.size(); i++)
            System.out.println(String.format("%d: %d", i, coins.get(i - 1).getValue()));
        int choice = Input.getInputWithValidate(1, coins.size());
        if (choice != -1)
            machine.pushCoin(coins.get(choice - 1));
    }
}
