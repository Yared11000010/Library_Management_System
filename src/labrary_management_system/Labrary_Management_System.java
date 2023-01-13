/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labrary_management_system;

import jframe.Login;
import jframe.Progress;

/**
 *
 * @author yared
 */
public class Labrary_Management_System {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Progress log=new Progress();
        log.setVisible(true);
        for(int i=1; i<=100; i++){
          try{
              Thread.sleep(50);
             
              log.ProgressBar.setValue(i);
              if(i%2==0){
                  log.please.setText("Please Wait .....");
              }else{
                  log.please.setText("Please Wait ......");
              }
              if(i==100){
                  log.setVisible(false);
                  Login login=new Login();
                  login.setVisible(true);
              }
          }catch(InterruptedException ex){
              ex.printStackTrace();
          }
        }
    }
    
}
