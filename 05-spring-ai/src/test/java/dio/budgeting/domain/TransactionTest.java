package dio.budgeting.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {

    @Test
    void deveCriarTransacaoValida() {
        var transaction = assertDoesNotThrow(
                () -> new Transaction("Mercado", 5000, Category.GROCERIES));

        assertEquals("Mercado", transaction.getDescription());
        assertEquals(5000, transaction.getAmount());
        assertEquals(Category.GROCERIES, transaction.getCategory());
    }

    @Test
    void naoDeveCriarComValorZeroOuNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("Mercado", 0, Category.GROCERIES));
        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("Mercado", -100, Category.GROCERIES));
    }

    @Test
    void naoDeveCriarComDescricaoVazia() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("  ", 5000, Category.GROCERIES));
    }

    @Test
    void naoDeveCriarComCategoriaNula() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transaction("Mercado", 5000, null));
    }
}
