package ui;

import model.Circuit;
import model.Exercise;
import model.NegativeException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

// CLASS LEVEL COMMENT: This is the class that actually displays output to the user by using scanners and inputs
// the user gets to add delete adn view their exercises

public class FitnessApp {
    private static final String JSON_STORE = "./data/circuit.json";
    private Scanner input;
    private Circuit newCircuit;
    private Exercise exercise1;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Runs the Application
    public FitnessApp() throws NegativeException {
        input = new Scanner(System.in);
        newCircuit = new Circuit("CircuitName");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // EFFECTS: Responsible for running the application loop until the user wants to quit
    private void runApp() throws NegativeException {
        boolean keepRunning = true;
        String command = null;

        init();

        while (keepRunning) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepRunning = false;
            } else {
                processCommand(command);
            }

        }
        System.out.println("Thank you for using MYTRACK FITNESS");
    }

    // MODIFIES: this
    // EFFECTS: adds,deletes or views workout
    private void processCommand(String command) throws NegativeException {
        if (command.equals("c")) {
            addCircuit();
        } else if (command.equals("d")) {
            deleteCircuit();
        } else if (command.equals("a")) {
            addAnExercise();
        } else if (command.equals("s")) {
            saveCircuit();
        } else if (command.equals("l")) {
            loadCircuit();
        } else if (command.equals("v")) {
            viewCircuit();
        } else {
            System.out.println("INVALID ARGUMENT");
        }
    }

    // EFFECTS: displays menu of choices
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tpress c to create a new circuit");
        System.out.println("\tpress a to add an exercise");
        System.out.println("\tpress d to delete an exercise");
        System.out.println("\tpress s to save the circuit");
        System.out.println("\tpress l to load the circuit");
        System.out.println("\tpress v to view");
        System.out.println("\tpress q to quit");
    }

    // MODIFIES: this
    // EFFECTS: initializes the workout
    private void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: Adds a given exercise to the circuit and keeps repeating until stops
    public void addCircuit() throws NegativeException {
        System.out.print("Enter the name of your circuit: ");
        String name = input.next();
        newCircuit = new Circuit(name);
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Enter the name of your exercise: ");
            String name2 = input.next();
            System.out.print("Enter the sets of your exercise: ");
            int sets = input.nextInt();
            System.out.print("Enter the reps of your exercise: ");
            int reps = input.nextInt();
            if (sets > 0 && reps > 0) {
                Exercise newExercise = new Exercise(name2, sets, reps);
                newCircuit.addExercise(newExercise);
                System.out.println(newExercise.getExerciseName() + " added for " + newExercise.getRepetitions()
                        + " reps and " + newExercise.getSets() + " sets");
            }
            System.out.print("Add more moves? enter Y or N: ");
            String decision = input.next();
            if (Objects.equals(decision, "N")) {
                keepRunning = false;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds an Exercise to the circuit already created
    public void addAnExercise() throws NegativeException {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.print("Enter the name of your exercise: ");
            String name2 = input.next();
            System.out.print("Enter the sets of your exercise: ");
            int sets = input.nextInt();
            System.out.print("Enter the reps of your exercise: ");
            int reps = input.nextInt();
            if (sets > 0 && reps > 0) {
                Exercise newExercise = new Exercise(name2, sets, reps);
                newCircuit.addExercise(newExercise);
                System.out.println(newExercise.getExerciseName() + " added for " + newExercise.getRepetitions()
                        + " reps and " + newExercise.getSets() + " sets");
            }
            System.out.print("Add more moves? enter Y or N: ");
            String decision = input.next();
            if (Objects.equals(decision, "N")) {
                keepRunning = false;
            }
        }
    }

    // EFFECTS: Views the exercises in the circuit
    public void viewCircuit() {
        for (int i = 0; i < newCircuit.getCircuit().size(); i++) {
            Exercise exercise1 = newCircuit.getCircuit().get(i);
            System.out.println(exercise1.getExerciseName() + " for "
                    + exercise1.getRepetitions() + " reps and "
                    + exercise1.getSets() + " sets");
        }
    }


    // MODIFIES: this
    // EFFECTS: Deletes a given exercise from the List
    public void deleteCircuit() {
        System.out.print("Enter the name of the exercise you want to delete: ");
        String name2 = input.next();
        for (int i = 0; i < newCircuit.getCircuit().size(); i++) {
            Exercise exercise1 =  newCircuit.getCircuit().get(i);
            if (Objects.equals(exercise1.getExerciseName(), name2)) {
                newCircuit.deleteExercise(exercise1);
            }
        }
    }

    // EFFECTS: Save Circuit to file
    private void saveCircuit() {
        try {
            jsonWriter.open();
            jsonWriter.write(newCircuit);
            jsonWriter.close();
            System.out.println("Saved to file");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, unable to write file");
        }
    }

    // MODIFIES: this
    // EFFECTS: Load Circuit from file
    private void loadCircuit() {
        try {
            Circuit savedCircuit = jsonReader.read();
            System.out.println("Loaded Circuit");
            for (Exercise exercise : savedCircuit.getCircuit()) {
                newCircuit.getCircuit().add(exercise);
            }
        } catch (IOException e) {
            System.out.println("unable to read the file");
        } catch (NegativeException e) {
            System.out.println("Cant have a negative number of reps/sets");
        }

    }

}
