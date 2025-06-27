package ma.skypay;

import ma.skypay.exceptions.InsufficientFundsException;
import ma.skypay.exceptions.InvalidAmountException;
import ma.skypay.service.AccountServiceImplementor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountServiceTest {
    private AccountServiceImplementor accountService;


    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImplementor();
    }

    @Test
    void testDepositAndWithdraw() {
        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(500);

        assertEquals(2500, accountService.getBalance());
    }

    @Test
    void testWithdrawWithInsufficientFunds() {
        accountService.deposit(1000); // Deposit of 1000
        try {
            accountService.withdraw(1500); // withdrawal of 1500
        } catch (InsufficientFundsException e) {
            assertEquals("Insufficient funds for withdrawal", e.getMessage());
        }
    }

    @Test
    void deposit_withNegativeAmount_throwsInvalidAmountException() {

        // Test with negative amount
        assertThrows(InvalidAmountException.class, () ->
                accountService.deposit(-500));
    }

    @Test
    void withdraw_withNegativeAmount_throwsInvalidAmountException() {

        // Test with negative amount
        assertThrows(InvalidAmountException.class, () ->
                accountService.withdraw(-500));
    }

    @Test
    void testPrintStatement() {
        accountService.deposit(1000);
        accountService.deposit(2000);
        accountService.withdraw(500);

        // Test if the printStatement method print the result as expected
        accountService.printStatement(); // Got the print on the console
    }
}
