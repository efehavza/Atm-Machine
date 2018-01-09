
public class Account {
    public enum Type {
        WITHDRAWAL,
        DEPOSIT,
        UNKNOWN

    }
    private String name;
    private String surname;
    private long accountNumber;
    private String emailAddress;
    private double balance;

    public Account(String name, String surname, long accountNumber, String emailAddress) {
        this.name = name;
        this.surname = surname;
        this.accountNumber = accountNumber;
        this.emailAddress = emailAddress;
        this.balance = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setName(String nm) {
        this.name = nm;
    }

    public void setSurname(String sn) {
        this.surname = sn;
    }

    public void setAccountNumber(Long accNum) {
        this.accountNumber = accNum;
    }

    public void setEmailAddress(String addr) {
        this.emailAddress = addr;
    }

    public void setBalance(Type t, double amount) throws Exception {
        switch(t) {
            case DEPOSIT:
                this.balance = this.balance + amount;
                break;
            case WITHDRAWAL:
                if(balance < amount) {
                    throw new Exception("Insufficient funds.");
                } else {
                    this.balance = this.balance - amount;
                }
                break;
            default:
                throw new Exception("Invalid type of Transaction.");
        }
    }

}
