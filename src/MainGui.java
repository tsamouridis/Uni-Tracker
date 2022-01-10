import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

public class MainGui {
    // variables
    ImageIcon img;
    int k;      
    GridBagConstraints gbc;
    JFrame mainFrame;
    JPanel pCenter, pWest, pTop, pEast, pCenter_inSemester, pCenter_inStats;
    JTabbedPane tp;
    JMenu m_settings, m_courses;
    JMenuBar mb;
    JMenuItem i11, i12, i21, i22, i23;
    JButton[] b_semesters, b_courses;
    JLabel[] l_semesters;
    JLabel l_title;
    JButton b_back, b_addCourse, b_showStats;
    JScrollPane scroll, scroll2;
    int numOfSemesters;
    String newCourse_grade;
    String newCourse_name, fileId, oldName;
    JLabel l_stats;
    Settings s;
    Color color_inside, color_outside, color_letters;

    MainGui(Semester[] semArray){
        s = new Settings();
        String theme = s.getSettings()[5];
        System.out.println(theme);
        if(theme.equals("light")){
            System.out.println("in light");
            color_inside = Color.WHITE;
            color_letters = Color.BLACK;
            color_outside = Color.decode("#324ca8");
        }
        else{
            System.out.println("in dark");
            color_inside = Color.decode("#222d36");
            color_outside = Color.BLACK;
            color_letters = Color.WHITE;
        }
         // mainFrame
        mainFrame = new JFrame("Uni-Tracker");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        img = new ImageIcon("icons/icon2.png");
        mainFrame.setIconImage(img.getImage());
        mainFrame.setSize(700, 500);

        // GridBagConstraints used when components are added
        gbc = new GridBagConstraints();
        
        // panels
        tp = new JTabbedPane();

        pCenter = new JPanel();
        pCenter.setBackground(color_inside);
        pCenter.setLayout(new GridBagLayout());
        scroll2 = new JScrollPane(pCenter);  
        scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  

        pWest = new JPanel();
        pWest.setLayout(new GridBagLayout());
        pWest.setBackground(color_outside);

        pTop = new JPanel();
        pTop.setLayout(new GridBagLayout());
        pTop.setBackground(color_outside);

        pEast = new JPanel();
        pEast.setLayout(new GridBagLayout());
        pEast.setBackground(color_outside);


        // menuBar
        mb = new JMenuBar();  
        m_settings = new JMenu("Settings");
        m_courses = new JMenu("Courses");
        i11 = new JMenuItem("Edit information");
        i12 = new JMenuItem("Appearance Settings");
        i21 = new JMenuItem("Edit Course");
        i22 = new JMenuItem("Delete Course");
        i23 = new JMenuItem("Add Course");
        m_settings.add(i11);
        m_settings.add(i12);
        m_courses.add(i21);
        m_courses.add(i22);
        m_courses.add(i23);
        // JMenu subMenu=new JMenu("Sub Menu"); 
        // menu.add(subMenu);  
        mb.add(m_settings);  
        mb.add(m_courses);  
        mainFrame.setJMenuBar(mb);        

        // l_title
        String name = s.getSettings()[1];
        String university = s.getSettings()[2];
        String department = s.getSettings()[3];
        String title = "<html><center><b><h2>" + university + "</b><br>" + department + "<br>"+ name + "</h2></center></html>";
        l_title = new JLabel(title);
        l_title.setForeground(Color.WHITE);
        pTop.add(l_title);

        // buttons and labels for the main menu
        numOfSemesters = Integer.parseInt(s.getSettings()[4]) * 2;
        b_semesters = new JButton[numOfSemesters];
        gbc.insets = new Insets(10, 10, 10, 10);
        int year = 1;
        for(int i=0 ; i<numOfSemesters ; i++){
            String text = "Semester " + (i+1);
            text = "<html><h4><b>" + text + "</b></h4></html>";
            b_semesters[i] = new JButton(text);
            if(i%2 == 0){
                JLabel lab = new JLabel("Year " + year);
                lab.setForeground(color_letters);
                year++;
                gbc.ipadx = 40;
                gbc.ipady = 5;
                gbc.gridx = 0;
                gbc.gridy = i;
                gbc.gridwidth = 1;
                pCenter.add(lab, gbc);
                gbc.ipadx = 40;
                gbc.ipady = 5;
                gbc.gridx = 1;
                gbc.gridy = i;
                gbc.gridwidth = 1;
            }
            else{
                gbc.gridx = 2;
                gbc.gridy = i-1;
                gbc.gridwidth = 1;
            } 
            pCenter.add(b_semesters[i], gbc);
        }

        // add panels
        mainFrame.add(tp, BorderLayout.CENTER);
        tp.add(scroll2, "Main menu");
        pWest.setBorder( BorderFactory.createEmptyBorder(0,0,0,100) );
        pEast.setBorder( BorderFactory.createEmptyBorder(0,0,0,100) );
        mainFrame.add(pWest, BorderLayout.WEST);   
        mainFrame.add(pEast, BorderLayout.EAST);   
        mainFrame.add(pTop, BorderLayout.NORTH);   

        pCenter_inSemester = new JPanel();
        pCenter_inSemester.setBackground(color_inside);
        pCenter_inSemester.setLayout(new GridBagLayout());
        tp.add(pCenter_inSemester, "Semester Grades");
        scroll = new JScrollPane(pCenter_inSemester);  
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        tp.add(scroll, "Semester Grades");  

        pCenter_inStats = new JPanel();
        pCenter_inStats.setLayout(new GridBagLayout());
        pCenter_inStats.setBackground(color_inside);
        
        tp.add(pCenter_inStats, "Semester Grades");
        mainFrame.setVisible(true);

        tp.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if(tp.getSelectedIndex() == 0){
                    pCenter_inSemester.setVisible(false);
                    pCenter_inStats.setVisible(false);
                    pCenter_inStats.removeAll();
                    pCenter_inSemester.removeAll();
                    pCenter.setVisible(true);
                }
                else if(tp.getSelectedIndex() == 1){
                    pCenter_inStats.setVisible(false);
                    pCenter_inSemester.setVisible(true);
                    pCenter.setVisible(false);
                }
                else if(tp.getSelectedIndex() == 2){ 
                    pCenter_inStats.setVisible(true);
                    pCenter_inSemester.setVisible(false);
                    pCenter.setVisible(false);
                }
            }
        });

        i12.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                JFrame f_themePick = new JFrame("Theme Selection");

                f_themePick.setIconImage(img.getImage());
                f_themePick.setLayout(new GridBagLayout());
                f_themePick.setSize(350, 200);
                f_themePick.setResizable(false);
                JLabel l_themeSelection = new JLabel("Select Theme: ");
                JRadioButton rb_darkTheme = new JRadioButton("Dark Theme");
                JRadioButton rb_lightTheme = new JRadioButton("Light Theme");
                ButtonGroup bg=new ButtonGroup();    
                bg.add(rb_darkTheme);bg.add(rb_lightTheme); 
                rb_darkTheme.setSelected(true);
                JPanel p = new JPanel();
                p.setLayout(new GridBagLayout());
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.ipadx = 10;
                gbc.ipady = 10;
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                p.add(l_themeSelection, gbc);
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 1;
                p.add(rb_darkTheme, gbc);
                gbc.gridx = 1;
                gbc.gridy = 1;
                p.add(rb_lightTheme, gbc);
                
                JButton b_ok = new JButton("Confirm");
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.gridwidth = 2;
                p.add(b_ok, gbc);

                f_themePick.add(p);
                f_themePick.setVisible(true);

                b_ok.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){
                        boolean restart = false;
                        if(rb_darkTheme.isSelected()){
                            System.out.println("dark");
                            String[] temp = s.getSettings();
                            if(!temp[5].equals("dark") ){
                                temp[5] = "dark";
                                s.editSettings(temp);
                                restart = true;
                            }
                        }
                        else if(rb_lightTheme.isSelected()){
                            System.out.println("light");
                            String[] temp = s.getSettings();
                            if(!temp[5].equals("light") ){
                                temp[5] = "light";
                                s.editSettings(temp);
                                restart = true;
                            }
                        }
                        f_themePick.removeAll();
                        f_themePick.setVisible(false);
                        f_themePick.dispose();
                        if(restart == true){
                            mainFrame.dispose();
                            new MainGui(semArray);
                        }
                    }   
                });
            }
        });  

        for(int i=0 ; i<numOfSemesters ; i++){
            b_semesters[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    tp.setSelectedIndex(1);
                    JButton pressed = (JButton) e.getSource();
                    k = Character.getNumericValue(pressed.getText().charAt(22)) - 1;
                    if(pressed.getText().charAt(23) == '0' ){
                        k = 9;
                    }
                    pCenter.setVisible(false);
                    gbc.fill = GridBagConstraints.NONE; 
                    gbc.ipadx = 0;
                    gbc.ipady = 0;

                    String semesterTitle = "<html><center><h2>Semester " + (k+1) + "</h2></center></html>";
                    JLabel l_SemesterTitle = new JLabel(semesterTitle); 
                    l_SemesterTitle.setForeground(Color.BLUE);
                    b_courses = new JButton[semArray[k].getNumberOfCourses()]; 
                    JLabel[] l_grades = new JLabel[semArray[k].getNumberOfCourses()];
                    int j = 0; 

                    gbc.gridwidth = 2;
                    gbc.gridx = 0;
                    gbc.gridy = 0;
                    pCenter_inSemester.add(l_SemesterTitle, gbc);

                    b_back = new JButton(" < Back to main menu");
                    b_back.setToolTipText("Return to Semester menu");
                    gbc.gridx = 0;
                    gbc.gridy = 1;
                    gbc.gridwidth = 2;
                    pCenter_inSemester.add(b_back, gbc);

                    if(semArray[k].getNumberOfCourses() == 0){
                        JLabel noCourseMessage = new JLabel("There are no courses in this semester yet...");
                        noCourseMessage.setForeground(color_letters);
                        gbc.gridx = 0;
                        gbc.gridy = j+2;
                        gbc.gridwidth = 1;
                        pCenter_inSemester.add(noCourseMessage, gbc);
                        j++;
                    }
                    else{
                        gbc.gridwidth = 1;
                        gbc.ipady = 0;
                        gbc.ipadx = 0;
                        for(j=0 ; j<semArray[k].getNumberOfCourses() ; j++){
                            b_courses[j] = new JButton("<html><font face=\"Serif\" size=\"+1\">" + semArray[k].getCourses()[j].getName()+ "</font></html>");
                            b_courses[j].setToolTipText("Edit Name and/or Grade of Course");
                            b_courses[j].setName(semArray[k].getCourses()[j].getId());
                            b_courses[j].setBackground(Color.WHITE);
                            b_courses[j].setForeground(Color.BLACK);
                            l_grades[j] = new JLabel("<html><font face=\"Serif\" size=\"+1\">" +Float.toString(semArray[k].getCourses()[j].getGrade())+"</font></html>");
                            if(semArray[k].getCourses()[j].getGrade() >= 5){
                                l_grades[j].setForeground(Color.decode("#18a31b"));
                            }
                            else{
                                l_grades[j].setForeground(Color.RED);
                            }
                            gbc.gridx = 0;
                            gbc.gridy = j+2;
                            pCenter_inSemester.add(b_courses[j], gbc);
                            gbc.gridx = 1;
                            gbc.gridy = j+2;
                            pCenter_inSemester.add(l_grades[j], gbc);
                            b_courses[j].addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JButton pressed = (JButton) e.getSource();
                                    String oldNameRaw = pressed.getText();
                                    oldName = "";
                                    oldName += oldNameRaw.charAt(35);
                                    int iter = 36;
                                    while(oldNameRaw.charAt(iter) != '<' && oldNameRaw.charAt(iter+1)!='/' && oldNameRaw.charAt(iter+2) != 'f' && oldNameRaw.charAt(iter+3)!= 'o' && oldNameRaw.charAt(iter+4)!= 'n' && oldNameRaw.charAt(iter+5)!= 't' && oldNameRaw.charAt(iter+6)!= '>'){
                                        oldName += oldNameRaw.charAt(iter);
                                        iter++;
                                    }
                                    
                                    fileId = pressed.getName();
                                    JFrame optionPane = new JFrame("Edit Course info");
                                    optionPane.setIconImage(img.getImage());
                                    optionPane.setLayout(new GridBagLayout());

                                    optionPane.setSize(700, 300);
                                    optionPane.setResizable(false);
                                    JLabel l_newCourseName = new JLabel("Name of the Course: ");
                                    JLabel l_newCourseGrade = new JLabel("Grade of the Course: ");
                                    JLabel l_restartToChange = new JLabel("* Note that the changes will be visible when you reopen the App");
                                    JTextField t_newCourseName= new JTextField(oldName);
                                    JTextField t_newCourseGrade= new JTextField();

                                    JPanel p = new JPanel();
                                    p.setLayout(new GridBagLayout());
                                    gbc.gridwidth = 1;
                                    gbc.fill = GridBagConstraints.HORIZONTAL;
                                    gbc.ipadx = 200;
                                    gbc.ipady = 20;

                                    gbc.gridx = 0;
                                    gbc.gridy = 0;
                                    p.add(l_newCourseName, gbc);
                                    gbc.gridx = 1;
                                    gbc.gridy = 0;
                                    p.add(t_newCourseName, gbc);
                                    gbc.gridx = 0;
                                    gbc.gridy = 1;
                                    p.add(l_newCourseGrade, gbc);
                                    gbc.gridx = 1;
                                    gbc.gridy = 1;
                                    p.add(t_newCourseGrade, gbc);

                                    JButton b_ok = new JButton("Confirm");
                                    JButton b_cancel = new JButton("Cancel");
                                    b_cancel.setBackground(Color.RED);
                                    b_cancel.setForeground(Color.WHITE);
                                    gbc.gridx = 0;
                                    gbc.gridy = 2;
                                    p.add(b_ok, gbc);
                                    gbc.gridx = 1;
                                    gbc.gridy = 2;
                                    p.add(b_cancel, gbc);
                                    gbc.gridx = 0;
                                    gbc.gridy = 3;
                                    gbc.gridwidth = 2;
                                    p.add(l_restartToChange, gbc);

                                    optionPane.add(p);
                                    optionPane.setVisible(true);
                                    
                                    b_ok.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            //save data
                                            newCourse_name = t_newCourseName.getText();
                                            newCourse_grade = t_newCourseGrade.getText();
                                            
                                            // open file and edit 
                                            Course.edit_csv(fileId, newCourse_name, newCourse_grade);
                                            p.removeAll();
                                            optionPane.removeAll();
                                            optionPane.setVisible(false);
                                            optionPane.dispose();
                                        }   
                                    });

                                    b_cancel.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            p.removeAll();
                                            optionPane.removeAll();
                                            optionPane.setVisible(false);
                                            optionPane.dispose();
                                        }   
                                    });
                                }   
                            });
                        }    
                    }
                    
                    b_addCourse = new JButton("Add Course");
                    b_addCourse.setToolTipText("Add a new course to this semester");
                    gbc.gridwidth = 1;
                    gbc.gridx = 0;
                    gbc.gridy = j+2;
                    pCenter_inSemester.add(b_addCourse, gbc);

                    b_showStats = new JButton("Show Stats");
                    b_showStats.setToolTipText("Shows statistics of this semester and generates statistical graphs shown as png files. This process might take a while");
                    gbc.gridwidth = 1;
                    gbc.gridx = 1;
                    gbc.gridy = j+2;
                    pCenter_inSemester.add(b_showStats, gbc);

                    if(semArray[k].getNumberOfCourses() == 0){
                        b_showStats.setEnabled(false);
                    }

                    b_back.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            tp.setSelectedIndex(0);
                            pCenter_inSemester.removeAll();
                            pCenter_inSemester.setVisible(false);
                            pCenter.setVisible(true);
                        }   
                    });

                    b_showStats.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            tp.setSelectedIndex(2);
                            String toDisplay = "<html><center><font face=\"Serif\" size=\"+1\"";
                            semArray[k].createStats();
                            
                            if(semArray[k].getStats() != null){
                                toDisplay += "Mean: " + semArray[k].getStats().getMean() + "<br>";
                                toDisplay += "Median: " + semArray[k].getStats().getMedian() + "<br>";
                                if(semArray[k].getStats().getVariance() < 0){
                                    toDisplay += "Variance: -" + "<br>";
                                    toDisplay += "Standard Deviation: -" + "<br>";
                                }
                                else{
                                    toDisplay += "Variance: " + semArray[k].getStats().getVariance() + "<br>";
                                    toDisplay += "Standard Deviation: " + semArray[k].getStats().getStd() + "<br>";
                                }
                                toDisplay += "Minimum: " + semArray[k].getStats().getMinimum() + "<br>";
                                toDisplay += "Maximum: " + semArray[k].getStats().getMaximum() + "<br>";
                                toDisplay += "Number of Passed Courses : " + (int)semArray[k].getStats().getN() + "<br>";
                                toDisplay += "</font></center></html>";
                            }
                            else{
                                toDisplay = "No Statistics available for this semester";
                            }
                            JLabel l_statsTitle = new JLabel("<html><center><h2>Semester's statistics for passed Courses</h2></center></html>");
                            l_statsTitle.setForeground(color_letters);
                            gbc.gridx = 0;
                            gbc.gridy = 0;
                            pCenter_inStats.add(l_statsTitle, gbc);

                            l_stats = new JLabel(toDisplay);
                            l_stats.setForeground(color_letters);
                            gbc.gridx = 0;
                            gbc.gridy = 1;
                            pCenter_inStats.add(l_stats, gbc);
                            l_stats.setVisible(true);
                        }   
                    });

                    b_addCourse.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            newCourse_grade = "-1";
                            newCourse_name = "";
                            JFrame optionPane = new JFrame("Add a new course");
                            optionPane.setIconImage(img.getImage());
                            optionPane.setLayout(new GridBagLayout());
                            optionPane.setSize(700, 300);
                            optionPane.setResizable(false);
                            JLabel l_newCourseName = new JLabel("Name of the Course: ");
                            JLabel l_newCourseGrade = new JLabel("Grade of the Course: ");
                            JTextField t_newCourseName= new JTextField();
                            JTextField t_newCourseGrade= new JTextField();

                            JPanel p = new JPanel();
                            p.setLayout(new GridBagLayout());
                            gbc.fill = GridBagConstraints.HORIZONTAL;
                            gbc.ipadx = 200;
                            gbc.ipady = 20;

                            gbc.gridx = 0;
                            gbc.gridy = 0;
                            p.add(l_newCourseName, gbc);
                            gbc.gridx = 1;
                            gbc.gridy = 0;
                            p.add(t_newCourseName, gbc);
                            gbc.gridx = 0;
                            gbc.gridy = 1;
                            p.add(l_newCourseGrade, gbc);
                            gbc.gridx = 1;
                            gbc.gridy = 1;
                            p.add(t_newCourseGrade, gbc);

                            JButton b_ok2 = new JButton("Confirm");
                            JButton b_cancel2 = new JButton("Cancel");
                            b_cancel2.setBackground(Color.RED);
                            b_cancel2.setForeground(Color.WHITE);
                            gbc.gridx = 0;
                            gbc.gridy = 2;
                            p.add(b_ok2, gbc);
                            gbc.gridx = 1;
                            gbc.gridy = 2;
                            p.add(b_cancel2, gbc);

                            optionPane.add(p);
                            optionPane.setVisible(true);
                            
                            b_ok2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    //save data
                                    newCourse_name = t_newCourseName.getText();
                                    newCourse_grade = t_newCourseGrade.getText();
                                    semArray[k].addCourse(newCourse_name, semArray[k].getSerialNumber()+1, Float.parseFloat(newCourse_grade));

                                    optionPane.setVisible(false);
                                    optionPane.dispose();
                                    pCenter_inSemester.removeAll();
                                    b_semesters[k].doClick();
                                }   
                            });

                            b_cancel2.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    optionPane.setVisible(false);
                                    optionPane.dispose();
                                }   
                            });
                        }   
                    });
                } 
            });
        }   
    }
}
