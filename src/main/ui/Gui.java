package ui;

import model.Circuit;
import model.EventLog;
import model.Event;
import model.Exercise;
import model.NegativeException;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

// CLASS LEVEL COMMENT: This is the graphical user interface implementation of the app. It uses the console based model
// while adding some buttons and panels in order to visually display to the user
public class Gui extends JFrame implements ActionListener {
    private JButton addButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JButton loadButton;
    private JTextArea logArea;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/circuit.json";
    Circuit circuit;
    JPanel panel;
    JButton button;



    // EFFECTS: Constructs the panels and buttons for the whole GUI along with JSON
    public Gui() throws IOException {
        circuit = new Circuit("Exercises List");
        panel =  new JPanel();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        createPanel();

    }

    // MODIFIES: this
    // EFFECTS: Adds an Exercise to the circuit already created
    public void createTextField() throws NegativeException {
        //JButton button = new JButton();
        button = new JButton();
        String name = JOptionPane.showInputDialog("Enter the name of the exercise: ");
        int sets = Integer.parseInt(JOptionPane.showInputDialog("Enter the sets: "));
        int reps = Integer.parseInt(JOptionPane.showInputDialog("Enter the reps: "));
        button.setBounds(200 + sets,200 + reps,300,150);
        button.addActionListener(this);
        this.setLayout(null);
        this.setSize(1000,1000);
        //this.setVisible(true);
        this.add(button);
        button.setText(name + " added for " + sets + " sets and " + reps + " reps");
        panel.add(button);
        panel.setVisible(true);
        Exercise newExercise = new Exercise(name, sets, reps);
        circuit.addExercise(newExercise);

    }


    // MODIFIES: this
    // EFFECTS: Deletes a given exercise from the List
    public void createTextFieldDelete() {
        String name = JOptionPane.showInputDialog("Enter the name of the exercise you want to delete: ");
        for (int i = 0; i < circuit.getCircuit().size(); i++) {
            Exercise exercise1 =  circuit.getCircuit().get(i);
            if (Objects.equals(exercise1.getExerciseName(), name)) {
                circuit.deleteExercise(exercise1);
            }
        }
    }


    // EFFECTS: Save Circuit to file
    public void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(circuit);
            jsonWriter.close();
            System.out.println("Saved to file");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, unable to write file");
        }

    }

    // MODIFIES: this
    // EFFECTS: Load Circuit from file
    public void loadData() {
        try {
            Circuit savedCircuit = jsonReader.read();
            System.out.println("Loaded Circuit");
            for (Exercise exercise : savedCircuit.getCircuit()) {
                circuit.getCircuit().add(exercise);
                JButton button = new JButton();
                button.setBounds(400 + exercise.getSets(),400 + exercise.getRepetitions(),300,150);
                button.addActionListener(this);
                this.setLayout(null);
                this.setSize(1000,1000);
                //this.setVisible(true);
                this.add(button);
                button.setText(exercise.getExerciseName() + " added for " + exercise.getSets() + " sets "
                        + exercise.getRepetitions() + "reps");
                panel.add(button);
                panel.setVisible(true);
            }
        } catch (IOException e) {
            System.out.println("unable to read the file");
        } catch (NegativeException e) {
            System.out.println("Cant have a negative number of reps/sets");
        }
    }


    // EFFECTS: Does different functions based on what buttons the user presses in the GUI
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            try {
                createTextField();
            } catch (NegativeException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == deleteButton) {
            createTextFieldDelete();
        }
        if (e.getSource() == saveButton) {
            saveData();
        }
        if (e.getSource() == loadButton) {
            loadData();
        }
        if (e.getSource() == button) {
            panel.remove(button);
            panel.invalidate();
            panel.repaint();
        }

    }















    // EFFECTS: Creates all the buttons together
    public void createButtons() {
        createAddButton();
        createDeleteButton();
        createSaveButton();
        createLoadButton();
    }

    // MODIFIES: this
    // EFFECTS: Creates the add Button
    public void createAddButton() {
        addButton = new JButton();
        addButton.setBounds(200,100,100,50);
        addButton.addActionListener(this);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(addButton);
        addButton.setText("Add");
    }

    // MODIFIES: this
    // EFFECTS: Creates the load Button
    public void createLoadButton() {
        loadButton = new JButton();
        loadButton.setBounds(200,200,100,50);
        loadButton.addActionListener(this);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(loadButton);
        loadButton.setText("Load");
    }

    // MODIFIES: this
    // EFFECTS: Creates the delete Button
    public void createDeleteButton() {
        deleteButton = new JButton();
        deleteButton.setBounds(200,0,100,50);
        deleteButton.addActionListener(this);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(deleteButton);
        deleteButton.setText("Delete");
    }

    // MODIFIES: this
    // EFFECTS: Creates the save Button
    public void createSaveButton() {
        saveButton = new JButton();
        saveButton.setBounds(200,400,100,50);
        saveButton.addActionListener(this);
        this.setLayout(null);
        this.setSize(500,500);
        this.setVisible(true);
        this.add(saveButton);
        saveButton.setText("Save");
    }

    // MODIFIES: this
    // EFFECTS: Creates the panel and adds the image along with the buttons
    public void createPanel() throws IOException {
        panel.setBackground(Color.blue);
        ImageIcon imageIcon = new ImageIcon("data/work.jpeg");
        JLabel label = new JLabel(imageIcon);
        label.setSize(100, 100);
        setVisible(true);
        panel.setBounds(0,0,1000,1000);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                printLog(EventLog.getInstance());
                super.windowClosing(evt);
            }
        });
        createFrame(label, frame);
    }

    private void createFrame(JLabel label, JFrame frame) {
        frame.setLayout(null);
        frame.setSize(1000,1000);
        frame.setVisible(true);
        frame.add(panel);
        panel.add(label);
        createButtons();
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(saveButton);
        panel.add(loadButton);
    }

    public void printLog(EventLog el) {
        for (Event next : el) {
            //System.out.println(next.getDescription());
            System.out.println(next);
        }
        repaint();
    }
}
