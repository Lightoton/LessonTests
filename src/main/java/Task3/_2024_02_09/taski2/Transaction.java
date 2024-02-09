package Task3._2024_02_09.taski2;

public class Transaction {
    private final double amount;
    private final boolean isSuccess;

    public Transaction(double amount, boolean isSuccess) {
        this.amount = amount;
        this.isSuccess = isSuccess;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
