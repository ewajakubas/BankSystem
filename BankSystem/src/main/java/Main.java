
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
// bbbbbb
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();
        try {
            initializeData(bank);

            boolean exitRequested = false;
            while (!exitRequested) {
                System.out.println("Wybierz:\n"
                        + "1.Zaloguj\n"
                        + "2.Wyjdz");

                String choice = sc.next();
                switch (choice) {
                    case "1":
                        System.out.println("Wpisz login");
                        String login = sc.next();
                        System.out.println("Podaj haslo");
                        String password = sc.next();

                        Account account = bank.loginAccount(login, password);
                        if (account != null) {
                            loggedUserMenu(account, bank);
                        } else {
                            System.out.println("Podany login nie isnieje lub haslo jest nieprawidlowe");
                        }
                        break;
                    case "2":
                        exitRequested = true;
                        break;
                    default:
                        System.out.println("Niepoprawny wybor");
                        break;
                }
            }
        } catch (Exception ex) {
            System.out.println("Wystapil nieoczekiwany blad " + ex.getMessage());
        }
    }

    private static void initializeData(Bank bank) {
        Account account1 = new Account("Ewa", "Jakubas", "ewa", "123456", "PL83101010230000261395100000");
        Account account2 = new Account("Jan", "Kowalski", "jan", "qwerty", "00999999999999999999999999999");
        bank.getBankAccounts().add(account1);
        bank.getBankAccounts().add(account2);

    }

    private static void loggedUserMenu(Account account, Bank bank) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean exitRequested = false;
        while (!exitRequested) {
            System.out.println("Wybierz akcje\n"
                    + "0.Stan konta\n"
                    + "1.Wplac pieniadze\n"
                    + "2.Wyplac pieniadze\n"
                    + "3.Wykonaj przelew\n"
                    + "4.Wyjdz");

            String userChoice = sc.next();
            switch (userChoice) {
                case "0":
                    System.out.println("Stan konta:" + account.getBalance());
                    break;
                case "1":
                    System.out.println("Podaj kwote wplaty:");
                    BigDecimal depositAmount = sc.nextBigDecimal();
                    account.depositMoney(depositAmount);
                    break;
                case "2":
                    System.out.println("Podaj kwote wyplaty");
                    BigDecimal withdrawalAmount = sc.nextBigDecimal();
                    account.withdrawal(withdrawalAmount);
                    break;
                case "3":
                    try {
                    System.out.println("Podaj konto, na ktore chcesz wyslac pieniadze ");
                    String accountNumber = sc.next();
                    if (!bank.isAccountNumberCorect(accountNumber)) {
                        System.out.println("Numer konta jest nieprawidlowy");
                        break;
                    }

                    Account toAccount = bank.getAccountByNumber(accountNumber);
                    if (toAccount == null) {
                        System.out.println("Rachunek odbiorcy nie istnieje");
                    } else {

                        System.out.println("podaj kwote, ktra chcesz przelac");
                        BigDecimal moneyToTransfer = sc.nextBigDecimal();
                        account.moneyTransfer(toAccount, moneyToTransfer);
                        System.out.println("na koncie zostalo " + account.getBalance());
                        System.out.println("na koncie docelowym znajduje sie " + toAccount.getBalance());

                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                        break;

                case "4":
                    exitRequested = true;
                    break;
                default:
                    System.out.println("Nieprawidllowy wybor");
                    break;
            }
        }

    }
}
