package task4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileReader extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    private static final double width = 1100;
    private static final double height = 700;
    private static boolean fiboBreakFlag = false;
    @Override
    public void start(Stage primaryStage) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(2);

        final int RETREAT = 5;
        Pane root = new Pane();
        Scene scene = new Scene(root);

        setProperty(primaryStage);

//-----------------------Text area------------------------------

        TextArea textArea = new TextArea();
        textArea.setLayoutY(37);
        textArea.setMinWidth(width - textArea.getLayoutX());
        textArea.setMinHeight(height - textArea.getLayoutY());

//---------------------Load button------------------------------

        Button buttonLoad = new Button("Load");
        buttonLoad.setLayoutX(RETREAT);
        buttonLoad.setLayoutY(RETREAT);
        buttonLoad.setDisable(true);

//---------------------Save button------------------------------
        Button buttonSave = new Button("Save");
        buttonSave.setLayoutX(380);
        buttonSave.setLayoutY(RETREAT);

//-------------------Set patch button---------------------------
        Button buttonOpen = new Button("Set patch");
        buttonOpen.setLayoutY(RETREAT);
        buttonOpen.setLayoutX(80);

//---------------------Text field------------------------------
        TextField textField = new TextField();
        textField.setLayoutY(RETREAT);
        textField.setLayoutX(170);
        textField.setMinWidth(200);

//---------------------Text field Fibonacci--------------------
        TextField fibonacciField = new TextField();
        fibonacciField.setLayoutY(RETREAT);
        fibonacciField.setLayoutX(500);
        fibonacciField.setMaxSize(80,20);

//---------------------Button Fibonacci--------------------
        Button fibonacciButton = new Button("Calc fibonacci");
        fibonacciButton.setLayoutY(RETREAT);
        fibonacciButton.setLayoutX(590);

//---------------------Button Cancell--------------------
        Button cancelButton = new Button("Cancel");
        fibonacciButton.setLayoutY(RETREAT);
        fibonacciButton.setLayoutX(680);

//-------------------------------------------------------------
        root.getChildren().addAll(textArea, buttonLoad, buttonOpen,
                textField, buttonSave, fibonacciButton, fibonacciField);

        primaryStage.setScene(scene);
        primaryStage.show();

//-------------------------------------------------------------
//-------------------------EVENTS------------------------------
//-------------------------------------------------------------

//---------------------Load button event-----------------------

        buttonLoad.setOnMouseClicked((event)->{
            File file = new File(textField.getText());
            textArea.clear();
            Callable<String> load = ()-> loadFromFile(file);
            try {
                String text = service.submit(load).get();
                textArea.appendText(text);
                buttonLoad.setDisable(true);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            buttonSave.setDisable(false);
        });

//---------------------Set button event-----------------------
        buttonOpen.setOnMouseClicked((event)->{
            File file = openFile(primaryStage);
            buttonLoad.setDisable(false);
            try {
                textField.setText(file.getPath());
            }catch (NullPointerException e){
//                e.printStackTrace();
                System.out.println("=======================");
            }
            buttonLoad.setDefaultButton(true);
        });
//---------------------Save button event-----------------------
        buttonSave.setOnMouseClicked((event -> {
            File file = new File(textField.getText());
            String s = textArea.getText();
            Callable<Boolean> save = ()-> saveToFile(file, s);
            service.submit(save);
        }));
//------------Text field, text Area events-----------------------
        textField.setOnMouseClicked((e)-> buttonLoad.setDisable(false));
        textField.setOnKeyPressed((e)-> buttonSave.setDisable(false));
        textArea.setOnMouseClicked((e)-> buttonLoad.setDisable(false));
//------------------------------------------------------------
        fibonacciButton.setOnAction(e-> {
            buttonSave.setDisable(true);
            service.submit(()->{
                try {
                    int a = Integer.parseInt(fibonacciField.getText());
                    File file = new File(textField.getText());
                    if (!file.canWrite()) throw new FileNotFoundException();
                    FileWriter writer = new FileWriter(file);

                    long[] f = new long[a];
                    writer.write(f[0] + " ");
                    int lenString = 0;
                    for (long i = 1, j = 1; i < a; i++, j = f[(int)i-2]) {
                        long i1 = j + f[(int)i-1];
                        f[(int)i] = i1;
                        writer.write(f[(int)i] + " ");
                        lenString += (" " + f[(int)i]).length();
                        if (fiboBreakFlag) break;
                        if (lenString > 120) {
                            writer.write("\n");
                            lenString = 0;
                        }
                    }
                    writer.flush();

                } catch (NumberFormatException | FileNotFoundException e1) {
                    Thread.currentThread().interrupt();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        });

        primaryStage.setOnCloseRequest((event)-> service.shutdown());
    }//start(stagePimary)

    private int[] fibonacci(String s){
        int a = 0;
        try {
            a = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return new int[0];
        }
        int[] f = new int[a];
        for (int i = 1, j = 1; i < a; i++, j = f[i-2]) {
            int i1 = j + f[i-1];
            f[i] = i1;
        }
        return f;
    }

    private boolean saveToFile(File file, String s){
        try {
            OutputStream fos = new FileOutputStream(file);
            fos.write((s.getBytes("UTF-8")));
            fos.flush();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String loadFromFile(File file){

        try {
            Scanner scanner = new Scanner(new FileInputStream(file));
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNext()){
                sb.append(scanner.nextLine());
                if(scanner.hasNext()) sb.append("\n");
                if (Thread.currentThread().isInterrupted()) break;
            }
            return String.valueOf(sb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File openFile(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Document");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        return (fileChooser.showOpenDialog(stage));
    }

    private void setProperty(Stage stage){
        stage.setMinWidth(width);
        stage.setMaxWidth(width);
        stage.setMinHeight(height);
        stage.setMaxHeight(height);
    }
}
