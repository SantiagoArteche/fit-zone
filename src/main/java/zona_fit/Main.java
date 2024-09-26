package zona_fit;

import zona_fit.connection.Connect;
import zona_fit.data.ClientDAO;
import zona_fit.data.IClientDAO;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        var clientDAO = new ClientDAO();
        var newClient = new Client(3);
        var allClients = clientDAO.clientList();
        allClients.forEach(System.out::println);
        var search = clientDAO.searchClientById(newClient);
        if(search){
            System.out.println(newClient);
        }else{
            System.out.println("Cliente wit id %d not found".formatted(newClient.getId()))  ;
        }
        var createClient = clientDAO.addClient(new Client("Simon", "Arteche", 6));
        if (createClient){
            System.out.println("Client added: " + new Client("Simon", "Arteche", 1));
        }else{
            System.out.println("Error creating client");
        }
       var updateClient = clientDAO.updateClient(new Client(1, "Santiago updatxd", "Arteche", 33));
        if(updateClient){
            System.out.println("Client modified: " + new Client(1, "Santiago updatxd", "Arteche", 33));
        }else{
            System.out.println("Client not modified");
        }
        var removeClient = clientDAO.removeClient(new Client(6));
        if (removeClient){
            System.out.println("Client with id: " + 6 + " was removed");
        }else{
            System.out.println("Client not removed");
        }

    }
}