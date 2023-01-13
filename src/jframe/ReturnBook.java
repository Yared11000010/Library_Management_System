/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yared
 */
public class ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBooks
     */
    public ReturnBook() {
        initComponents();
        setStudentDetailsToTable();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
    }
      public void setStudentDetailsToTable(){
        try{
             String driver="com.mysql.cj.jdbc.Driver";
            String datbaseurl="jdbc:mysql://localhost:3306/universitylibrary";
            String username="root";
            String password="";
            Class.forName(driver);
           java.sql.Connection conn=DriverManager.getConnection(datbaseurl,username,password);
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery("select *from issue_book_details where status='"+"pending"+"'");
           while(rs.next()){
               String id=rs.getString("book_id");
               String bookName=rs.getString("book_name");
               String Studentid=rs.getString("student_id");
               String StudentName=rs.getString("student_name");
  
               
               
               Object[] obj={id,bookName,Studentid,StudentName};
                 DefaultTableModel model = (DefaultTableModel)issuebookdetails.getModel();
                 model.addRow(obj);
               
           }
           
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   //to fetch the issue book details from the database and display it to panel
    public void getIssueBookDetails(){
    
        int bookId=Integer.parseInt(txt_bookid.getText());
        int studentId=Integer.parseInt(txt_studentid.getText());
        try{
                         java.sql.Connection conn=DBConnection.getConnection();
                         String sql="select *from issue_book_details where book_id=? and student_id=? and status =?";
                         
                         PreparedStatement pst=conn.prepareStatement(sql);
                         pst.setInt(1,bookId);
                         pst.setInt(2, studentId);
                         pst.setString(3, "pending ");
                         ResultSet rs=pst.executeQuery();
                         if(rs.next()){
                             
                            lbiissue.setText(rs.getString("book_id"));
                            lblbook_name.setText(rs.getString("book_name"));
                            lbl_studentName.setText(rs.getString("student_name"));
                            lblissueDate.setText(rs.getString("issue_date"));
                            lbldueDate.setText(rs.getString("due_date"));
                            lblerror.setText("");
                         }else
                         {
                             lblerror.setText("No Record is Found !!!");
                             lbiissue.setText("");
                             lblbook_name.setText("");
                             lbl_studentName.setText("");
                             lblissueDate.setText("");
                             lbldueDate.setText("");
                             
                         }
            
        }catch(Exception e){
            e.printStackTrace();
        }
 
        
    }
     //return the book
    public boolean returnBook(){
        boolean isReturned=false;
          long l=System.currentTimeMillis();
       java.sql.Date todayDate=new java.sql.Date(l);
        int bookId=Integer.parseInt(txt_bookid.getText());
        int studentId=Integer.parseInt(txt_studentid.getText());
        try{
    java.sql.Connection conn=DBConnection.getConnection();
    String sql="update issue_book_details set status=? where student_id=?  and book_id=? and status=?";
    PreparedStatement pstt=conn.prepareStatement("select *from issue_book_details  where due_date < ? and status =? and student_id=? ");
           pstt.setDate(1, todayDate);
           pstt.setString(2, "pending");
           pstt.setInt(3,studentId);
           ResultSet rs=pstt.executeQuery();
            if(rs.next()){     
              Penality pen=new Penality();
              pen.setVisible(true);            
             }
    PreparedStatement pst=conn.prepareStatement(sql);
    pst.setString(1, "returned");
    pst.setInt(2, studentId);
    pst.setInt(3, bookId);
    pst.setString(4, "pending");
    int rowCount=pst.executeUpdate();
    if(rowCount>0){
        isReturned=true;
    }else{
        isReturned=false;
    }
        }catch(Exception e){
            e.printStackTrace();
        }

        return isReturned;
    }
    //updateing book count
    public void updateBookCount(){
        int bookId=Integer.parseInt(txt_bookid.getText());
        try{
             java.sql.Connection conn=DBConnection.getConnection();
            String sql="update book_details set quantity=quantity + 1 where book_id=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, bookId);
            int rowCount=pst.executeUpdate();
            if(rowCount >0){
                JOptionPane.showMessageDialog(this, "book count updated");
                
            }else{
                JOptionPane.showMessageDialog(this, "Can't update book count");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbiissue = new javax.swing.JTextField();
        lblbook_name = new javax.swing.JTextField();
        lbl_studentName = new javax.swing.JTextField();
        lblerror = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bookinvalid = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblissueDate = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lbldueDate = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        issuebookdetails = new rojeru_san.complementos.RSTableMetro();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_bookid = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_studentid = new javax.swing.JTextField();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 102, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Due Date");

        lbiissue.setBackground(new java.awt.Color(255, 102, 0));
        lbiissue.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbiissue.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbiissue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbiissueActionPerformed(evt);
            }
        });

        lblbook_name.setBackground(new java.awt.Color(255, 102, 0));
        lblbook_name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblbook_name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblbook_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblbook_nameActionPerformed(evt);
            }
        });

        lbl_studentName.setBackground(new java.awt.Color(255, 102, 0));
        lbl_studentName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_studentName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblerror.setBackground(new java.awt.Color(255, 255, 0));
        lblerror.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblerror.setForeground(new java.awt.Color(255, 0, 51));
        lblerror.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Issue id");

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Student Name");

        bookinvalid.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bookinvalid.setForeground(new java.awt.Color(255, 51, 51));

        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 102));
        jLabel18.setText("Book Name");

        lblissueDate.setBackground(new java.awt.Color(255, 102, 0));
        lblissueDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblissueDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setText("Issue Date");

        lbldueDate.setBackground(new java.awt.Color(255, 102, 0));
        lbldueDate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbldueDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbldueDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbldueDateActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 102));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/New folder/Books-2-icon (1).png"))); // NOI18N
        jLabel16.setText("Book Details");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel7))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(lbiissue, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel18))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblbook_name, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(bookinvalid, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel8))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(lbl_studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel5))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(lblissueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jLabel3))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(lbldueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(lblerror, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addGap(1, 1, 1)
                .addComponent(lbiissue, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel18)
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblbook_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookinvalid))
                .addGap(10, 10, 10)
                .addComponent(jLabel8)
                .addGap(1, 1, 1)
                .addComponent(lbl_studentName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel5)
                .addGap(1, 1, 1)
                .addComponent(lblissueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(lbldueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblerror, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 390, 610));

        jPanel4.setBackground(new java.awt.Color(255, 255, 0));

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/New folder/Books-2-icon (1).png"))); // NOI18N
        jLabel13.setText("Student List");

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

        issuebookdetails.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        issuebookdetails.setForeground(new java.awt.Color(255, 255, 0));
        issuebookdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book_id", "Book_Name", "Student_id", "Student_Name"
            }
        ));
        issuebookdetails.setColorBackgoundHead(new java.awt.Color(255, 51, 0));
        issuebookdetails.setColorBordeFilas(new java.awt.Color(0, 0, 102));
        issuebookdetails.setColorBordeHead(new java.awt.Color(255, 51, 0));
        issuebookdetails.setColorFilasBackgound1(new java.awt.Color(255, 255, 0));
        issuebookdetails.setColorFilasBackgound2(new java.awt.Color(153, 204, 255));
        issuebookdetails.setColorForegroundHead(new java.awt.Color(255, 255, 0));
        issuebookdetails.setColorSelForeground(new java.awt.Color(255, 255, 0));
        issuebookdetails.setFont(new java.awt.Font("Verdana", 1, 8)); // NOI18N
        issuebookdetails.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        issuebookdetails.setRowHeight(30);
        issuebookdetails.setSelectionForeground(new java.awt.Color(255, 255, 0));
        issuebookdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                issuebookdetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(issuebookdetails);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 600));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 570));

        jPanel3.setBackground(new java.awt.Color(255, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setBackground(new java.awt.Color(0, 0, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/New folder/Science-Courses-icon (1).png"))); // NOI18N
        jLabel2.setText("Return Books");

        txt_bookid.setBackground(new java.awt.Color(255, 255, 0));
        txt_bookid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_bookid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_bookid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookidFocusLost(evt);
            }
        });
        txt_bookid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookidActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 102));
        jLabel14.setText("Book Id");

        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 102));
        jLabel15.setText("Student Id");

        txt_studentid.setBackground(new java.awt.Color(255, 255, 0));
        txt_studentid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_studentid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_studentid.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentidFocusLost(evt);
            }
        });
        txt_studentid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentidActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(153, 0, 51));
        rSMaterialButtonRectangle1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSMaterialButtonRectangle1.setText("return book");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(0, 0, 102));
        rSMaterialButtonRectangle3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSMaterialButtonRectangle3.setText("Issue Book");
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_bookid, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_studentid, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(86, 86, 86))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_bookid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_studentid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(29, 29, 29)
                .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 0, 370, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentidActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here
         
          if(returnBook()==true){
                 JOptionPane.showMessageDialog(this, "Book Returned Successfully");
                 updateBookCount();
                
           }else
             {
                JOptionPane.showMessageDialog(this, "Book Returned Failed");

             }
    
            
          
       
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        // TODO add your handling code here:
     
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
        // TODO add your handling code here:
    
    }//GEN-LAST:event_txt_studentidFocusLost

    private void lblbook_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblbook_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblbook_nameActionPerformed

    private void lbiissueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbiissueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbiissueActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        HomePage home=new HomePage();
        home.setVisible(true);
         dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
        // TODO add your handling code here:
        
        getIssueBookDetails();
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void lbldueDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbldueDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbldueDateActionPerformed

    private void issuebookdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_issuebookdetailsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_issuebookdetailsMouseClicked

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
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bookinvalid;
    private rojeru_san.complementos.RSTableMetro issuebookdetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private javax.swing.JTextField lbiissue;
    private javax.swing.JTextField lbl_studentName;
    private javax.swing.JTextField lblbook_name;
    private javax.swing.JTextField lbldueDate;
    private javax.swing.JTextField lblerror;
    private javax.swing.JTextField lblissueDate;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle3;
    private javax.swing.JTextField txt_bookid;
    private javax.swing.JTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
