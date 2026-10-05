package service;
import exception.InvalidAmountException;
import exception.InsufficientBalanceException;
import model.BankAccount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class BankManagement {
    private Map<String, BankAccount> accounts;

    public BankManagement() {
        accounts = new HashMap<>();
    }
    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(),account);
    }
    public BankAccount findAccount(String accountNumber){
        return accounts.get(accountNumber);
    }
    public void displayAllAccounts() {
        for (BankAccount account : accounts.values()) {
            System.out.println ("STK: " + account.getAccountNumber());
            System.out.println("Chu tai khoan: " + account.getHolderName());
            System.out.println(("So du:" + account.getBalance() ));
            System.out.println("------------------------");
        }
    }
    public void transferMoney(String fromACc, String toACc, double amount) throws InvalidAmountException, InsufficientBalanceException{
        BankAccount sender = findAccount(fromACc);
        BankAccount receiver = findAccount(toACc);

        if (sender == null) {
            throw new IllegalArgumentException("Khong tim thay tai khoan gui.");
        }
        if (receiver== null) {
            throw new IllegalArgumentException("Khong tim thay tai khoan nhan");
        }
        sender.withdraw(amount);
        receiver.withdraw(amount);
    }
    public double calculateTotalBalance(List<? extends BankAccount> accounts) {
        double total = 0.0;

        for (BankAccount acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }
    public List<BankAccount> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
}
