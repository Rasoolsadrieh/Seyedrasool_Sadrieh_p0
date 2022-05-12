package com.revature.Rossbank.util.collections;

import com.revature.Rossbank.models.BankUser;

public class TempDriver {

    public static void main(String[] args) {
        // Establishing a new list object called trainersLL as a linkedlist to memory
        List<BankUser> trainersLL = new LinkedList<>();

        BankUser bankUser = new BankUser("char", "le", "cl@mail.com", "pass", "02-21-1987");
        BankUser bankUser2 = new BankUser("ar", "qa", "aq@mail.com", "pass", "02-21-1987");
        BankUser bankUser3 = new BankUser();
        System.out.println(trainersLL.add(bankUser));
        System.out.println(trainersLL.add(bankUser));
        System.out.println(trainersLL.add(bankUser2));
        System.out.println(trainersLL.add(bankUser));
        System.out.println(trainersLL.add(bankUser));
        System.out.println(trainersLL.add(bankUser));
        System.out.println(trainersLL.size());

        System.out.println(trainersLL.contains(bankUser));
        System.out.println(trainersLL.remove(bankUser3));
        System.out.println(trainersLL.size());
        System.out.println(trainersLL.get(2));

        List<String> stringLL = new LinkedList<>();
        stringLL.add("Hello there");
        stringLL.add("Ahhhh");
        stringLL.add("General");
        stringLL.add("Kenobi");

        System.out.println(stringLL.contains("General"));
        System.out.println(stringLL.get(2));

        List<Integer> integerList = new LinkedList<>();
        integerList.add(1);
    }

}
