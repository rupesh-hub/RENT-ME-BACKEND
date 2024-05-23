package com.rupesh.app.notification;

public interface INotificationService<T> {

    void send(T request);

}
