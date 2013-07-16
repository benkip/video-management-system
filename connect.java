
package interfacegui;

import java.sql.Connection;
import java.sql.DriverManager;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Benard Kipkoech
 */
public class connect {
    public static void main(String[]args)
    {
        Connection con=null;
        try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance ();
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/videostarlet","root","");
             }
            catch(Exception e) {
                e.printStackTrace();
               System.out.println("Cannot connect to the database:");
            }
    }
    
}
