package game;

import user.User;

public class SendSMSCommandBuilder {
    NotificationBuilder notificationBuilder;
    String link;
    String templateId;
    String template;

    public SendSMSCommandBuilder user(User user){
        notificationBuilder.user(user);
        return this;
    }
    public SendSMSCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }
}
