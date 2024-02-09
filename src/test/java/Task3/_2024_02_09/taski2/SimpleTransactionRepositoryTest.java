package Task3._2024_02_09.taski2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SimpleTransactionRepositoryTest {
    @InjectMocks
    SimpleTransactionRepository repository;
    @Mock
    List<Transaction> transactions;

    @Test
    void processTransactionPositiveTest() {
        Assertions.assertTrue(repository.processTransaction(0.1));
    }
    @Test
    void processTransactionNegativeTest() {
        Assertions.assertFalse(repository.processTransaction(-0.1));
    }

    @Test
    void getAllTransactionsTest() {
        Assertions.assertEquals(transactions.size(),repository.getAllTransactions().size());
    }
}