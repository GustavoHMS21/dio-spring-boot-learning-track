package dio.budgeting.domain;

import lombok.Getter;

@Getter
public class Transaction {
    private TransactionId id;
    private String description;
    private long amount;
    private Category category;

    public Transaction(TransactionId id, String description, long amount, Category category) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public Transaction(String description, long amount, Category category) {
        validate(description, amount, category);
        this.id = new TransactionId();
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    // Regras de negocio: uma transacao so e valida com dados consistentes
    private void validate(String description, long amount, Category category) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("A descricao da transacao e obrigatoria.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("O valor da transacao deve ser maior que zero.");
        }
        if (category == null) {
            throw new IllegalArgumentException("A categoria da transacao e obrigatoria.");
        }
    }
}
