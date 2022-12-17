package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private final Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    public boolean deleteUser(String passport) {
        List<Account> deleted = users.remove(findByPassport(passport));
        return deleted != null;
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        List<Account> accounts = users.get(user);
        if (!accounts.contains(account)) {
            accounts.add(account);
        }
    }

    public User findByPassport(String passport) {
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    public Account findByRequisite(String passport, String requisite) {
        List<Account> accounts = users.get(findByPassport(passport));
        if (accounts != null) {
            for (Account account : accounts) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account account1 = findByRequisite(srcPassport, srcRequisite);
        Account account2 = findByRequisite(destPassport, destRequisite);
        User user1 = findByPassport(srcPassport);
        User user2 = findByPassport(destPassport);
        List<Account> accounts1 = users.get(user1);
        List<Account> accounts2 = users.get(user2);
        if (accounts1.contains(account1) && accounts2.contains(account2) && amount <= account1.getBalance()) {
            account1.setBalance(account1.getBalance() - amount);
        account2.setBalance(account2.getBalance() + amount);
        rsl = true;
    }
        return rsl;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}