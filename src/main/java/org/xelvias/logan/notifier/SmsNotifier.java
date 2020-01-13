package org.xelvias.logan.notifier;

import org.xelvias.logan.configuration.configurationmodels.subelements.User;

import java.io.Serializable;

public class SmsNotifier<T extends Serializable> implements Notifier<T> {


    @Override
    public void notify(T data, User user) {
        System.out.printf("Sending SMS to %s , with phone number %s , ",user.getName(),user.getTelephone(),data.toString());
    }
}
