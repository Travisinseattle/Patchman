package com.company;

import org.omg.CORBA.SetOverrideType;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.xml.soap.SOAPPart;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Travis on 3/3/2015.
 */
public class SubSnakeGUI {

    protected static String[] names = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static void main(String[] arg) {
        new SubSnakeGUI();
    }

    public SubSnakeGUI() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Create the frame
                JFrame frame = new JFrame("Sub Snakes");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.add(new SubSnakePane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public class SubSnakePane extends JPanel {

        public SubSnakePane() {

            final java.util.List<SubSnake> subSnakeList = new ArrayList<SubSnake>();
            //add button
            JButton btnAddSnake = new JButton("Add New Sub Snake"){
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
            JButton btnCreate = new JButton("Create");
            JLabel headerLabel = new JLabel("Create a Sub Snake");

            setLayout(new BorderLayout());

            JPanel left = new JPanel();
            left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));
            JPanel header = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));

            left.add(btnAddSnake);
            left.add(btnRemove);
            header.add(headerLabel);
            footer.add(btnCreate);
            add(header, BorderLayout.NORTH);
            header.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            add(left, BorderLayout.BEFORE_LINE_BEGINS);
            left.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
            add(footer, BorderLayout.AFTER_LAST_LINE);
            footer.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));

            //add a bottom border for buttons

            //add panel to hold new frames
            final JPanel content = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weighty = 1;
            content.add(new JPanel(), gbc);

            //add scroll pane?
            add(new JScrollPane(content));

            //add listener for button
            btnAddSnake.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SubSnakeCreationPane pane = new SubSnakeCreationPane();
                    int insertAt = Math.max(0, content.getComponentCount() - 1);
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.weightx = 1;
                    content.add(pane, gbc, insertAt);
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
                    SubSnakeCreationPane.count--;  //update count for snakes
                }
            });

            btnCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for(int n=0; n<content.getComponentCount(); n++){
                        if(content.getComponent(n) instanceof SubSnakeCreationPane){
                            SubSnakeCreationPane subSnakePane = (SubSnakeCreationPane) content.getComponent(n);
                            SubSnake newSubSnake = new SubSnake(subSnakePane.getSnakeChannel(),
                                    subSnakePane.getChannelCount());  //Creates an object of SubSnake
                            subSnakeList.add(newSubSnake); //add new object to array list to use elsewhere
                            }
                    }
                    getTopLevelAncestor().setVisible(false);  //this sets current window invisible
                    for (SubSnake temp: subSnakeList) {
                        System.out.println("Snake Channel is :" + temp.getName());
                        System.out.println("Channel Count is :" + temp.getChannelCount());
                    }
                }
            });
        }

        //set size of window
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(1200, 600);
        }
    }

    public static class SubSnakeCreationPane extends JPanel {
        //Count of how many snakes created
        private static int count = 1;
        private JComboBox snakeChannel = null;
        private JTextField channelCount = null;


        public SubSnakeCreationPane() {

            setLayout(new GridLayout(2,3,10,10));  //creates Sub Snake Panes in a grid 2 wide, 2 tall
            add(new JLabel("Sub Snake " + (count++) + "."));
            add(new JLabel("Snake Letter"));
            add(new JLabel("Channel Count"));
            add(new JLabel(""));
            snakeChannel = new JComboBox(names);
            add(snakeChannel);  //combo box of letters A-Z
            channelCount = new JTextField("0",3);
            add(channelCount);
            setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new EmptyBorder(10, 10, 10, 10)));
        }

        public String getSnakeChannel() {
            String tempSnakeChannel = (String) snakeChannel.getSelectedItem();
            return tempSnakeChannel;
        }

        public int getChannelCount() {
            String temp = channelCount.getText();
            int channelCount = Integer.valueOf(temp);
            return channelCount;
        }
    }
}


