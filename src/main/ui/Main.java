package ui;

import model.NegativeException;

import java.io.IOException;

// The main method where we run the application calls fitness app

public class Main {
    public static void main(String[] args) throws NegativeException, IOException {
        new Gui();
    }
}
