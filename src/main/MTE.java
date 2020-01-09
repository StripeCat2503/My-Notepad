package main;

import com.ozten.font.JFontChooser;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import utils.FileHandler;

/**
 *
 * @author Nguyen Khanh Duy
 */
public class MTE extends javax.swing.JFrame implements Runnable {

    private final long STACK_DELAY = 500; // in ms
    private final String FONT_FILE = "font.txt";

    private String buffer;
    private boolean cutting;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    private Thread stackThread;

    private File openingFile;

    private String oldText = "";

    public MTE() {
        initComponents();
        setTitle("Untitled - My Text Editor");
        setLocationRelativeTo(null);

        try {
            Font initFont = getInitialFont();
            this.txt.setFont(initFont);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to load font!");
        }

        this.buffer = "";
        this.cutting = false;
        this.txt.setLineWrap(true);

        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();

        FileHandler.setFileFilter();

        this.stackThread = new Thread(this, "Auto Stack Thread");
        this.stackThread.start();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txt = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuNew = new javax.swing.JMenuItem();
        menuOpen = new javax.swing.JMenuItem();
        menuSave = new javax.swing.JMenuItem();
        menuSaveAs = new javax.swing.JMenuItem();
        menuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuSelectAll = new javax.swing.JMenuItem();
        menuCut = new javax.swing.JMenuItem();
        menuCopy = new javax.swing.JMenuItem();
        menuPaste = new javax.swing.JMenuItem();
        menuUndo = new javax.swing.JMenuItem();
        menuRedo = new javax.swing.JMenuItem();
        menuFind = new javax.swing.JMenuItem();
        menuReplace = new javax.swing.JMenuItem();
        menuChangeFont = new javax.swing.JMenuItem();
        menuItemWordWrap = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        txt.setColumns(20);
        txt.setRows(5);
        jScrollPane1.setViewportView(txt);

        jMenu1.setText("File");

        menuNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menuNew.setText("New");
        menuNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNewActionPerformed(evt);
            }
        });
        jMenu1.add(menuNew);

        menuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuOpen.setText("Open");
        menuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenActionPerformed(evt);
            }
        });
        jMenu1.add(menuOpen);

        menuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSave.setText("Save");
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        jMenu1.add(menuSave);

        menuSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuSaveAs.setText("Save as...");
        menuSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveAsActionPerformed(evt);
            }
        });
        jMenu1.add(menuSaveAs);

        menuExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        menuExit.setText("Exit");
        menuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExitActionPerformed(evt);
            }
        });
        jMenu1.add(menuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        menuSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        menuSelectAll.setText("Select All");
        menuSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSelectAllActionPerformed(evt);
            }
        });
        jMenu2.add(menuSelectAll);

        menuCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        menuCut.setText("Cut");
        menuCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCutActionPerformed(evt);
            }
        });
        jMenu2.add(menuCut);

        menuCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menuCopy.setText("Copy");
        menuCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCopyActionPerformed(evt);
            }
        });
        jMenu2.add(menuCopy);

        menuPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        menuPaste.setText("Paste");
        menuPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPasteActionPerformed(evt);
            }
        });
        jMenu2.add(menuPaste);

        menuUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        menuUndo.setText("Undo");
        menuUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuUndoActionPerformed(evt);
            }
        });
        jMenu2.add(menuUndo);

        menuRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        menuRedo.setText("Redo");
        menuRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuRedoActionPerformed(evt);
            }
        });
        jMenu2.add(menuRedo);

        menuFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        menuFind.setText("Find");
        menuFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFindActionPerformed(evt);
            }
        });
        jMenu2.add(menuFind);

        menuReplace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuReplace.setText("Replace");
        menuReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuReplaceActionPerformed(evt);
            }
        });
        jMenu2.add(menuReplace);

        menuChangeFont.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        menuChangeFont.setText("Change font");
        menuChangeFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChangeFontActionPerformed(evt);
            }
        });
        jMenu2.add(menuChangeFont);

        menuItemWordWrap.setSelected(true);
        menuItemWordWrap.setText("Word Wrap");
        menuItemWordWrap.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                menuItemWordWrapStateChanged(evt);
            }
        });
        jMenu2.add(menuItemWordWrap);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveFont(Font font) throws IOException {
        File file = new File(FONT_FILE);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        String fontDetails = "";
        fontDetails = font.getFontName() + "-" + font.getStyle() + "-" + font.getSize();

        pw.write(fontDetails);

        pw.close();

    }

    private Font getInitialFont() throws IOException {
        File file = new File(FONT_FILE);
        if (!file.exists()) {
            Font initFont = new Font("monospaced", Font.PLAIN, 12);
            saveFont(initFont);
            return initFont;
        }

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();

        StringTokenizer stk = new StringTokenizer(line, "-");
        String fontName = stk.nextToken();
        int fontStyle = Integer.parseInt(stk.nextToken());
        int fontSize = Integer.parseInt(stk.nextToken());

        Font font = new Font(fontName, fontStyle, fontSize);
        return font;

    }

    private void menuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenActionPerformed

        if (!this.oldText.equals(this.txt.getText())) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to save file?");
            if (choice == JOptionPane.OK_OPTION) {
                save();
            }
        }

        this.openingFile = FileHandler.openFileDialog(this);

        if (this.openingFile != null) {

            try {
                String fileContent = FileHandler.readFile(this.openingFile);
                if (fileContent != null) {
                    this.setTitle(this.openingFile.getName() + " - My Text Editor");
                    this.txt.setText(fileContent);
                    this.oldText = this.txt.getText();
                }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "File not found!");
                this.openingFile = null;
            }

            this.undoStack.clear();
            this.redoStack.clear();
            this.buffer = "";

        }
    }//GEN-LAST:event_menuOpenActionPerformed


    private void menuNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuNewActionPerformed
        newFile();
    }//GEN-LAST:event_menuNewActionPerformed

    private void newFile() {

        if (!this.txt.getText().equals(this.oldText)) {

            int choice = JOptionPane.showConfirmDialog(this, "Do you want to save file?");
            if (choice == JOptionPane.OK_OPTION) {
                save();
            }
        }

        this.txt.setText("");
        this.setTitle("Untitled - My Text Editor");
        this.openingFile = null;

        this.undoStack.clear();
        this.redoStack.clear();
        this.buffer = "";
        this.oldText = this.txt.getText();
    }

    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        save();

    }//GEN-LAST:event_menuSaveActionPerformed

    private boolean save() {
        File file = null;
        boolean canSave = true;

        if (this.openingFile != null) {
            file = this.openingFile;
        } else {
            File f = null;
            do {
                int choice = FileHandler.getFileChooser().showSaveDialog(this);
                if (choice == JFileChooser.APPROVE_OPTION) {

                    f = FileHandler.getFileChooser().getSelectedFile();

                    if (f.exists()) {
                        int replaceChoice = JOptionPane.showConfirmDialog(this, "This file has already existed!\n"
                                + "Do you want to replace it?");
                        if (replaceChoice == JOptionPane.OK_OPTION) {
                            break;
                        }
                    }

                    if (!f.getName().endsWith(".txt")) {
                        file = new File(f.getAbsolutePath() + ".txt");
                    } else {
                        file = new File(f.getAbsolutePath());
                    }

                } else {
                    canSave = false;
                    break;
                }
            } while (f.exists());

        }

        if (!canSave || file == null) {
            return false;
        }

        boolean success = FileHandler.saveFile(file, this.txt.getText());
        if (success) {
            this.oldText = this.txt.getText();
            this.openingFile = file;
            this.setTitle(this.openingFile.getName() + " - My Text Editor");
            return true;
        } else {
            return false;
        }
    }

    private void saveAs() throws IOException {
        File f = null;
        boolean canSave = true;
        String dir = "";
        do {
            int choice = FileHandler.getFileChooser().showSaveDialog(this);
            if (choice == JFileChooser.APPROVE_OPTION) {
                f = FileHandler.getFileChooser().getSelectedFile();
                if (f.exists()) {
                    int replaceChoice = JOptionPane.showConfirmDialog(this, "This file has already existed!\n"
                            + "Do you want to replace it?");
                    if (replaceChoice == JOptionPane.OK_OPTION) {
                        break;
                    }
                }

            } else {
                canSave = false;
                break;
            }
        } while (f.exists());

        if (f == null || !canSave) {
            return;
        }

        dir = f.getAbsolutePath();
        if (!dir.endsWith(".txt")) {
            dir += ".txt";
        }
        File newFile = new File(dir);

        FileHandler.saveFile(newFile, this.txt.getText());

        this.oldText = this.txt.getText();
        this.openingFile = newFile;
        this.setTitle(this.openingFile.getName() + " - My Text Editor");

    }

    private void menuSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveAsActionPerformed
        try {
            saveAs();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error while saving file!");
        }
    }//GEN-LAST:event_menuSaveAsActionPerformed

    private void menuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExitActionPerformed
        if (!this.oldText.equals(this.txt.getText())) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to save file?");
            if (choice == JOptionPane.OK_OPTION) {
                if (this.openingFile != null) {
                    FileHandler.saveFile(openingFile, this.txt.getText());
                } else {
                    int fileChoice = FileHandler.getFileChooser().showSaveDialog(this);
                    if (fileChoice == JFileChooser.APPROVE_OPTION) {
                        File f = FileHandler.getFileChooser().getSelectedFile();
                        String dir = f.getAbsolutePath();
                        if (!dir.endsWith(".txt")) {
                            dir += ".txt";
                        }
                        File fileToSave = new File(dir);
                        FileHandler.saveFile(fileToSave, this.txt.getText());
                    }
                }
            }

        }

        System.exit(0);
    }//GEN-LAST:event_menuExitActionPerformed

    private void menuSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSelectAllActionPerformed
        this.txt.selectAll();
    }//GEN-LAST:event_menuSelectAllActionPerformed

    private void menuCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCutActionPerformed
        this.undoStack.push(this.txt.getText());
        this.txt.cut();
    }//GEN-LAST:event_menuCutActionPerformed

    private void menuCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCopyActionPerformed

        this.txt.copy();
    }//GEN-LAST:event_menuCopyActionPerformed

    private void menuPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPasteActionPerformed
        this.undoStack.push(this.txt.getText());
        this.txt.paste();

    }//GEN-LAST:event_menuPasteActionPerformed

    private void menuUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuUndoActionPerformed
        undo();
    }//GEN-LAST:event_menuUndoActionPerformed

    private void menuRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuRedoActionPerformed
        redo();
    }//GEN-LAST:event_menuRedoActionPerformed

    private void menuFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFindActionPerformed
        FindDialog dialog = new FindDialog(this, true);
    }//GEN-LAST:event_menuFindActionPerformed

    private void menuReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuReplaceActionPerformed
        ReplaceDialog dialog = new ReplaceDialog(this, true);
    }//GEN-LAST:event_menuReplaceActionPerformed

    private void menuChangeFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChangeFontActionPerformed

        Font initialFont = this.txt.getFont();
        Font font = JFontChooser.showDialog(this, "Choose text font", "This is a text", initialFont);

        if (font != null) {
            try {
                saveFont(font);
                this.txt.setFont(font);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to save font!");
            }

        }
    }//GEN-LAST:event_menuChangeFontActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (!this.oldText.equals(this.txt.getText())) {
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to save file?");
            if (choice == JOptionPane.OK_OPTION) {
                if (this.openingFile != null) {
                    FileHandler.saveFile(openingFile, this.txt.getText());
                } else {
                    int fileChoice = FileHandler.getFileChooser().showSaveDialog(this);
                    if (fileChoice == JFileChooser.APPROVE_OPTION) {
                        File f = FileHandler.getFileChooser().getSelectedFile();
                        String dir = f.getAbsolutePath();
                        if (!dir.endsWith(".txt")) {
                            dir += ".txt";
                        }
                        File fileToSave = new File(dir);
                        FileHandler.saveFile(fileToSave, this.txt.getText());
                    }
                }
            }

        }

    }//GEN-LAST:event_formWindowClosing

    private void menuItemWordWrapStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_menuItemWordWrapStateChanged
        if (menuItemWordWrap.isSelected()) {
            this.txt.setLineWrap(true);
        } else {
            this.txt.setLineWrap(false);
        }
    }//GEN-LAST:event_menuItemWordWrapStateChanged

    public JTextArea getTxt() {
        return txt;
    }

    public Stack<String> getUndoStack() {
        return undoStack;
    }

    private void undo() {
        if (this.undoStack.isEmpty()) {
            return;
        }

        String earlierText = this.undoStack.pop();
        this.redoStack.push(this.txt.getText());
        this.txt.setText(earlierText);

    }

    private void redo() {
        if (this.redoStack.isEmpty()) {
            return;
        }

        String laterText = this.redoStack.pop();
        this.txt.setText(laterText);
        this.undoStack.push(laterText);
    }

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
            java.util.logging.Logger.getLogger(MTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MTE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuChangeFont;
    private javax.swing.JMenuItem menuCopy;
    private javax.swing.JMenuItem menuCut;
    private javax.swing.JMenuItem menuExit;
    private javax.swing.JMenuItem menuFind;
    private javax.swing.JCheckBoxMenuItem menuItemWordWrap;
    private javax.swing.JMenuItem menuNew;
    private javax.swing.JMenuItem menuOpen;
    private javax.swing.JMenuItem menuPaste;
    private javax.swing.JMenuItem menuRedo;
    private javax.swing.JMenuItem menuReplace;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JMenuItem menuSaveAs;
    private javax.swing.JMenuItem menuSelectAll;
    private javax.swing.JMenuItem menuUndo;
    private javax.swing.JTextArea txt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        if (this.txt.getText().isEmpty()) {
            this.undoStack.push("");
        }
        while (true) {

            String prevText = this.txt.getText();
            while (prevText.equals(this.txt.getText())) {
                // wait until there're some changes in text
            }

            if (!this.txt.getText().isEmpty()) {
                this.undoStack.push(this.txt.getText());
            }

            try {
                Thread.sleep(STACK_DELAY);
            } catch (InterruptedException ex) {
                System.err.println("Error when sleeping thread!");
            }

        }
    }
}
