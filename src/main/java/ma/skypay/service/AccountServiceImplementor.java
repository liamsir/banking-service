package ma.skypay.service;

import ma.skypay.dto.TransactionDTO;
import ma.skypay.exceptions.InsufficientFundsException;
import ma.skypay.exceptions.InvalidAmountException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountServiceImplementor implements AccountService{

    // In a real system, the balance would be stored in a database to persist the account sold.
    // Storing it in memory here is only for the simplicity of this technical test.
    private int balance = 0;
    private final List<TransactionDTO> transactions = new ArrayList<>();

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }
        // In a real system, this transaction would be saved to a database via a DAO.
        // For now, we're adding it to an in-memory list for simplicity.
        balance += amount;
        transactions.add(0, new TransactionDTO(amount, balance, new Date()));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }
        if (balance >= amount)
        {
            // Same as deposit: in a real system, this would save the transaction to the database using a DAO.
            // For now, we're adding it to the in-memory list for simplicity.
            balance -= amount;
            transactions.add(0, new TransactionDTO(amount, balance, new Date()));
        }
        else
        {
            throw new InsufficientFundsException();
        }
    }

    @Override
    public void printStatement() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Date         ||  Amount  ||  Balance");
        for (TransactionDTO transaction : transactions)
        {
            String date = sdf.format(transaction.getTransactionDate());
            System.out.printf("%s   ||   %d   ||   %d\n", date, transaction.getAmount(), transaction.getBalance());
        }
    }

    public int getBalance() {
        return balance;
    }

}
