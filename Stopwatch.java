import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Stopwatch implements ActionListener {
    //ActionListener in Java is a class that is responsible for handling all action events such as when the user clicks on a component
    JFrame frame = new JFrame(); //creates frame of window
    JButton startButton = new JButton("Start"); //creates a button
    JButton resetButton = new JButton("Reset");
    JLabel timeLabel = new JLabel(); //creates a label

    //initialize different vars
    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;

    //how to format the time
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() { //timer goes by milaseconds so put delay at 1000 to start at 1 second
        @Override
        public void actionPerformed(ActionEvent e) { //when an action event occurs such as button press

            elapsedTime = elapsedTime+1000; //start at 1 second
            hours = (elapsedTime/36000000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            String seconds_string = String.format("%02d", seconds);
            String minutes_string = String.format("%02d", minutes);
            String hours_string = String.format("%02d", hours);
            timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string); //how it will look on the window

        }
    });

    Stopwatch(){

        //creating how the time will look and where on the window it will  be
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timeLabel.setBounds(100,100,200,100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        //how button will look and where on the window it will be
        startButton.setBounds(100,200,100,50);
        startButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        //where reset button will be and how it will look
        resetButton.setBounds(200,200,100,50);
        resetButton.setFont(new Font("Ink Free",Font.PLAIN,20));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);

        //how the frame will look that will hold everything
        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == startButton){ //if source of action is start button
            if(!started){ //if not started
                started =true; //make it true that it is
                startButton.setText("Stop"); //replace text with stop
                start(); //and start the timer
            }
            else{ //else if already going
                started = false; //set started to false
                startButton.setText("Start"); //replace text with start
                stop(); //stop the timer
            }
        }
        if(e.getSource() == resetButton){ //if reset button was pressed
            started = false; //set started to false
            startButton.setText("Start"); //replace start button text with start since it would be on stop
            reset(); //and reset
        }

    }

    void start(){
        timer.start();
    }//start timer

    void stop(){
        timer.stop(); //stop timer

    }

    void reset(){ //reset timer
        timer.stop(); //stop timer

        //set everything to 0
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        String seconds_string = String.format("%02d", seconds);
        String minutes_string = String.format("%02d", minutes);
        String hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string); //put it back to original format
    }
}
