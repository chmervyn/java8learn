package com.sap.mervyn.methodreference.predicate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class StreamTest1 {

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction0 = new Transaction(10, "CHN", 0);
        Transaction transaction1 = new Transaction(39, "USA", 1);
        Transaction transaction2 = new Transaction(71, "SPA", 2);
        Transaction transaction3 = new Transaction(22, "MEX", 3);
        Transaction transaction4 = new Transaction(44, "CHN", 4);
        Transaction transaction5 = new Transaction(56, "FRA", 5);
        Transaction transaction6 = new Transaction(11, "CHN", 6);
        Transaction transaction7 = new Transaction(69, "CAN", 7);
        Transaction transaction8 = new Transaction(23, "USA", 8);
        Transaction transaction9 = new Transaction(31, "CHN", 9);
        Transaction transaction10 = new Transaction(27, "BRT", 10);
        Transaction transaction11 = new Transaction(16, "CHN", 11);
        Transaction transaction12 = new Transaction(19, "ITA", 12);
        Transaction transaction13 = new Transaction(33, "CHN", 13);
        Transaction transaction14 = new Transaction(21, "FRA", 14);
        Transaction transaction15 = new Transaction(14, "GER", 15);

        transactions.add(transaction0);
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        transactions.add(transaction4);
        transactions.add(transaction5);
        transactions.add(transaction6);
        transactions.add(transaction7);
        transactions.add(transaction8);
        transactions.add(transaction9);
        transactions.add(transaction10);
        transactions.add(transaction11);
        transactions.add(transaction12);
        transactions.add(transaction13);
        transactions.add(transaction14);
        transactions.add(transaction15);

        // implementation before java 8
        Map<String, List<Transaction>> result = new HashMap<>();
        for (Transaction transaction : transactions) {
            if (transaction.getPrice() > 30) {
                List<Transaction> currecyTransactions = result.get(transaction.getCurrency());
                if (currecyTransactions == null) {
                    currecyTransactions = new ArrayList<>();
                    result.put(transaction.getCurrency(), currecyTransactions);
                }

                currecyTransactions.add(transaction);
            }
        }
        result.forEach((key, value) -> {
            value.stream().forEach(transaction -> {
                System.out.println("currency: " + key + ", " + transaction);
            });
        });
        System.out.println("==============================");

        result = transactions.stream().filter(transaction -> transaction.getPrice() > 30)
                .collect(groupingBy(Transaction::getCurrency));
        result.forEach((key, value) -> {
            value.stream().forEach(transaction -> {
                System.out.println("currency: " + key + ", " + transaction);
            });
        });
        System.out.println("==============================");
    }

}

class Transaction {
    private int price;
    private String currency;
    private int id;

    public Transaction() {
    }

    public Transaction(int price, String currency, int id) {
        this.price = price;
        this.currency = currency;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "price=" + price +
                ", currency='" + currency + '\'' +
                ", id=" + id +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
