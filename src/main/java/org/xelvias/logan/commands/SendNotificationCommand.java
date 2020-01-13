package org.xelvias.logan.commands;

import org.xelvias.logan.configuration.configurationmodels.subelements.User;
import org.xelvias.logan.logs.events.LogEvent;
import org.xelvias.logan.notifier.Notifier;

import java.io.Serializable;
import java.util.Iterator;

public class SendNotificationCommand<T extends Serializable> implements Command {
    T dataElement;
    User user;
    Notifier notifier;


    public SendNotificationCommand(T dataElement, User user, Notifier notifier){
        this.dataElement = dataElement;
        this.user = user;
        this.notifier = notifier;
    }

    @Override
    public void execute() {
        notifier.notify(dataElement,user);
    }
}
