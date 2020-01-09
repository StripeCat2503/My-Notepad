
package main;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Khanh Duy
 */
public class FindDialog extends javax.swing.JDialog {

    private MTE editor;
    private ArrayList<Point> searchPositions;
    private int currentResultIndex;

    public FindDialog(MTE editor, boolean modal) {
        super(editor, modal);
        initComponents();
        this.editor = editor;
        this.searchPositions = new ArrayList<>();
        this.currentResultIndex = 0;
        this.setLocationRelativeTo(this.editor);
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnFind = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        cbIgnoreCase = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Find text");

        btnFind.setText("Find");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        cbIgnoreCase.setText("Ignore case");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFind)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbIgnoreCase))
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFind)
                    .addComponent(btnCancel)
                    .addComponent(cbIgnoreCase))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed

        if (!this.searchPositions.isEmpty()) {
            this.searchPositions.clear();
        }

        String searchValue = this.txtSearch.getText();
        if (searchValue.isEmpty()) {
            return;
        }

        String text = this.editor.getTxt().getText();
      
        for (int i = 0; i < text.length() - searchValue.length() + 1; i++) {
            String subText = text.substring(i, searchValue.length() + i);
            if (!this.cbIgnoreCase.isSelected()) {
                if (searchValue.equals(subText)) {
                    int start = i;
                    int end = i + searchValue.length();
                    this.searchPositions.add(new Point(start, end));
                }
            }
            else{
                if (searchValue.equalsIgnoreCase(subText)) {
                    int start = i;
                    int end = i + searchValue.length();
                    this.searchPositions.add(new Point(start, end));
                }
            }
        }

        if (this.searchPositions.isEmpty()) {
            this.editor.getTxt().setSelectionEnd(0);
            JOptionPane.showMessageDialog(this.editor, "Can not find '" + searchValue + "'");
            return;
        }

        if (this.currentResultIndex >= this.searchPositions.size()) {
            this.currentResultIndex = 0;
        }
        Point resultPosition = searchPositions.get(this.currentResultIndex++);
        this.editor.getTxt().setSelectionStart(resultPosition.x);
        this.editor.getTxt().setSelectionEnd(resultPosition.y);

    }//GEN-LAST:event_btnFindActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFind;
    private javax.swing.JCheckBox cbIgnoreCase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
