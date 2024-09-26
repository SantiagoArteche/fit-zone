package zona_fit.data;

import zona_fit.Client;
import static zona_fit.connection.Connect.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements IClientDAO {
    public List<Client> clientList(){
        List<Client> clients = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection connection = getConnection();
        String query = "SELECT * FROM clients ORDER BY id";

        try{
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setLastName(rs.getString("lastName"));
                client.setMembership(rs.getInt("membership"));
                clients.add(client);
            }
        }catch (Exception e){
            System.out.println("Error retrieving clients: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
            }catch (Exception e){
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }

        return clients;
    }
    public boolean searchClientById(Client client){

        PreparedStatement ps;
        ResultSet rs;
        Connection connection = getConnection();
        String query = "SELECT * FROM clients WHERE id = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setInt(1, client.getId());
            rs = ps.executeQuery();
            if(rs.next()){
             client.setName(rs.getString("name"));
             client.setLastName(rs.getString("lastName"));
             client.setMembership(rs.getInt("membership"));
             return true;
            }
        }catch (Exception e){
            System.out.println("Error retrieving client: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
            }catch (Exception e){
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }
    public boolean addClient(Client client) {
        PreparedStatement ps;
        Connection connection = getConnection();
        String query = "INSERT INTO clients(name, lastName, membership) VALUES(?, ?, ?)";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1 , client.getName());
            ps.setString(2 , client.getLastName());
            ps.setInt(3 , client.getMembership());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error creating new client: " + e.getMessage());
        }
        finally {
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }
    public boolean updateClient(Client client){
        PreparedStatement ps;
        Connection connection = getConnection();
        String query = "UPDATE clients SET name = ?, lastName = ?, membership = ? WHERE id = ?";
        try{
            ps = connection.prepareStatement(query);
            ps.setString(1, client.getName());
            ps.setString(2 , client.getLastName());
            ps.setInt(3 , client.getMembership());
            ps.setInt(4 , client.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error updating client: " + e.getMessage());
        }
        finally {
            try{
                connection.close();
            }catch (Exception e){
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }

    public boolean removeClient(Client client){
        PreparedStatement ps;
        Connection connection = getConnection();
        String query = "DELETE FROM clients WHERE id = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, client.getId());
            ps.execute();
            return true;
        }catch (Exception e){
            System.out.println("Error removing client: " + e.getMessage());
        }
        finally {
            try {
                connection.close();
            }catch (Exception e){
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
        return false;
    }
}
