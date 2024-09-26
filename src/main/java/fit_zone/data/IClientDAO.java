package fit_zone.data;

import fit_zone.domain.Client;

import java.util.List;

public interface IClientDAO {
    List<Client> clientList();
    boolean findClientById(Client client);
    boolean addClient(Client client);
    boolean updateClient(Client client);
    boolean removeClient(Client client);
}
