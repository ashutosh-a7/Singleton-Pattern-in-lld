import java.io.*;
import java.util.*;
import java.util.Scanner;


class message{
    String text, senderName, receiverName;
}

interface messageBuilder{
   message buildMessage();
   public void info();
   
}

class SMSMessageBuilder implements messageBuilder{
    public message buildMessage(){
       return new message();
    }
    public void info(){
        System.out.println("This is SMS Message Builder\n");
    }
}
class EMAILMessageBuilder implements messageBuilder{
    public message buildMessage(){
       return new message();
    }
    public void info(){
        System.out.println("This is Email Message Builder\n");
    }
}
class NotificationMessageBuilder implements messageBuilder{
    public message buildMessage(){
       return new message();
    }
    public void info(){
        System.out.println("This is Notifications Message Builder\n");
    }
}


class myMessageBuilder{

    messageFactory mf;
    public myMessageBuilder(messageFactory mF){
        mf = mF;
    }
    public messageBuilder build(String myMessage){
        return mf.build(myMessage);
    }  
}

class messageFactory{
     messageBuilder mb;
     private static Map<String,messageBuilder> messages = new HashMap<String,messageBuilder>();

        private static messageFactory myfac;          //// Declaring ref and constructor as private
        private messageFactory(){
        }

        public static messageFactory getInstance(){
            if(myfac==null){
                myfac = new messageFactory();
            }
            return myfac;
        }
     
     final void populateMap(messageFactory mF)
     {
         mb = new SMSMessageBuilder();
         mF.messages.put("SMS",mb);
         mb = new NotificationMessageBuilder();
         mF.messages.put("Notifications",mb);
         mb = new EMAILMessageBuilder();
         mF.messages.put("Email",mb);
    }

    public messageBuilder build(String myMessage){
          return messages.get(myMessage);
    }
}

public class singletonPattern {
    public static void main(String[] args)
    {
        System.out.println("\nThis is Singleton Pattern\n");

        messageFactory myfac = messageFactory.getInstance();
        myfac.populateMap(myfac);
        myMessageBuilder my = new myMessageBuilder(myfac);

        messageBuilder mb = my.build("SMS");
        mb.info();     

    }
}
