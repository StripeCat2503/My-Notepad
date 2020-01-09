package main;

import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Nguyen Khanh Duy
 */
public class ReplaceDialog extends javax.swing.JDialog {

    private MTE editor;
    private ArrayList<Point> searchPositions;
    private int currentResultIndex;

    private boolean replacing = false;

    public ReplaceDialog(MTE editor, boolean modal) {
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
        btnReplace = new javax.swing.JButton();
        btnReplaceAll = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtReplace = new javax.swing.JTextField();

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

        btnReplace.setText("Replace");
        btnReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplaceActionPerformed(evt);
            }
        });

        btnReplaceAll.setText("Replace All");
        btnReplaceAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReplaceAllActionPerformed(evt);
            }
        });

        jLabel2.setText("Replace with");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbIgnoreCase)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addComponent(txtReplace))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFind, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReplace, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReplaceAll, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReplace)
                    .addComponent(jLabel2)
                    .addComponent(txtReplace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReplaceAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel)
                    .addComponent(cbIgnoreCase))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        String searchValue = this.txtSearch.getText();
  
        if(searchValue.isEmpty()){
            return;
        }

        boolean found = found(searchValue);
        if (!found) {
            this.editor.getTxt().setSelectionEnd(0);
            JOptionPane.showMessageDialog(this.editor, "Can not find '" + searchValue + "'");
        } else {

            if(this.editor.getTxt().getSelectedText() == null){
                this.currentResultIndex = 0;
            }
            
            if (this.currentResultIndex >= this.searchPositions.size()) {
                this.currentResultIndex = 0;
            }
            setSelection(this.currentResultIndex);
            this.currentResultIndex++;

        }

    }//GEN-LAST:event_btnFindActionPerformed

    private void setSelection(int index) {
        Point resultPosition = searchPositions.get(index);
        this.editor.getTxt().setSelectionStart(resultPosition.x);
        this.editor.getTxt().setSelectionEnd(resultPosition.y);
    }

    private boolean found(String searchValue) {
        String text = this.editor.getTxt().getText();
        this.searchPositions = getSearchPositions(searchValue, text);

        if (searchValue.isEmpty()) {
            return false;
        }

        if (this.searchPositions.isEmpty()) {
            return false;
        }

        return true;
    }

    private ArrayList<Point> getSearchPositions(String searchValue, String text) {
        ArrayList<Point> searchPositions = new ArrayList<>();
        for (int i = 0; i < text.length() - searchValue.length() + 1; i++) {
            String subText = text.substring(i, searchValue.length() + i);          
            if (!this.cbIgnoreCase.isSelected()) {
                if (searchValue.equals(subText)) {
                    int start = i;
                    int end = i + searchValue.length();
                    searchPositions.add(new Point(start, end));
                }
            } else {
                if (searchValue.equalsIgnoreCase(subText)) {
                    int start = i;
                    int end = i + searchValue.length();
                    searchPositions.add(new Point(start, end));
                }
            }
        }

        return searchPositions;
    }

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplaceActionPerformed
   
        String searchValue = this.txtSearch.getText();
        if(searchValue.isEmpty()){
            return;
        }
        
        boolean found = found(searchValue);
        if (found) {
      
            if (this.editor.getTxt().getSelectedText() == null) {           
                Point resultPosition = searchPositions.get(0);
                this.editor.getTxt().setSelectionStart(resultPosition.x);
                this.editor.getTxt().setSelectionEnd(resultPosition.y);
                return;
            }

            String replaceValue = this.txtReplace.getText();

            int start = this.editor.getTxt().getSelectionStart();
            int end = this.editor.getTxt().getSelectionEnd();

            String oldText = this.editor.getTxt().getText();
            String newText = oldText.substring(0, start) + replaceValue + oldText.substring(end, oldText.length());
            this.editor.getTxt().setText(newText);
            this.searchPositions = getSearchPositions(searchValue, this.editor.getTxt().getText());
            
            if(this.searchPositions.isEmpty()){
                JOptionPane.showMessageDialog(this, "Can not find '" + searchValue + "'");
                return;
            }
            this.editor.getTxt().setSelectionStart(this.searchPositions.get(0).x);
            this.editor.getTxt().setSelectionEnd(this.searchPositions.get(0).y);
            this.editor.getUndoStack().push(oldText);
        
        } else {
            this.editor.getTxt().setSelectionEnd(0);
            JOptionPane.showMessageDialog(this.editor, "Can not find '" + searchValue + "'");
        }

    }//GEN-LAST:event_btnReplaceActionPerformed

    private void btnReplaceAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReplaceAllActionPerformed
        
        String oldText = this.editor.getTxt().getText();
        String searchValue = this.txtSearch.getText();
        boolean found = found(searchValue);
        
        if(found){
            String replaceValue = this.txtReplace.getText();
            String newText = oldText.replace(searchValue, replaceValue);
            if(this.cbIgnoreCase.isSelected()){
                newText = oldText.replaceAll("(?i)" + searchValue, replaceValue);
            }
            
            this.editor.getTxt().setText(newText);
        }
        else{
            JOptionPane.showMessageDialog(this, "Can not find " + searchValue);
        }
      
    }//GEN-LAST:event_btnReplaceAllActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnReplace;
    private javax.swing.JButton btnReplaceAll;
    private javax.swing.JCheckBox cbIgnoreCase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtReplace;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
