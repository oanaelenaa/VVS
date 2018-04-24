package controller;
import model.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import repository.DataManager;

import static junit.framework.TestCase.assertEquals;

@RunWith(AllTests.class)
public class ClientControllerTest{

    @Test
    public void testAddInvalidClient(){
        ClientController c=new ClientController();
        assertEquals("Client already exists!", c.AddClient("Lorincz Arnold","Mera 278", "1"));
        assertEquals("Name or address cannot be empty!", c.AddClient("","Mera 27", "123"));
        assertEquals("Invalid character:@" , c.AddClient("@sa","Mera 27", "123"));

    }

    @Test
    public void testAddValidClient(){
        ClientController c=new ClientController();
        assertEquals(null,c.AddClient("Oana","Mera 27", "123"));

    }
    @Test
    public void testAddInvalidClientIndex(){
        ClientController c=new ClientController();
        c.AddClient("OanaT","Mera 24", "12345");
        Client client=new Client("OanaT","Mera 24", "12345");
        assertEquals("Money to pay can't be less than 0!",c.AddClientIndex(client,2018,5,-3));
        assertEquals("Month can't be 0 or less!",c.AddClientIndex(client,2018,-5,3));
        assertEquals("Year is not valid",c.AddClientIndex(client,218,5,3));
        Client client2=new Client("Elena","Mera 24", "12345");
        assertEquals("Client does not exist!",c.AddClientIndex(client2,2018,5,3));
        c.AddClientIndex(client,2018,5,10);
        assertEquals("Monthly index already exists!",c.AddClientIndex(client,2018,5,3));
    }

    @Test
    public void testAddValidClientIndex() {
        ClientController c=new ClientController();
        c.AddClient("OanaT","Mera 24", "12345");
        Client client=new Client("OanaT","Mera 24", "12345");
        assertEquals(null,c.AddClientIndex(client,2018,5,3));
    }


    @Test
    public void testIntegrationAddClient(){
        //top down approach
        ClientController ctrl = new ClientController();
        String result=ctrl.AddClient("OanaT","Mera 24", "12345");
        assertEquals(null,result);
        Client c=new Client("OanaT","Mera 24", "12345");
        String resultAddInvoice=ctrl.AddClientIndex(c,2018, 3,12);
        assertEquals(null,resultAddInvoice);
        String r=ctrl.ListIssue(c);
        assertEquals("Year:2018, Month:3, Penalty:0",r);
        String fileClient = "client.txt";
        String fileIssue = "issue.txt";
        DataManager dataManager=new DataManager();
        assertEquals (0,dataManager.getClientsList());


    }

}