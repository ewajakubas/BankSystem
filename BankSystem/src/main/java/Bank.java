
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Bank {

    private List<Account> bankAccounts;
    private Scanner sc;
// pierwszy kom
    private final HashMap<String, Integer> countries = new HashMap<String, Integer>() {
        {
            put("A", 10);
            put("B", 11);
            put("C", 12);
            put("D", 13);
            put("E", 14);
            put("F", 15);
            put("G", 16);
            put("H", 17);
            put("I", 18);
            put("J", 19);
            put("K", 20);
            put("L", 21);
            put("M", 22);
            put("N", 23);
            put("O", 24);
            put("P", 25);
            put("Q", 26);
            put("R", 27);
            put("S", 28);
            put("T", 29);
            put("U", 30);
        }
    };

    public List<Account> getBankAccounts() {
        return bankAccounts;
    }

    public Bank() {
        bankAccounts = new ArrayList<>();
        sc = new Scanner(System.in);

    }

    public Account isAccountExist(String login, String password) {
        for (Account account : bankAccounts) {
            if (account.getLogin().equals(login) && account.getPassword().equals(password)) {
                return account;
            }
        }
        System.out.println("Nieprawidlowe dane");
        return null;
    }

    public Account loginAccount(String login, String password) {
        for (Account account : bankAccounts) {
            if (account.getLogin().equals(login) && account.getPassword().equals(password)) {
                return account;
            }
        }

        return null;
    }

    public Account getAccountByNumber(String accountNumber) {
        for (Account account : bankAccounts) {
            if (account.getIban().equals(accountNumber)) {
                return account;
            }

        }
        return null;
    }

    public boolean isAccountNumberCorect(String accountNumber) throws Exception {
        boolean isCorrect = false;
        String formattedAccountNumber = accountNumber.replace(" ", "");
        String countryCode = formattedAccountNumber.substring(0, 2);

        if (!countryCode.toUpperCase().equals("PL")) {
            throw new Exception("Nie mozna realizowac przelewow miedzynarodowych");

        }

        if (formattedAccountNumber.length() != 28) {
            throw new Exception("Nieprawidlowa dlugosc konta");
        }

        String s = formattedAccountNumber.substring(4) + formattedAccountNumber.substring(0, 4);
        String ss = s.replace("P", countries.get("P").toString()).replace("L", countries.get("L").toString());
        BigInteger number = new BigInteger(ss);
        BigInteger rest = number.mod(new BigInteger("97"));
        if (rest.compareTo(new BigInteger("1")) == 0) {
            isCorrect = true;
        }
        return isCorrect;
    }

}
