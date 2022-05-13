package com.revature.Rossbank.util.collections;

import com.revature.Rossbank.models.BankUser;

public class TempDriver {

    public static void main(String[] args) {
        // Establishing a new list object called usersLL as a linkedlist to memory
        List<BankUser> usersLL = new LinkedList<>();

        BankUser bankUser = new BankUser(1, "le", "sebai","cl@mail.com", "pass", "02-21-1987");
        BankUser bankUser2 = new BankUser(2, "qa", "gerna","aq@mail.com", "pass", "02-21-1987");
        BankUser bankUser3 = new BankUser();
        System.out.println(usersLL.add(bankUser));
        System.out.println(usersLL.add(bankUser));
        System.out.println(usersLL.add(bankUser2));
        System.out.println(usersLL.add(bankUser));
        System.out.println(usersLL.add(bankUser));
        System.out.println(usersLL.add(bankUser));
        System.out.println(usersLL.size());

        System.out.println(usersLL.contains(bankUser));
        System.out.println(usersLL.remove(bankUser3));
        System.out.println(usersLL.size());
        System.out.println(usersLL.get(2));

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
