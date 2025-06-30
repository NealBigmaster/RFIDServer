import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Client Server(recieve Tag/display Tag/Save Tag/transmit Tag)
 *
 */
public class Client 
{
    public static void main( String[] args ) throws Exception
    {
        int n = 0;
        System.out.println("Starting Client.");
        //setting Ip and port
        Socket s = new Socket( "172.16.0.121",8888);

        OutputStream os = s.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        InputStream is = s.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        while(true){
            String command = dis.readUTF();

            if (command.equals("Start")) {

                System.out.println("-----Welcome-----");

                ArrayList<String> data = SargasServer.reader();
                if(data.size() == 0){
                    System.out.println("no Tag");
                }else{

                    for(int i = 0; i < data.size(); i++){

                        System.out.println(data.get(i));
                        dos.writeUTF(data.get(i));  // send each tag
                        dos.flush();
                    }
                    //写数据
                    dos.writeUTF("Client Working!");
                }

            }else{

                System.out.println("no command");
            }


            if(n == 1){
                break;
            }
        }

        dis.close();
        is.close();
        dos.close();
        os.close();
        s.close();
    }
}
