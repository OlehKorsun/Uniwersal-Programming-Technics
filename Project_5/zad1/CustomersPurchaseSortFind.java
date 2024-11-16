package zad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CustomersPurchaseSortFind {
    ArrayList<Purchase> list;
    public void readFile(String path){
        list = new ArrayList<>();
        try{
            List<String> date = Files.readAllLines(Paths.get(path));
            for(String el : date){
                String[] tmp = el.split(";");
                list.add(new Purchase(tmp[0], tmp[1], tmp[2], Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4])));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showSortedBy(String str){

        if(str.equals("Nazwiska")){
            System.out.println();
            System.out.println("Nazwiska");
            PurchaseSortedBySyrname comparator = new PurchaseSortedBySyrname();
            ArrayList<Purchase> tmp = new ArrayList<>();
            for(Purchase el : list)
                tmp.add(el);
            tmp.sort(comparator);
            for(Purchase e : tmp){
                System.out.println(e);
            }

        }
        else if(str.equals("Koszty")){
            System.out.println();
            System.out.println("Koszty");
            PurchaseSortedByPrice comparator = new PurchaseSortedByPrice();
            ArrayList<Purchase> tmp = new ArrayList<>();
            for(Purchase el : list)
                tmp.add(el);
            tmp.sort(comparator);
            for(Purchase e : tmp){
                System.out.println(e + " (koszt: "+e.quantyty*e.price+")");
            }

        }
    }

    public void showPurchaseFor(String str){
        System.out.println();
        System.out.println("Klient "+str);
        for(Purchase el : list){
            if(el.index.equals(str))
                System.out.println(el);
        }

    }
}
