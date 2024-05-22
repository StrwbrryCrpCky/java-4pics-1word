import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//school project of Gunda, Kabigting, Parcon, Sarmiento, and Vargas
//DONT YOU REMOVE THIS TEXT (it's our signature)

public class JavaGame extends JFrame implements ActionListener{

    public int leveltxt = 0;
    public int bacount = 0;
    public static int thint = 0;
    public String path, intxt, guesspath, pathanswer, pathguess;
    public boolean fiCRyc_DmiA;
    public Graphics g;
    public Timer t = new Timer();
    
    JButton b1, bclear, benter, bhintdel, bh2p; //game buttons
    JButton ba1, ba2, ba3, ba4, ba5, ba6, ba7, ba8, ba9, ba10, ba11, ba12; //letter buttons
    ImageCanvas imgcnv = new ImageCanvas();
    JTextField inputField;
    JLabel blvlcnt;
    
    public JavaGame(){
        JFrame f = new JFrame("4 Pics, 1 Word (On a Budget)");

        inputField = new JTextField(null);
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setBounds(25, 550, 500,60);
        inputField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 45));
        inputField.setEditable(false);
        inputField.setCaretColor(inputField.getBackground());

        b1 = new JButton("Start");
        b1.setBounds(385, 625, 80, 30);
        bclear = new JButton("Clear 🗑️");
        bclear.setBounds(385, 665, 80, 30);
        benter = new JButton("Enter ↩");
        benter.setBounds(385, 705, 80, 30);
        bhintdel = new JButton("💣");
        bhintdel.setBounds(475, 625, 50, 50);

        blvlcnt = new JLabel("", SwingConstants.CENTER);
        blvlcnt.setBounds(475, 685, 50, 50);
        blvlcnt.setForeground(Color.WHITE);

        ba1 = new JButton();
        ba1.setBounds(25, 625, 50, 50);        
        ba2 = new JButton();
        ba2.setBounds(85, 625, 50, 50); 
        ba3 = new JButton();
        ba3.setBounds(145, 625, 50, 50);        
        ba4 = new JButton();
        ba4.setBounds(205, 625, 50, 50); 
        ba5 = new JButton();
        ba5.setBounds(265, 625, 50, 50);        
        ba6 = new JButton();
        ba6.setBounds(325, 625, 50, 50); 
        ba7 = new JButton();
        ba7.setBounds(25, 685, 50, 50);        
        ba8 = new JButton();
        ba8.setBounds(85, 685, 50, 50);
        ba9 = new JButton();
        ba9.setBounds(145, 685, 50, 50);        
        ba10 = new JButton();
        ba10.setBounds(205,685, 50, 50); 
        ba11 = new JButton();
        ba11.setBounds(265, 685, 50, 50);        
        ba12 = new JButton();
        ba12.setBounds(325, 685, 50, 50);
        
        JButton[] bttns = {ba1, ba2, ba3, ba4, ba5, ba6, ba7, ba8, ba9, ba10, ba11, ba12};
        for(int i = 0; i < bttns.length; i++){
            bttns[i].setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        }

        blvlcnt.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));

        ba1.setFocusPainted(false);
        ba2.setFocusPainted(false);
        ba3.setFocusPainted(false);
        ba4.setFocusPainted(false);
        ba5.setFocusPainted(false);
        ba6.setFocusPainted(false);
        ba7.setFocusPainted(false);
        ba8.setFocusPainted(false);
        ba9.setFocusPainted(false);
        ba10.setFocusPainted(false);
        ba11.setFocusPainted(false);
        ba12.setFocusPainted(false);
        bclear.setFocusPainted(false);
        b1.setFocusPainted(false);
        benter.setFocusPainted(false);
        bhintdel.setFocusPainted(false);

        ba1.setVisible(false);
        ba2.setVisible(false);
        ba3.setVisible(false);
        ba4.setVisible(false);
        ba5.setVisible(false);
        ba6.setVisible(false);
        ba7.setVisible(false);
        ba8.setVisible(false);
        ba9.setVisible(false);
        ba10.setVisible(false);
        ba11.setVisible(false);
        ba12.setVisible(false);

        bclear.setEnabled(false);
        benter.setEnabled(false);
        bhintdel.setEnabled(false);

        b1.addActionListener(this);
        bclear.addActionListener(this);
        benter.addActionListener(this);
        bhintdel.addActionListener(this);
        ba1.addActionListener(this);
        ba2.addActionListener(this);
        ba3.addActionListener(this);
        ba4.addActionListener(this);
        ba5.addActionListener(this);
        ba6.addActionListener(this);
        ba7.addActionListener(this);
        ba8.addActionListener(this);
        ba9.addActionListener(this);
        ba10.addActionListener(this);
        ba11.addActionListener(this);
        ba12.addActionListener(this);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(564, 800);
        f.setLayout(null);
        f.getContentPane().setBackground(new java.awt.Color(25, 38, 87));
        f.setVisible(true);
        f.setResizable(false);

        f.add(imgcnv);
        f.add(b1); f.add(bclear); f.add(benter); f.add(ba1);f.add(ba2); f.add(ba3); f.add(ba4); f.add(ba5);f.add(ba6);f.add(ba7); f.add(ba8);f.add(ba9);f.add(ba10); f.add(ba11); f.add(ba12); f.add(inputField); f.add(bhintdel); f.add(blvlcnt);
    }

    public void windowDeiconified(WindowEvent e){
        imgcnv.repaint();
    }
    
    public static boolean filesCompareByByte(String pathanswer, String pathguess) throws IOException {
        
        File fileObj1 = new File(pathanswer);
        FileReader fReader1 = null;
        BufferedReader bReader1 = null;
        File fileObj2 = new File(pathguess);
        FileReader fReader2 = null;
        BufferedReader bReader2 = null;
        try {
            fReader1 = new FileReader(fileObj1);
            bReader1 = new BufferedReader(fReader1);
            fReader2 = new FileReader(fileObj2);
            bReader2 = new BufferedReader(fReader2);
            int ch1 = bReader1.read();
            int ch2 = bReader2.read();
            while (ch1 != -1) {
                char charV1 = (char) ch1;
                char charV2 = (char) ch2;
                if (charV1 != charV2) {
                    
                    return false;
                }
                ch1 = bReader1.read();
                ch2 = bReader2.read();
            }

            while (ch2 != -1) {
                char charV1 = (char) ch1;
                char charV2 = (char) ch2;
                if (charV2 != charV1) {
                    
                    return false;
                }
                ch1 = bReader1.read();
                ch2 = bReader2.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try{
                bReader1.close();
                bReader2.close();
                fReader1.close();
                fReader2.close();
            }catch(IIOException e1){
                e1.getStackTrace();
            }
            
        }
        return true;
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == b1){
            b1.setText("Next ▶");
            
            leveltxt++;

            File pathfol = new File("./data/" + leveltxt);

            boolean c4CTNaDfAtA = pathfol.exists();

            if(c4CTNaDfAtA == true){
                String lvltxt = String.valueOf(leveltxt);
                blvlcnt.setText(lvltxt);

                inputField.setForeground(Color.BLACK);

                ba1.setText(null);
                ba2.setText(null);
                ba3.setText(null);
                ba4.setText(null);
                ba5.setText(null);
                ba6.setText(null);
                ba7.setText(null);
                ba8.setText(null);
                ba9.setText(null);
                ba10.setText(null);
                ba11.setText(null);
                ba12.setText(null);
                

                thint = 0;

                ba1.setVisible(true);
                ba2.setVisible(true);
                ba3.setVisible(true);
                ba4.setVisible(true);
                ba5.setVisible(true);
                ba6.setVisible(true);
                ba7.setVisible(true);
                ba8.setVisible(true);
                ba9.setVisible(true);
                ba10.setVisible(true);
                ba11.setVisible(true);
                ba12.setVisible(true);
                blvlcnt.setVisible(true);

                ba1.setEnabled(true);
                ba2.setEnabled(true);
                ba3.setEnabled(true);
                ba4.setEnabled(true);
                ba5.setEnabled(true);
                ba6.setEnabled(true);
                ba7.setEnabled(true);
                ba8.setEnabled(true);
                ba9.setEnabled(true);
                ba10.setEnabled(true);
                ba11.setEnabled(true);
                ba12.setEnabled(true);
                bclear.setEnabled(true);
                benter.setEnabled(true);
                bhintdel.setEnabled(true);
                b1.setEnabled(false);

                bhintdel.setToolTipText("Deletes 3 non-answer");

                inputField.setText(null);

                bacount = 1;

                imgcnv.update(imgcnv.getGraphics());

                path = "./data/"+leveltxt+"/letters.txt";

                File fileObj = null;
                FileReader fReader = null;
                BufferedReader bReader = null;

                fileObj = new File(path);
                if(!fileObj.exists()){
                    JFrame w = new JFrame();
                    JOptionPane.showMessageDialog(
                    w,
                    "ERROR\nFiles are not found\nPlease check your data folder",
                    "ERROR",
                    JOptionPane.ERROR_MESSAGE
                    );
                }else{
                    try{
                        fReader = new FileReader(fileObj);
                        bReader = new BufferedReader(fReader);
                        int charNo = bReader.read();
                        
                        while(charNo != -1){
                            char CharC = (char) charNo;
                            String CharV = String.valueOf(CharC);

                                if(bacount == 1){
                                    ba1.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 2){
                                    ba2.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 3){
                                    ba3.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 4){
                                    ba4.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 5){
                                    ba5.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 6){
                                    ba6.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 7){
                                    ba7.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 8){
                                    ba8.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 9){
                                    ba9.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 10){
                                    ba10.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 11){
                                    ba11.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }else if(bacount == 12){
                                    ba12.setText(CharV);
        
                                    bacount++;
                                    charNo = bReader.read();
                                }
                            }
                    }
                    catch(Exception e1){
                        JFrame w = new JFrame();
                        JOptionPane.showMessageDialog(
                        w,
                        "ERROR\nError Opening",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE
                        );  
                    }finally{
                        try{
                            bReader.close();
                            fReader.close();
                        }catch(IOException e6){
                            e6.getStackTrace();
                        }
                        
                    }

                    
                }
            }
            else{
                imgcnv.update(imgcnv.getGraphics());

                ba1.setVisible(true);
                ba2.setVisible(true);
                ba3.setVisible(true);
                ba4.setVisible(true);
                ba5.setVisible(true);
                ba6.setVisible(true);
                ba7.setVisible(true);
                ba8.setVisible(true);
                ba9.setVisible(true);
                ba10.setVisible(true);
                ba11.setVisible(true);
                ba12.setVisible(true);
                blvlcnt.setVisible(false);

                b1.setEnabled(true);
                ba1.setEnabled(false);
                ba2.setEnabled(false);
                ba3.setEnabled(false);
                ba4.setEnabled(false);
                ba5.setEnabled(false);
                ba6.setEnabled(false);
                ba7.setEnabled(false);
                ba8.setEnabled(false);
                ba9.setEnabled(false);
                ba10.setEnabled(false);
                ba11.setEnabled(false);
                ba12.setEnabled(false);
                bclear.setEnabled(false);
                benter.setEnabled(false);
                bhintdel.setEnabled(false);
                b1.setText("Again?");
                ba1.setText("T");
                ba2.setText("H");
                ba3.setText("X");
                ba4.setText("F");
                ba5.setText("O");
                ba6.setText("R");
                ba7.setText("P");
                ba8.setText("L");
                ba9.setText("A");
                ba10.setText("Y");
                ba11.setText("I");
                ba12.setText("N");

                inputField.setText("Congrats! You won!");

                leveltxt = 0;

                imgcnv.level = 0;
                
                
            }
        }
        
        if(e.getSource() == bclear){
            if(thint == 1){
                ba1.setEnabled(true);
                ba2.setEnabled(true);
                ba3.setEnabled(true);
                ba4.setEnabled(true);
                ba5.setEnabled(true);
                ba6.setEnabled(true);
                ba7.setEnabled(true);
                ba8.setEnabled(true);
                ba9.setEnabled(true);
                ba10.setEnabled(true);
                ba11.setEnabled(true);
                ba12.setEnabled(true);
                bclear.setEnabled(true);
                benter.setEnabled(true);
                bhintdel.setEnabled(false);
                inputField.setText(null);
            }else{
                ba1.setEnabled(true);
                ba2.setEnabled(true);
                ba3.setEnabled(true);
                ba4.setEnabled(true);
                ba5.setEnabled(true);
                ba6.setEnabled(true);
                ba7.setEnabled(true);
                ba8.setEnabled(true);
                ba9.setEnabled(true);
                ba10.setEnabled(true);
                ba11.setEnabled(true);
                ba12.setEnabled(true);
                bclear.setEnabled(true);
                benter.setEnabled(true);
                bhintdel.setEnabled(true);
                inputField.setText(null);
            }
        }
        
        if(e.getSource() == benter){
            
            intxt = inputField.getText();
            guesspath = "./data/"+leveltxt+"/guess.txt";

            try{
                FileWriter fWriter = new FileWriter(guesspath);
                BufferedWriter bWriter = new BufferedWriter(fWriter);
                bWriter.write(intxt);
                bWriter.close();
            }catch(Exception e3){
                e3.getStackTrace();
            }

            pathanswer = "./data/"+leveltxt+"/answer.txt";
            pathguess = "./data/"+leveltxt+"/guess.txt";

            try{
                fiCRyc_DmiA = filesCompareByByte(pathanswer, pathguess);
            }catch(IOException e4){
                e4.getStackTrace();
            }

            if(intxt.isBlank()){
                ba1.setEnabled(false);
                ba2.setEnabled(false);
                ba3.setEnabled(false);
                ba4.setEnabled(false);
                ba5.setEnabled(false);
                ba6.setEnabled(false);
                ba7.setEnabled(false);
                ba8.setEnabled(false);
                ba9.setEnabled(false);
                ba10.setEnabled(false);
                ba11.setEnabled(false);
                ba12.setEnabled(false);
                bclear.setEnabled(false);
                benter.setEnabled(false);
                bhintdel.setEnabled(false);
                inputField.setForeground(Color.RED);
                inputField.setText("No text found");
                t.schedule(new TimerTask() {
                    @Override
                    public void run(){
                        if(thint == 1){
                            ba1.setEnabled(true);
                            ba2.setEnabled(true);
                            ba3.setEnabled(true);
                            ba4.setEnabled(true);
                            ba5.setEnabled(true);
                            ba6.setEnabled(true);
                            ba7.setEnabled(true);
                            ba8.setEnabled(true);
                            ba9.setEnabled(true);
                            ba10.setEnabled(true);
                            ba11.setEnabled(true);
                            ba12.setEnabled(true);
                            bclear.setEnabled(true);
                            benter.setEnabled(true);
                            bhintdel.setEnabled(false);
                            inputField.setForeground(Color.BLACK);
                            inputField.setText(null);
                        }else{
                            ba1.setEnabled(true);
                            ba2.setEnabled(true);
                            ba3.setEnabled(true);
                            ba4.setEnabled(true);
                            ba5.setEnabled(true);
                            ba6.setEnabled(true);
                            ba7.setEnabled(true);
                            ba8.setEnabled(true);
                            ba9.setEnabled(true);
                            ba10.setEnabled(true);
                            ba11.setEnabled(true);
                            ba12.setEnabled(true);
                            bclear.setEnabled(true);
                            benter.setEnabled(true);
                            bhintdel.setEnabled(true);
                            inputField.setForeground(Color.BLACK);
                            inputField.setText(null);
                        }
                    }
                }, 2000);
            }else if(fiCRyc_DmiA == true){
                b1.setEnabled(true);
                ba1.setVisible(false);
                ba2.setVisible(false);
                ba3.setVisible(false);
                ba4.setVisible(false);
                ba5.setVisible(false);
                ba6.setVisible(false);
                ba7.setVisible(false);
                ba8.setVisible(false);
                ba9.setVisible(false);
                ba10.setVisible(false);
                ba11.setVisible(false);
                ba12.setVisible(false);
                bclear.setEnabled(false);
                benter.setEnabled(false);
                bhintdel.setEnabled(false);
                inputField.setForeground(new java.awt.Color(0, 102, 0));
            }else{
                ba1.setEnabled(false);
                ba2.setEnabled(false);
                ba3.setEnabled(false);
                ba4.setEnabled(false);
                ba5.setEnabled(false);
                ba6.setEnabled(false);
                ba7.setEnabled(false);
                ba8.setEnabled(false);
                ba9.setEnabled(false);
                ba10.setEnabled(false);
                ba11.setEnabled(false);
                ba12.setEnabled(false);
                bclear.setEnabled(false);
                benter.setEnabled(false);
                bhintdel.setEnabled(false);
                inputField.setForeground(Color.RED);
                t.schedule(new TimerTask() {
                    @Override
                    public void run(){
                        if(thint == 1){
                            ba1.setEnabled(true);
                            ba2.setEnabled(true);
                            ba3.setEnabled(true);
                            ba4.setEnabled(true);
                            ba5.setEnabled(true);
                            ba6.setEnabled(true);
                            ba7.setEnabled(true);
                            ba8.setEnabled(true);
                            ba9.setEnabled(true);
                            ba10.setEnabled(true);
                            ba11.setEnabled(true);
                            ba12.setEnabled(true);
                            bclear.setEnabled(true);
                            benter.setEnabled(true);
                            bhintdel.setEnabled(false);
                            inputField.setForeground(Color.BLACK);
                            inputField.setText(null);
                        }else{
                            ba1.setEnabled(true);
                            ba2.setEnabled(true);
                            ba3.setEnabled(true);
                            ba4.setEnabled(true);
                            ba5.setEnabled(true);
                            ba6.setEnabled(true);
                            ba7.setEnabled(true);
                            ba8.setEnabled(true);
                            ba9.setEnabled(true);
                            ba10.setEnabled(true);
                            ba11.setEnabled(true);
                            ba12.setEnabled(true);
                            bclear.setEnabled(true);
                            benter.setEnabled(true);
                            bhintdel.setEnabled(true);
                            inputField.setForeground(Color.BLACK);
                            inputField.setText(null);
                        }
                    }
                }, 2000);
            }
            
            
        }

        if(e.getSource() == bhintdel){
            try{
                if(leveltxt == 0){
                
                }else{
                    thint++;
                    String arrcntnt = null;

                    Path path = Path.of("./data/"+leveltxt+"/hintdel.txt");
                    try{
                        arrcntnt = Files.readString(path);
                    }catch(Exception e5){
                        e5.getStackTrace();
                    }

                    String[] bStr = arrcntnt.split(",");

                    Random random = new Random();
                    Set<Integer> uniqueNumbers = new HashSet<>();   
                    int length = bStr.length;

                    while (uniqueNumbers.size() < 3) {
                        int randomNumber = random.nextInt(length);
                        uniqueNumbers.add(randomNumber);
                    }

                    int[] delint = uniqueNumbers.stream().mapToInt(Integer::intValue).toArray();

                    for(int i = 0; i < delint.length; i++){
                        String varS = bStr[delint[i]];
                        invisVar(varS);
                    }

                    ba1.setEnabled(true);
                    ba2.setEnabled(true);
                    ba3.setEnabled(true);
                    ba4.setEnabled(true);
                    ba5.setEnabled(true);
                    ba6.setEnabled(true);
                    ba7.setEnabled(true);
                    ba8.setEnabled(true);
                    ba9.setEnabled(true);
                    ba10.setEnabled(true);
                    ba11.setEnabled(true);
                    ba12.setEnabled(true);
                    inputField.setText(null);

                    bhintdel.setEnabled(false);
                    bhintdel.setToolTipText(null);
                }
            }catch(Exception e7){
                JFrame w = new JFrame();
                JOptionPane.showMessageDialog(
                w,
                "ERROR\nFiles are not found\nPlease check your data folder",
                "ERROR",
                JOptionPane.ERROR_MESSAGE
                );
            }
        }

        //letter buttons
        if(e.getSource() == ba1){
            String letter = ba1.getText();
            inputField.setText(inputField.getText() + letter);
            ba1.setEnabled(false);
        }
        
        if(e.getSource() == ba2){
            String letter = ba2.getText();
            inputField.setText(inputField.getText() + letter);
            ba2.setEnabled(false);
        }

        if(e.getSource() == ba3){
            String letter = ba3.getText();
            inputField.setText(inputField.getText() + letter);
            ba3.setEnabled(false);
        }
        
        if(e.getSource() == ba4){
            String letter = ba4.getText();
            inputField.setText(inputField.getText() + letter);
            ba4.setEnabled(false);
        }

        if(e.getSource() == ba5){
            String letter = ba5.getText();
            inputField.setText(inputField.getText() + letter);
            ba5.setEnabled(false);
        }

        if(e.getSource() == ba6){
            String letter = ba6.getText();
            inputField.setText(inputField.getText() + letter);
            ba6.setEnabled(false);
        }        
        
        if(e.getSource() == ba7){
            String letter = ba7.getText();
            inputField.setText(inputField.getText() + letter);
            ba7.setEnabled(false);
        }

        if(e.getSource() == ba8){
            String letter = ba8.getText();
            inputField.setText(inputField.getText() + letter);
            ba8.setEnabled(false);
        }

        if(e.getSource() == ba9){
            String letter = ba9.getText();
            inputField.setText(inputField.getText() + letter);
            ba9.setEnabled(false);
        }
        
        if(e.getSource() == ba10){
            String letter = ba10.getText();
            inputField.setText(inputField.getText() + letter);
            ba10.setEnabled(false);
        }
        
        if(e.getSource() == ba11){
            String letter = ba11.getText();
            inputField.setText(inputField.getText() + letter);
            ba11.setEnabled(false);
        }

        if(e.getSource() == ba12){
            String letter = ba12.getText();
            inputField.setText(inputField.getText() + letter);
            ba12.setEnabled(false);
        }

    }

    public void invisVar(String varS){
        if(varS.equals("ba1")) {
            ba1.setVisible(false);
        } else if(varS.equals("ba2")) {
            ba2.setVisible(false);
        } else if(varS.equals("ba3")) {
            ba3.setVisible(false);
        } else if(varS.equals("ba4")) {
            ba4.setVisible(false);
        } else if(varS.equals("ba5")) {
            ba5.setVisible(false);
        } else if(varS.equals("ba6")) {
            ba6.setVisible(false);
        } else if(varS.equals("ba7")) {
            ba7.setVisible(false);
        } else if(varS.equals("ba8")) {
            ba8.setVisible(false);
        } else if(varS.equals("ba9")) {
            ba9.setVisible(false);
        } else if(varS.equals("ba10")) {
            ba10.setVisible(false);
        } else if(varS.equals("ba11")) {
            ba11.setVisible(false);
        } else if(varS.equals("ba12")) {
            ba12.setVisible(false);
        }
    }
    public static void main(String[] args) {
        new JavaGame();
    }
}

