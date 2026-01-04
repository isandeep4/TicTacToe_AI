package game;

import user.User;

public class NotificationBuilder {
    public User user;
    public String message;

    public NotificationBuilder user(User user) {
        this.user = user;
        return this;
    }
    public NotificationBuilder message(String message){
        this.message = message;
        return this;
    }

    public NotificationDetails build(){
        return new NotificationDetails(user, message);
    };
}
