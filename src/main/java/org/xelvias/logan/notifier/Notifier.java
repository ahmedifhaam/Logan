package org.xelvias.logan.notifier;

import org.xelvias.logan.configuration.configurationmodels.subelements.User;

import java.io.Serializable;

public interface Notifier<T extends Serializable> {
    public void notify(T data, User user);
}
