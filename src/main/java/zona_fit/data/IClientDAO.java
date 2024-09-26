package zona_fit.data;

import zona_fit.Client;

import java.util.List;

public interface IClientDAO {
    List<Client> clientList();
    boolean searchClientById(Client client);
    boolean addClient(Client client);
    boolean updateClient(Client client);
    boolean removeClient(Client client);
}
