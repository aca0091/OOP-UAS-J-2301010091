/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cruise_agent;

import static cruise_agent.dbkoneksi_agent.koneksi;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Ni Kadek Candra Dewi 
 * 2301010091
 * 27 June 2025
 */
public class form_seaman extends javax.swing.JFrame {

    DefaultTableModel DM = new DefaultTableModel();
    
    public form_seaman() throws SQLException {
        initComponents();
        TM.setModel(DM);
        DM.addColumn("NAMA");
        DM.addColumn("CREW_ID");
        DM.addColumn("EXPERIENCE");
        DM.addColumn("POSITION");
        DM.addColumn("DOKUMENT");
        DM.addColumn("INTERVIEW_PASSED");
        DM.addColumn("EMERGENCY_CONTACT");
        DM.addColumn("REMARK");
        
        
        cleartextField();
        this.ListDataTable();
        tombol(false);
        cBARU.setEnabled(true);
        cUBAH.setEnabled(true);
        cHAPUS.setEnabled(true);
        cTUTUP.setEnabled(true);
        cbPos.setVisible(false);
        cbInter.setVisible(false);
        fieldIsian(false);
        expyesno();
        postcb();
        intercb();
        
    
    }
    private void expyesno(){
        txEXPERIENCE.removeAllItems();
        txEXPERIENCE.addItem("YES");
        txEXPERIENCE.addItem("NO");
    }
    
    private void postcb(){
        cbPos.removeAllItems();
        cbPos.addItem("Waitress");
        cbPos.addItem("Waiter");
        cbPos.addItem("Cabin Steward");
        cbPos.addItem("Hotel Steward");
        cbPos.addItem("Laundry Attd");
        cbPos.addItem("Cook");
        cbPos.addItem("Asst Cook");
        cbPos.addItem("Galley");
        cbPos.addItem("Barista");
        cbPos.addItem("Bartender");
        cbPos.addItem("Bar Server");
        
        
    }
    
    private void intercb(){
        cbInter.removeAllItems();
        cbInter.addItem("English");
        cbInter.addItem("Knowledge");
        cbInter.addItem("Final");
        cbInter.addItem("HireFlix");
        cbInter.addItem("False");
        
        
    }
    
    private void storeDta()throws SQLException{
        if(!txID.getText().equals("")){
        
            Connection cnn = koneksi();
            if(!cnn.isClosed()){
                PreparedStatement PS = cnn.prepareStatement("INSERT INTO seaman(NAMA,CREW_ID,EXPERIENCE,POSITION,DOKUMENT,INTERVIEW_PASSED,EMERGENCY_CONTACT,REMARK) VALUES(?,?,?,?,?,?,?,?);");
                PS.setString(1, txNAMA.getText());
                PS.setString(2, txID.getText());
                PS.setString(3, txEXPERIENCE.getSelectedItem().toString());
                PS.setString(4, txPOSITION.getText());
                PS.setString(5, txDOKUMENT.getText());
                PS.setString(6, txINTERVIEW.getText());
                PS.setString(7, txCONTACT.getText());
                PS.setString(8, txREMARK.getText());
                PS.executeUpdate();
                cnn.close();
            } 
        }
    }
    
    private void updatedta()throws SQLException{
        Connection cnn = koneksi();
        if(!cnn.isClosed()){
            PreparedStatement PS = cnn.prepareStatement("UPDATE seaman SET NAMA=?, EXPERIENCE=?, POSITION=?, DOKUMENT=?, INTERVIEW_PASSED=?, EMERGENCY_CONTACT=?, REMARK=? WHERE  CREW_ID=?;");
            PS.setString(1, txNAMA.getText());
            PS.setString(8, txID.getText());
            PS.setString(2, (String) txEXPERIENCE.getSelectedItem());
            PS.setString(3, txPOSITION.getText());
            PS.setString(4, txDOKUMENT.getText());
            PS.setString(5, txINTERVIEW.getText());
            PS.setString(6, txCONTACT.getText());
            PS.setString(7, txREMARK.getText());
            PS.executeUpdate();
            cnn.close();
        }
    }

