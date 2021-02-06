package az.orient.cource.service;

import az.orient.cource.model.AdvancedSearch;
import az.orient.cource.model.Payment;

import java.util.List;

public interface PaymentService {
    public List<Payment> getPaymentList() throws Exception;
    public boolean addPayment(Payment payment) throws Exception;
    public Payment getPaymentById(Long paymentId) throws Exception;
    boolean updatePayment(Payment payment,Long paymentId) throws  Exception;
    boolean deletePayment(Long paymentId) throws  Exception;

    List<Payment> advancedSearchPayment(AdvancedSearch advancedSearch) throws Exception;
}
