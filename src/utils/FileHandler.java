/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Admin
 */
public class FileHandler {

    private static JFileChooser fileChooser = new JFileChooser();

    public static void setFileFilter() {
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.txt)", "txt"));
        fileChooser.setAcceptAllFileFilterUsed(true);
    }

    public static File openFileDialog(JFrame frame) {
        File file = null;

        int choice = fileChooser.showOpenDialog(frame);
        if (choice == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }

        return file;
    }
    
    public static boolean saveFile(File file, String fileContent) {
        FileWriter fw;
        PrintWriter pw;

        try {
            if (file != null) {
                fw = new FileWriter(file);
                pw = new PrintWriter(fw);                
                pw.write(fileContent);

                pw.close();
                fw.close();

            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error while saving file!");
            return false;
        }
        return true;
    }
    
    public static String readFile(File f) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        String line;
        StringBuilder fileContent = new StringBuilder();
        while ((line = br.readLine()) != null) {
            fileContent.append(line + "\n");
        }

        fr.close();
        br.close();

        return fileContent.toString();

    }
    
    public static JFileChooser getFileChooser(){
        return fileChooser;
    }
}
