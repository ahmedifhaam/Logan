package org.xelvias.logan.configuration.configurationmodels.subelements;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    public String name;
    public String email;
    public String telephone;
    public List<String> notificationTypes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<String> getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(List<String> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getTelephone(), user.getTelephone()) &&
                Objects.equals(getNotificationTypes(), user.getNotificationTypes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail(), getTelephone(), getNotificationTypes());
    }
}
