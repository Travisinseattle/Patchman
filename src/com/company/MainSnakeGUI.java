package com.company;

import com.sun.xml.internal.ws.api.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import static com.company.MainMenu.*;


public class MainSnakeGUI {



    public static void main(String[] arg) {
        new MainSnakeGUI();
    }

    public MainSnakeGUI() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Create the frame
                JFrame frame = new JFrame("Snake Channels");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new MainSnakePane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class MainSnakePane extends JPanel {


        public MainSnakePane() {

            //final List<MainSnake> mainSnakeList = new ArrayList<MainSnake>();


            //Create buttons and labels
            JButton btnAdd = new JButton("Add New Snake"){
                {
                    setSize(200, 350);
                    setMaximumSize(getSize());
                }
            };
            JButton btnRemove = new JButton("Delete Snake"){
                {
                    setSize(200, 350);
                    setMaximumSize(getSize());
                }
            };
            JLabel headerLabel = new JLabel("Create 1 or More Split Snakes and Click 'Create' To complete." +
                    "Changes will only save if you click 'Create'");
            final JButton btnCreate = new JButton("Create");

            setLayout(new BorderLayout());

            //Create JPanels
            JPanel left = new JPanel();
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));

            //add items to JPanels
            left.add(btnAdd);
            left.add(btnRemove);
            header.add(headerLabel);
            footer.add(btnCreate);


            //add JPanels to window
            add(header, BorderLayout.NORTH);  //sets header at the top of window
            header.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            add(left, BorderLayout.BEFORE_LINE_BEGINS);  //sets the button to the left of the display pane
            left.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            add(footer, BorderLayout.AFTER_LAST_LINE);  //sets buttons on the bottom of window
            footer.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));


            //add panel to hold new frames
            final JPanel content = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weighty = 1;
            content.add(new JPanel(), gbc);

            //add scroll pane?
            add(new JScrollPane(content));

            //add listener for button
            btnAdd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    MainSnakeCreationPane pane = new MainSnakeCreationPane();  //create a new SnakeCreationPane
                    int insertAt = Math.max(0, content.getComponentCount() - 1);  //insert it at the end of the list
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.weightx = 1;
                    content.add(pane, gbc, insertAt);  //add the pane to the window
                    content.revalidate();
                    content.repaint();
                }
            });

            btnRemove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int paneCount = content.getComponentCount()-1;  //get count of the pane
                    content.remove(content.getComponent(paneCount-1));  //remove the pane at paneCount
                    content.revalidate();  //update window with changes
                    content.repaint();
                    MainSnakeCreationPane.snakeCount--;  //update count for snakes
                }
            });

            btnCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int n=0; n<content.getComponentCount(); n++){
                        if(content.getComponent(n) instanceof MainSnakeCreationPane){
                            MainSnakeCreationPane mainSnakePane = (MainSnakeCreationPane) content.getComponent(n);
                            MainSnake newMainSnake = new MainSnake(mainSnakePane.getSnakeName(),
                                    mainSnakePane.getChannelCount(), mainSnakePane.getArtistSupplied());
                            mainSnakeList.add(newMainSnake); //add new object to array list in MainMenu
                        }
                    }
                    //getTopLevelAncestor().setVisible(false);  //this sets current window invisible
                    MainMenu mainMenuPane = new MainMenu();
                    final Window windowAncestor = SwingUtilities.getWindowAncestor(btnCreate);
                    windowAncestor.dispose();
                }
            });

        }

        //set size of window
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1200, 600);
        }
    }

    public static class MainSnakeCreationPane extends JPanel {
        //Count of how many snakes created
        private static int snakeCount = 1;

        private JTextField snakeName = null;
        private JTextField channelCount = null;
        private JCheckBox artistSupplied = null;

        public MainSnakeCreationPane() {

            //creates Snake Panes
            setLayout(new GridLayout(2, 4, 10, 10));  //creates a grid 4 wide, 2 tall
            add(new JLabel("Split Snake " + (snakeCount++) + "."));
            add(new JLabel("Snake Name"));
            add(new JLabel("Channel Count"));
            add(new JLabel(""));
            add(new JLabel(""));
            snakeName =  new JTextField(30);
            channelCount = new JTextField("0",3);
            artistSupplied = new JCheckBox("Artist Supplied?");
            add(snakeName); //Snake Name field
            add(channelCount);  //Channel Count field
            add(artistSupplied); //Artist supplied checkbox
            setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
        }

        public String getSnakeName() {
            return snakeName.getText();
        }

        public int getChannelCount() {
            String temp = channelCount.getText();
            int channelCount = Integer.valueOf(temp);
            return channelCount;
        }

        public boolean getArtistSupplied() {
            return artistSupplied.isSelected();
        }
    }
}

