
import com.thingmagic.*;
import java.util.ArrayList;

public class SargasServer {


    public static ArrayList reader() {
        Reader reader = null;
        ArrayList list = new ArrayList();

        try {
            // 替换为你阅读器的 IP 地址
            //String readerUri = "tmr://192.168.12.167";
            String readerUri = "tmr://172.16.0.167";
            reader = Reader.create(readerUri);
            reader.connect();
            System.out.print(reader);

            // 读取标签（5秒）
            System.out.println("Start reading...");
            TagReadData[] tags = reader.read(5000);

            // // 输出标签信息
            for (TagReadData tag : tags) {

                //System.out.println("EPC: " + tag.toString() + " RSSI: " + tag.getRssi());
                String Tag = tag.toString();
                list.add(Tag);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.destroy();
                } catch (Exception e) {
                    System.err.println("Failed to close reader.");
                }
            }
        }

        return list;
    
    }
}

// javac -d bin -cp "lib/*" src/SargasServer.java                                
//  java -cp "bin;lib/*" SargasServer
