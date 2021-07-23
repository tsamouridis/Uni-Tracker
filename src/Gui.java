import java.awt.*;
import javax.swing.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui {
    JFrame f1;
    JLabel l_welMessage, l_university, l_department, l_name, l_years;
    JTextField t_university, t_department, t_name;
    JComboBox<String> years;
    JButton b_confirm;
    JPanel p1;
    Settings s;

    JFrame f2;
    JPanel p2, p3;
    JMenu menu;
    JMenuBar mb;
    JMenuItem i1, i2;
    JButton[] b_semesters;
    JLabel[] l_semesters;

    int numOfSemesters;



    Gui(){
        s = new Settings();


        // * frame 1 ///////////////////////////////////////////////////////////////////////////////////////////////////
        f1 = new JFrame("Uni-Tracker");
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLayout(new GridBagLayout());
        f1.setSize(550, 400);
        ImageIcon img = new ImageIcon("icon2.png");
        f1.setIconImage(img.getImage());


        f2 = new JFrame("Uni-Tracker");
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f2.setLayout(new BorderLayout());
       
        f2.setIconImage(img.getImage());

        //panel
        p1 = new JPanel();
        p1.setLayout(new GridBagLayout());
        // p1.setBackground(Color.LIGHT_GRAY);

        // GridBagContraints used when components are added
        GridBagConstraints gbc = new GridBagConstraints();

        // labels
        l_welMessage = new JLabel("<html><b><center><h3>Before you can use this App, you first have to provide the following informations:</h3></center></b><html>");;
        l_name = new JLabel("Student's name and surname: ");
        l_name.setToolTipText("Enter your name as you want it to be displayed in the App");
        l_university = new JLabel("University: ");
        l_name.setToolTipText("Enter your University's as you want it to be displayed in the App");
        l_department = new JLabel("Department: ");
        l_department.setToolTipText("Enter your Department's as you want it to be displayed in the App");
        l_years = new JLabel("Studying years: ");
        l_years.setToolTipText("Enter your Department's length of curriculum");

        // textfields
        t_university = new JTextField(); 
        t_name = new JTextField(); 
        t_department = new JTextField();

        // combobox
        String years_arr[]={"1", "2", "3", "4", "5"}; 
        years = new JComboBox<>(years_arr);    

        // Buttons
        b_confirm = new JButton("Confirm and continue");
        b_confirm.setToolTipText("Save all the data provided above and continue");

        // add l_welcome
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;  
        gbc.gridwidth = 2;
        gbc.ipady = 40;
        gbc.ipadx = 10;
        p1.add(l_welMessage, gbc);
        gbc.ipady = 10;
        gbc.ipadx = 10;

        gbc.insets = new Insets(10,0,10,0);

        // add l_name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        p1.add(l_name, gbc);

        // add t_name
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        p1.add(t_name, gbc);

        // add l_university
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; 
        p1.add(l_university, gbc);

        // add t_university
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1; 
        p1.add(t_university, gbc);

        // add l_department
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; 
        p1.add(l_department, gbc);

        // add t_department
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1; 
        p1.add(t_department, gbc);

        // add l_years
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; 
        p1.add(l_years, gbc);

        // add years combobox
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1; 
        p1.add(years, gbc);

        // add years combobox
        gbc.insets = new Insets(20,60,20,60);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; 
        p1.add(b_confirm, gbc);

        // add frame and panel
        f1.add(p1, gbc);
        f1.setVisible(true);

        

        b_confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String[] data = new String[4];
                boolean ok = true;
                data[0] = t_name.getText();
                data[1] = t_university.getText();
                data[2] = t_department.getText();
                data[3] = years.getSelectedItem().toString();
                for(int i=0 ; i<4 ; i++){
                    if (data[i].equals("")){
                        ok = false;
                        break;
                    }
                }

                if(ok){
                    int ans = JOptionPane.showConfirmDialog(f1, "Are you sure you want to save the information?");
                    if(ans == JOptionPane.YES_OPTION){  
                        f1.setVisible(false); // Set frame's visibility to false
                        f1.dispose(); // Destroy the frame
                        String[] newSettings = {"false", data[0], data[1], data[2], data[3]};
                        s.editSettings(newSettings);
                    }
                }

                else{
                    JOptionPane.showMessageDialog(f1,"You must fill in all the needed information", "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }   
        });

        // * frame 2 ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        mb = new JMenuBar();  
        menu = new JMenu();
        i1=new JMenuItem("Item 1");
        i2=new JMenuItem("Item 2");
        menu.add(i1);
        menu.add(i2);
        JMenu submenu=new JMenu("Sub Menu"); 
        menu.add(submenu);  
        mb.add(menu);  
        f2.setJMenuBar(mb);

        f2.setSize(600, 500);
        

        numOfSemesters = Integer.parseInt(s.getSettings()[4]) * 2;
        Semester semArray[] = new Semester[numOfSemesters];

        for(int i=0 ; i<numOfSemesters ; i++){
            semArray[i] = new Semester();
            semArray[i].setSerialNumber(i);
        }

        p2 = new JPanel();
        p2.setLayout(new GridBagLayout());

        p3 = new JPanel();
        p3.setLayout(new GridBagLayout());
        p3.setBackground(Color.BLACK);


        b_semesters = new JButton[numOfSemesters];
        
        gbc.insets = new Insets(10, 10, 10, 10);

        for(int i=0 ; i<numOfSemesters ; i++){
            String text = "Semester " + (i+1);
            text = "<html><h3><b>" + text + "</b></h3></html>";
            b_semesters[i] = new JButton(text);
            if(i%2 == 0){
                gbc.ipadx = 40;
                gbc.ipady = 5;
                gbc.gridx = 0;
                gbc.gridy = i;
                gbc.gridwidth = 1;
            }
            else{
                gbc.gridx = 1;
                gbc.gridy = i-1;
                gbc.gridwidth = 1;
            } 
            p2.add(b_semesters[i], gbc);
        }


        // for(int i=0 ; i<(int)numOfSemesters/2 ; i++){
        //     b_semesters[i] = new JButton("Semester " + (i+1));
        //     gbc.gridx = i;
        //     gbc.gridy = 0;
        //     gbc.gridwidth = 1; 
        //     p2.add(b_semesters[i], gbc);
        // }
        
        // int j = 0;
        //     for(int i=(int)numOfSemesters/2 + 1 ; i<numOfSemesters ; i++){
        //     b_semesters[i] = new JButton("Semester " + (i+1));
        //     gbc.gridx = j;
        //     gbc.gridy = 0;
        //     j++;
        //     gbc.gridwidth = 1; 
        //     p2.add(b_semesters[i], gbc);
        // }

        f2.add(p2, BorderLayout.CENTER);
        JButton p = new JButton("test");
        p3.add(p);
        f2.add(p3, BorderLayout.WEST);   

        if(s.getSettings()[0].equals("false")){
            f1.setVisible(false);
            f1.dispose();
            f2.setVisible(true);
        }
    }
}
