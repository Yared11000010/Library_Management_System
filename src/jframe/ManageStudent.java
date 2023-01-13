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
public class ManageStudent extends javax.swing.JFrame {

    /**
     * Creates new form Managebooks
     */
     String studentName,course,branch;
        int studentid;
        DefaultTableModel model;
    public ManageStudent() {
      
        initComponents();
        setStudentDetailsToTable();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
    }
//to set the book details into the table
   public void setStudentDetailsToTable(){
        try{
             String driver="com.mysql.cj.jdbc.Driver";
            String datbaseurl="jdbc:mysql://localhost:3306/universitylibrary";
            String username="root";
            String password="";
            Class.forName(driver);
           java.sql.Connection conn=DriverManager.getConnection(datbaseurl,username,password);
           Statement st=conn.createStatement();
           ResultSet rs=st.executeQuery("select *from student_details");
           while(rs.next()){
               String StudentId=rs.getString("student_id");
               String StudentName=rs.getString("name");
               String course=rs.getString("course");
               String branch=rs.getString("branch");
               Object[] obj={StudentId,StudentName,course,branch};
                 DefaultTableModel model = (DefaultTableModel)studenttable.getModel();
                 model.addRow(obj);
               
           }
           
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   //
   
public boolean validatelogin(){
        studentid=Integer.parseInt(txt_student_id.getText());
        studentName=txt_student_name.getText();
        course=combo_course1.getSelectedItem().toString();
        branch=combo_branch.getSelectedItem().toString();
   
    if(course.equals(" ")){
        JOptionPane.showMessageDialog(this, "please enter course");
        return false;
    }
    if(studentName.equals("")){
        JOptionPane.showMessageDialog(this, "please enter StudentName");
        return false;
    }
        if(branch.equals("")){
        JOptionPane.showMessageDialog(this, "please enter branch");
        return false;
    }
    return true;
}
    public boolean addStudent(){
        boolean isAdded=false;
        studentid=Integer.parseInt(txt_student_id.getText());
        studentName=txt_student_name.getText();
        course=combo_course1.getSelectedItem().toString();
        branch=combo_branch.getSelectedItem().toString();
        try{
             java.sql.Connection conn=DBConnection.getConnection();
             String sql="insert into student_details values(?,?,?,?)";
             PreparedStatement pst=conn.prepareStatement(sql);
             pst.setInt(1, studentid);
             pst.setString(2,studentName);
             pst.setString(3,course);
             pst.setString(4,branch);
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
    //method to clear table
   public void clearTable(){
        DefaultTableModel model=(DefaultTableModel)studenttable.getModel();
        model.setRowCount(0);
    }
    
   public boolean updateStudent(){
        boolean isUpdated=false;
         studentid=Integer.parseInt(txt_student_id.getText());
        studentName=txt_student_name.getText();
        course=combo_course1.getSelectedItem().toString();
        branch=combo_branch.getSelectedItem().toString();
        try{
                   java.sql.Connection conn=DBConnection.getConnection();
                   String sql="update student_details set name=?,course=?,branch=? where student_id=?";
                   PreparedStatement pst=conn.prepareStatement(sql);
                   pst.setString(1,studentName);
                   pst.setString(2,course);
                   pst.setString(3, branch);
                   pst.setInt(4,studentid);
               
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
     public boolean deletestudent(){
        boolean isDeleted=false;
        studentid=Integer.parseInt(txt_student_id.getText());
        try{
              java.sql.Connection conn=DBConnection.getConnection();
              String sql="delete from student_details where student_id=?";
              PreparedStatement pst=conn.prepareStatement(sql);
              pst.setInt(1,studentid);
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
     
       public boolean checkDuplicateStudent(){
        String id=txt_student_id.getText();
        boolean isExist=false;
        try{
             String driver="com.mysql.cj.jdbc.Driver";
            String datbaseurl="jdbc:mysql://localhost:3306/universitylibrary";
            String username="root";
            String password="";
            Class.forName(driver);
           java.sql.Connection conn=DriverManager.getConnection(datbaseurl,username,password);
          PreparedStatement pst=conn.prepareStatement("select * from student_details where student_id=?");
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
        txt_student_id = new rojerusan.RSMetroTextPlaceHolder();
        jLabel6 = new javax.swing.JLabel();
        txt_student_name = new rojerusan.RSMetroTextPlaceHolder();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle3 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        combo_branch = new javax.swing.JComboBox<>();
        combo_course1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rSMaterialButtonRectangle4 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle5 = new rojerusan.RSMaterialButtonRectangle();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        studenttable = new rojeru_san.complementos.RSTableMetro();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        report = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 51, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_student_id.setBackground(new java.awt.Color(255, 255, 0));
        txt_student_id.setForeground(new java.awt.Color(255, 0, 0));
        txt_student_id.setBorderColor(new java.awt.Color(0, 102, 204));
        txt_student_id.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txt_student_id.setPlaceholder("Enter Student ID.....");
        txt_student_id.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_student_idFocusLost(evt);
            }
        });
        txt_student_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_student_idActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 51));
        jLabel6.setText("Student ID");

        txt_student_name.setBackground(new java.awt.Color(255, 255, 0));
        txt_student_name.setForeground(new java.awt.Color(255, 0, 0));
        txt_student_name.setBorderColor(new java.awt.Color(0, 102, 204));
        txt_student_name.setPlaceholder("Enter Student Name....");
        txt_student_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_student_nameFocusLost(evt);
            }
        });
        txt_student_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_student_nameActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 51));
        jLabel7.setText("Student Name");

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 51));
        jLabel8.setText("Course ");

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 51));
        jLabel9.setText("Branch");

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(0, 102, 51));
        rSMaterialButtonRectangle1.setText("Update");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle3.setBackground(new java.awt.Color(0, 51, 0));
        rSMaterialButtonRectangle3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSMaterialButtonRectangle3.setText("Save");
        rSMaterialButtonRectangle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle3ActionPerformed(evt);
            }
        });

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(153, 0, 0));
        rSMaterialButtonRectangle2.setText("Delete");
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N

        combo_branch.setBackground(new java.awt.Color(255, 255, 0));
        combo_branch.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        combo_branch.setForeground(new java.awt.Color(0, 0, 0));
        combo_branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Main Campus(Adama)", "Holota Campus", "Bale Robe Campus", "Fiche Campus", "Muketuri Campus", "mexico Campus" }));
        combo_branch.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        combo_branch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_branchActionPerformed(evt);
            }
        });

        combo_course1.setBackground(new java.awt.Color(255, 255, 0));
        combo_course1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        combo_course1.setForeground(new java.awt.Color(0, 0, 0));
        combo_course1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "C++", "Java", "Networking", "Database", "Opreating Syste." }));
        combo_course1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        combo_course1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_course1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 51));
        jLabel13.setText("Student Registration");

        jLabel14.setBackground(new java.awt.Color(0, 102, 102));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/back (1).png"))); // NOI18N
        jLabel14.setText("Back");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
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

        rSMaterialButtonRectangle5.setBackground(new java.awt.Color(0, 51, 0));
        rSMaterialButtonRectangle5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        rSMaterialButtonRectangle5.setText("Clear");
        rSMaterialButtonRectangle5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rSMaterialButtonRectangle5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(141, 141, 141))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(combo_branch, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combo_course1, javax.swing.GroupLayout.Alignment.LEADING, 0, 308, Short.MAX_VALUE)
                            .addComponent(txt_student_name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_student_id, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addGap(14, 14, 14)
                .addComponent(jLabel13)
                .addGap(16, 16, 16)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_student_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txt_student_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(combo_course1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_branch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rSMaterialButtonRectangle3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rSMaterialButtonRectangle2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSMaterialButtonRectangle5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSMaterialButtonRectangle4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(70, 70, 70))))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 410, 570));

        jPanel2.setBackground(new java.awt.Color(255, 255, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        studenttable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        studenttable.setForeground(new java.awt.Color(255, 0, 0));
        studenttable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "StudentID", "Student_name", "Course", "Branch"
            }
        ));
        studenttable.setColorBackgoundHead(new java.awt.Color(255, 0, 0));
        studenttable.setColorBordeFilas(new java.awt.Color(0, 102, 204));
        studenttable.setColorBordeHead(new java.awt.Color(255, 51, 0));
        studenttable.setColorFilasBackgound1(new java.awt.Color(255, 255, 0));
        studenttable.setColorForegroundHead(new java.awt.Color(255, 255, 0));
        studenttable.setColorSelForeground(new java.awt.Color(0, 153, 153));
        studenttable.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        studenttable.setFuenteHead(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        studenttable.setRowHeight(30);
        studenttable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                studenttableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(studenttable);
        if (studenttable.getColumnModel().getColumnCount() > 0) {
            studenttable.getColumnModel().getColumn(0).setResizable(false);
            studenttable.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 740, 460));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 102));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/icons8_Account_50px.png"))); // NOI18N
        jLabel11.setText("Manage Students");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 264, 50));

        jLabel12.setBackground(new java.awt.Color(255, 0, 51));
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 200, 10));

        report.setBackground(new java.awt.Color(204, 204, 0));
        report.setForeground(new java.awt.Color(0, 0, 0));
        report.setText("report");
        report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportActionPerformed(evt);
            }
        });
        jPanel2.add(report, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, 133, 60));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(402, 3, 780, 570));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void studenttableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_studenttableMouseClicked
        // TODO add your handling code here:
        int rowNo=studenttable.getSelectedRow();
        TableModel model=studenttable.getModel();
        txt_student_id.setText(model.getValueAt(rowNo,0).toString());
        txt_student_name.setText(model.getValueAt(rowNo,1).toString());
        combo_course1.setSelectedItem(model.getValueAt(rowNo,2).toString());
        combo_branch.setSelectedItem(model.getValueAt(rowNo,3).toString());
    }//GEN-LAST:event_studenttableMouseClicked

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
        if(deletestudent()==true){
            JOptionPane.showMessageDialog(this,"Student Deleted !!");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Student Deletion failed !!");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle3ActionPerformed
        // TODO add your handling code here:
//rSMaterialButtonRectangle1.setEnabled(false);
if(validatelogin()){
    if(checkDuplicateStudent()==false){
        if(addStudent()==true){
              rSMaterialButtonRectangle1.setEnabled(true);
        rSMaterialButtonRectangle2.setEnabled(true);
     
            JOptionPane.showMessageDialog(this,"Student is  Added !!");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Student addition failed !!");
        }
    }
}
    }//GEN-LAST:event_rSMaterialButtonRectangle3ActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
        if(updateStudent()==true){
            
            JOptionPane.showMessageDialog(this,"Student Updated !!");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Studnet Updated failed !!");
        }
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    private void txt_student_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_student_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_student_nameActionPerformed

    private void txt_student_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_student_nameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_student_nameFocusLost

    private void txt_student_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_student_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_student_idActionPerformed

    private void txt_student_idFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_student_idFocusLost
        // TODO add your handling code here:
            if(checkDuplicateStudent()==true){
            JOptionPane.showMessageDialog(this, "Student Id already exist");
        }
    }//GEN-LAST:event_txt_student_idFocusLost

    private void combo_branchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_branchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_branchActionPerformed

    private void combo_course1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_course1ActionPerformed
        // TODO add your handling code here:
        try{
             java.sql.Connection conn=DBConnection.getConnection();
           Statement stm=conn.createStatement();
           ResultSet rs=stm.executeQuery("select book_name from book_details ");
           while(rs.next()){
               String name=rs.getString("book_name");
               combo_course1.addItem(name);
           }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_combo_course1ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        HomePage home=new HomePage();
        home.setVisible(true);
         dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void rSMaterialButtonRectangle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle4ActionPerformed
        // TODO add your handling code here:
        rSMaterialButtonRectangle1.setEnabled(false);
        rSMaterialButtonRectangle2.setEnabled(false);
        rSMaterialButtonRectangle3.setEnabled(true);
        txt_student_id.setText("");
        txt_student_name.setText("");
        combo_course1.setSelectedItem("");
        combo_branch.setSelectedItem("");
     
    }//GEN-LAST:event_rSMaterialButtonRectangle4ActionPerformed

    private void reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportActionPerformed
        // TODO add your handling code here:

            MessageFormat header=new MessageFormat(" Harmbee University Labirary MS");
        MessageFormat footer=new MessageFormat("Page {0, number, integer }");
        try {
            studenttable.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (java.awt.print.PrinterException ex) {
            System.err.format("Cannot print ",ex.getMessage());
        }
    }//GEN-LAST:event_reportActionPerformed

    private void rSMaterialButtonRectangle5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle5ActionPerformed
        // TODO add your handling code here:
        txt_student_id.setText("");
        txt_student_name.setText("");
        combo_course1.setSelectedItem("");
        combo_branch.setSelectedItem("");
    }//GEN-LAST:event_rSMaterialButtonRectangle5ActionPerformed

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
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_branch;
    private javax.swing.JComboBox<String> combo_course1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle5;
    private rojerusan.RSMaterialButtonRectangle report;
    private rojeru_san.complementos.RSTableMetro studenttable;
    private rojerusan.RSMetroTextPlaceHolder txt_student_id;
    private rojerusan.RSMetroTextPlaceHolder txt_student_name;
    // End of variables declaration//GEN-END:variables
}
