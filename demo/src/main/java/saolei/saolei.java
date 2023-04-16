package saolei;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class saolei extends Application {

    private int rows  ;
    private int columns  ;
    private int mines ;
    private int remainingMines;

    private boolean[][] minesArray;
    private int[][] counts;
    private Button[][] buttons;

    private Timeline timeline;
    private int secondsElapsed;
    private Label timerLabel;
    private Label remainingMinesLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Minesweeper");

        Button easyButton = new Button("Easy");
        easyButton.setOnAction(event->{
            mines = 10;
            rows = 8;
            columns = 8;
            createGame();
            GridPane root = new GridPane();
            root.setPadding(new Insets(10));
            root.setHgap(10);
            root.setVgap(10);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Button button = buttons[i][j];
                    root.add(button, j, i);
                    final int row = i; // 将i声明为final类型变量
                    final int col = j; // 将j声明为final类型变量
                    button.setOnMouseClicked(e -> {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            handleLeftClick(row, col); // 使用final变量
                        } else if (e.getButton() == MouseButton.SECONDARY) {
                            handleRightClick(row, col); // 使用final变量
                        }
                    });
                }
            }

            timerLabel = new Label("Time: 0");
            remainingMinesLabel = new Label("Mines: " + remainingMines);
            HBox hbox = new HBox(timerLabel, remainingMinesLabel);
            hbox.setPadding(new Insets(10));
            root.add(hbox, 0, rows, columns, 1);
            Scene scene2 = new Scene(root);
            primaryStage.setScene(scene2);
            //show
            primaryStage.show();
        });

        Button mediumButton = new Button("Medium");
        mediumButton.setOnAction(event->{
            mines = 15;
            rows = 10;
            columns = 10;
            createGame();
            GridPane root = new GridPane();
            root.setPadding(new Insets(10));
            root.setHgap(10);
            root.setVgap(10);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Button button = buttons[i][j];
                    root.add(button, j, i);
                    final int row = i; // 将i声明为final类型变量
                    final int col = j; // 将j声明为final类型变量
                    button.setOnMouseClicked(e -> {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            handleLeftClick(row, col); // 使用final变量
                        } else if (e.getButton() == MouseButton.SECONDARY) {
                            handleRightClick(row, col); // 使用final变量
                        }
                    });
                }
            }

            timerLabel = new Label("Time: 0");
            remainingMinesLabel = new Label("Mines: " + remainingMines);
            HBox hbox = new HBox(timerLabel, remainingMinesLabel);
            hbox.setPadding(new Insets(10));
            root.add(hbox, 0, rows, columns, 1);
            Scene scene2 = new Scene(root);
            primaryStage.setScene(scene2);
            //show
            primaryStage.show();
        });

        Button hardButton = new Button("Hard");
        hardButton.setOnAction(event->{
            mines = 30;
            rows =14;
            columns = 14;
            createGame();
            GridPane root = new GridPane();
            root.setPadding(new Insets(10));
            root.setHgap(10);
            root.setVgap(10);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    Button button = buttons[i][j];
                    root.add(button, j, i);
                    final int row = i; // 将i声明为final类型变量
                    final int col = j; // 将j声明为final类型变量
                    button.setOnMouseClicked(e -> {
                        if (e.getButton() == MouseButton.PRIMARY) {
                            handleLeftClick(row, col); // 使用final变量
                        } else if (e.getButton() == MouseButton.SECONDARY) {
                            handleRightClick(row, col); // 使用final变量
                        }
                    });
                }
            }

            timerLabel = new Label("Time: 0");
            remainingMinesLabel = new Label("Mines: " + remainingMines);
            HBox hbox = new HBox(timerLabel, remainingMinesLabel);
            hbox.setPadding(new Insets(10));
            root.add(hbox, 0, rows, columns, 1);
            Scene scene2 = new Scene(root);
            primaryStage.setScene(scene2);
            //show
            primaryStage.show();
        });


        VBox difficultyPane = new VBox(10, easyButton, mediumButton, hardButton);
        difficultyPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(difficultyPane, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // 创建游戏
    private void createGame() {
        minesArray = new boolean[rows][columns];
        counts = new int[rows][columns];
        buttons = new Button[rows][columns];

        remainingMines = mines;

        // 生成地雷位置
        Random random = new Random();
        for (int i = 0; i < mines; i++) {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            if (minesArray[row][column]) {
                i--;
            } else {
                minesArray[row][column] = true;
            }
        }

        // 计算每个方块周围的地雷数量
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int count = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k >= 0 && k < rows && l >= 0 && l < columns && minesArray[k][l]) {
                            count++;
                        }
                    }
                }
                counts[i][j] = count;
            }
        }

        // 创建按钮
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(30, 30);
                buttons[i][j].setStyle("-fx-font-size: 15px; -fx-background-color: #cccccc");
            }
        }

        // 创建计时器
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            secondsElapsed++;
            timerLabel.setText("Time: " + secondsElapsed);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    // 处理左键点击事件
    private void handleLeftClick(int row, int column) {
        Button button = buttons[row][column];
        if (minesArray[row][column]) {
            gameOver();
            return;
        }

        int count = counts[row][column];
        if (count == 0) {
            button.setStyle("-fx-background-color: #ffffff");
            revealAdjacentButtons(row, column);
        } else {
            button.setText(String.valueOf(count));
            button.setStyle("-fx-background-color: #ffffff");
        }

        checkWin();
    }

    // 处理右键点击事件
    private void handleRightClick(int row, int column) {
        Button button = buttons[row][column];
        if (button.getText().equals("!")) {
            button.setText("");
            remainingMines++;
        } else if (button.getText().equals("")) {
            button.setText("!");
            remainingMines--;
        }
        remainingMinesLabel.setText("Mines: " + remainingMines);
    }

    // 揭开周围的方块
    private void revealAdjacentButtons(int row, int column) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < columns && buttons[i][j].getStyle().equals("-fx-font-size: 15px; -fx-background-color: #cccccc")) {
                    handleLeftClick(i, j);
                }
            }
        }
    }

    // 游戏结束
    private void gameOver() {
        timeline.stop();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Button button = buttons[i][j];
                if (minesArray[i][j]) {
                    button.setStyle("-fx-background-color: #ff0000");
                } else {
                    int count = counts[i][j];
                    if (count > 0) {
                        button.setText(String.valueOf(count));
                    }
                    button.setStyle("-fx-background-color: #ffffff");
                }
            }
        }
    }

    // 检查是否获胜
    private void checkWin() {
        int revealedCount = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!minesArray[i][j] && !buttons[i][j].getStyle().equals("-fx-font-size: 15px; -fx-background-color: #cccccc")) {
                    revealedCount++;
                }
            }
        }
        if (revealedCount == rows * columns - mines) {
            gameWon();
        }
    }

    // 获胜
    private void gameWon() {
        timeline.stop();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Button button = buttons[i][j];
                if (minesArray[i][j]) {
                    button.setText("!");
                }
                button.setStyle("-fx-background-color: #ffffff");
            }
        }
    }

    // 启动游戏
    public static void main(String[] args) {
        launch(args);
    }
}