public interface PaymentProcessor {

    public abstract void processPayment(Payable payable);
    // I don't know why this is needed either
}