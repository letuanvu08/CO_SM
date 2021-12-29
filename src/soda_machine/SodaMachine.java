package soda_machine;

import Coin.Coin;
import Request.Request;
import command.*;
import product.Product;
import purchase.Purchase;
import utils.Input;
import java.util.*;

public class SodaMachine implements IMachine {
    private double rateWin;
    private long remainBudget;
    private final double rateIncrease;
    private final long budget;
    private List<Purchase> historys;
    private Request currentRequest;
    private Map<Action, Command> commands;

    public SodaMachine(double rateWin, long budget, double rateIncrease) {
        this.rateWin = rateWin;
        this.budget = budget;
        this.remainBudget = budget;
        this.rateIncrease = rateIncrease;
        historys = new ArrayList<>();
        currentRequest = new Request();
        this.commands = Map.of(
                Action.PUSH_COIN, new PushCoinCommand(this),
                Action.CHOOSE_PRODUCT, new ChooseProductCommand(this),
                Action.RELEASE, new ReleaseCommand(this),
                Action.CANCEL, new CancelCommand(this),
                Action.NEXT_DAY, new NextDayCommand(this));
    }

    @Override
    public void pushCoin(Coin coin) {
        currentRequest.addCoin(coin.getValue());
    }

    @Override
    public void chooseProduct(Product product) {
        currentRequest.setProduct(product);
    }

    @Override
    public String release() {
        if (!currentRequest.hasChooseProduct()) {
            return "Please choose one product";
        }
        if (!currentRequest.enoughMoney()) {
            return "Please push more coin";
        }
        Product product = currentRequest.releaseProduct();
        if (checkPromotion(product)) {
            this.historys.clear();
            this.remainBudget -= product.getPrice();
            this.currentRequest.addCoin(product.getPrice());
            historys.add(new Purchase(true,product));
            return String.format("Congratulations, you receive %s for free!", product.getName());
        }
        historys.add(new Purchase(false,product));
        return String.format("Release product: %s", product.getName());
    }

    @Override
    public String cancel() {
        this.currentRequest.setProduct(null);
        List<Coin> coiRefunds = Coin.getMinimalCoinRefund(this.currentRequest.getBalance());
        this.currentRequest.setBalance(0);
        return String.format("Refund coin: %s",
                coiRefunds.stream()
                        .map(coin -> Long.toString(coin.getValue()))
                        .reduce("", (subStr, coin) -> subStr + " " + coin));
    }

    @Override
    public String nextDay() {
        if (this.remainBudget > 0)
            this.rateWin = this.rateWin * (this.rateIncrease + 1) > 1 ? 1 : this.rateWin * (this.rateIncrease+1);
        this.remainBudget = this.budget;
        return String.format("win rate = %d", (int) (rateWin * 100)) + "%";
    }

    @Override
    public String getStatusRequest() {
        return String.format("Balance: %d \nProduct: %s\nRemainBudget: %d",
                currentRequest.getBalance(),
                currentRequest.getProduct() != null ? currentRequest.getProduct().getName() : "",
                this.remainBudget);
    }

    private boolean checkPromotion(Product productRequest) {
        Product lastProduct = this.historys.size() == 0 ? null :
                this.historys.get(historys.size() - 1).getProduct();
        boolean sameProduct =
                                lastProduct != null &&
                                historys.size()>=3 &&
                                historys
                                        .subList(historys.size()-3,historys.size())
                                        .stream()
                                        .allMatch(purchase -> purchase.getProduct().equals(lastProduct) && !purchase.isFree());
        return sameProduct && remainBudget - productRequest.getPrice() >= 0 && Math.random() <= rateWin;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("************************************************************************");
            System.out.println("Please Choose action: ");
            for (Action action : Action.values())
                System.out.println(String.format("%d: %s", action.ordinal() + 1, action.name()));
            int choice = Input.getInputWithValidate(1, Action.values().length + 1);
            if (choice == -1)
                continue;
            Action action = Action.values()[choice - 1];
            if (action == Action.EXIT)
                break;
            Command command = commands.get(action);
            System.out.println("************************************************************************");
            command.execute();
            System.out.println("************************************************************************");
            System.out.println(getStatusRequest());
        }
    }
}