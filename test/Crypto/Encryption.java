package Crypto;

import Connection.ClientReceive;
import Message.Message;
import java.security.*;
import javax.crypto.*;
import java.io.*;
import java.security.spec.X509EncodedKeySpec;

public class Encryption{

	public Encryption(){
	}

	public byte[] encryptString(Key publicKey, String data){
        try{
        	Cipher encrypt = Cipher.getInstance("RSA");
        	encrypt.init(Cipher.ENCRYPT_MODE, publicKey);
        	byte[] encryptedData = encrypt.doFinal(data.getBytes());
        	return encryptedData; 
        }catch(Exception ex){
            throw new RuntimeException(ex);
	    }
	}

	public byte[] decryptString(byte[] data, char[] localPassword){
		KeyVault kv = new KeyVault();
		KeyPair rsaKeys = kv.getRSAKeys(localPassword);
		PrivateKey privateKey = rsaKeys.getPrivate();
		try{
			Cipher decrypt = Cipher.getInstance("RSA");
			decrypt.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptedData = decrypt.doFinal(data);
			return decryptedData;
		}catch(Exception ex){
            throw new RuntimeException(ex);
	    }
	}

	public String bTS(byte[] input){
		String s = new String(input);
		return s;
	}

	public byte[] encryptRemotePassword(byte[] remotePassword, char[] localPassword){
		try{
			KeyVault kv = new KeyVault();
			Key aesKey = kv.getAESKey(localPassword);
		    Cipher encrypt = Cipher.getInstance("AES");
        	encrypt.init(Cipher.ENCRYPT_MODE, aesKey);
        	byte[] encryptedData = encrypt.doFinal(remotePassword);
        	return encryptedData; 
        }catch(Exception ex){
            throw new RuntimeException(ex);
	    }
	}

	public byte[] decryptRemotePassword(byte[] encryptedPassword, char[] localPassword){
		KeyVault kv = new KeyVault();
		Key aesKey = kv.getAESKey(localPassword);
		try{
			Cipher decrypt = Cipher.getInstance("AES");
			decrypt.init(Cipher.DECRYPT_MODE, aesKey);
			byte[] decryptedData = decrypt.doFinal(encryptedPassword);
			return decryptedData;
		}catch(Exception ex){
            throw new RuntimeException(ex);
	    }
	}
        
    public PublicKey getKey(String id){
        Message m = new Message();
        m.receiver = id;
        m.needingKey = true; 
        byte [] key = ClientReceive.getKey(m);
        try {
            X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(key);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey pk = kf.generatePublic(pubKeySpec);                
            return pk;
        }catch(Exception ex){
        	throw new RuntimeException(ex);
        }        
    } 
//	public static void main (String[] args){
//		Encryption exampleEncryption = new Encryption();
//		KeyVault kv = new KeyVault();
//		KeyPair kp = kv.getRSAKeys("".toCharArray());
//		PublicKey pk = kp.getPublic();
//		byte[] encrypted = exampleEncryption.encryptString(pk, "String to encrypt");
//		System.out.println(exampleEncryption.bTS(exampleEncryption.decryptString(encrypted, "")));
//	}
}