package Task3._2024_02_09.taski2;

public interface TransactionRepository {
    /**
     * Обрабатывает транзакцию с указанной суммой.
     * @param amount сумма транзакции.
     * @return true, если транзакция успешно обработана, иначе false.
     */
    boolean processTransaction(double amount);
}
