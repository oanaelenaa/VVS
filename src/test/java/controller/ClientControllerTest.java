package controller;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

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

}



/*import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class ClientControllerTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ClientController.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @org.junit.Test
    public void addClient() {
    }

    @org.junit.Test
    public void addInvalidClient(){

    }


    @org.junit.Test
    public void addClientIndex() {
    }

    @org.junit.Test
    public void listIssue() {
    }
}
*/