package entity;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    public int id;
    public String name;
    public List<Client> clients = new ArrayList<>();

    public Bank() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", name='" + name  +
                 '\''+
                '}';

    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> client) {
        this.clients = client;
    }
}
