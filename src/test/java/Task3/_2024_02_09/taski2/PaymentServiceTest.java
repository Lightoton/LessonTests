package Task3._2024_02_09.taski2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {
    @InjectMocks
    PaymentService paymentService;
    @Mock
    SimpleNotificationService notificationService;
    @Mock
    SimpleTransactionRepository transactionRepository;


    @Test
    void makePaymentPositiveTest() {

        Mockito.when(transactionRepository.processTransaction(Mockito.anyDouble())).thenReturn(true);
        Assertions.assertTrue(paymentService.makePayment(Mockito.anyDouble()));
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
        Mockito.verify(transactionRepository).processTransaction(Mockito.anyDouble());
    }
    @Test
    void makePaymentNegativeTest() {
        Mockito.when(transactionRepository.processTransaction(Mockito.anyDouble())).thenReturn(false);
        Assertions.assertFalse(paymentService.makePayment(Mockito.anyDouble()));
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
        Mockito.verify(transactionRepository).processTransaction(Mockito.anyDouble());
    }

    @Test
    void refundPaymentPositiveTest() {
        Mockito.when(transactionRepository.processTransaction(-Mockito.anyDouble())).thenReturn(true);
        paymentService.refundPayment(-Mockito.anyDouble());
        Mockito.verify(transactionRepository).processTransaction(-Mockito.anyDouble());
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void refundPaymentNegativeTest() {
        Mockito.when(transactionRepository.processTransaction(-Mockito.anyDouble())).thenReturn(false);
        paymentService.refundPayment(-Mockito.anyDouble());
        Mockito.verify(transactionRepository).processTransaction(-Mockito.anyDouble());
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }

    @Test
    void adjustPaymentTestScenario1() {
        Mockito.when(transactionRepository.processTransaction(Mockito.anyDouble())).thenReturn(true).thenReturn(true);
        paymentService.adjustPayment(0.1,0.3);
        Mockito.verify(notificationService,Mockito.times(2)).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void adjustPaymentTestScenario2() {
        Mockito.when(transactionRepository.processTransaction(Mockito.anyDouble())).thenReturn(true).thenReturn(false);
        paymentService.adjustPayment(0.1,0.3);
        Mockito.verify(notificationService,Mockito.times(2)).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void adjustPaymentTestScenario3() {
        Mockito.when(transactionRepository.processTransaction(-Mockito.anyDouble())).thenReturn(false);
        paymentService.adjustPayment(0.1,0.3);
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void verifyPaymentTest() {
        Assertions.assertTrue(paymentService.verifyPayment(Mockito.anyDouble()));
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }

    @Test
    void cancelPaymentSuccessfulTest() {
        Mockito.when(transactionRepository.processTransaction(Mockito.anyDouble())).thenReturn(true);
        paymentService.cancelPayment(Mockito.anyDouble());
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void cancelPaymentFailedTest() {
        Mockito.when(transactionRepository.processTransaction(Mockito.anyDouble())).thenReturn(false);
        paymentService.cancelPayment(Mockito.anyDouble());
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }

    @Test
    void freezeTransactionTest() {
        paymentService.freezeTransaction(Mockito.anyDouble());
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }

    @Test
    void unfreezeTransactionTest() {
        paymentService.unfreezeTransaction(Mockito.anyDouble());
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }

    @Test
    void schedulePaymentTest() {
        Assertions.assertTrue(paymentService.schedulePayment(Mockito.anyDouble(),"Test"));
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }

    @Test
    void confirmPaymentTest() {
        paymentService.confirmPayment(Mockito.anyDouble());
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }

    @Test
    void declinePayment() {
        paymentService.declinePayment(Mockito.anyDouble());
        Mockito.verify(notificationService).sendPaymentNotification(Mockito.anyString());
    }
}