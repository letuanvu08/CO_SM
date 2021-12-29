package soda_machine;

import Coin.Coin;
import product.Product;

public interface IMachine {
    void pushCoin(Coin coin);
    void chooseProduct(Product product);
    String  release();
    String cancel();
    String nextDay();
    String getStatusRequest();

    void run();
}
