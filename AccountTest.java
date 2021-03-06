
import java.util.Scanner;

public class AccountTest {
    public static void main(String[] args) throws Exception{
        Account jane = new Account("Jane","Appleseed", 00000001l, "jane.appleseed@apple.com");
        jane.setBalance(Account.Type.DEPOSIT,200);
        Account.Type t = Account.Type.UNKNOWN;
        String decision;
        String select;
        double amount;
        String type;
        Scanner in;


        while (true) {
            System.out.println("Name: " + jane.getName() + "\n" + "Surname: " + jane.getSurname() + "\n" + "Account Number: " + jane.getAccountNumber() + "\n" + "E-mail address: " + jane.getEmailAddress());

            in = new Scanner(System.in);
            System.out.println("Withdraw, deposit or balance ? (type withdraw, deposit or balance) ");
            type = in.next();

            if (type.toLowerCase().compareTo(("balance")) == 0) {
                System.out.println("Your balance: " + jane.getBalance() + "$");
            } else {
                if (type.toLowerCase().compareTo("withdraw") == 0) t = Account.Type.WITHDRAWAL;
                if (type.toLowerCase().compareTo("deposit") == 0) t = Account.Type.DEPOSIT;
                System.out.println("Set the amount: ");
                amount = in.nextDouble();
                jane.setBalance(t, amount);
                System.out.println("Selected Amount: " + amount + "$");
                System.out.println("Your balance: " + jane.getBalance() + "$");

            }

            System.out.println("Do you wish to continue Yes/No ?");
            select = in.next();
            decision = select.toLowerCase();

            if (decision.equals("yes"))
            {
                continue;
            }
            else if(decision.equals("no"))
            {
                break;
            }
        }
    }
}
