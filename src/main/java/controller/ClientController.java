package controller;

import model.*;
import repository.DataManager;

import java.util.Calendar;

public class ClientController {
	private DataManager _dataManager;
    
    public ClientController(){
        _dataManager = new DataManager();
    }

    private String ValidateClient(String name, String address, String id){
        if(IsEmpty(name) || IsEmpty(address) || IsEmpty(id)){
            return "Name or address cannot be empty!";
        }else{
            for(int i=0;i<name.length();i++){
                if((!Character.isUpperCase(name.charAt(i))) && (!Character.isLowerCase(name.charAt(i))) && (!Character.isSpaceChar(name.charAt(i)))){
                    return "Invalid character: " + name.charAt(i);
                }
            }
            return null;
        }
    }
    private static boolean IsEmpty( final String s ) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }


    /*@Test
    public void testValidateClient() 
    {
        assertEquals("Client already exists!", ClientController.AddClient("Lorincz Arnold","Mera 278", 1));
        assertEquals(null, ClientController.AddClient("Oana","Mera 27", 123));
        assertEquals("Name or address cannot be empty!", ClientController.AddClient("","Mera 27", 123));
        assertEquals("Invalid character:@" , ClientController.AddClient("@sa","Mera 27", 123));
    }*/
    
    public String AddClient(String name, String address, String id){
        //validation
        String valid;
        if((valid = ValidateClient(name, address,id)) != null){
            return valid;
        }
        Client c = new Client(name, address,id);
        //uniqueness
        for(int j=0; j<_dataManager.Clients.size(); j++){
            if(_dataManager.Clients.get(j).equals(c)){
                return "Client already exists!";
            }
        }
        try{
            _dataManager.Clients.add(c);
            _dataManager.SaveChanges();
            return null;
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
    
    public String AddClientIndex(Client c, int year, int month, float toPay){
        if(year > 1900 && year<= Calendar.getInstance().get(Calendar.YEAR)){
            if(month >0 && month<=12){
                if(toPay >= 0){
                    //validate client attributes
                    String valid;
                    if((valid = ValidateClient(c.Name, c.Address, c.idClient)) == null){

                        //check if client exist
                        Boolean exist = false;
                        for(int i=0; i<_dataManager.Clients.size(); i++){
                            if(_dataManager.Clients.get(i).equals(c)){
                                exist = true;
                                break;
                            }
                        }
                        if(exist){
                            Issue i = new Issue(c, year, month, toPay, 0);
                            //uniqueness
                            for(int j=0; j<_dataManager.Issues.size(); j++){
                                if(_dataManager.Issues.get(j).equals(i)){
                                    return "Monthly index already exists!";
                                }
                            }
                        
                            _dataManager.Issues.add(i);
                            _dataManager.SaveChanges();
                            return null;
                        }else{
                            return "Client does not exist!";
                        }
                    }else{
                        return valid;
                    }
                }else{
                    return "Money to pay can't be less than 0!";
                }
            }else{
                return "Month can't be 0 or less!";
            }
        }else{
            return "Year is not valid";
        }
    }
    
    public String ListIssue(Client c){
        String s = "";
        String pen = "";
        Double total = 0.0;
        Issue last = null, beforeLast;       
        for(int i=0; i<_dataManager.Issues.size(); i++){
        	if(_dataManager.Issues.get(i).Client.equals(c)){
            	 pen += String.format("Year: %d, Month: %d, Penalty: %2.0f\n");
            	 s += pen;
        	}            
        }  
        return s;
    }
    
}
