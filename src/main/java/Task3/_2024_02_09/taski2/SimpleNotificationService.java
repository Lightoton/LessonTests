package Task3._2024_02_09.taski2;

public class SimpleNotificationService implements NotificationService {

    @Override
    public void sendPaymentNotification(String message) {
        System.out.println("Notification sent: " + message);
    }
}