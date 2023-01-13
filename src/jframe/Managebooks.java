/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;
import java.sql.DriverManager;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import javax.swing.table.TableModel;
import java.sql.*;
import java.text.MessageFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author yared
 */ 
public class Managebooks extends javax.swing.JFrame {

    /**
     * Creates new form Managebooks
     */
      String bookName,author;
        int bookId,quantity;
        DefaultTableModel model;
    public Managebooks() {
      
        initComponents();
        setBookDetailsToTable();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
    }
//this method is display book from database 
    public void setBookDetailsToTable(){
        
        try{
             String driver="com.mysql.cj.jdbc.Driver";
            String datbaseurl="jdbc:mysql://localhost:3306/universitylibrary";
            String username="root";
            String password="";
            Class.forName(driver);
           java.sql.Connection conn=DriverManager.getConnection(datbaseurl,username,password);
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery("select *from book_details");
           
           while(rs.next()){
               String bookId=rs.getString("book_id");
               String bookName=rs.getString("book_name");
               String author=rs.getString("author");
               int quantity=rs.getInt("quantity");
               Object[] obj={bookId,bookName,author,quantity};
                 DefaultTableModel model = (DefaultTableModel)booktable.getModel();
                 model.addRow(obj);
               
               
           }
           
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
     public boolean validatesingnup(){
         bookId=Integer.parseInt(txt_book_id.getText());
         bookName=txt_book_name.getText();
         author=txt_author_name.getText();
          quantity=Integer.parseInt(txt_quantity.getText());
        
//       if(bookId.getText().equals("")){
//           JOptionPane.showMessageDialog(this, "Please Enter ud !!");
//           return false;
//       }
        if(bookName.equals("")){
           JOptionPane.showMessageDialog(this, "Please Enter BookName !!");
           return false;
       }
         if(author.equals("")){
           JOptionPane.showMessageDialog(this, "Please Enter Author !!");
           return false;
       }
          if(txt_quantity.getText().isEmpty()){
           JOptionPane.showMessageDialog(this, "Please Enter Yourr nQuantity !!");
           return false;
       }
         
        return true;
        
        
        
    }
    
    public boolean addBook(){
        boolean isAdded=false;
        bookId=Integer.parseInt(txt_book_id.getText());
        bookName=txt_book_name.getText();
        author=txt_author_name.getText();
        quantity=Integer.parseInt(txt_quantity.getText());
        try{
             java.sql.Connection conn=DBConnection.getConnection();
             String sql="insert into book_details values(?,?,?,?)";
             PreparedStatement pst=conn.prepareStatement(sql);
             pst.setInt(1, bookId);
             pst.setString(2,bookName);
             pst.setString(3,author);
             pst.setInt(4,quantity);
             int rowCount=pst.executeUpdate();
             if(rowCount>0)
             {
                 isAdded=true;
             }else{
                 isAdded=false;
             }
             
             
        }catch(Exception e){
            e.printStackTrace();
        }
        return isAdded;
        
    }
    public void clearTable(){
        DefaultTableModel model=(DefaultTableModel)booktable.getModel();
        model.setRowCount(0);
    }
    
    public boolean updateBook(){
        boolean isUpdated=false;
          bookId=Integer.parseInt(txt_book_id.getText());
          bookName=txt_book_name.getText();
          author=txt_author_name.getText();
          quantity=Integer.parseInt(txt_quantity.getText());
                                                 

        try{
                   java.sql.Connection conn=DBConnection.getConnection();
                   String sql="update book_details set book_name=?,author=?,quantity=? where book_id=?";
                   PreparedStatement pst=conn.prepareStatement(sql);
                   pst.setString(1,bookName);
                   pst.setString(2,author);
                   pst.setInt(3, quantity);
                   pst.setInt(4,bookId);

                   int rowCount=pst.executeUpdate();
                   if(rowCount > 0){
                       isUpdated=true;
                   }else{
                       isUpdated=false;
                   }
                   
                   
                   
        }catch(Exception e){
            e.printStackTrace();
        }
        return isUpdated;
    }
    public boolean deleteBook(){
        boolean isDeleted=false;
        bookId=Integer.parseInt(txt_book_id.getText());
        try{
              java.sql.Connection conn=DBConnection.getConnection();
              String sql="delete from book_details where book_id=?";
              PreparedStatement pst=conn.prepareStatement(sql);
              pst.setInt(1,bookId);
              int rowCount=pst.executeUpdate();
              if(rowCount >0){
                  isDeleted=true;
              }else
              {
                  isDeleted=false;
              }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isDeleted;
    }
      public boolean checkDuplicateBook(){
        String id=txt_book_id.getText();
        boolean isExist=false;
        try{
             String driver="com.mysql.cj.jdbc.Driver";
            String datbaseurl="jdbc:mysql://localhost:3306/universitylibrary";
            String username="root";
            String password="";
            Class.forName(driver);
           java.sql.Connection conn=DriverManager.getConnection(datbaseurl,username,password);
          PreparedStatement pst=conn.prepareStatement("select * from book_details where book_id=?");
          pst.setString(1, id);
          ResultSet rs=pst.executeQuery();
          if(rs.next()){
              isExist=true;
          }else{
              isExist=false;
          }
 
        }catch(Exception e){
            e.printStackTrace();
        }
        return isExist;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_book_id = new rojerusan.RSMetroTextPlaceHolder();
        jLabel6 = new javax.swing.JLabel();
        txt_book_name = new rojerusan.RSMetroTextPlaceHolder();
        jLabel7 = new javax.swing.JLabel();
        txt_author_name = new rojerusan.RSMetroTextPlaceHolder();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_quantity = new rojerusan.RSMetroTextPlaceHolder();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        rSMaterialButtonRectangle4 = new rojerusan.RSMaterialButtonRectangle();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        booktable = new rojeru_san.complementos.RSTableMetro();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        report = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 51, 0));

        txt_book_id.setBackground(new java.awt.Color(255, 255, 0));
        txt_book_id.setForeground(new java.awt.Color(0, 0, 102));
        txt_book_id.setBorderColor(new java.awt.Color(255, 51, 0));
        txt_book_id.setPlaceholder("Enter book id.....");
        txt_book_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_book_idFocusLost(evt);
            }
        });
        txt_book_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_book_idActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("Book_Id");

        txt_book_name.setBackground(new java.awt.Color(255, 255, 0));
        txt_book_name.setForeground(new java.awt.Color(0, 0, 102));
        txt_book_name.setBorderColor(new java.awt.Color(255, 51, 0));
        txt_book_name.setPlaceholder("Enter book name....");
        txt_book_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_book_nameFocusLost(evt);
            }
        });
        txt_book_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_book_nameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Book_Name");

        txt_author_name.setBackground(new java.awt.Color(255, 255, 0));
        txt_author_name.setForeground(new java.awt.Color(0, 0, 102));
        txt_author_name.setBorderColor(new java.awt.Color(255, 51, 0));
        txt_author_name.setPlaceholder("Enter author name....");
        txt_author_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_author_nameFocusLost(evt);
            }
        });
        txt_author_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_author_nameActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Author_Name");

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setText("Quantity");

        txt_quantity.setBackground(new java.awt.Color(255, 255, 0));
        txt_quantity.setForeground(new java.awt.Color(0, 0, 102));
        txt_quantity.setBorderColor(new java.awt.Color(255, 51, 0));
        txt_quantity.setPlaceholder("Enter quantity....");
        txt_quantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_quantityFocusLost(evt);
            }
        });
        txt_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantityActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(0, 0, 102));
        rSMaterialButtonRectangle1.setText("Update");
        rSMaterialButtonRectangle1.setFont(new java.awt.Font("Dubai Medium", 1, 14)); // NOI18N
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(0, 0, 102));
        rSMaterialButtonRectangle3.setText("Save");
        rSMaterialButtonRectangle3.setFont(new java.awt.Font("MS Reference Sans Serif", 1, 14)); // NOI18N
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(0, 0, 102));
        rSMaterialButtonRectangle2.setText("Delete");
        rSMaterialButtonRectangle2.setFont(new java.awt.Font("Power Geez Unicode2", 1, 14)); // NOI18N
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 102, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/back (1).png"))); // NOI18N
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        rSMaterialButtonRectangle4.setBackground(new java.awt.Color(0, 0, 102));
        rSMaterialButtonRectangle4.setText("Clear");
        rSMaterialButtonRectangle4.setFont(new java.awt.Font("Power Geez Unicode2", 1, 14)); // NOI18N
        rSMaterialButtonRectangle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_author_name, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                            .addComponent(txt_book_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_book_name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_quantity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 60, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_book_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txt_book_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txt_author_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txt_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        booktable.setForeground(new java.awt.Color(255, 255, 0));
        booktable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book_Id", "BookName", "AuthorName", "Quantity"
            }
        ));
        booktable.setColorBackgoundHead(new java.awt.Color(255, 51, 0));
        booktable.setColorBordeFilas(new java.awt.Color(0, 102, 204));
        booktable.setColorBordeHead(new java.awt.Color(255, 51, 0));
        booktable.setColorFilasBackgound1(new java.awt.Color(255, 255, 0));
        booktable.setColorFilasBackgound2(new java.awt.Color(153, 204, 255));
        booktable.setColorForegroundHead(new java.awt.Color(255, 255, 0));
        booktable.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        booktable.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        booktable.setRowHeight(30);
        booktable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                booktableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(booktable);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 770, 470));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/New folder/Science-Courses-icon (1).png"))); // NOI18N
        jLabel11.setText("Manage Books");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 264, 60));

        jLabel12.setBackground(new java.awt.Color(255, 0, 51));
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 200, 10));

        report.setBackground(new java.awt.Color(204, 204, 0));
        report.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        report.setForeground(new java.awt.Color(0, 0, 0));
        report.setText("report");
        report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportActionPerformed(evt);
            }
        });
        jPanel2.add(report, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 130, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_book_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_book_idFocusLost
        // TODO add your handling code here:
   if(checkDuplicateBook()==true){
            JOptionPane.showMessageDialog(this, "Book Id already exist");
        }
    }//GEN-LAST:event_txt_book_idFocusLost

    private void txt_book_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_book_idActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_book_idActionPerformed

    private void txt_book_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_book_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_book_nameFocusLost

    private void txt_book_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_book_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_book_nameActionPerformed

    private void txt_author_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_author_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_author_nameFocusLost

    private void txt_author_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_author_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_author_nameActionPerformed

    private void txt_quantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_quantityFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityFocusLost

    private void txt_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantityActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
      
         if(updateBook()==true){
            JOptionPane.showMessageDialog(this,"Book Updated !!");
            clearTable();
            setBookDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Book Updated failed !!");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
            if(deleteBook()==true){
            JOptionPane.showMessageDialog(this,"Book Deleted !!");
            clearTable();
            setBookDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Book Deletion failed !!");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
        // TODO add your handling code here:
        if(validatesingnup()==true){
            if(checkDuplicateBook()==false){
        if(addBook()==true){
            JOptionPane.showMessageDialog(this,"Book Added !!");
            clearTable();
            setBookDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Book addition failed !!");
        }
            }
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void booktableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_booktableMouseClicked
        // TODO add your handling code here:
      int rowNo=booktable.getSelectedRow();
      TableModel model=booktable.getModel();
      txt_book_id.setText(model.getValueAt(rowNo,0).toString());
      txt_book_name.setText(model.getValueAt(rowNo,1).toString());
      txt_author_name.setText(model.getValueAt(rowNo,2).toString());
      txt_quantity.setText(model.getValueAt(rowNo,3).toString());

    }//GEN-LAST:event_booktableMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        HomePage home=new HomePage();
        home.setVisible(true);
         dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void rSMaterialButtonRectangle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle4ActionPerformed
        // TODO add your handling code here:
        txt_book_id.setText("");
        txt_book_name.setText("");
        txt_author_name.setText("");
        txt_quantity.setText("");
        
    }//GEN-LAST:event_rSMaterialButtonRectangle4ActionPerformed

    private void reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportActionPerformed
        // TODO add your handling code here:

        MessageFormat header=new MessageFormat(" Harmbee University Labirary MS");
        MessageFormat footer=new MessageFormat("Page {0, number, integer }");
        try {
            booktable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException ex) {
            System.err.format("Cannot print ",ex.getMessage());
        }
    }//GEN-LAST:event_reportActionPerformed

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
            java.util.logging.Logger.getLogger(Managebooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Managebooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Managebooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Managebooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Managebooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.RSTableMetro booktable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle4;
    private rojerusan.RSMaterialButtonRectangle report;
    private rojerusan.RSMetroTextPlaceHolder txt_author_name;
    private rojerusan.RSMetroTextPlaceHolder txt_book_id;
    private rojerusan.RSMetroTextPlaceHolder txt_book_name;
    private rojerusan.RSMetroTextPlaceHolder txt_quantity;
    // End of variables declaration//GEN-END:variables
}
