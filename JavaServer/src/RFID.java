import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RFID {

    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    private String Tag;

    public String getTag() {
        return Tag;
    }
    public void setTag(String tag) {
        Tag = tag;
    }

    private String worker;

    public String getWorker() {
        return worker;
    }
    public void setWorker(String worker) {
        this.worker = worker;
    }

    //Searching
    public static RFID findId(int number) throws Exception{

        RFID data = null;
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rfid?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Australia/Sydney&allowPublicKeyRetrieval=true","root","root");
   
        Statement sta = conn.createStatement();
        //增加数据
        //int i = sta.executeUpdate("insert into rfid_tag (id,Tag,worker) values (1,'Ed123847829','Amin')");
    
        //查询表
        ResultSet rs = sta.executeQuery("select * from rfid_tag where Tag = " + number);

        if(rs.next()){

            System.out.println(rs.getInt("id") + "---" + rs.getString("Tag") + "---" +rs.getString("worker"));

            int id =rs.getInt("id");
            String Tag = rs.getString("Tag");
            String worker = rs.getString("worker");

            data = new RFID();
            data.setId(id);
            data.setTag(Tag);
            data.setWorker(worker);

        }else{
            System.out.println("no result");
        }

        sta.close();
        conn.close();

        return data;
    }

    //Search All
    public static ArrayList findAll() throws Exception{

        ArrayList list = new ArrayList();
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rfid?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Australia/Sydney&allowPublicKeyRetrieval=true","root","root");
   
        Statement sta = conn.createStatement();
        //增加数据
        //int i = sta.executeUpdate("insert into rfid_tag (id,Tag,worker) values (1,'Ed123847829','Amin')");
    
        //查询表
        ResultSet rs = sta.executeQuery("select * from rfid_tag");

        while(rs.next()){
            //int id =rs.getInt("id");
            String Tag = rs.getString("Tag");
            //String worker = rs.getString("worker");

            RFID data = new RFID();
            //data.setId(id);
            data.setTag(Tag);
            //data.setWorker(worker);

            list.add(data);

        }

        sta.close();
        conn.close();

        return list;
    }

    //DeletData
    public static int delTag() throws Exception{

        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rfid?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Australia/Sydney&allowPublicKeyRetrieval=true","root","root");
   
        Statement sta = conn.createStatement();
        //增加数据
        //int i = sta.executeUpdate("insert into rfid_tag (id,Tag,worker) values (1,'Ed123847829','Amin')");
    
        //查询表
        int n = sta.executeUpdate("delete from rfid_tag ");

        sta.close();
        conn.close();

        return n;
    }

        //DeletData
    public static void AddTag(String Tag) throws Exception{

        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/rfid?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Australia/Sydney&allowPublicKeyRetrieval=true","root","root");
   
        Statement sta = conn.createStatement();
        //增加数据
        int i = sta.executeUpdate("insert into rfid_tag (Tag) values ('"+ Tag + "')");

        sta.close();
        conn.close();


    }



}
