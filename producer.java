
package interfacegui;

import java.sql.DriverManager;
import java.sql.*;
import java.io.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Benard Kipkoech
 */
public class Producer extends javax.swing.JFrame {

    /**
     * Creates new form Producer
     */
    private Vector<Vector<String>> data; //used for data from database
    private Vector<String> header; //used to store data header
    Connection con=null;
    ResultSet rs=null;
    Statement st=null;
    PreparedStatement query=null;
    String condition;
    public Producer() {
        initComponents();
      // connect();
       updatetable();
    }
public void connect()
    {
        try
            {
                Class.forName("com.mysql.jdbc.Driver").newInstance ();
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/videostarlet","root","");
                
             }
            catch(Exception e) {
                e.printStackTrace();
                System.out.println("Could not connect to the database:");
            }
    }
public void close()
    {
        try{
            rs=null;
            st=null;
            con.close();
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("Connection not closed:");
        }
    }
 public void updatetable(){
        connect();
         try
            {
                st=con.createStatement() ;
                rs=st.executeQuery("select * from producer");
                Table_producer.setModel(DbUtils.resultSetToTableModel(rs));
             }
            catch(Exception e) {
                e.printStackTrace();
            }
         close();
    }
 public void insertproducer(){
            try {
             rs=null;
             st=null;
             connect();
             String name;
             name=jtxtname.getText();
              query=con.prepareStatement("insert into producer (prod_name) values(?)");
              query.setString(1, name);
              query.executeUpdate(); 
            }
            catch (Exception e) {
                e.printStackTrace();
            }
 }
   public void updateproducer() {
     try {
             rs=null;
             st=null;
             connect();
             String name;
             int id,row;
             row=Table_producer.getSelectedRow();
             id=(int) Table_producer.getValueAt(row, 0);
             name=jtxtname.getText();
              query=con.prepareStatement("update producer set prod_name=? where prod_id=?");
              query.setString(1, name);
              query.setInt(2, id);
              query.executeUpdate();
              close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
 }
  public void deleteproducer(){
          int id,row;
          row=Table_producer.getSelectedRow();
       id=(int) Table_producer.getValueAt(row, 0);
        connect();
        try{
        query=con.prepareStatement("Delete from producer where prod_id=?");
        query.setInt(1,id);
        query.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
        close();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jadd = new javax.swing.JButton();
        jupdate = new javax.swing.JButton();
        jdelete = new javax.swing.JButton();
        jrefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jid = new javax.swing.JLabel();
        jname = new javax.swing.JLabel();
        jtxtid = new javax.swing.JTextField();
        jtxtname = new javax.swing.JTextField();
        jsave = new javax.swing.JButton();
        jcancel = new javax.swing.JButton();
        jclear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_producer = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setTitle("PRODUCER");
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jadd.setText("ADD");
        jadd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jaddMouseClicked(evt);
            }
        });

        jupdate.setText("UPDATE");
        jupdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jupdateMouseClicked(evt);
            }
        });

        jdelete.setText("DELETE");
        jdelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jdeleteMouseClicked(evt);
            }
        });

        jrefresh.setText("REFRESH");
        jrefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrefreshMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jupdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jdelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jrefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jadd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jupdate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jrefresh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jid.setText("ID");

        jname.setText("NAME");

        jtxtid.setEditable(false);

        jsave.setText("SAVE");
        jsave.setEnabled(false);
        jsave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jsaveMouseClicked(evt);
            }
        });

        jcancel.setText("CANCEL");
        jcancel.setEnabled(false);
        jcancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcancelMouseClicked(evt);
            }
        });

        jclear.setText("CLEAR");
        jclear.setEnabled(false);
        jclear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jclearMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jname, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(jid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtxtid)
                            .addComponent(jtxtname)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jsave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jclear)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jid, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jname, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jtxtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jsave)
                    .addComponent(jcancel)
                    .addComponent(jclear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Table_producer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Table_producer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_producerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Table_producer);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jclearMouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        jtxtid.setText("");
        jtxtname.setText("");
        
    }                                   

    private void jsaveMouseClicked(java.awt.event.MouseEvent evt) {                                   
        // TODO add your handling code here:
        if("add".equals(condition)){
           insertproducer(); 
        }
        else if("update".equals(condition)){
            updateproducer();
        }
        updatetable();
        jsave.setEnabled(false);
        jcancel.setEnabled(false);
        jclear.setEnabled(false);
        jadd.setEnabled(true);
        jupdate.setEnabled(true);
        jdelete.setEnabled(true);
        jtxtid.setText("");
        jtxtname.setText("");
        condition="";
    }                                  

    private void jrefreshMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        updatetable();
    }                                     

    private void jdeleteMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, JOptionPane.YES_NO_OPTION) ==0){
            deleteproducer();
        } else {
            //load();
            updatetable();
        }
    }                                    

    private void jaddMouseClicked(java.awt.event.MouseEvent evt) {                                  
        // TODO add your handling code here:
        jsave.setEnabled(true);
        jcancel.setEnabled(true);
        jclear.setEnabled(true);
        jadd.setEnabled(false);
        jupdate.setEnabled(false);
        jdelete.setEnabled(false);
        jtxtid.setText("");
        jtxtname.setText("");
        condition="add";
    }                                 

    private void jupdateMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        jsave.setEnabled(true);
        jcancel.setEnabled(true);
        jclear.setEnabled(true);
        jadd.setEnabled(false);
        jupdate.setEnabled(false);
        jdelete.setEnabled(false);
        condition="update";
    }                                    

    private void jcancelMouseClicked(java.awt.event.MouseEvent evt) {                                     
        // TODO add your handling code here:
        jsave.setEnabled(false);
        jcancel.setEnabled(false);
        jclear.setEnabled(false);
        jadd.setEnabled(true);
        jupdate.setEnabled(true);
        jdelete.setEnabled(true);
    }                                    

    private void Table_producerMouseClicked(java.awt.event.MouseEvent evt) {                                            
        // TODO add your handling code here:
        String name;
        int id,row;
        row=Table_producer.getSelectedRow();
        jtxtid.setText(Table_producer.getValueAt(row, 0).toString());
        jtxtname.setText(Table_producer.getValueAt(row, 1).toString());
    }                                           

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Producer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Producer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Producer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Producer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Producer().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JTable Table_producer;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jadd;
    private javax.swing.JButton jcancel;
    private javax.swing.JButton jclear;
    private javax.swing.JButton jdelete;
    private javax.swing.JLabel jid;
    private javax.swing.JLabel jname;
    private javax.swing.JButton jrefresh;
    private javax.swing.JButton jsave;
    private javax.swing.JTextField jtxtid;
    private javax.swing.JTextField jtxtname;
    private javax.swing.JButton jupdate;
    // End of variables declaration                   
}
