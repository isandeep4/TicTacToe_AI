package game;

import user.User;

public class SendEmailCommandBuilder {
    NotificationBuilder notificationBuilder;
    String link;
    String templateId;
    String template;
    public SendEmailCommand build() {
        return new SendEmailCommand(notificationBuilder.build(), link);
    }
    public SendEmailCommandBuilder user(User user){
        notificationBuilder.user(user);
        return this;
    }

    public SendEmailCommandBuilder message(String message) {
        notificationBuilder.message(message);
        return this;
    }
}
