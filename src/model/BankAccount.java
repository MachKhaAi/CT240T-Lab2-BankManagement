package model;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

public abstract class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;

        if(balance < 0) {
            throw new IllegalArgumentException(
                    "So du ban dau khong duoc am"
            );
        }
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance < 0) {
            throw new IllegalArgumentException(
                    "So du ban dau khong duoc am"
            );
        }
        this.balance = balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
            if (amount <= 0) {
                throw new InvalidAmountException (
                        "So tien nap phai lon hon 0" );
            }
            balance += amount;
        }

        public abstract void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException;
}
