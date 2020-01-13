package org.xelvias.logan.configuration.configurationmodels.subelements;

import java.util.List;
import java.util.Objects;

public class ClientAppConfiguration {
    public String log_file;
    public String application_name;
    public List<User> users;

    public String getLog_file() {
        return log_file;
    }

    public void setLog_file(String log_file) {
        this.log_file = log_file;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientAppConfiguration that = (ClientAppConfiguration) o;
        return Objects.equals(getLog_file(), that.getLog_file()) &&
                Objects.equals(getApplication_name(), that.getApplication_name()) &&
                Objects.equals(getUsers(), that.getUsers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLog_file(), getApplication_name(), getUsers());
    }
}
