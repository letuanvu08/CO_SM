package Request;

import product.Product;

import java.util.List;

public class Request {
    private long balance;
    private Product product;

    public long getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public void addCoin(long amount){
        this.balance+=amount;
    }
    public boolean enoughMoney(){
        return hasChooseProduct() && this.balance>= this.product.getPrice();
    }
    public boolean hasChooseProduct(){
        return this.product!=null;
    }
    public Product releaseProduct(){
        this.balance-=this.product.getPrice();
        Product product = this.product;
        setProduct(null);
        return product;
    }
}
