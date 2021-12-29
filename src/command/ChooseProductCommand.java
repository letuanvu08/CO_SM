package command;

import product.Product;
import soda_machine.SodaMachine;
import utils.Input;

import java.util.List;

public class ChooseProductCommand extends Command {
    private final List<Product> products;

    public ChooseProductCommand(SodaMachine sodaMachine) {
        super(sodaMachine);
        this.products = Product.getProducts();
    }

    @Override
    public void execute() {
        System.out.println("Please Choose product: ");
        for (int i = 1; i <= products.size(); i++)
            System.out.println(String.format("%d: Product: %s - Price: %d", i, products.get(i - 1).getName(),products.get(i-1).getPrice()));
        int choice = Input.getInputWithValidate(1, products.size());
        if (choice != -1)
            machine.chooseProduct(products.get(choice - 1));
    }
}
