import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import model.BankAccount;
import model.SavingAccount;
import service.BankManagement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankManagement manager = new BankManagement();

        manager.addAccount( new SavingAccount("TK001", "Nguyen Van A", 1000000, 0.05 ));

        manager.addAccount( new SavingAccount("TK002", "Tran Thi B", 2000000, 0.06));

        int choice;
        do {
            System.out.println();
            System.out.println("=====BANK MANAGEMENT=====");
            System.out.println("1.Xem danh sach tai khoan");
            System.out.println("2. Nap tien");
            System.out.println("3. Rut tien");
            System.out.println("4. Chuyen tien");
            System.out.println("5. Tinh tong so du");
            System.out.println("0. Thoat");

            System.out.print(" Nhap lua chon: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        manager.displayAllAccounts();
                        break;
                    case 2:
                        deposit(scanner,manager);
                        break;
                    case 3:
                        withdraw(scanner,manager);
                        break;
                    case 4:
                        transfer(scanner,manager);
                        break;
                    case 5:
                        double total = manager.calculateTotalBalance(manager.getAllAccounts());
                        System.out.println("Tong so du: " + total + "VND");
                        break;
                    case 0:
                        System.out.println("Da thoat chuong trinh.");
                        break;
                    default:
                        System.out.println("Lua chon khong hop le.");
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Vui long nhap mot so."
                );
                choice = -1;
            }
        } while (choice != 0);
        scanner.close();
    }

    private static void deposit(
            Scanner scanner,BankManagement manager) {
        try {
            System.out.print("Nhap so tai khoan: ");
            String accountNumber = scanner.nextLine();

            BankAccount account = manager.findAccount(accountNumber);

            if (account == null) {
                System.out.println("Khong tim thay tai khoan");
                return;
            }
            System.out.println("Nhap so tien nap: ");
            double amount = Double.parseDouble(scanner.nextLine());
            account.deposit(amount);
            System.out.println("Nap tien thanh cong.");
            System.out.println("So du moi: " + account.getBalance());
        } catch (InvalidAmountException e) {
            System.out.println(
                    "Loi: " + e.getMessage()
            );
        } catch (NumberFormatException e) {
            System.out.println("So tien khong hop le.");
        }
    }
    private static void withdraw(Scanner scanner, BankManagement management) {
        try {
            System.out.print("Nhap so tai khoan: ");
            String accountNumber = scanner.nextLine();

            BankAccount account = management.findAccount(accountNumber);

            if (account == null) {
                System.out.println("Khong tim thay tai khoan.");
                return;
            }

            System.out.print("Nhap so tien rut: ");
            double amount = Double.parseDouble(scanner.nextLine());
            account.withdraw(amount);

            System.out.println(" Rut tien thanh cong. ");
            System.out.println("So du moi: " + account.getBalance());
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Loi: " +e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("So tien khong hop le.");
        }

    }
    private static void transfer(
            Scanner scanner, BankManagement manager ) {
        try {
            System.out.println(" Nhap tai khoan gui: ");
            String from = scanner.nextLine();
            System.out.print("Nhap tai khoan nhan: ");
            String to = scanner.nextLine();
            System.out.println("Nhap so tien:");
            double amount = Double.parseDouble(scanner.nextLine());
            manager.transferMoney(from, to, amount);
            System.out.println("Chuyen tien thanh cong");
        } catch (InvalidAmountException | InsufficientBalanceException | IllegalArgumentException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }

}
