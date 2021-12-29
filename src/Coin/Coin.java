package Coin;

import java.util.ArrayList;
import java.util.List;

public class Coin {
    private static List<Coin> coins;
    private final long value;

    private Coin(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public static List<Coin> getListCoin() {
        if (coins == null) {
            coins = List.of(
                    new Coin(10000),
                    new Coin(20000),
                    new Coin(50000),
                    new Coin(100000),
                    new Coin(200000)
            );
        }
        return coins;
    }

    public static List<Coin> getMinimalCoinRefund(long refund) {
        List<Coin> coinRefunds = new ArrayList<>();
        while (refund > 0) {
            for (int i = coins.size() - 1; i >= 0; i--) {
                Coin coin = coins.get(i);
                if (refund - coin.value >= 0) {
                    coinRefunds.add(coin);
                    refund -= coin.value;
                    break;
                }
            }
        }
        return coinRefunds;
    }
}
