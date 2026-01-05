package game;

import event.Event;
import user.User;

public class SendSMSCommand{
    NotificationDetails details;
    String link;
    String templateId;
    String template;

    public SendSMSCommand(Event event) {
        this.details = new NotificationDetails(event.getUser(), event.getMessage());
        this.link = event.getLink();
    }
    public String getMessage(){
        return details.getMessage();
    }
    public User getReceiver(){
        return details.getReceiver();
    }
}
