/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import com.sun.jdi.connect.spi.Connection;
import java.awt.Image;
import java.sql.PreparedStatement;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JFrame;

import javax.swing.JOptionPane;

/**
 *
 * @author yared
 */
public class Signup extends javax.swing.JFrame  {

    /**
     */
    public Signup() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);

    }
    public void insertSignupDetails(){
        String name=txt_username.getText();
        String pwd=txt_password.getText();
        String email=text_email.getText();
        String contact=txt_contact.getText();
        String ques=questionn.getSelectedItem().toString();
        String ans=answer.getText();
        
        try{
          java.sql.Connection conn=DBConnection.getConnection();
         String sql="insert into users(name,password,Sec_Q,Answer,email,contact) values(?,?,?,?,?,?)";
         PreparedStatement pst;
         pst=conn.prepareStatement(sql);
         pst.setString(1,name);
         pst.setString(2,pwd);
         pst.setString(3, ques);
         pst.setString(4, ans);
         pst.setString(5,email);
         pst.setString(6,contact);
        int updateRowCount= pst.executeUpdate();
         
        if(updateRowCount >0){
            JOptionPane.showMessageDialog(this, "Recorded inserted Successfully !!");
            Login log=new Login();
            log.setVisible(true);
            this.setVisible(false);
        }else{
            JOptionPane.showMessageDialog(this, "Recorded  insertion failure !!");
        }
        }catch(Exception e){
            
        }
        
    }
   
    
    //validation
    public boolean validatesingnup(){
        String name=txt_username.getText();
        String pwd=txt_password.getText();
        String email=text_email.getText();
        String contact=txt_contact.getText();
       if(name.equals("")||!name.matches("[a-zA-Z]+")){
           JOptionPane.showMessageDialog(this, "Please Enter Valid Username !!");
           return false;
       }
        if(pwd.equals("")){
           JOptionPane.showMessageDialog(this, "Please Enter Password !!");
           return false;
       }
         if(email.equals("")|| !email.matches("^.+@.+\\..+$")){
           JOptionPane.showMessageDialog(this, "Please Enter Email !!");
           return false;
       }
          if(contact.equals("")||!contact.matches("[0-9]+")){
           JOptionPane.showMessageDialog(this, "Please Enter Your Valid Contact Number !!");
           return false;
       }
         
        return true;
        
        
        
    }
    
    public boolean checkDuplicateUser(){
        String name=txt_username.getText();
        boolean isExist=false;
        try{
             String driver="com.mysql.cj.jdbc.Driver";
            String datbaseurl="jdbc:mysql://localhost:3306/universitylibrary";
            String username="root";
            String password="";
            Class.forName(driver);
           java.sql.Connection conn=DriverManager.getConnection(datbaseurl,username,password);
          PreparedStatement pst=conn.prepareStatement("select * from users where name=?");
          pst.setString(1, name);
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

        materialShadowCircle1 = new efectos.MaterialShadowCircle();
        rSButtonMetroBeanInfo1 = new rojerusan.RSButtonMetroBeanInfo();
        materialShadow1 = new efectos.MaterialShadow();
        rSMaterialButtonRectangleBeanInfo1 = new rojerusan.RSMaterialButtonRectangleBeanInfo();
        customUI1 = new necesario.CustomUI();
        rSMetroTextPlaceHolderBeanInfo1 = new rojerusan.RSMetroTextPlaceHolderBeanInfo();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        rSMaterialButtonRectangle2 = new rojerusan.RSMaterialButtonRectangle();
        text_email = new rojerusan.RSMetroTextPlaceHolder();
        txt_password = new rojerusan.RSMetroTextPlaceHolder();
        txt_username = new rojerusan.RSMetroTextPlaceHolder();
        txt_contact = new rojerusan.RSMetroTextPlaceHolder();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        answer = new rojerusan.RSMetroTextPlaceHolder();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        questionn = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 51, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("        SignUp");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 230, 37));

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 102));
        jLabel6.setText("User_Name");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 109, 25));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 102));
        jLabel7.setText("Email");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 109, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Phone_Number");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 109, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 102));
        jLabel9.setText("Password");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 109, -1));

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(255, 204, 0));
        rSMaterialButtonRectangle1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        rSMaterialButtonRectangle1.setText("SignUp");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonRectangle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 290, 50));

        rSMaterialButtonRectangle2.setBackground(new java.awt.Color(0, 51, 102));
        rSMaterialButtonRectangle2.setText("Login");
        rSMaterialButtonRectangle2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSMaterialButtonRectangle2MouseClicked(evt);
            }
        });
        rSMaterialButtonRectangle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle2ActionPerformed(evt);
            }
        });
        jPanel2.add(rSMaterialButtonRectangle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 600, 290, 50));

        text_email.setBackground(new java.awt.Color(255, 102, 0));
        text_email.setForeground(new java.awt.Color(0, 0, 102));
        text_email.setBorderColor(new java.awt.Color(255, 0, 51));
        text_email.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(text_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 280, 50));

        txt_password.setBackground(new java.awt.Color(255, 102, 0));
        txt_password.setForeground(new java.awt.Color(0, 0, 102));
        txt_password.setBorderColor(new java.awt.Color(255, 0, 51));
        txt_password.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 280, 50));

        txt_username.setBackground(new java.awt.Color(255, 102, 0));
        txt_username.setForeground(new java.awt.Color(0, 0, 102));
        txt_username.setBorderColor(new java.awt.Color(255, 51, 0));
        txt_username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_usernameFocusLost(evt);
            }
        });
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        jPanel2.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 280, 50));

        txt_contact.setBackground(new java.awt.Color(255, 102, 0));
        txt_contact.setForeground(new java.awt.Color(0, 0, 102));
        txt_contact.setBorderColor(new java.awt.Color(255, 0, 51));
        txt_contact.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(txt_contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 280, 50));
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 375, 44, -1));
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 227, 44, -1));
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(7, 148, 50, -1));
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 639, 44, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Variable", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Create New Account");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 149, 20));
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, -1, -1));

        answer.setBackground(new java.awt.Color(255, 102, 0));
        answer.setForeground(new java.awt.Color(0, 0, 102));
        answer.setBorderColor(new java.awt.Color(255, 0, 51));
        answer.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(answer, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 280, 50));

        jLabel16.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 102));
        jLabel16.setText("Answer");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 109, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 102));
        jLabel17.setText("Security Questions");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 150, -1));

        questionn.setBackground(new java.awt.Color(255, 102, 0));
        questionn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        questionn.setForeground(new java.awt.Color(0, 0, 102));
        questionn.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What city were you born in?", "What is your mother name?", "What is your nick name?", "Whati is your hobbies?", " " }));
        questionn.setBorder(null);
        jPanel2.add(questionn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 280, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, -10, 410, 690));

        jPanel3.setBackground(new java.awt.Color(255, 255, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Digital Library Management System");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 270, 36));

        jLabel4.setFont(new java.awt.Font("Segoe UI Variable", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 102));
        jLabel4.setText("Welcome To Harambee Universtiy");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 310, 36));

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/New folder/icons8-online-registration-66.png"))); // NOI18N
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 70, 90));
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons/logo-resized.png"))); // NOI18N
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 190, 170));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 680));

        setSize(new java.awt.Dimension(780, 688));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_usernameActionPerformed

    private void txt_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_usernameFocusLost
        // TODO add your handling code here:
        if(checkDuplicateUser()==true){
            JOptionPane.showMessageDialog(this, "username already exist");
        }

    }//GEN-LAST:event_txt_usernameFocusLost

    private void rSMaterialButtonRectangle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonRectangle2ActionPerformed

    private void rSMaterialButtonRectangle2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle2MouseClicked
        // TODO add your handling code here:
        Login login=new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_rSMaterialButtonRectangle2MouseClicked

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        // TODO add your handling code here:
        if(validatesingnup()==true){
            if(checkDuplicateUser()==false){
                insertSignupDetails();
            }else{
                JOptionPane.showMessageDialog(this, "username already exist ");
            }

        }

    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {
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
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMetroTextPlaceHolder answer;
    private necesario.CustomUI customUI1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private efectos.MaterialShadow materialShadow1;
    private efectos.MaterialShadowCircle materialShadowCircle1;
    private javax.swing.JComboBox<String> questionn;
    private rojerusan.RSButtonMetroBeanInfo rSButtonMetroBeanInfo1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle2;
    private rojerusan.RSMaterialButtonRectangleBeanInfo rSMaterialButtonRectangleBeanInfo1;
    private rojerusan.RSMetroTextPlaceHolderBeanInfo rSMetroTextPlaceHolderBeanInfo1;
    private rojerusan.RSMetroTextPlaceHolder text_email;
    private rojerusan.RSMetroTextPlaceHolder txt_contact;
    private rojerusan.RSMetroTextPlaceHolder txt_password;
    private rojerusan.RSMetroTextPlaceHolder txt_username;
    // End of variables declaration//GEN-END:variables
}