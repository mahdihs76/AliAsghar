//package com.company;

import nooran.Child2;

import java.util.*;

public class Main {

    private static final String MULTIPLE_ADD_REGEX = "add [^ ]+( [^ ]+( \\d+){2})*";

    public static void main(String[] args) {

        Child2 child2 = new Child2();
        int field = child2.field();


        ShoppingCenter shoppingCenter = new ShoppingCenter();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(!input.equals("end")){
            String[] words = input.split(" ");
            if(input.matches("add.*")){
                if(input.matches(MULTIPLE_ADD_REGEX)){
                    if(words[1].matches("[^\\d].*") && words.length>2){
                        Set<String> productNames = new HashSet<>();
                        HashMap<Product, Integer> products = new HashMap<>();
                        boolean flag = false;
                        for(int ind = 2; ind<words.length; ind+=3){
                            if(productNames.contains(words[ind])) {
                                System.out.println("invalid input");
                                flag = true;
                                break;
                            }
                            products.put(new Product(words[ind],Integer.parseInt(words[ind+2])),Integer.parseInt(words[ind+1]));
                            productNames.add(words[ind]);
                        }
                        if(flag == false)
                            shoppingCenter.addStore(words[1],products);
                    }
                }
                else
                    System.out.println("invalid input");
            }
            else if(input.matches("buy from [^ ]+ [^ ]+ \\d+")){
                Store store = shoppingCenter.findStore(words[2]);
                if(store == null)
                    System.out.println("no such store");
                else {
                    Product product = store.findProduct(words[3]);
                    int number = Integer.parseInt(words[4]);
                    if (product != null && store.getProducts().get(product) >= number)
                        shoppingCenter.sell(words[2], words[3], number);
                    else
                        System.out.println("we don't have it");
                }

            }
            else if(input.matches("buy for [^ ]+ [^ ]+( \\d+){1,2}")){
                if(input.matches("buy for [^ ]+ [^ ]+( \\d+){2}")){
                    Store store = shoppingCenter.findStore(words[2]);
                    if(store == null)
                        System.out.println("no such store");
                    else if(Integer.parseInt(words[4])*Integer.parseInt(words[5])<= store.getMoney())
                        shoppingCenter.buy(words[2],words[3],Integer.parseInt(words[4]), Integer.parseInt(words[5]));
                    else
                        System.out.println("not enough money");
                }
                else
                    System.out.println("invalid input");

            }
            else if(input.matches("change price [^ ]+ of [^ ]+( \\d+)?")){
                if(input.matches("change price [^ ]+ of [^ ]+ \\d+")){
                    Store store = shoppingCenter.findStore(words[4]);
                    if(store == null)
                        System.out.println("no such store");
                    else{
                        store.changePrice(words[2],Integer.parseInt(words[5]));
                    }
                }
                else
                    System.out.println("invalid input");
            }
            else if(input.matches("show.*")){
                Store store = shoppingCenter.findStore(words[1]);
                if(store == null)
                    System.out.println("no such store");
                else
                    store.show();

            }
            input = scanner.nextLine();
        }
    }
}

class ShoppingCenter {
    public ArrayList<Store> stores = new ArrayList<>();

    public void addStore(String storeName, HashMap<Product, Integer> products){
        Store store = new Store();
        store.start(storeName, products);
        stores.add(store);
    }

    public void show(String storeName){
        Store store = findStore(storeName);
        store.show();
    }

    public void sell(String storeName, String productName, int number){
        Store store = findStore(storeName);
        store.sell(productName, number);
    }

    public void buy(String storeName, String productName, int number, int price){
        Store store = findStore(storeName);
        Product product = store.findProduct(productName);
        if(product == null) {
            product = new Product(productName, price);
            store.getProducts().put(product, 0);
        }
        else
            product.setPrice(price);
        store.buy(product, number);
    }

    public void changePrice(String storeName, String productName, int newPrice){
        Store store = findStore(storeName);
        store.changePrice(productName, newPrice);
    }

    public Store findStore(String storeName){
        Store found = null;
        for(Store store : stores){
            if(store.getName().equals(storeName)) {
                found = store;
            }
        }
        return found;
    }
}
class Product {
    private String name;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
class Store {

    private String name;
    private int money = 20;
    private HashMap<Product,Integer> products = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Product, Integer> getProducts() {
        return products;
    }

    public void changePrice(String productName, int newPrice){
        findProduct(productName).setPrice(newPrice);
    }

    public void buy(Product product, int number){
        products.put(product, products.get(product) + number);
        money -= number * product.getPrice();
    }

    public void sell(String productName, int number){
        Product product = findProduct(productName);
        if(products.get(product) == number)
            products.remove(product);
        else
            products.put(product, products.get(product) - number);
        money += number * product.getPrice();
    }

    public void start(String storeName, HashMap<Product, Integer> products){
        name = storeName;
        this.products = products;
    }

    public void show(){
        List<Map.Entry<Product, Integer>> list = new ArrayList(products.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Product, Integer>>() {
            @Override
            public int compare(Map.Entry<Product, Integer> first, Map.Entry<Product, Integer> second) {
                if(first.getValue().equals(second.getValue()))
                    return first.getKey().getName().compareTo(second.getKey().getName());
                else
                    return first.getValue() - second.getValue();
            }
        });
        for(Map.Entry<Product, Integer> entry : list){
            System.out.printf("%s %d %d\n", entry.getKey().getName(), entry.getValue(), entry.getKey().getPrice());
        }
    }

    public Product findProduct(String productName){
        Product found = null;
        for(Product product : products.keySet()){
            if(product.getName().equals(productName)) {
                found = product;
            }
        }
        return found;
    }

    public int getMoney() {
        return money;
    }
}