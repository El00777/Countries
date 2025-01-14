
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class Main {

    // Array of 10 Country objects
    private Country[] countryArray = new Country[10];  
    // Index of current shown country
    private int index = 0;

    // GUI elements
    private JFrame jFrame = new JFrame("Countries");
    private ImageIcon img;
    private JLabel imageLabel;
    private JLabel outputLabel;

    public static void main(String[] args) {
        // Create the GUI
        Main gui = new Main();
        gui.loadCountries();
        gui.showCountry();
    }

    /* loadCountries() reads in the data from the countries-data.csv file and fills in the countryArray with data. */
    public void loadCountries() {
        // Open the data file - do not change
        File file = new File("/workspaces/Countries/workspace/countries-data.csv");
        Scanner scan = null;
        
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) { 
            System.out.println("File not found");
            return; // Exit the method if the file is not found
        }
        
        // Populate countryArray
        for (int i = 0; i < countryArray.length && scan.hasNextLine(); i++) {
            String input = scan.nextLine();
            String[] data = input.split(",");
            if (data.length >= 4) { // Ensure enough data in the row
                countryArray[i] = new Country(data[0], data[1], data[2], data[3]);
                System.out.println("Loaded: " + data[0]); // Debugging
            } else {
                System.out.println("Invalid data format: " + input);
            }
        }
        scan.close();
    }

    /* showCountry() will show the image associated with the current country. */
    public void showCountry() {
        if (countryArray[index] != null) {
            // Get the image file name from the country object
            String imagefile = countryArray[index].getImageFile();
            img = new ImageIcon("/workspaces/Countries/workspace/" + imagefile);
            imageLabel.setIcon(img); // Update the image in the GUI
        } else {
            System.out.println("No country data available at index " + index);
        }
    }

    /* nextButton should increment index. If the index is greater than 9, reset it back to 0. Clear the outputLabel and call showCountry(). */
    public void nextButtonClick() {
        index += 1;
        if (index > 9) { // Wrap around to the first country
            index = 0;
        }
        outputLabel.setText(""); // Clear the output label
        showCountry(); // Display the next country's image
    }

    /* reviewButton should display the current country's details. */
    public void reviewButtonClick() {
        if (countryArray[index] != null) {
            String info = countryArray[index].toString();
            System.out.println(info); // Print to console for debugging
            outputLabel.setText(info); // Display the information in the GUI
        } else {
            System.out.println("No country data available at index " + index);
        }
    }

    /* quizButton should ask a question about the current country and validate the user's answer. */
    public void quizButtonClick() {
        outputLabel.setText(""); // Clear the output label
        Scanner scan = new Scanner(System.in);

        if (countryArray[index] != null) {
            System.out.println("What is the capital of " + countryArray[index].getName() + "?");
            String userAnswer = scan.nextLine();

            if (userAnswer.equalsIgnoreCase(countryArray[index].getCapital())) {
                System.out.println("Correct!");
                outputLabel.setText("Correct!");
            } else {
                System.out.println("Incorrect! The correct answer is " + countryArray[index].getCapital());
                outputLabel.setText("Incorrect! The correct answer is " + countryArray[index].getCapital());
            }
        } else {
            System.out.println("No country data available at index " + index);
        }
        scan.close();
    }



  /* Do NOT change anything below here */
  /* The Main() constructor is finished and will construct the GUI */
public Main() {
    jFrame.setLayout(new FlowLayout());
    jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // buttons at the top
        JButton reviewButton = new JButton("Review");
        JButton quizButton = new JButton("Quiz");
        JButton newButton = new JButton("Next");
        jFrame.add(reviewButton);
        jFrame.add(quizButton);
        jFrame.add(newButton);
        
        // create a new image icon
        img = new ImageIcon("worldmap.jpg");
        // create a label to display image
        imageLabel = new JLabel(img);
        // and one for output
        outputLabel = new JLabel();
        jFrame.add(imageLabel);
        jFrame.add(outputLabel);
        jFrame.setVisible(true);
        // add event listener for button click
        reviewButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      reviewButtonClick();
    }
        });
    quizButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) 
    {
      quizButtonClick();
    }
    });
   
   newButton.addActionListener(new ActionListener()  {
    public void actionPerformed(ActionEvent e) 
    {
      nextButtonClick();
    }
   });
}
  

}
