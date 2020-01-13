package org.xelvias.logan.notifier;

import org.xelvias.logan.configuration.configurationmodels.subelements.User;

import java.io.Serializable;

public class EmailNotifier<T extends Serializable> implements Notifier<T> {



    @Override
    public void notify(T data, User user) {
        System.out.printf("Sending Email to %s , with email %s , ",user.getName(),user.getEmail(),data.toString());
    }
}
