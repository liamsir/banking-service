package ma.skypay.dto;

import java.util.Date;

public class TransactionDTO {

    private final int amount;
    private final int balance;
    private final Date transactionDate;

    public TransactionDTO(int amount, int balance, Date date) {
        this.amount = amount;
        this.balance = balance;
        this.transactionDate = date;
    }

    public int getAmount() {
        return amount;
    }

    public int getBalance() {
        return balance;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }
}
