/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverymanagement123;

import javax.swing.JPanel;

/**
 *
 * @author coolsasisndu@gmail.com yt.com /techinbox
 */
public class JpanelLoader {
   
   public  void jPanelLoader(JPanel Main,JPanel setPanel){
      Main.removeAll();
     
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(Main);
        Main.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(setPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE)
        );
        System.gc();
    
    }

    void jPanelLoader(JPanel panel_load, homeMain hm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
}