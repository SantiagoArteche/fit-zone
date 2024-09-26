package fit_zone.presentation;

import fit_zone.domain.Client;
import fit_zone.data.ClientDAO;
import fit_zone.data.IClientDAO;

import java.util.List;
import java.util.Scanner;

public class FitZoneApp {
    private static Scanner console = new Scanner(System.in);

    public static void start(){
        boolean exit = false;
        IClientDAO clientDAO = new ClientDAO();
        while (!exit){
            try{
                int option = showMenu();
                exit = executeOptions(clientDAO, option);
            }catch (Exception e){
                System.out.println("Error in the execution: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int showMenu(){
        System.out.println("""
                *** Fit Zone (GYM) ***
                1. Clients List
                2. Find Client
                3. Add Client
                4. Update Client
                5. Remove Client
                6. Exit
                Choose an option:\s""");
        int option = Integer.parseInt(console.nextLine());
        return option;
    }

    private static boolean executeOptions(IClientDAO clientDAO, int option){
        boolean exit = false;
        switch (option){
            case 1 -> {
                System.out.println("--- Clients List ---");
                List<Client> clientList = clientDAO.clientList();
                clientList.forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("Insert the client ID: ");
                int idClient = Integer.parseInt(console.nextLine());
                Client client = new Client(idClient);
                boolean found = clientDAO.findClientById(client);
                if(found){
                    System.out.println("Client found: " + client);
                }else{
                    System.out.println("Client with id %d not found".formatted(client.getId()));
                }
            }
            case 3 -> {
                System.out.print("Insert the client Name: ");
                String name = console.nextLine();
                System.out.print("Insert the client Last Name: ");
                String lastName = console.nextLine();
                System.out.print("Inser the client Membership: ");
                int membership = Integer.parseInt(console.nextLine());
                Client client = new Client(name, lastName, membership);
                boolean addClient = clientDAO.addClient(client);
                if(addClient){
                    System.out.print("Client added: " + client);
                }else{
                    System.out.print("Error creating client");
                }
            }
            case 4 -> {
                System.out.print("Insert the client ID: ");
                int clientId = Integer.parseInt(console.nextLine());
                System.out.print("Insert the new Name: ");
                String name = console.nextLine();
                System.out.print("Insert the new Last Name: ");
                String lastName = console.nextLine();
                System.out.print("Inser the new Membership: ");
                int membership = Integer.parseInt(console.nextLine());
                Client client = new Client(clientId, name, lastName, membership);
                boolean updatedClient = clientDAO.updateClient(client);
                if(updatedClient){
                    System.out.print("Client updated: " + client);
                }else{
                    System.out.print("Error updating client");
                }
            }
            case 5 -> {
                System.out.print("Insert the client ID: ");
                int clientId = Integer.parseInt(console.nextLine());
                Client client = new Client(clientId);
                boolean deleted = clientDAO.removeClient(client);
                if(deleted){
                    System.out.print("Client with id %d removed".formatted(client.getId()));
                }else{
                    System.out.print("Error removing client");
                }
            }
            case 6 -> {
                System.out.println("Goodbye!");
                exit = true;
            }
            default -> System.out.println("Option not recognized");
        }
        return exit;
    }
}
