package dev.sky.elements.impls.notification;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author LangYa466
 * @date 2024/4/22 18:43
 */

@Getter
@Setter
public class NotificationManager {
    CopyOnWriteArrayList<Notification> notifications;

    public NotificationManager() {
        notifications = new CopyOnWriteArrayList<>();
    }

    public void addNotification(String message, NotifyType type) {
        notifications.add(new Notification(message, type));
    }

}
