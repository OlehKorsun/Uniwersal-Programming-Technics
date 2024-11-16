/**
 *
 *  @author Korsun Oleh S30911
 *
 */

package zad1;

import java.util.Comparator;

public class Purchase {
    String index;
    String nameAndSurname;
    String product;
    double price;
    double quantyty;

    public Purchase(String index, String nameAndSurname, String product, double price, double quantyty) {
        this.index = index;
        this.nameAndSurname = nameAndSurname;
        this.product = product;
        this.price = price;
        this.quantyty = quantyty;
    }

    @Override
    public String toString() {
        return index +
                ";" + nameAndSurname +
                ";" + product +
                ";" + price +
                ";" + quantyty;
    }
}

class PurchaseSortedBySyrname implements Comparator<Purchase> {

    @Override
    public int compare(Purchase o1, Purchase o2) {
        int tmp = o1.nameAndSurname.compareTo(o2.nameAndSurname);
        return tmp == 0 ? o1.index.compareTo(o2.index) : tmp;
    }
}

class PurchaseSortedByPrice implements Comparator<Purchase>{
    @Override
    public int compare(Purchase o1, Purchase o2) {
        int tmp = Double.valueOf(o2.price*o2.quantyty).compareTo(Double.valueOf(o1.price*o1.quantyty));
        return tmp==0 ? o1.index.compareTo(o2.index) : tmp;
    }
}