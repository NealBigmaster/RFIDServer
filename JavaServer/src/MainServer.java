import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) throws Exception {
        
        ServerSocket ss = new ServerSocket(8888);
        Socket s = ss.accept();

        //读数据
        InputStream is = s.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        //写数据
        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("Start");

        Thread receiverThread = new Thread(() -> {
            try {
                while (true) {
                    String tag = dis.readUTF();

                    if (tag.startsWith("Tag")){

                        RFID.AddTag(tag);

                    }else{

                        System.out.println(tag);
                        
                    }
                    
                }
            } catch (Exception e) {
                System.out.println("Client disconnected or error: " + e.getMessage());
            }
        });
        receiverThread.start();

        while(true){

            System.out.println("-----Welcome-----");
            System.out.println("1.Searching ID.");
            System.out.println("2.Searching All.");
            System.out.println("3.Deleting.");
            System.out.println("4.Quit");

            Scanner sc = new Scanner(System.in);
            System.out.println("Select your choice:");
            int choice = sc.nextInt();

            if(choice == 1){

                System.out.println("Input the ID:");
                int number = sc.nextInt();

                RFID data = RFID.findId(number);
                System.out.println(data);
                dos.writeUTF("Start");
            }

            if(choice == 2){

                ArrayList data = RFID.findAll();
                if(data.size() == 0){
                    System.out.println("no Tag");
                }else{

                    for(int i = 0; i < data.size(); i++){
                        RFID d = (RFID)(data.get(i));
                        System.out.println(d.getTag());
                    }
                }
                dos.writeUTF("Start");
            }

            if(choice == 3){
                //System.out.println("Delete ID:");
                
                int n = RFID.delTag();
                if(n <= 0){
                    System.out.println("no Tag");
                    dos.writeUTF("Start");
                }else{
                    System.out.println("finish.");
                    dos.writeUTF("Start");
                }
                
            }
                    
            if(choice == 4){
                System.out.println("Quiting....");
                break;
            }
                 
        }

        String str = dis.readUTF();
        System.out.println(str);


        dos.close();
        os.close();
        dis.close();
        is.close();
        s.close();
        ss.close();
    }

}