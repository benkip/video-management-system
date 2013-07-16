/*
 * The login page
 */
package interfacegui;

/**
 *
 * @author benard kipkoech
 */

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.util.Vector;
import java.sql.*;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.util.Date;
import javax.swing.*;
import java.text.*;
import javax.swing.table.TableModel;
public class home extends javax.swing.JFrame {

    /**
     * Creates new form home
     */
            
    private Vector<Vector<String>> data; //used for data from database
    private Vector<String> header; //used to store data header
    Connection con=null;
    ResultSet rs=null;
    Statement st=null;
    PreparedStatement query=null;
    String state,sql;
    String name,status,supplier,producer,category,description;
    Date date_received,date_published;
    String condition;
    /** Creates new form TableExamples */
    
    public home () throws Exception{

        initComponents();
        load();
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
               // System.out.println("Could not connect to the database:");
                JOptionPane.showMessageDialog(rootPane, "Please check your database parameters");
                
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
            //System.out.println("Connection not closed:");
        }
    }
    
    public void load(){
        connect();
        //Border border=2;
        Table_video.setRowHeight(30);
        
        try {
        st=con.createStatement() ;
        rs=st.executeQuery("select sup_name from supplier");
        while(rs.next()){
         JComboBoxSupplier.addItem(rs.getObject("sup_name"));
         jcombosupplier.addItem(rs.getObject("sup_name"));
        // JComboBoxProducer.addItem(rs.getObject("prod_name"));
        // JComboBoxCategory.addItem(rs.getObject("cat_name"));
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        close();
         connect();
        try {
        st=con.createStatement() ;
        rs=st.executeQuery("select prod_name from producer");
        while(rs.next()){
         //JComboBoxSupplier.addItem(rs.getObject("prod_name"));  
         JComboBoxProducer.addItem(rs.getObject("prod_name"));
         jcomboproducer.addItem(rs.getObject("prod_name"));
        // JComboBoxCategory.addItem(rs.getObject("cat_name"));
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        close();
         connect();
        try {
        st=con.createStatement() ;
        rs=st.executeQuery("select cat_name from video_category");
        while(rs.next()){
         //JComboBoxSupplier.addItem(rs.getObject("cat_name"));  
        // JComboBoxProducer.addItem(rs.getObject("prod_name"));
         JComboBoxCategory.addItem(rs.getObject("cat_name"));
         jcombocategory.addItem(rs.getObject("cat_name"));
        }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        close();
    }
    
public void toExcel(){
    try{
        TableModel model = Table_video.getModel();
        FileWriter excel;
        excel = new FileWriter("C:/result.xls");

        for(int i = 0; i < model.getColumnCount(); i++){
            excel.write(model.getColumnName(i) + "\t");
        }

        excel.write("\n");

        for(int i=0; i< model.getRowCount(); i++) {
            for(int j=0; j < model.getColumnCount(); j++) {
                excel.write(model.getValueAt(i,j).toString()+"\t");
            }
            excel.write("\n");            
        }
        excel.close();
        JOptionPane.showMessageDialog(null, "Data saved at " + "'C:\\ result.xls' successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
    }catch(Exception e){ 
        e.printStackTrace();
    }
}

    public void insertvideo(){
        connect();
       int sup,prod,cat;
       sup=1;
       prod=1;
       cat=1;
        name=JTextFieldName.getText();
        date_received=JXDatePickerReceived.getDate();
        date_published=JXDatePickerPublished.getDate();
        description=JTextAreaDescription.getText();
        status=JComboBoxStatus.getSelectedItem().toString();
        supplier=JComboBoxSupplier.getSelectedItem().toString();
        producer=JComboBoxProducer.getSelectedItem().toString();
        category=JComboBoxCategory.getSelectedItem().toString();
       try{

              st=con.createStatement();
              sql="select * from supplier where sup_name='"+supplier+"'";
             rs=st.executeQuery(sql) ;
             while(rs.next()){
             sup=(int) rs.getObject("sup_id");
           // System.out.print(sup);
            }
             rs=null;
             st=null;
              st=con.createStatement();
              sql="select * from producer where prod_name='"+producer+"'";
             rs=st.executeQuery(sql) ;
             while(rs.next()){
             prod=(int) rs.getObject("prod_id");
           // System.out.print(prod);
            }
             rs=null;
             st=null;
              st=con.createStatement();
              sql="select * from video_category where cat_name='"+category+"'";
             rs=st.executeQuery(sql) ;
             while(rs.next()){
             cat=(int) rs.getObject("cat_id");
           // System.out.print(cat);
            }
             rs=null;
             st=null;
              query=con.prepareStatement("insert into video (vid_name,date_received,date_published,description,status,sup_id,cat_id,prod_id) values(?,?,?,?,?,?,?,?)");
              query.setString(1, name);
              query.setDate(2, new java.sql.Date(date_received.getTime()));
              query.setDate(3,new java.sql.Date(date_published.getTime()));
              query.setString(4,description);
              query.setString(5,status);
              query.setInt(6,sup);
              query.setInt(7, cat);
              query.setInt(8,prod);
              query.executeUpdate();             
        }
       catch(Exception e){
           e.printStackTrace();
       }
        close();
    }
    
      public void updatevideo(){
        connect();
       int sup,prod,cat,id,row;
       id=0;
       sup=1;
       prod=1;
       cat=1;
       row=Table_video.getSelectedRow();
       id=(int) Table_video.getValueAt(row, 0);
        name=JTextFieldName.getText();
        date_received=JXDatePickerReceived.getDate();
        date_published=JXDatePickerPublished.getDate();
        description=JTextAreaDescription.getText();
        status=JComboBoxStatus.getSelectedItem().toString();
        supplier=JComboBoxSupplier.getSelectedItem().toString();
        producer=JComboBoxProducer.getSelectedItem().toString();
        category=JComboBoxCategory.getSelectedItem().toString();
       try{

              st=con.createStatement();
              sql="select * from supplier where sup_name='"+supplier+"'";
             rs=st.executeQuery(sql) ;
             while(rs.next()){
             sup=(int) rs.getObject("sup_id");
            //System.out.print(sup);
            }
             rs=null;
             st=null;
              st=con.createStatement();
              sql="select * from producer where prod_name='"+producer+"'";
             rs=st.executeQuery(sql) ;
             while(rs.next()){
             prod=(int) rs.getObject("prod_id");
            //System.out.print(prod);
            }
             rs=null;
             st=null;
              st=con.createStatement();
              sql="select * from video_category where cat_name='"+category+"'";
             rs=st.executeQuery(sql) ;
             while(rs.next()){
             cat=(int) rs.getObject("cat_id");
           // System.out.print(cat);
            }
             rs=null;
             st=null;
              query=con.prepareStatement("update video set vid_name=?,date_received=?,date_published=?,description=?,status=?,sup_id=?,cat_id=?,prod_id=? where vid_id=?");
              query.setString(1, name);
              query.setDate(2, new java.sql.Date(date_received.getTime()));
              query.setDate(3,new java.sql.Date(date_published.getTime()));
              query.setString(4,description);
              query.setString(5,status);
              query.setInt(6,sup);
              query.setInt(7, cat);
              query.setInt(8,prod);
              query.setInt(9,id);
              query.executeUpdate();             
        }
       catch(Exception e){
           e.printStackTrace();
       }
        close();
    }
    
      public void deletevideo(){
          int id,row;
          row=Table_video.getSelectedRow();
       id=(int) Table_video.getValueAt(row, 0);
        connect();
        try{
        query=con.prepareStatement("Delete from video where vid_id=?");
        query.setInt(1,id);
        query.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }
        close();
    }
    
      public void viewvideo(){
        connect();
        
        close();
    }
    
    
   public void search() {
       String text=JTextFieldSearch.getText();
       String record;
       int id;
       //System.out.println(text);
       connect();
       try{
       st=con.createStatement() ;
       query=con.prepareStatement("select vid_id,vid_name,date_received,date_published,description,status,sup_name as supplier,prod_name as producer,cat_name as language from video,video_category,producer,supplier where video.cat_id=video_category.cat_id and supplier.sup_id=video.sup_id and producer.prod_id=video.prod_id and vid_name like ?");
       query.setString(1, "%" + text + "%");
       rs=query.executeQuery();
       Table_video.setModel(DbUtils.resultSetToTableModel(rs));
            
       }
       catch(Exception e){
           e.printStackTrace();
       }
       close();
   }

   public void searchupdate(){
       String item;
       //ColumnModel modelcol;
      //item=JListSearch.getSelectedValue().toString();
      connect();
      try{
       st=con.createStatement() ;
       query=con.prepareStatement("select vid_id from video where vid_name like ?");
       //query.setString(1,  item );
       rs=query.executeQuery();
       //ColumnModel modelcol=Table_video.getColumnModel();
      }
      catch(Exception e){
          e.printStackTrace();
      }
      
   } 
   public void updatetable(){
        connect();
        int x;
         try
            {
                st=con.createStatement() ;
                rs=st.executeQuery("select vid_id,vid_name,date_received,date_published,description,status,sup_name as supplier,prod_name as producer,cat_name as language from video,video_category,producer,supplier where video.cat_id=video_category.cat_id and supplier.sup_id=video.sup_id and producer.prod_id=video.prod_id order by vid_id ");
                Table_video.setModel(DbUtils.resultSetToTableModel(rs));
                x=Table_video.getRowCount();
                jtxtcount.setText(x+"Records");
                
             }
            catch(Exception e) {
                e.printStackTrace();
            }
         close();
         Table_video.setRowHeight(30);
    }
   public void filtertable(){
       System.out.print("hello");
       
   }
   public void viewcategory(){
       try {
           connect();
       String cat;
       int x;
       cat=jcombocategory.getSelectedItem().toString();
       query=con.prepareStatement("select vid_id,vid_name,date_received,date_published,description,status,sup_name as supplier,prod_name as producer,cat_name as language from video,video_category,producer,supplier where video.cat_id=video_category.cat_id and supplier.sup_id=video.sup_id and producer.prod_id=video.prod_id and cat_name=?");
       query.setString(1, cat );
       rs=query.executeQuery();
       Table_video.setModel(DbUtils.resultSetToTableModel(rs));
       Table_video.setRowHeight(30);
       x=Table_video.getRowCount();
       jtxtcount.setText(x+"Records");
       close();
       }
       catch (Exception e) {
           e.printStackTrace();
       }
   }
   public void viewproducer(){
     try {
           connect();
       String cat;
       int x;
       cat=jcomboproducer.getSelectedItem().toString();
       query=con.prepareStatement("select vid_id,vid_name,date_received,date_published,description,status,sup_name as supplier,prod_name as producer,cat_name as language from video,video_category,producer,supplier where video.cat_id=video_category.cat_id and supplier.sup_id=video.sup_id and producer.prod_id=video.prod_id and prod_name=?");
       query.setString(1, cat );
       rs=query.executeQuery();
       Table_video.setModel(DbUtils.resultSetToTableModel(rs));
       Table_video.setRowHeight(30);
       x=Table_video.getRowCount();
       jtxtcount.setText(x+"Records");
       close();
       }
       catch (Exception e) {
           e.printStackTrace();
       }  
   }
   public void viewsupplier() {
       try {
           connect();
       String cat;
       int x;
       cat=jcombosupplier.getSelectedItem().toString();
       query=con.prepareStatement("select vid_id,vid_name,date_received,date_published,description,status,sup_name as supplier,prod_name as producer,cat_name as language from video,video_category,producer,supplier where video.cat_id=video_category.cat_id and supplier.sup_id=video.sup_id and producer.prod_id=video.prod_id and sup_name=?");
       query.setString(1, cat );
       rs=query.executeQuery();
       Table_video.setModel(DbUtils.resultSetToTableModel(rs));
       Table_video.setRowHeight(30);
       x=Table_video.getRowCount();
       jtxtcount.setText( x+ " "+ "Records");
       close();
       }
       catch (Exception e) {
           e.printStackTrace();
       }
   }
   public void viewstatus()
   {
      try {
           connect();
       String cat;
       int x;
       cat=jcombostatus.getSelectedItem().toString();
       query=con.prepareStatement("select vid_id,vid_name,date_received,date_published,description,status,sup_name as supplier,prod_name as producer,cat_name as language from video,video_category,producer,supplier where video.cat_id=video_category.cat_id and supplier.sup_id=video.sup_id and producer.prod_id=video.prod_id and status=?");
       query.setString(1, cat );
       rs=query.executeQuery();
       Table_video.setModel(DbUtils.resultSetToTableModel(rs));
       Table_video.setRowHeight(30);
       x=Table_video.getRowCount();
       jtxtcount.setText( x+ " "+ "Records");
       close();
       }
       catch (Exception e) {
           e.printStackTrace();
       } 
   }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        //entityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("videostarlet?zeroDateTimeBehavior=convertToNullPU").createEntityManager();
        //supplierQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT s FROM Supplier s");
        //supplierList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : supplierQuery.getResultList();
        //videoQuery = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT v FROM Video v");
        //videoList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : videoQuery.getResultList();
        //videoQuery1 = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT v FROM Video v");
        //videoList1 = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : videoQuery1.getResultList();
        jPanel1 = new javax.swing.JPanel();
        JLabelName = new javax.swing.JLabel();
        JTextFieldName = new javax.swing.JTextField();
        JLabelDateReceived = new javax.swing.JLabel();
        JLabelDatePublished = new javax.swing.JLabel();
        JLabelStatus = new javax.swing.JLabel();
        JComboBoxStatus = new javax.swing.JComboBox();
        JLabelSupplier = new javax.swing.JLabel();
        JLabelCategory = new javax.swing.JLabel();
        JComboBoxCategory = new javax.swing.JComboBox();
        JLabelProducer = new javax.swing.JLabel();
        JComboBoxProducer = new javax.swing.JComboBox();
        JLabelDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTextAreaDescription = new javax.swing.JTextArea();
        JComboBoxSupplier = new javax.swing.JComboBox();
        JButtonSave = new javax.swing.JButton();
        JButtonCancel = new javax.swing.JButton();
        JButtonClear = new javax.swing.JButton();
        JXDatePickerReceived = new org.jdesktop.swingx.JXDatePicker();
        JXDatePickerPublished = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        JButtonAdd = new javax.swing.JButton();
        JButtonUpdate = new javax.swing.JButton();
        JButtonDelete = new javax.swing.JButton();
        JButtonRefresh = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jexport = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_video = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        JTextFieldSearch = new javax.swing.JTextField();
        JButtonSearch = new javax.swing.JButton();
        jsearchclear = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jcategory = new javax.swing.JLabel();
        jcombocategory = new javax.swing.JComboBox();
        jviewcategory = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jproducer = new javax.swing.JLabel();
        jcomboproducer = new javax.swing.JComboBox();
        jviewproducer = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jsupplier = new javax.swing.JLabel();
        jcombosupplier = new javax.swing.JComboBox();
        jviewsupplier = new javax.swing.JButton();
        jtxtcount = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jcombostatus = new javax.swing.JComboBox();
        jviewstatus = new javax.swing.JButton();
        JMenuBarVideo = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuemail = new javax.swing.JMenuItem();
        menuexit = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HOME");
        setBackground(new java.awt.Color(255, 51, 255));
        setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        setForeground(new java.awt.Color(255, 0, 204));
        setMaximumSize(new java.awt.Dimension(1207, 565));

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        JLabelName.setText("NAME");

        JLabelDateReceived.setText("DATE_RECEIVED");

        JLabelDatePublished.setText("DATE_PUBLISHED");

        JLabelStatus.setText("STATUS");

        JComboBoxStatus.setEditable(true);
        JComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ripped", "Unripped","Editted","Published" }));

        JLabelSupplier.setText("SUPPLIER");

        JLabelCategory.setText("CATEGORY");

        JComboBoxCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        JLabelProducer.setText("PRODUCER");

        JComboBoxProducer.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        JLabelDescription.setText("DESCRIPTION");

        JTextAreaDescription.setColumns(20);
        JTextAreaDescription.setRows(5);
        jScrollPane1.setViewportView(JTextAreaDescription);

        JComboBoxSupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        JButtonSave.setText("SAVE");
        JButtonSave.setEnabled(false);
        JButtonSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonSaveMouseClicked(evt);
            }
        });

        JButtonCancel.setText("CANCEL");
        JButtonCancel.setEnabled(false);
        JButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonCancelActionPerformed(evt);
            }
        });

        JButtonClear.setText("CLEAR");
        JButtonClear.setEnabled(false);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(JLabelStatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED))
                                .add(jPanel1Layout.createSequentialGroup()
                                    .add(JLabelSupplier, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                    .add(10, 10, 10)))
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(JComboBoxSupplier, 0, 124, Short.MAX_VALUE)
                                .add(JComboBoxStatus, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(jPanel1Layout.createSequentialGroup()
                            .add(JLabelName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 34, Short.MAX_VALUE)
                            .add(JTextFieldName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(JLabelDateReceived)
                            .add(JLabelDatePublished))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(JXDatePickerReceived, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(JXDatePickerPublished, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(JLabelDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(25, 25, 25)
                        .add(JButtonSave)
                        .add(18, 18, 18)
                        .add(JButtonCancel)
                        .add(18, 18, 18)
                        .add(JButtonClear))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(JLabelProducer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .add(JLabelCategory, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(JComboBoxProducer, 0, 156, Short.MAX_VALUE)
                                .add(JComboBoxCategory, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JTextFieldName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(JLabelName))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JLabelDateReceived, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 21, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(JXDatePickerReceived, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JLabelDatePublished, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(JXDatePickerPublished, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JLabelStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(JComboBoxStatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JLabelSupplier, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(JComboBoxSupplier, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JLabelCategory, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(JComboBoxCategory, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JLabelProducer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(JComboBoxProducer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(JLabelDescription, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 141, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JButtonSave)
                    .add(JButtonClear)
                    .add(JButtonCancel))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        JButtonAdd.setText("ADD");
        JButtonAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonAddMouseClicked(evt);
            }
        });

        JButtonUpdate.setText("UPDATE");
        JButtonUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonUpdateMouseClicked(evt);
            }
        });
        JButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonUpdateActionPerformed(evt);
            }
        });

        JButtonDelete.setText("DELETE");
        JButtonDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonDeleteMouseClicked(evt);
            }
        });

        JButtonRefresh.setText("REFRESH");
        JButtonRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonRefreshMouseClicked(evt);
            }
        });

        jButton1.setText("PRINT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jexport.setText("EXPORT");
        jexport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jexportActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(20, 20, 20)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jexport, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(JButtonRefresh, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .add(JButtonDelete, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(JButtonUpdate, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(JButtonAdd, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(JButtonAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(JButtonUpdate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(JButtonDelete, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(JButtonRefresh, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jexport, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Table_video.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Table_video.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        Table_video.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Table_video.setIntercellSpacing(new java.awt.Dimension(2, 2));
        Table_video.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Table_videoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Table_video);

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        JButtonSearch.setText("SEARCH");
        JButtonSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JButtonSearchMouseClicked(evt);
            }
        });

        jsearchclear.setText("CLEAR");
        jsearchclear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jsearchclearMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3Layout.createSequentialGroup()
                        .add(JButtonSearch)
                        .add(18, 18, 18)
                        .add(jsearchclear)
                        .add(0, 13, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, JTextFieldSearch))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(JTextFieldSearch, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(JButtonSearch)
                    .add(jsearchclear))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jcategory.setText("CATEGORY");

        jcombocategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jviewcategory.setText("VIEW");
        jviewcategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jviewcategoryMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jcategory, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jcombocategory, 0, 108, Short.MAX_VALUE)
                .addContainerGap())
            .add(jPanel4Layout.createSequentialGroup()
                .add(51, 51, 51)
                .add(jviewcategory, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jcategory, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jcombocategory, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jviewcategory, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jproducer.setText("PRODUCER");

        jcomboproducer.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jviewproducer.setText("VIEW");
        jviewproducer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jviewproducerMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jproducer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 86, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jcomboproducer, 0, 107, Short.MAX_VALUE)
                .addContainerGap())
            .add(jPanel5Layout.createSequentialGroup()
                .add(56, 56, 56)
                .add(jviewproducer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jproducer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jcomboproducer, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jviewproducer, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jsupplier.setText("SUPPLIER");

        jcombosupplier.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jviewsupplier.setText("VIEW");
        jviewsupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jviewsupplierMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jsupplier, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 71, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jcombosupplier, 0, 114, Short.MAX_VALUE)
                .add(18, 18, 18))
            .add(jPanel6Layout.createSequentialGroup()
                .add(54, 54, 54)
                .add(jviewsupplier, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 74, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jsupplier, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jcombosupplier, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jviewsupplier, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtxtcount.setEditable(false);

        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setText("STATUS");

        jcombostatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ripped", "Unripped", "Editted", "Published" }));

        jviewstatus.setText("VIEW");
        jviewstatus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jviewstatusMouseClicked(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jcombostatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 119, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jviewstatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 73, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(80, 80, 80))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jcombostatus, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jviewstatus, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        JMenuBarVideo.setForeground(new java.awt.Color(51, 102, 255));

        jMenu1.setText("File");

        menuemail.setText("Email");
        menuemail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuemailMouseClicked(evt);
            }
        });
        jMenu1.add(menuemail);

        menuexit.setText("Exit");
        menuexit.setName(""); // NOI18N
        jMenu1.add(menuexit);

        JMenuBarVideo.add(jMenu1);

        jMenu3.setText("Supplier");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        JMenuBarVideo.add(jMenu3);

        jMenu4.setText("Producer");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        JMenuBarVideo.add(jMenu4);

        jMenu5.setText("Category");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        JMenuBarVideo.add(jMenu5);

        setJMenuBar(JMenuBarVideo);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jtxtcount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 137, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(31, 31, 31)
                        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(137, 138, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane2)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createSequentialGroup()
                                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(jtxtcount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 14, Short.MAX_VALUE)
                        .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(53, 53, 53)))
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 437, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(19, 19, 19))
        );

        pack();
    }// </editor-fold>                        

    private void JButtonAddMouseClicked(java.awt.event.MouseEvent evt) {                                        
        // TODO add your handling code here:
       JTextFieldName.setText("");
       JTextAreaDescription.setText("");
       JButtonUpdate.setEnabled(false);
       JButtonDelete.setEnabled(false);
       JButtonRefresh.setEnabled(false);
       JButtonSave.setEnabled(true);
       JButtonCancel.setEnabled(true);
       JButtonClear.setEnabled(true);
       condition="add";
    }                                       

    private void JButtonSaveMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        if(condition=="add") {
         insertvideo();
        }
        else if(condition=="update") {
            updatevideo();
        }
         updatetable();
       JButtonUpdate.setEnabled(true);
       JButtonDelete.setEnabled(true);
       JButtonRefresh.setEnabled(true);
       JButtonSave.setEnabled(false);
       JButtonCancel.setEnabled(false);
       JButtonClear.setEnabled(false);
       condition="";
    }                                        

    private void Table_videoMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        Date rec,pub;
        int row=Table_video.getSelectedRow();
        JTextFieldName.setText(Table_video.getValueAt(row, 1).toString());
        rec=(Date) Table_video.getValueAt(row, 2); 
        pub=(Date) Table_video.getValueAt(row, 3);
        JXDatePickerReceived.setDate(rec);
        JXDatePickerPublished.setDate(pub);
        JTextAreaDescription.setText(Table_video.getValueAt(row, 4).toString());
        JComboBoxStatus.setSelectedItem(Table_video.getValueAt(row, 5));
        JComboBoxSupplier.setSelectedItem(Table_video.getValueAt(row, 6));
        JComboBoxProducer.setSelectedItem(Table_video.getValueAt(row, 7));
        JComboBoxCategory.setSelectedItem(Table_video.getValueAt(row, 8));
    }                                        

    private void JButtonRefreshMouseClicked(java.awt.event.MouseEvent evt) {                                            
        // TODO add your handling code here:
        //initComponents();
        //load();
        updatetable();
    }                                           

    private void JButtonUpdateMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
       
       JButtonUpdate.setEnabled(false);
       JButtonDelete.setEnabled(false);
       JButtonRefresh.setEnabled(false);
       JButtonSave.setEnabled(true);
       JButtonCancel.setEnabled(true);
       JButtonClear.setEnabled(true);
       condition="update";
    }                                          

    private void JButtonDeleteMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(null, JOptionPane.YES_NO_OPTION) ==0){
        JTextFieldName.setText("");
       JTextAreaDescription.setText("");
        deletevideo();
        updatetable();
        } else {
            //load();
            updatetable();
        }
    }                                          

    private void JButtonSearchMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        search();
        //filtertable();
    }                                          

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        MessageFormat header=new MessageFormat("LIST OF VIDEOS");
        MessageFormat footerr=new MessageFormat("page{0,number,integer}");
       //MessageFormat footerr1=new MessageFormat(Table_video.getRowCount() + "records");
        try{
            //Table_video.putClientProperty("print.printable", Boolean.TRUE);
            Table_video.print(JTable.PrintMode.FIT_WIDTH, header, footerr);
            
        }
        catch(java.awt.print.PrinterException e) {
            System.err.format("cannot print:",e.getLocalizedMessage());
        }
       
    }                                        

    private void JButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void JButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
        JButtonUpdate.setEnabled(true);
       JButtonDelete.setEnabled(true);
       JButtonRefresh.setEnabled(true);
       JButtonSave.setEnabled(false);
       JButtonCancel.setEnabled(false);
       JButtonClear.setEnabled(false);
    }                                             

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        new Supplier().setVisible(true);
        //this.setVisible(false);
    }                                   

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        new Producer().setVisible(true);
        //this.setVisible(false);
    }                                   

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {                                    
        // TODO add your handling code here:
        new category().setVisible(true);
        //this.setVisible(false);
    }                                   

    private void jviewcategoryMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        viewcategory();
    }                                          

    private void jviewproducerMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        viewproducer();
    }                                          

    private void jviewsupplierMouseClicked(java.awt.event.MouseEvent evt) {                                           
        // TODO add your handling code here:
        viewsupplier();
    }                                          

    private void menuemailMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        System.out.println("Email me");
    }                                      

    private void jsearchclearMouseClicked(java.awt.event.MouseEvent evt) {                                          
        // TODO add your handling code here:
        JTextFieldSearch.setText("");
        updatetable();
    }                                         

    private void jviewstatusMouseClicked(java.awt.event.MouseEvent evt) {                                         
        // TODO add your handling code here:
        viewstatus();
    }                                        

    private void jexportActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        toExcel();
    }                                       
    //}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])    {
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
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        int id;
        String name;
     
          /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                 
                new home().setVisible(true);
            }
                catch( Exception e)
                {
                    System.out.print("error");
                }
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton JButtonAdd;
    private javax.swing.JButton JButtonCancel;
    private javax.swing.JButton JButtonClear;
    private javax.swing.JButton JButtonDelete;
    private javax.swing.JButton JButtonRefresh;
    private javax.swing.JButton JButtonSave;
    private javax.swing.JButton JButtonSearch;
    private javax.swing.JButton JButtonUpdate;
    private javax.swing.JComboBox JComboBoxCategory;
    private javax.swing.JComboBox JComboBoxProducer;
    private javax.swing.JComboBox JComboBoxStatus;
    private javax.swing.JComboBox JComboBoxSupplier;
    private javax.swing.JLabel JLabelCategory;
    private javax.swing.JLabel JLabelDatePublished;
    private javax.swing.JLabel JLabelDateReceived;
    private javax.swing.JLabel JLabelDescription;
    private javax.swing.JLabel JLabelName;
    private javax.swing.JLabel JLabelProducer;
    private javax.swing.JLabel JLabelStatus;
    private javax.swing.JLabel JLabelSupplier;
    private javax.swing.JMenuBar JMenuBarVideo;
    private javax.swing.JTextArea JTextAreaDescription;
    private javax.swing.JTextField JTextFieldName;
    private javax.swing.JTextField JTextFieldSearch;
    private org.jdesktop.swingx.JXDatePicker JXDatePickerPublished;
    private org.jdesktop.swingx.JXDatePicker JXDatePickerReceived;
    private javax.swing.JTable Table_video;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jcategory;
    private javax.swing.JComboBox jcombocategory;
    private javax.swing.JComboBox jcomboproducer;
    private javax.swing.JComboBox jcombostatus;
    private javax.swing.JComboBox jcombosupplier;
    private javax.swing.JButton jexport;
    private javax.swing.JLabel jproducer;
    private javax.swing.JButton jsearchclear;
    private javax.swing.JLabel jsupplier;
    private javax.swing.JTextField jtxtcount;
    private javax.swing.JButton jviewcategory;
    private javax.swing.JButton jviewproducer;
    private javax.swing.JButton jviewstatus;
    private javax.swing.JButton jviewsupplier;
    private javax.swing.JMenuItem menuemail;
    private javax.swing.JMenuItem menuexit;
    private java.util.List<interfacegui.Supplier> supplierList;
    private javax.persistence.Query supplierQuery;
    private java.util.List<interfacegui.Video> videoList;
    private java.util.List<interfacegui.Video> videoList1;
    private javax.persistence.Query videoQuery;
    private javax.persistence.Query videoQuery1;
    // End of variables declaration                   
}