    private void destroydta(String CREW_ID) throws SQLException{
        Connection cnn = koneksi();
        if(!cnn.isClosed()){
            PreparedStatement PS = cnn.prepareStatement("DELETE FROM seaman WHERE CREW_ID=?;");
            PS.setString(1, CREW_ID);
            PS.executeUpdate();
            cnn.close();
        }
    }
    
 private void tombol(boolean opsi){
        cBARU.setEnabled(opsi);
        cUBAH.setEnabled(opsi);
        cHAPUS.setEnabled(opsi);
    }
    
    private void fieldIsian(boolean opsi){
        txNAMA.setEnabled(opsi);
        txID.setEnabled(opsi);
        txEXPERIENCE.setEnabled(opsi);
        txPOSITION.setEnabled(opsi);
        txDOKUMENT.setEnabled(opsi);
        txINTERVIEW.setEnabled(opsi);
        txCONTACT.setEnabled(opsi);
        txREMARK.setEnabled(opsi);
        
    }
    private void cleartextField(){
        txNAMA.setText("");
        txID.setText("");
        txEXPERIENCE.setSelectedItem("");
        txPOSITION.setText("");
        txDOKUMENT.setText("");
        txINTERVIEW.setText("");
        txCONTACT.setText("");
        txREMARK.setText("");
    }
    
