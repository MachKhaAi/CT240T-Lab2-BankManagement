package model;
import exception.InvalidAmountException;
import exception.InsufficientBalanceException;
public class SavingAccount extends BankAccount {
    private double interestRate;
    private static final double MIN_BALANCE = 50000;
    public SavingAccount(String accountNumber, String holderName,double balance, double interestRate) {
        super(accountNumber, holderName, balance);
        if (balance < MIN_BALANCE) {
            throw new IllegalArgumentException("Tai khoan tiet kiem phai co it nhat 50.000VND.");
        }
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException, InvalidAmountException{
            if (amount <= 0) {
                throw new InvalidAmountException("So tien ru t phai lon hon 0. ");
            }
            if (getBalance() < amount) {
                throw new InsufficientBalanceException("So du tai khoan khong du.");
            }
            if (getBalance() - amount < MIN_BALANCE) {
                throw new InsufficientBalanceException("Tai khoan tiet kiem phai duy tri toi thieu 50.000VND.");
            }
            setBalance(getBalance()-amount);
        }

}
