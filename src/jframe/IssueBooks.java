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
public class IssueBooks extends javax.swing.JFrame {

    /**
     * Creates new form IssueBooks
     */
    public IssueBooks() {
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
           ResultSet rs=st.executeQuery("select *from book_details");
           while(rs.next()){
               String id=rs.getString("book_id");
               String bookName=rs.getString("book_name");
               String Studentid=rs.getString("quantity");
     
               Object[] obj={id,bookName,Studentid};
                 DefaultTableModel model = (DefaultTableModel)issuebookdetails.getModel();
                 model.addRow(obj);
               
           }
           
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //to fetch the book details from the database and display it to book details panel
    public void getBookDetails(){
        int bookID=Integer.parseInt(txt_bookid.getText());
        try{
             java.sql.Connection conn=DBConnection.getConnection();
             
             PreparedStatement pst=conn.prepareStatement("select *from book_details where book_id =?");
             pst.setInt(1, bookID);
             ResultSet rs=pst.executeQuery();
             if(rs.next()){
                 lblbook_id.setText(rs.getString("book_id"));
                 lblbook_name.setText(rs.getString("book_name"));
                 lblauthor.setText(rs.getString("author"));
                 lblquantity.setText(rs.getString("quantity"));
                 
             }else{
                 bookinvalid.setText("invalid book id !!!");
             }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
      //to fetch the Students details from the database and display it to Student details panel
    public void getStudentDetails(){
        int studentId=Integer.parseInt(txt_studentid.getText());
        try{
             java.sql.Connection conn=DBConnection.getConnection();
             
             PreparedStatement pst=conn.prepareStatement("select *from student_details where student_id =?");
             pst.setInt(1, studentId);
             ResultSet rs=pst.executeQuery();
             if(rs.next()){
                 lblstudent_id.setText(rs.getString("student_id"));
                 lblname.setText(rs.getString("name"));
                 lblcourse.setText(rs.getString("course"));
                 lblbranch.setText(rs.getString("branch"));
                 
             }else{
                 studentinvalid.setText("invalid student id !!!");
             }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public boolean issueBook(){
        boolean isIssued=false;
        int bookId=Integer.parseInt(txt_bookid.getText());
        int studentId=Integer.parseInt(txt_studentid.getText());
        String bookName=lblbook_name.getText();
        String studentName=lblname.getText();
        
        
        Date uIssueDate=Date_issueDate.getDatoFecha();
        Date uDueDate=Date_dueDate.getDatoFecha();
         if(txt_bookid.getText().isEmpty()){
        JOptionPane.showMessageDialog(this, "please enter uIssueDate");
        return false;
       }
    if(uDueDate.equals("")){
        JOptionPane.showMessageDialog(this, "please enter uDueDate");
        return false;
    }
         Long l1=uIssueDate.getTime();
        long l2=uDueDate.getTime();
        java.sql.Date sIssueDate=new java.sql.Date(l1);
        java.sql.Date sDueDate=new java.sql.Date(l2);
        try{
            java.sql.Connection conn=DBConnection.getConnection();
            String sql="insert into issue_book_details(book_id,book_name,student_id,student_name,"
                    + "issue_date,due_date,status) values(?,?,?,?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,bookId);
            pst.setString(2,bookName);
            pst.setInt(3,studentId);
            pst.setString(4,studentName);
            pst.setDate(5,sIssueDate);
            pst.setDate(6, sDueDate);
            pst.setString(7, "pending");
            
            int rowCount=pst.executeUpdate();
            if(rowCount >0){
                isIssued=true;
            }else
            {
                isIssued=false;
            }
        }catch(Exception e){
            e.printStackTrace();
    }
        return isIssued;
    }
   
    //updateing book count
    public void updateBookCount(){
        int bookId=Integer.parseInt(lblbook_id.getText());
        try{
             java.sql.Connection conn=DBConnection.getConnection();
            String sql="update book_details set quantity=quantity -1 where book_id=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, bookId);
            int rowCount=pst.executeUpdate();
            if(rowCount >0){
                JOptionPane.showMessageDialog(this, "book count updated");
                int initialCount=Integer.parseInt(lblquantity.getText());
                lblquantity.setText(Integer.toString(initialCount -1));
            }else{
                JOptionPane.showMessageDialog(this, "Can't update book count");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //checking wether book already allocated or not
    public boolean isAlreadyIssued(){
        boolean isAlreadyIssued =false;
        int bookId=Integer.parseInt(txt_bookid.getText());
        int studentId=Integer.parseInt(txt_studentid.getText());
        try{
                   java.sql.Connection conn=DBConnection.getConnection();
        String sql="select *from issue_book_details  where book_id=? and student_id=? and status=? ";
       PreparedStatement pst=conn.prepareStatement(sql);
       pst.setInt(1,bookId);
       pst.setInt(2, studentId);
       pst.setString(3, "pending");
       ResultSet rs=pst.executeQuery();
       if(rs.next()){
           isAlreadyIssued=true;
       }else{
           isAlreadyIssued=false;
       }
       
        }catch(Exception e){
            e.printStackTrace();
        }
        return isAlreadyIssued;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        txt_bookid = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_studentid = new javax.swing.JTextField();
        Date_dueDate = new rojeru_san.componentes.RSDateChooser();
        Date_issueDate = new rojeru_san.componentes.RSDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblbook_id = new javax.swing.JTextField();
        lblbook_name = new javax.swing.JTextField();
        lblauthor = new javax.swing.JTextField();
        lblquantity = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        bookinvalid = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblstudent_id = new javax.swing.JTextField();
        lblname = new javax.swing.JTextField();
        lblcourse = new javax.swing.JTextField();
        lblbranch = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        studentinvalid = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        issuebookdetails = new rojeru_san.complementos.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 51, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel3.add(txt_bookid, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 170, 251, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 51));
        jLabel14.setText("Book Id");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 191, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 51));
        jLabel15.setText("Student Id");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 249, -1, -1));

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
        jPanel3.add(txt_studentid, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 228, 251, 40));

        Date_dueDate.setBackground(new java.awt.Color(255, 255, 0));
        Date_dueDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Date_dueDate.setColorBackground(new java.awt.Color(255, 255, 0));
        Date_dueDate.setColorSelForeground(new java.awt.Color(0, 102, 204));
        Date_dueDate.setColorTextDiaActual(new java.awt.Color(0, 102, 204));
        jPanel3.add(Date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 345, 251, -1));

        Date_issueDate.setBackground(new java.awt.Color(255, 255, 0));
        Date_issueDate.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Date_issueDate.setColorBackground(new java.awt.Color(255, 255, 0));
        Date_issueDate.setColorSelForeground(new java.awt.Color(0, 102, 204));
        Date_issueDate.setColorTextDiaActual(new java.awt.Color(255, 153, 0));
        jPanel3.add(Date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 287, 251, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 51));
        jLabel16.setText("Due Date");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 366, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 51));
        jLabel17.setText("Issue Date");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 308, -1, -1));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(0, 0, 102));
        rSMaterialButtonRectangle1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSMaterialButtonRectangle1.setText("Issue Book");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel3.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 251, 50));

        jPanel5.setBackground(new java.awt.Color(255, 51, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/New folder/Books-2-icon (1).png"))); // NOI18N
        jLabel2.setText("Issues Books");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 210, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 0, 430, 570));

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Quantity");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, -1, -1));

        lblbook_id.setBackground(new java.awt.Color(255, 153, 51));
        lblbook_id.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblbook_id.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblbook_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblbook_idActionPerformed(evt);
            }
        });
        jPanel2.add(lblbook_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 190, 40));

        lblbook_name.setBackground(new java.awt.Color(255, 153, 51));
        lblbook_name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblbook_name.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblbook_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblbook_nameActionPerformed(evt);
            }
        });
        jPanel2.add(lblbook_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, 190, 40));

        lblauthor.setBackground(new java.awt.Color(255, 153, 51));
        lblauthor.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblauthor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(lblauthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 110, 210, 40));

        lblquantity.setBackground(new java.awt.Color(255, 153, 51));
        lblquantity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblquantity.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.add(lblquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, 210, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Book Id");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Author");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, -1, -1));

        bookinvalid.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bookinvalid.setForeground(new java.awt.Color(255, 51, 51));
        jPanel2.add(bookinvalid, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 170, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 102));
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel13.setText("Book Details");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 211, 60));

        jLabel18.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 102));
        jLabel18.setText("Book Name");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 0, 530, 270));

        jPanel4.setBackground(new java.awt.Color(255, 255, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel4.setForeground(new java.awt.Color(0, 102, 204));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 102));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jLabel5.setText("Student Details");

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("Branch");

        lblstudent_id.setBackground(new java.awt.Color(255, 153, 51));
        lblstudent_id.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblstudent_id.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblstudent_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblstudent_idActionPerformed(evt);
            }
        });

        lblname.setBackground(new java.awt.Color(255, 153, 51));
        lblname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblname.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblcourse.setBackground(new java.awt.Color(255, 153, 51));
        lblcourse.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblcourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblbranch.setBackground(new java.awt.Color(255, 153, 51));
        lblbranch.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblbranch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 102));
        jLabel10.setText("Student Id");

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setText("Course");

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 102));
        jLabel12.setText("Student Name");

        studentinvalid.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        studentinvalid.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jLabel10)
                .addGap(122, 122, 122)
                .addComponent(jLabel11))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(lblstudent_id, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblcourse, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel12)
                .addGap(94, 94, 94)
                .addComponent(jLabel6))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(studentinvalid, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblname, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(lblbranch, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblstudent_id, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcourse, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel6))
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(studentinvalid))
                    .addComponent(lblname, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblbranch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 530, 300));

        jPanel1.setBackground(new java.awt.Color(255, 255, 0));

        issuebookdetails.setBackground(new java.awt.Color(255, 255, 0));
        issuebookdetails.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        issuebookdetails.setForeground(new java.awt.Color(255, 255, 0));
        issuebookdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book_id", "Book_Name", "Quantity"
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblbook_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblbook_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblbook_idActionPerformed

    private void lblbook_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblbook_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblbook_nameActionPerformed

    private void txt_bookidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookidActionPerformed

    private void txt_studentidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentidActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
        if(lblquantity.getText().equals("0")){
            JOptionPane.showMessageDialog(this, "book is not availbale ");
        }else{
             if(isAlreadyIssued()==false){
        if(issueBook()==true){
            JOptionPane.showMessageDialog(this, " book issue succesfully !!");
            updateBookCount();
        }else
        {
            JOptionPane.showMessageDialog(this, "cant't issues books");
        }
        }else{
            JOptionPane.showMessageDialog(this, "this student already has this book");
        }
        }
        
       
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void txt_bookidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookidFocusLost
        // TODO add your handling code here:
        if(!txt_bookid.getText().equals("")){
        getBookDetails();
    }
    }//GEN-LAST:event_txt_bookidFocusLost

    private void txt_studentidFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentidFocusLost
        // TODO add your handling code here:
        if(!txt_studentid.getText().equals(""));{
        getStudentDetails();
    }
    }//GEN-LAST:event_txt_studentidFocusLost

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        HomePage home=new HomePage();
        home.setVisible(true);
         dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void lblstudent_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblstudent_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lblstudent_idActionPerformed

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
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBooks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBooks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser Date_dueDate;
    private rojeru_san.componentes.RSDateChooser Date_issueDate;
    private javax.swing.JLabel bookinvalid;
    private rojeru_san.complementos.RSTableMetro issuebookdetails;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lblauthor;
    private javax.swing.JTextField lblbook_id;
    private javax.swing.JTextField lblbook_name;
    private javax.swing.JTextField lblbranch;
    private javax.swing.JTextField lblcourse;
    private javax.swing.JTextField lblname;
    private javax.swing.JTextField lblquantity;
    private javax.swing.JTextField lblstudent_id;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private javax.swing.JLabel studentinvalid;
    private javax.swing.JTextField txt_bookid;
    private javax.swing.JTextField txt_studentid;
    // End of variables declaration//GEN-END:variables
}