    private void ListDataTable() throws SQLException{
        Connection cnn = koneksi();
        DM.getDataVector().removeAllElements();
        DM.fireTableDataChanged();
        
        if(!cnn.isClosed()){
            PreparedStatement PS = cnn.prepareStatement("SELECT * FROM seaman;");
            ResultSet RS = PS.executeQuery();
            
            while(RS.next()){
                Object[] dta = new Object[8];
                dta[0] = RS.getString("NAMA");
                dta[1] = RS.getString("CREW_ID");
                dta[2] = RS.getString("EXPERIENCE");
                dta[3] = RS.getString("POSITION");
                dta[4] = RS.getString("DOKUMENT");
                dta[5] = RS.getString("INTERVIEW_PASSED");
                dta[6] = RS.getString("EMERGENCY_CONTACT");
                dta[7] = RS.getString("REMARK");
                
                
                
                DM.addRow(dta);
            }
            cnn.close();
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TM = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txNAMA = new javax.swing.JTextField();
        txDOKUMENT = new javax.swing.JTextField();
        txINTERVIEW = new javax.swing.JTextField();
        txID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txPOSITION = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txCONTACT = new javax.swing.JTextField();
        txREMARK = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cBARU = new javax.swing.JButton();
        cUBAH = new javax.swing.JButton();
        cHAPUS = new javax.swing.JButton();
        cTUTUP = new javax.swing.JButton();
        txEXPERIENCE = new javax.swing.JComboBox<>();
        cbPos = new javax.swing.JComboBox<>();
        cbInter = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel1.setText("DATA SEAMAN");

        TM.setBackground(new java.awt.Color(25, 25, 122));
        TM.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        TM.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        TM.setForeground(new java.awt.Color(224, 255, 255));
        TM.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "NAMA", "CREW_ID", "EXPERIENCE", "POSITION", "DOKUMENT", "INTERVIEW_PASSED", "EMERGENCY_CONTACT", "REMARK"
            }
        ));
        TM.setSelectionBackground(new java.awt.Color(25, 25, 122));
        TM.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TMMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TM);

        jLabel2.setText("Edit View Data");

        jLabel3.setText("NAMA");

        jLabel4.setText("CREW_ID");

        jLabel5.setText("DOKUMENT");

        jLabel6.setText("POSITION");

        txNAMA.setForeground(new java.awt.Color(25, 25, 122));
        txNAMA.setText("jTextField1");

        txDOKUMENT.setText("jTextField1");
        txDOKUMENT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txDOKUMENTActionPerformed(evt);
            }
        });

        txINTERVIEW.setText("jTextField1");
        txINTERVIEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txINTERVIEWActionPerformed(evt);
            }
        });

        txID.setText("jTextField1");
        txID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txIDActionPerformed(evt);
            }
        });

        jLabel7.setText("EXPERIENCE");

        jLabel8.setText("INTERVIEW_PASSED");

        txPOSITION.setText("jTextField1");
        txPOSITION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txPOSITIONActionPerformed(evt);
            }
        });

        jLabel9.setText("EMERGENCY_CONTACT");

        txCONTACT.setText("jTextField1");

        txREMARK.setText("jTextField1");
        txREMARK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txREMARKActionPerformed(evt);
            }
        });

        jLabel10.setText("REMARK");

        cBARU.setBackground(new java.awt.Color(255, 192, 203));
        cBARU.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        cBARU.setText("Baru");
        cBARU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBARUActionPerformed(evt);
            }
        });

        cUBAH.setBackground(new java.awt.Color(255, 192, 203));
        cUBAH.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        cUBAH.setText("Ubah");
        cUBAH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cUBAHActionPerformed(evt);
            }
        });

        cHAPUS.setBackground(new java.awt.Color(255, 192, 203));
        cHAPUS.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        cHAPUS.setText("Hapus");
        cHAPUS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cHAPUSActionPerformed(evt);
            }
        });

        cTUTUP.setBackground(new java.awt.Color(255, 192, 203));
        cTUTUP.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        cTUTUP.setText("Tutup");
        cTUTUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cTUTUPActionPerformed(evt);
            }
        });

        txEXPERIENCE.setForeground(new java.awt.Color(25, 25, 122));
        txEXPERIENCE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        txEXPERIENCE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txEXPERIENCEActionPerformed(evt);
            }
        });

        cbPos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbInter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(416, 620, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cBARU)
                                        .addGap(18, 18, 18)
                                        .addComponent(cUBAH)
                                        .addGap(18, 18, 18)
                                        .addComponent(cHAPUS))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txDOKUMENT, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txCONTACT, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txNAMA, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txEXPERIENCE, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txID, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                                        .addComponent(txPOSITION, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                                        .addComponent(txINTERVIEW, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txREMARK, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cbPos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbInter, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(cTUTUP, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addContainerGap(203, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(270, 270, 270))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txNAMA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txEXPERIENCE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txDOKUMENT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txCONTACT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txPOSITION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txINTERVIEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(cbInter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txREMARK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cTUTUP)
                    .addComponent(cHAPUS)
                    .addComponent(cUBAH)
                    .addComponent(cBARU))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txINTERVIEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txINTERVIEWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txINTERVIEWActionPerformed

    private void txIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txIDActionPerformed

    private void txPOSITIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txPOSITIONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txPOSITIONActionPerformed

    private void txREMARKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txREMARKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txREMARKActionPerformed

    private void txDOKUMENTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txDOKUMENTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txDOKUMENTActionPerformed

    private void cBARUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBARUActionPerformed
        if(cBARU.getText().equals("Baru")){
            cBARU.setText("Simpan");
            cTUTUP.setText("Batal");
            cUBAH.setEnabled(false);
            cHAPUS.setEnabled(false);
            cbPos.setVisible(true);
            txPOSITION.setVisible(false);
            cbInter.setVisible(true);
            txINTERVIEW.setVisible(false);
            cleartextField();
            fieldIsian(true);
        }else{
            cBARU.setText("Baru");
            cTUTUP.setText("Tutup");
            try {
                storeDta();
                ListDataTable();
            } catch (SQLException ex) {
                Logger.getLogger(form_seaman.class.getName()).log(Level.SEVERE, null, ex);
            }
            cleartextField();
            cbPos.setVisible(false);
            txPOSITION.setVisible(true);
            cbInter.setVisible(false);
            txINTERVIEW.setVisible(true);
            fieldIsian(false);
        }
    }//GEN-LAST:event_cBARUActionPerformed

    private void TMMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TMMouseClicked
        txNAMA.setText(TM.getValueAt(TM.getSelectedRow(),0).toString());
        txID.setText(TM.getValueAt(TM.getSelectedRow(),1).toString());
        txEXPERIENCE.setSelectedItem(TM.getValueAt(TM.getSelectedRow(),2).toString());
        txPOSITION.setText(TM.getValueAt(TM.getSelectedRow(),3).toString());
        txDOKUMENT.setText(TM.getValueAt(TM.getSelectedRow(),4).toString());
        txINTERVIEW.setText(TM.getValueAt(TM.getSelectedRow(),5).toString());
        txCONTACT.setText(TM.getValueAt(TM.getSelectedRow(),6).toString());
        txREMARK.setText(TM.getValueAt(TM.getSelectedRow(),7).toString());
        cUBAH.setEnabled(true);
        cHAPUS.setEnabled(true);
    }//GEN-LAST:event_TMMouseClicked

    private void cUBAHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cUBAHActionPerformed
     if(cUBAH.getText().equals("Ubah")){
            cUBAH.setText("Simpan");
            cTUTUP.setText("Batal");
            cBARU.setEnabled(false);
            cHAPUS.setEnabled(false);
            fieldIsian(true);
            txID.setEditable(false);
            cbPos.setVisible(true);
            txPOSITION.setVisible(false);
            cbInter.setVisible(true);
            txINTERVIEW.setVisible(false);
        }else{
            cUBAH.setText("Ubah");
            cTUTUP.setText("Tutup");
            try {
                updatedta();   
                ListDataTable();
            } catch (SQLException ex) {
                Logger.getLogger(form_seaman.class.getName()).log(Level.SEVERE, null, ex);
            }
            cleartextField();
            fieldIsian(false);
            cbInter.setVisible(false);
            txINTERVIEW.setVisible(true);
            cbPos.setVisible(false);
            txPOSITION.setVisible(true);
            cBARU.setEnabled(true);
            cUBAH.setEnabled(false);
        }
    }//GEN-LAST:event_cUBAHActionPerformed

    private void cTUTUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cTUTUPActionPerformed
       if(cTUTUP.getText().equals("Tutup")){
        
            int jwb = JOptionPane.showOptionDialog(this,
                        "Yakin akan menutup aplikasi?",
                        "Konfirmasi Hapus Data Seaman",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE,
                        null, null, null);
            if(jwb == JOptionPane.YES_OPTION){

            System.exit(0);
            }
        }else{
            cTUTUP.setText("Tutup");
            cBARU.setText("Baru");
            cUBAH.setText("Ubah");
            fieldIsian(false);
            cbPos.setVisible(false);
            txPOSITION.setVisible(true);
            cbInter.setVisible(false);
            txINTERVIEW.setVisible(true);
            cBARU.setEnabled(true);
            cUBAH.setEnabled(true);
            cHAPUS.setEnabled(true);
            
        }
    }//GEN-LAST:event_cTUTUPActionPerformed

    private void cHAPUSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cHAPUSActionPerformed
    if(cHAPUS.getText().equals("Hapus")){
             
         }
             String CREW_ID = txID.getText();
             int jwb = JOptionPane.showOptionDialog(this,
                    "Yakin akan menghapus data dengan CREW_ID : " + CREW_ID,
                    "Konfirmasi Hapus Data",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.ERROR_MESSAGE,
                    null, null, null);
             
             if (jwb == JOptionPane.YES_OPTION){
            
                try {
                    destroydta(CREW_ID);
                    ListDataTable();
                } catch (SQLException ex) {
                    Logger.getLogger(form_seaman.class.getName()).log(Level.SEVERE, null, ex);
                }
                cleartextField();
                fieldIsian(false);
                cBARU.setEnabled(true);
                cUBAH.setEnabled(false);
                cHAPUS.setEnabled(false);
        }
    }//GEN-LAST:event_cHAPUSActionPerformed

    private void txEXPERIENCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txEXPERIENCEActionPerformed
        
    }//GEN-LAST:event_txEXPERIENCEActionPerformed

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
            java.util.logging.Logger.getLogger(form_seaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(form_seaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(form_seaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(form_seaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new form_seaman().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(form_seaman.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TM;
    private javax.swing.JButton cBARU;
    private javax.swing.JButton cHAPUS;
    private javax.swing.JButton cTUTUP;
    private javax.swing.JButton cUBAH;
    private javax.swing.JComboBox<String> cbInter;
    private javax.swing.JComboBox<String> cbPos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txCONTACT;
    private javax.swing.JTextField txDOKUMENT;
    private javax.swing.JComboBox<String> txEXPERIENCE;
    private javax.swing.JTextField txID;
    private javax.swing.JTextField txINTERVIEW;
    private javax.swing.JTextField txNAMA;
    private javax.swing.JTextField txPOSITION;
    private javax.swing.JTextField txREMARK;
    // End of variables declaration//GEN-END:variables
}
