/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Console;

import Message.Message;
import Connection.*;
import java.io.*;
import java.security.*;
import java.util.HashMap;

/**
 *
 * @author Marc
 */
public class SecureDetails {
    
    private HashMap details;
    private final String dir = "user2.ser";
    
    public SecureDetails(){
        
        
    
        try{
            
            FileInputStream fis = new FileInputStream(dir);
            ObjectInputStream ois = new ObjectInputStream(fis);
            details = (HashMap) ois.readObject();
            ois.close();

        }
        catch(FileNotFoundException e){
            try{
                details = new HashMap();
                details.put("Registered", false);
                
                FileOutputStream fos = new FileOutputStream(dir);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(details);
                oos.close();
      
                
            }
            catch(FileNotFoundException ex){
                
            } 
            catch (IOException ex) {
            }
    
        }
        catch(IOException e){
            
        }
        catch(ClassNotFoundException e){
            
        }


    }
    
    
    public void setRegistered(){
        
        details.put("Registered", true);

    }
    
    
    
    public void setId(String key){
        
        details.put("ID", key);
                
        
    }
    
    public void addMessage(Message m){
        
        Message[] savedMessages = getMessages();
        
        int i = savedMessages.length + 1;
        
        savedMessages[i] = m;
        
        details.put("Messages",savedMessages);
        this.saveDetails();
    }
    
    public void deleteMessage(Message m){
        
        Message[] savedMessages = getMessages();
        
        for(int i =0; i<= savedMessages.length; i++){
            
            if(m == savedMessages[i]){
                savedMessages[i] = null;
            }
            
        }
        details.put("Messages",savedMessages);
        this.saveDetails();
    }
    
    
    public Message[] getMessages(){
        
        Message[] savedMessages = (Message[])details.get("Messages");
        
        return savedMessages;
        
    }
    

    
    public String getID(){
        
        String id = (String)details.get("ID");
        
        return id;
        
    }
    

    
    public boolean getRegistered(){
        
        boolean registered = (boolean)details.get("Registered");

        return registered;
        
    }
    
    public void saveDetails(){
        
         try{

                
                FileOutputStream fos = new FileOutputStream(dir);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(details);
                oos.close();
                
            }
            catch(FileNotFoundException ex){
                
            } 
            catch (IOException ex) {
            }
        
    }
    
    
    
    
    
    
    
}
