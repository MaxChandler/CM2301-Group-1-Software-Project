package Server; 

public class Message implements java.io.Serializable {
    public byte[] sender;
    public String receiver;
    public byte[] message;
}
