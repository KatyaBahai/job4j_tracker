package ru.job4j.bank;

import ru.job4j.bank.Account;
import ru.job4j.bank.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает работу банковского сервиса, который объединяет для работы пользоателей банка и их счетов.
 * Класс позволяет добавлять, искать и удалять пользователей, переводить деньги с одного счета на другой.
 * @author EKATERINA SHCHERBAKOVA
 * @version 1.0
 */
public class BankService {
    /**
     * Хранение пользователей осуществляется в HashMap, где ключом является пользователь,
     * а значением - список счетов этого пользователя.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользователя и помещает его в Map с пользователями, если такого
     * пользователя там еще нет.
     * @param user пользователь, которого нужно поместить в HashMap пользователей.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод принимает на вход номер паспорта пользователя, который удаляется из Map пользователей.
     * @param passport паспорт пользователя, который будет удален в методе
     * @return возвращает true, если удаление прошло успешно, false - если удаления не произошло.
     */
    public boolean deleteUser(String passport) {
        return users.remove(new User(passport, "")) != null;
    }

    /**
     * Метод добавляет новый счет пользователю.
     * @param passport паспорт пользователя, который добавляется в HashMap пользователей
     * @param account вовращает аккаунт пользователя, который добавляется в HashMap пользователей
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод ищет пользователя по номеру паспорта.
     * @param passport паспорт пользователя, которого нужно найти
     * @return возвращает искомого пользователя.
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> (user.getPassport().equals(passport)))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод ищет аккаунт по реквизитам.
     * @param passport паспорт пользователя, аккаунт которого находит метод
     * @param requisite реквизиты, по которым происходит поиск аккаунта
     * @return возвращает искомый аккаунт
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            return users.get(user)
                    .stream()
                    .filter(account -> account.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод переводит деньги с одного счета на другой счет.
     * Если денег не хватает или счета не существует, то метод возвращает false.
     * @param srcPassport паспорт пользователя, со счета которого переводятся деньги
     * @param srcRequisite реквизиты аккаунта пользователя, со счета которого переводятся деньги
     * @param destPassport паспорт пользователя, которому переводятся деньги
     * @param destRequisite - реквизиты аккаунта пользователя, которому переводятся деньги
     * @param amount - сумма денег для перевода
     * @return метод возвращает true, если деньги успешно переведены, false - если перевод не осуществлен.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);

        if (srcAccount != null && destAccount != null && amount <= srcAccount.getBalance()) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            rsl = true;
    }
        return rsl;
    }

    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}