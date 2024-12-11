import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserInterface extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Layout of window
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label startLabel = new Label("Starting Location:");
        GridPane.setConstraints(startLabel, 0, 0);

        TextField startInput = new TextField();
        startInput.setPromptText("Enter starting location");
        GridPane.setConstraints(startInput, 1, 0);

        Label endLabel = new Label("Ending Location:");
        GridPane.setConstraints(endLabel, 0, 1);

        TextField endInput = new TextField();
        endInput.setPromptText("Enter ending location");
        GridPane.setConstraints(endInput, 1, 1);

        Button calculateButton = new Button("Calculate Shortest Path");
        GridPane.setConstraints(calculateButton, 1, 2);

        grid.getChildren().addAll(startLabel, startInput, endLabel, endInput, calculateButton);

        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Traffic Flow Optimization");
        primaryStage.show();

        calculateButton.setOnAction(e -> {
            String startingLocation = startInput.getText();
            String endingLocation = endInput.getText();

            if (!startingLocation.isEmpty() && !endingLocation.isEmpty()) {
                System.out.println("Starting Location: " + startingLocation);
                System.out.println("Ending Location: " + endingLocation);

                // Here we can add the logic for Dijkstra ose A* algorithms
            } else {
                System.out.println("Please enter both locations.");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
