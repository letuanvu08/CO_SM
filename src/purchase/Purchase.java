package purchase;

import product.Product;

public class Purchase {
    private boolean isFree;
    private Product product;

    public Purchase(boolean isFree, Product product) {
        this.isFree = isFree;
        this.product = product;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
