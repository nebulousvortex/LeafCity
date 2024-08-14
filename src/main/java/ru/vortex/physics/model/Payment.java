package ru.vortex.physics.model;

public class Payment {

    private String id;
    private String status;
    private Amount amount;
    private String description;
    private Recipient recipient;
    private String created_at;
    private boolean test;
    private boolean paid;
    private boolean refundable;
    private boolean capture;
    private PaymentMethodData payment_method_data;
    private  Confirmation confirmation;

    public Payment(String id, String status, Amount amount, String description, Recipient recipient, String created_at, boolean test, boolean paid, boolean refundable, boolean capture, PaymentMethodData payment_method_data, Confirmation confirmation) {
        this.id = id;
        this.status = status;
        this.amount = amount;
        this.description = description;
        this.recipient = recipient;
        this.created_at = created_at;
        this.test = test;
        this.paid = paid;
        this.refundable = refundable;
        this.capture = capture;
        this.payment_method_data = payment_method_data;
        this.confirmation = confirmation;
    }

    public Payment() {
    }
    public Confirmation getConfirmation() {
        return confirmation;
    }

    public void setConfirmation(Confirmation confirmation) {
        this.confirmation = confirmation;
    }

    public boolean isCapture() {
        return capture;
    }

    public void setCapture(boolean capture) {
        this.capture = capture;
    }

    public PaymentMethodData getPayment_method_data() {
        return payment_method_data;
    }

    public void setPayment_method_data(PaymentMethodData payment_method_data) {
        this.payment_method_data = payment_method_data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }
}
