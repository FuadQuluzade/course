package az.orient.cource.service;

import az.orient.cource.dao.PaymentDao;
import az.orient.cource.model.AdvancedSearch;
import az.orient.cource.model.Payment;

import java.util.List;

public class PaymentServiceImpl implements PaymentService{
    private PaymentDao paymentDao;
    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao=paymentDao;
    }

    @Override
    public List<Payment> getPaymentList() throws Exception {
        return paymentDao.getPaymentList();
    }

    @Override
    public boolean addPayment(Payment payment) throws Exception {
        return paymentDao.addPayment(payment);
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        return paymentDao.getPaymentById(paymentId);
    }

    @Override
    public boolean updatePayment(Payment payment, Long paymentId) throws Exception {
        return paymentDao.updatePayment(payment,paymentId);
    }

    @Override
    public boolean deletePayment(Long paymentId) throws Exception {
        return paymentDao.deletePayment(paymentId);
    }

    @Override
    public List<Payment> advancedSearchPayment(AdvancedSearch advancedSearch) throws Exception{
        return paymentDao.advancedSearchPayment(advancedSearch);
    }
}
