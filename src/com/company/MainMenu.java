package com.company;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Created by Travis on 6/24/2015.
 */
public class MainMenu {

    static final public List<MainSnake> mainSnakeList = new ArrayList<MainSnake>();
    static final public String header = "Unnamed";
    protected int mainSnakePanelCount = 1;

    public static void main(String[] arg) {
        new MainMenu();
    }

    public MainMenu() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Create the frame
                JFrame frame = new JFrame("Main Menu");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new MenuPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class MenuPane extends JPanel {

        public MenuPane() {
            //Create labels for the header and footer
            JLabel headerLabel = new JLabel("Welcome to the Main Menu, Please choose from the following.  " +
                    "Once a gig has been chosen, details will appear on the right.");
            JLabel headerLabel1 = new JLabel("Thanks for using Patchman!");

            //Create buttons
            JButton btnNewGig = new JButton("Create a New Gig"){
                {
                    setSize(200, 150);
                    setMaximumSize(getSize());
                }
            };
            JButton btnLoadGig = new JButton("Load an Existing Gig"){
                {
                    setSize(200, 150);
                    setMaximumSize(getSize());
                }
            };
            final JButton btnMainSnake = new JButton("Configure Main Snakes"){
                {
                    setSize(200, 150);
                    setMaximumSize(getSize());
                }
            };
            final JButton btnSubSnake = new JButton("Configure Sub Snakes"){
                {
                    setSize(200, 150);
                    setMaximumSize(getSize());
                }
            };
            JButton btnMikeLocker = new JButton("Configure the Mike Locker"){
                {
                    setSize(200, 150);
                    setMaximumSize(getSize());
                }
            };

            //set the layout to have top, bottom, left, and right.
            setLayout(new BorderLayout());

            //Create the top, bottom, left and right panels
            JPanel left = new JPanel();
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER));

            /*set the viewing window as the right panel. This will hold the components as the
            each step is completed.
             */
            final JPanel mainMenuContent = new JPanel(new GridBagLayout());
            GridBagConstraints gbcr = new GridBagConstraints();
            gbcr.gridwidth = GridBagConstraints.REMAINDER;
            gbcr.weighty = 1;
            mainMenuContent.add(new JPanel(), gbcr);

            //Add a Header with the Gig Name
            HeaderPanel mainMenuPanelHeader = new HeaderPanel();
            int insertAtMainMenuPanelHeader = Math.max(0, mainMenuContent.getComponentCount() - 1);  //insert it at the end of the list
            GridBagConstraints MainMenuPanelHeadergbc = new GridBagConstraints();
            MainMenuPanelHeadergbc.gridwidth = GridBagConstraints.REMAINDER;
            MainMenuPanelHeadergbc.fill = GridBagConstraints.HORIZONTAL;
            MainMenuPanelHeadergbc.weightx = 1;
            mainMenuContent.add(mainMenuPanelHeader, MainMenuPanelHeadergbc, insertAtMainMenuPanelHeader);  //add the pane to the window
            mainMenuContent.revalidate();
            mainMenuContent.repaint();

            //For Loop to add Main Snakes to the gig
            for (MainSnake temp: MainMenu.mainSnakeList) {
                MainSnakePanel subPanel = new MainSnakePanel(temp.getName(), temp.getChannelCount(), temp.getArtistSupplied());
                int insertAtSubPanel = Math.max(0, mainMenuContent.getComponentCount() - 1);  //insert it at the end of the list
                GridBagConstraints SubPanelgbc = new GridBagConstraints();
                SubPanelgbc.gridwidth = GridBagConstraints.REMAINDER;
                SubPanelgbc.fill = GridBagConstraints.HORIZONTAL;
                SubPanelgbc.weightx = 1;
                mainMenuContent.add(subPanel, SubPanelgbc, insertAtSubPanel);
                mainMenuContent.revalidate();
                mainMenuContent.repaint();
                mainSnakePanelCount++;
            }

            add(new JScrollPane(mainMenuContent));

            top.add(headerLabel);
            bottom.add(headerLabel1);
            left.add(btnNewGig);
            left.add(btnLoadGig);
            left.add(btnMainSnake);
            left.add(btnSubSnake);
            left.add(btnMikeLocker);

            add(top, BorderLayout.NORTH);  //sets header at the top of window
            top.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            add(left, BorderLayout.WEST);  //sets the button to the left of the display pane
            left.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            add(bottom, BorderLayout.AFTER_LAST_LINE);  //sets buttons on the bottom of window
            bottom.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));

            //set listener for btnNewGig button
            btnNewGig.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //open new btnNewGig window
                }
            });

            //set listener for btnLoadGig button
            btnLoadGig.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                      //open new btnLoadGig window
                }
            });

            //set listener for mainSnake button
            btnMainSnake.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainSnakeGUI mainSnakeGUIPane = new MainSnakeGUI();  //open new MainSnakeGUI window
                    final Window windowAncestor = SwingUtilities.getWindowAncestor(btnMainSnake);
                    windowAncestor.dispose();
                }
            });

            //set listener for btnSubSnake button
            btnSubSnake.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SubSnakeGUI subSnakeGUIPane = new SubSnakeGUI();  //open new MainSnakeGUI window
                    final Window windowAncestor = SwingUtilities.getWindowAncestor(btnSubSnake);
                    windowAncestor.dispose();
                }
            });

            //set listener for btnMikeLocker button
            btnMikeLocker.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //open new btnMikeLocker window
                }
            });
        }

        //set size of window
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1600, 800);
        }
    }


    public class HeaderPanel extends JPanel {

        public HeaderPanel() {
            String header = MainMenu.header;

            setLayout(new GridLayout(1, 1, 10, 10));
            add(new JLabel("Gig Name: " + header, SwingConstants.CENTER));
            setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2), new EmptyBorder(10, 10, 10, 10)));
        }
    }

    public class MainSnakePanel extends JPanel {
        int snakeCount = mainSnakePanelCount;

        public MainSnakePanel(String name, int count, boolean artist) {
            setLayout(new GridLayout(1, 3, 10, 10));
            add(new JLabel("Main Snake " + snakeCount + ": " + name));
            add(new JLabel("Channels: " + count));
            add(new JLabel("Arist Supplied: " + artist));
            setBorder(new CompoundBorder(new LineBorder(Color.BLACK, 2), new EmptyBorder(10, 10, 10, 10)));
        }
    }
}