class ImageCanvas extends Canvas{

    public int level = 0;
    public Graphics m;
    public File pathfol;
    public boolean c4CTNaDfAtA;

    public ImageCanvas(){
        setSize(550, 550);
        setBackground(new java.awt.Color(25, 38, 87));
    }

    public void paint(Graphics g){

            if(level == 0){
                String intpath = "./data/intro.png";
                BufferedImage imgint = null;
                try{
                    imgint = ImageIO.read(MainMenu_ScottsVitaminCS.class.getResource(intpath));
                }catch(Exception e){
                    System.out.println("Error: \nSomething went wrong. \nEither missing file/s or reading file/s went wrong.");
                }
                
                g.drawImage(imgint, 25, 25, 500, 500, null);
            }else{
                if(c4CTNaDfAtA == true){
                    String img1path = "./data/"+ level + "/1.jpg";
                    String img2path = "./data/"+ level + "/2.jpg";
                    String img3path = "./data/"+ level + "/3.jpg";
                    String img4path = "./data/"+ level + "/4.jpg";
                    BufferedImage img1 = null;
                    BufferedImage img2 = null;
                    BufferedImage img3 = null;
                    BufferedImage img4 = null;
                
                    try{
                        img1 = ImageIO.read(MainMenu_ScottsVitaminCS.class.getResource(img1path));
                        img2 = ImageIO.read(MainMenu_ScottsVitaminCS.class.getResource(img2path));
                        img3 = ImageIO.read(MainMenu_ScottsVitaminCS.class.getResource(img3path));
                        img4 = ImageIO.read(MainMenu_ScottsVitaminCS.class.getResource(img4path));
                    }catch(Exception e){
                        System.out.println("Error: \nSomething went wrong. \nEither missing file/s or reading file/s went wrong.");
                    }
                    g.setColor(new java.awt.Color(204, 204, 204));
                    g.fillRoundRect(25, 25, 240, 240, 15, 15);
                    g.fillRoundRect(25, 285, 240, 240, 15, 15);
                    g.fillRoundRect(285, 25, 240, 240, 15, 15);
                    g.fillRoundRect(285, 285, 240, 240, 15, 15);
                    
            
                    g.drawImage(img1, 30, 30, 230, 230, null);
            
                    g.drawImage(img2, 290, 30, 230, 230, null);
            
                    g.drawImage(img3, 30, 290, 230, 230, null);
            
                    g.drawImage(img4, 290, 290, 230, 230, null);
                }
                else{
                    g.setColor(new java.awt.Color(25, 38, 87));
                    g.fillRect(0, 0, 550, 550);
                    
                    String intpath = "./data/intro.png";
                    BufferedImage imgint = null;
                    try{
                        imgint = ImageIO.read(MainMenu_ScottsVitaminCS.class.getResource(intpath));
                    }catch(Exception e){
                        System.out.println("Error: \nSomething went wrong. \nEither missing file/s or reading file/s went wrong.");
                    }
                    
                    g.drawImage(imgint, 25, 25, 500, 500, null);
                }
            }
    }

    public void update(Graphics g){
        level++;
        g.setColor(new java.awt.Color(25, 38, 87));
        g.fillRect(0,0,550,550);

        pathfol = new File("./data/" + level);

        c4CTNaDfAtA = pathfol.exists();

        paint(g);
    }
}
