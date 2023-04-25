# Outline
-[Additional components](#Additional-components)
-[Purpose of the software](#Purpose-of-the-software)  
    -[Type of Software development process & reason](#Type-of-Software-development-process-&-reason)  
    -[Possible usage of your project](#Possible-usage-of-your-project)  
-[Software development plan](#Software-development-plan)  
    -[Development Process](#Development-Process)    
    -[Members](#Members)  
    -[Schedule](#Schedule)  
    -[Algorithm](#Algorithm)  
    -[Current status of software](#Current-status-of-software)  
    -[Future Plan](#Future-Plan)


# Additional components
## URL of demo
## Enviroments of software

***Programming language***  
   C++, java,python.  
***Minimum Hardware Requirements***  
  * A computer with a processor that supports SSE2 instruction set 
  * At least 2GB of RAM 
  * A monitor with a resolution of 1024x768 or higher  
***Minimum Software Requirements***  
  * A C++ compiler such as GCC, Clang, or Visual Studio *
  * A text editor or an integrated development environment (IDE) such as Visual Studio Code, Code::Blocks, or Eclipse *
  * An operating system that supports the chosen compiler and IDE, such as Windows, Linux, or macOS *  
***Require Packages***  
  * SFML (Simple and Fast Multimedia Library) for graphics and input handling *
  * Boost for random number generation and other utilities *
  * Qt for cross-platform GUI development *
  * SDL (Simple DirectMedia Layer) for game development * 
  * ncurses for console-based user interface *







# Purpose of the software
## Type of Software development process & reason
### Type
We chose the Agile methodology.
### Reason
1. Agile is a flexible and iterative development approach  
2. It emphasizes collaboration,customer satisfaction and delivering working software in short iterations.  
3. The requirements of minesweeper are relatively simple and may evolve throught the development process.   
4. Agile process allows for faster delivery of working software in short iterations.Our team can release new versions of Minesweeper more frequently and get to market faster.    
5. Ageli process places a strong emphasis on customer satisfaction and involves stakeholders throughtout the development process approach to meet the requirements of stakeholders.  

## Possible usage of your project
1. Casual Gamers  
2. Older Adults  
3. Education Institutions  
4. Moblie Users  
5. Outline Gaming Communities  

# Software development plan
## Development Process
   
 ***Feasibility analysis*** 
 
（1）Investment feasibility: Minesweeper occupies less memory and can be mounted on any platform, which has investment value.  
（2）Financial feasibility: From the perspective of the beneficiaries, it doesn't take much money to develop this minesweeper.  
（3）Organizational feasibility: feasible project planning, personnel allocation, good communication among team members, regular meetings and discussions to ensure timely delivery of projects.  
（4）Economic feasibility: it can create benefits for the enterprise, increase jobs for the society, and improve people's life quality.  
（5）Legal feasibility: Any product needs to ensure that it does not violate the law before it can be designed. But Minesweeper doesn't break any laws and doesn't come into conflict with companies. The development of the game does not violate anyone's interests, nor does it break the law.  
（6）Technical feasibility: The function of Minesweeper game is simple, only a slightly experienced developer can easily develop, so the technical aspect is not too big problem, the main need to understand the gameplay of minesweeper in order to better design and implementation of the game.  

***Design***

(1) *<font color = red>start()</font>*  
start method used to initialize game resources such as size of map,the number fo mines and the user interface of change the difficulty.  
(2) *createGame()*  
createGame method used to create elements of the game such as generating mine positions, calculating mine positions around each block, creating buttons and timers.  
(3) *handleLeftClick()*   
used to handle the left-click events.  
(4) *handleRightClick()*   
used to handle the right-click events.  
(5) *revealAdjacentButtons()*   
used to uncovered the surrounding squares.  
(6) *gameOver()*   
used to quit the game.  
(7) *checkWin()*  
used to Check whether the game is won.  
(8) *gameWon()*    
used to show the result of game。

***Process Planing***  

There are three parts: after the difficulty is selected, the first time the player clicks on the grid, and the automatic opening of the grid for non-mines.
After selecting the difficulty of the game, get the thunder number set by the difficulty and the interface size display interface, but there is no ray. When the player clicks the grid for the first time, the system randomly mines and activates the timer.  


![屏幕截图 2023-04-16 185857](https://user-images.githubusercontent.com/130427783/232303048-b335e4ab-173a-4e1d-87c1-d67ceb0726ad.png)  

***Interface Planing***  
(1) Selective difficulty   
(2）Easy Level   
(3) Difficut Level  
(4) MineArea & MineNumberArea        
(1)<img src=https://user-images.githubusercontent.com/130427783/232304317-31475a89-a4ce-4ff8-8e4e-63b3a73dc6e1.jpg width= "400px">
(2)<img src=https://user-images.githubusercontent.com/130427783/232304433-43d5eefc-0c29-4519-8045-5a4f33abb26b.jpg width = "400px">  
(3)<img src=https://user-images.githubusercontent.com/130427783/232304532-0fada794-d744-4b75-9562-9945388e8095.jpg width = "400px">
(4)<img src=https://user-images.githubusercontent.com/130427783/232304490-7580b15c-c8ce-46dd-b7cc-fb56631596e8.jpg width = "400px">  


***Detailed game design***

The game interface is relatively simple compared with the minesweeper game, which is mainly composed of the game data display area and minefield. It is important to initialize the mine area and we use a loop to initialize it. Players can click on the game after the menu bar to select the difficulty of minesweeper game, different difficulties represent different mine total and size, custom difficulty will also set the number and size of mines, set these data are stored in several variables, when the minefield to initialize the program will call the variable property, and then through a loop to complete the initialization. The main code is as follows:

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
    
   ***Minefield layout***
   
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
    
   ***Mouse event***
   
   In this minesweeper design, we will use mouse events to respond to the player's actions, in which the left mouse button is used to open the grid that the player thinks there is no thunder, and the right mouse button is used to mark the grid that the player thinks there is thunder hidden below.
   
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


***Game difficulty selection***

Easy level: mines = 10; rows = 8; columns = 8  
Medium level : mines = 15; rows= 10; columns = 10  
Hard level: mines = 30;rows = 14;columns= 14  

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

***Game Judgement***


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
    
   ## Members
   This
   
![屏幕截图 2023-04-19 170724](https://user-images.githubusercontent.com/130427783/233026639-1dfb3dcb-94a0-4a21-b5fe-3a2e486a7083.png)

   

   
   ## Schedule
   This is the schedule of our team
   
   ![屏幕截图 2023-04-16 210445](https://user-images.githubusercontent.com/130427783/232313390-bf5fd609-6877-4005-a3e7-54d32bd385ea.png)  
   
   ## Algorithm
   
   

(1) Random mine-laying

Minesweeper game requires random mines in the minefield, the number of mines should not be too much, so it is impossible to judge whether there are mines around; But not too little, so that you get a big blank space when you click on it. A random number is generated using java's native Math.random() method, which is computed to convert the random number to an integer, which is the corner marker for the location of Thunder. The object of the game is to mark all the mines and open the other grids that are not mines.

(2) Calculate the number of mines around the grid

When the grid without thunder is clicked, it will display all the thunder number in the 8 grids around the grid, and the player can judge the location of the thunder through this number, so the calculation of the thunder number around is also very important.

## Current status of software
So far our project has been relatively simple， we still have a lot of unfinished features like leaderboards.Considering that Minesweeper is a classic game, we will continue to improve it in the future to attract more users.

## Future Plan
1. Multiplayer Modes: Adding multiplayer modes to Minesweeper would allow players to compete against each other in real-time. This could involve adding features like leaderboards and matchmaking to connect players of similar skill levels.  
2. More Customization Options: Providing more customization options for players, such as the ability to change the theme, difficulty level, and game board size, would make the game more engaging and appealing to players.  
3. Virtual Reality Integration: Introducing virtual reality technology to Minesweeper would provide a more immersive gaming experience for players. This could involve creating a 3D game board and allowing players to interact with the game using hand gestures and other physical movements.  
4. Cross-Platform Play: Enabling cross-platform play would allow players on different devices to play together.
  
   








