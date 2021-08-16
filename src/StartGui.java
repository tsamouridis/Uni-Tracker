import java.awt.*;
import javax.swing.*;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class StartGui {
    JFrame frame;
    JLabel l_welMessage, l_university, l_department, l_name, l_years;
    JTextField t_university, t_department, t_name;
    JComboBox<String> years;
    JButton b_confirm;
    JPanel panel;
    Settings s;
    GridBagConstraints gbc;

    StartGui(){
        s = new Settings();

        // frame
        frame = new JFrame("Uni-Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.setSize(550, 400);
        ImageIcon img = new ImageIcon("icons/icon2.png");
        frame.setIconImage(img.getImage());

        //panel
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        // GridBagConstraints used when components are added
        gbc = new GridBagConstraints();

        // labels
        l_welMessage = new JLabel("<html><b><center><h3>Before you can use this App, you first have to provide the following information:</h3></center></b><html>");;
        l_name = new JLabel("Student's name and surname: ");
        l_name.setToolTipText("Enter your name as you want it to be displayed in the App");
        l_university = new JLabel("University: ");
        l_name.setToolTipText("Enter your University's as you want it to be displayed in the App");
        l_department = new JLabel("Department: ");
        l_department.setToolTipText("Enter your Department's as you want it to be displayed in the App");
        l_years = new JLabel("Studying years: ");
        l_years.setToolTipText("Enter your Department's length of curriculum");

        // textFields
        t_university = new JTextField(); 
        t_name = new JTextField(); 
        t_department = new JTextField();

        // comboBox
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
        panel.add(l_welMessage, gbc);
        gbc.ipady = 10;
        gbc.ipadx = 10;

        gbc.insets = new Insets(10,0,10,0);

        // add l_name
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        panel.add(l_name, gbc);

        // add t_name
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1; 
        panel.add(t_name, gbc);

        // add l_university
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; 
        panel.add(l_university, gbc);

        // add t_university
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1; 
        panel.add(t_university, gbc);

        // add l_department
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; 
        panel.add(l_department, gbc);

        // add t_department
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1; 
        panel.add(t_department, gbc);

        // add l_years
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1; 
        panel.add(l_years, gbc);

        // add years comboBox
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1; 
        panel.add(years, gbc);

        // add years comboBox
        gbc.insets = new Insets(20,60,20,60);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; 
        panel.add(b_confirm, gbc);

        // add frame and panel
        frame.add(panel, gbc);
        frame.setVisible(true);

        // actionListeners
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
                    int ans = JOptionPane.showConfirmDialog(frame, "Are you sure you want to save the information?");
                    if(ans == JOptionPane.YES_OPTION){  
                        frame.setVisible(false); 
                        frame.dispose();
                        String[] newSettings = {"false", data[0], data[1], data[2], data[3]};
                        s.editSettings(newSettings);
                        
                        // ! edit bellow
                        int numOfSemesters = Integer.parseInt(s.getSettings()[4]) * 2;  // set numOfSemesters
                        Semester semArray[] = new Semester[numOfSemesters];             // create array for semesters
                        for(int i=0 ; i<numOfSemesters ; i++){                          //initialize the semArray
                            semArray[i] = new Semester();
                            semArray[i].setSerialNumber(i);
                        }
                        int courseNum = Course.getCourseNum();                          //read number of courses
                        
                        String newData[] = new String[5];
                        for(int i=0 ; i<courseNum ; i++){
                            String id = "C_"+i;
                            newData = Main.read_courseData(id);
                            int belongsToSem = Integer.parseInt(newData[1]); 
                            semArray[belongsToSem-1].addCourse(newData[0], Integer.parseInt(newData[1]),
                                                                Float.parseFloat(newData[2]), Boolean.parseBoolean(newData[3]), Boolean.parseBoolean(newData[4]), id, false);
                        }
                        new MainGui(semArray);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame,"You must fill in all the needed information", "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                } 
            }  
        });
    }
}
