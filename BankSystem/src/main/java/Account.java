
import java.math.BigDecimal;

public class Account {
    private final String firstName;
    private final String lastName;
    private BigDecimal balance;
    private String password;
    private final String login;
    private final String iban;
 //mmmmmm

    public Account(String firstName, String lastName, String login, String password, String iban){
        // piata zmiana
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.iban = iban;
        this.balance = new BigDecimal(0);
//dfdfdfd

    }
 
    public String getFirstName(){
        return this.firstName;
    }
    public String getLastName(){
        return this.lastName;
    }
    public BigDecimal getBalance(){
        return this.balance;
    }
    public void setBalance(BigDecimal newBalance) {
        this.balance = newBalance;
    }

    public String getLogin() {
        return login;
    }
    
    public String getPassword(){
        return this.password;    
    }

    public String getIban() {
        return iban;
    }

   
    public void depositMoney(BigDecimal depositAmount) throws Exception{
        if (depositAmount.compareTo(new BigDecimal(0))<=0){
            System.out.println("Podana kwota jest nieprawidlowa");
        } else{
        this.balance = this.balance.add(depositAmount);
        System.out.println("Wplacono" +depositAmount +" na konto." + "\n" +
                "Stan konta: " +this.balance);
        
    }}

    public void withdrawal(BigDecimal withdrawalAmount){
        if(this.balance.compareTo(withdrawalAmount)<0) {
            System.out.println("Nie masz wystarczajacej kwoty na koncie.");
        } else {
            this.balance = this.balance.subtract(withdrawalAmount);
            System.out.println("Wyplacono " +withdrawalAmount + " z konta." + "\n" +
                    "Stan konta " +this.balance);
        }
    }

    public void moneyTransfer( Account toAccount, BigDecimal amountToTransfer) throws Exception{
        
         if (amountToTransfer.compareTo(new BigDecimal(0))<=0) throw new Exception("Podana kwota jest nieprawidlowa");
        
        if(this.getBalance().compareTo(amountToTransfer) > 0) {
            toAccount.setBalance(toAccount.balance= toAccount.balance.add(amountToTransfer));
            this.setBalance(this.balance = this.balance.add(amountToTransfer));
        } else {
            throw new Exception("Nie masz wystarczajacej kwoty");
        }
    }

}
