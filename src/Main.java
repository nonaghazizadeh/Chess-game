import Others.Control;
import Others.Player;
import Others.ScoreBoard;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private StackPane gamePage;
    private Control control;
    public static Stage window;
    private Scene loginScene;
    private Scene registerScene;
    private Scene editScene;
    private Scene startingScene;
    private Scene scoreBoardScene;

    @Override
    public void init() {
        gamePage = new StackPane();
        control = new Control();
        gamePage.getChildren().add(control);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Chess game");
        String style = "-fx-background-color: linear-gradient(#f2f2f2, #d6d6d6), linear-gradient(#fcfcfc 0%, #d9d9d9 20%, #d6d6d6 100%), "
                + "linear-gradient(#dddddd 0%, #f6f6f6 50%); -fx-background-radius: 8,7,6; -fx-background-insets: 0,1,2; -fx-text-fill: #000000;"
                + "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1 ); -fx-font-size: 1.9em; -fx-padding: 10px;";

        Label menuLabel = new Label("Game Menu");
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Button editButton = new Button("Edit account");
        Button exitButton = new Button("Exit");

        menuLabel.setStyle("-fx-font-size: 4em ; -fx-text-fill : white");
        loginButton.setStyle(style);
        registerButton.setStyle(style);
        editButton.setStyle(style);
        exitButton.setStyle(style);

        Label SUError = new Label();
        Label loginError = new Label();
        Label deleteError = new Label();
        Label changePassError = new Label();

        VBox menuVBox = new VBox(40);
        menuVBox.setAlignment(Pos.CENTER);

        menuVBox.getChildren().addAll(menuLabel, loginButton, registerButton, editButton, exitButton);
        Scene mainScene = new Scene(menuVBox, 600, 600);

        Image image = new Image("file:src/Images/Background.jpeg");
        BackgroundImage backgroundimage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background background = new Background(backgroundimage);
        menuVBox.setBackground(background);


        loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                window.setScene(loginScene);
            }
        });

        registerButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                window.setScene(registerScene);
            }
        });

        editButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                window.setScene(editScene);
            }
        });

        exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        window.setScene(mainScene);
        window.show();

        GridPane editGridPane = new GridPane();
        editGridPane.setAlignment(Pos.TOP_LEFT);
        editGridPane.setHgap(10);
        editGridPane.setVgap(10);
        editGridPane.setPadding(new Insets(25, 25, 25, 25));
        Text text = new Text("Delete account");
        text.setFont(Font.font("Tahoma", FontWeight.BOLD, 28));
        editGridPane.add(text, 0, 1, 2, 1);

        Label label = new Label("Enter username and password to delete account");
        label.setStyle("-fx-text-fill: BLACK");
        editGridPane.add(label, 0, 3);

        Label userName = new Label("Username:");
        userName.setStyle("-fx-text-fill: BLACK");
        editGridPane.add(userName, 0, 4);

        TextField userNameTextField = new TextField();
        editGridPane.add(userNameTextField, 1, 4);

        Label password = new Label("Password:");
        password.setStyle("-fx-text-fill: BLACK");
        editGridPane.add(password, 0, 5);

        PasswordField passwordField = new PasswordField();
        editGridPane.add(passwordField, 1, 5);

        Button deleteButton = new Button("Delete account");
        HBox deleteBox = new HBox(10);
        deleteBox.setAlignment(Pos.BOTTOM_RIGHT);
        deleteBox.getChildren().add(deleteButton);
        editGridPane.add(deleteBox, 1, 6);

        deleteButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Player.getPlayerByUsername(userNameTextField.getText()) != null) {
                    if (Player.validateUsernameAndPassword(userNameTextField.getText(), passwordField.getText())) {
                        Player.removePlayer(userNameTextField.getText());
                        deleteError.setText("Account deleted successfully");
                        deleteError.setStyle("-fx-text-fill: blue");
                    } else {
                        deleteError.setStyle("-fx-text-fill: red");
                        deleteError.setText("password is wrong");
                    }
                } else {
                    deleteError.setStyle("-fx-text-fill: red");
                    deleteError.setText("there isn't any account registered with this username");
                }
            }
        });
        editGridPane.add(deleteError, 0, 6);

        Button deleteBackButton = new Button("Back");
        HBox dBox = new HBox(10);
        dBox.setAlignment(Pos.TOP_LEFT);
        dBox.getChildren().add(deleteBackButton);
        editGridPane.add(deleteBackButton, 0, 0);

        Text changePassText = new Text("Change password");
        changePassText.setFont(Font.font("Tahoma", FontWeight.BOLD, 28));
        editGridPane.add(changePassText, 0, 8, 2, 1);

        Label changeLabel = new Label("Enter username and current password");
        changeLabel.setStyle("-fx-text-fill: BLACK");
        editGridPane.add(changeLabel, 0, 9);

        Label changeUserName = new Label("Username:");
        changeUserName.setStyle("-fx-text-fill: BLACK");
        editGridPane.add(changeUserName, 0, 10);

        TextField changeUserNameTextField = new TextField();
        editGridPane.add(changeUserNameTextField, 1, 10);

        Label currentPassword = new Label("Current Password:");
        currentPassword.setStyle("-fx-text-fill: BLACK");
        editGridPane.add(currentPassword, 0, 11);

        PasswordField currentPasswordField = new PasswordField();
        editGridPane.add(currentPasswordField, 1, 11);

        Label newPassword = new Label("new password:");
        newPassword.setStyle("-fx-text-fill: BLACK");
        editGridPane.add(newPassword, 0, 12);

        PasswordField newPasswordField = new PasswordField();
        editGridPane.add(newPasswordField, 1, 12);

        Button changeButton = new Button("change password");
        HBox changeBox = new HBox(10);
        changeBox.setAlignment(Pos.BOTTOM_RIGHT);
        changeBox.getChildren().add(changeButton);
        editGridPane.add(changeBox, 1, 13);

        changeButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Player.getPlayerByUsername(changeUserNameTextField.getText()) != null) {
                    if (Player.validateUsernameAndPassword(changeUserNameTextField.getText(), currentPasswordField.getText())) {
                        Player.getPlayerByUsername(changeUserNameTextField.getText()).setPassword(newPasswordField.getText());
                        changePassError.setStyle("-fx-text-fill: Blue");
                        changePassError.setText("password changed successfully");
                    } else {
                        changePassError.setStyle("-fx-text-fill: red");
                        changePassError.setText("password is wrong");
                    }
                } else {
                    changePassError.setStyle("-fx-text-fill: red");
                    changePassError.setText("there isn't any account registered with this username");
                }
            }

        });
        deleteBackButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                window.setScene(mainScene);
                changePassError.setText("");
                deleteError.setText("");
                userNameTextField.clear();
                passwordField.clear();
                changeUserNameTextField.clear();
                currentPasswordField.clear();
                newPasswordField.clear();
            }
        });
        editGridPane.add(changePassError, 0, 13);

        editGridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#1d4350 , #a43931)");
        editScene = new Scene(editGridPane, 600, 600);

        GridPane gamePane = new GridPane();

        gamePane.setHgap(10);
        gamePane.setVgap(10);
        gamePane.setPadding(new Insets(25, 25, 25, 25));

        Button scoreButton = new Button("Score board");
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER_LEFT);
        box.getChildren().add(scoreButton);
        gamePane.add(box, 15, 26);

        Button startButton = new Button("Start game");
        HBox startBox = new HBox(10);
        startBox.setAlignment(Pos.CENTER_RIGHT);
        startBox.getChildren().add(startButton);
        startBox.fillHeightProperty();
        gamePane.add(startBox, 16, 26);

        Button backBtn = new Button("Back");
        HBox backBox = new HBox(10);
        backBox.setAlignment(Pos.TOP_LEFT);
        backBox.getChildren().add(backBtn);
        gamePane.add(backBox, 0, 0);

        Button logoutButton = new Button("Logout");
        HBox logoutBox = new HBox(10);
        logoutBox.setAlignment(Pos.TOP_RIGHT);
        logoutBox.getChildren().add(logoutButton);
        gamePane.add(logoutBox, 32, 0);

        startButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Scene gameScene = new Scene(gamePage, 1000, 600);
                window.setScene(gameScene);
            }
        });

        scoreButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                GridPane scoreGridPane = new GridPane();
                scoreGridPane.setHgap(10);
                scoreGridPane.setVgap(10);
                scoreGridPane.setPadding(new Insets(25, 25, 25, 25));

                Button scoreBackButton = new Button("Back");
                HBox scoreBox = new HBox(10);
                scoreBox.setAlignment(Pos.TOP_LEFT);
                scoreBox.getChildren().add(scoreBackButton);
                scoreGridPane.add(scoreBox, 0, 0);

                scoreBackButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        window.setScene(startingScene);
                    }
                });
                TableView<Player> table = new TableView<>();
                table.getItems().addAll(ScoreBoard.getPlayerList());
                table.getColumns().addAll(ScoreBoard.getUserNameColumn(), ScoreBoard.getWinColumn(), ScoreBoard.getLosesColumn(),
                        ScoreBoard.getScoreColumn());
                table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                HBox hbox = new HBox(10);
                hbox.setAlignment(Pos.CENTER);
                hbox.getChildren().add(table);
                scoreGridPane.add(hbox, 6, 2);
                scoreGridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#1d4350 , #a43931)");
                scoreBoardScene = new Scene(scoreGridPane, 600, 600);
                window.setScene(scoreBoardScene);

            }
        });
        logoutButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                window.setScene(mainScene);
            }
        });
        backBtn.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                window.setScene(loginScene);
            }
        });
        gamePane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#1f1c2c , #928dab)");
        startingScene = new Scene(gamePane, 600, 600);

        GridPane loginGridPane = new GridPane();
        loginGridPane.setAlignment(Pos.TOP_LEFT);
        loginGridPane.setHgap(10);
        loginGridPane.setVgap(10);
        loginGridPane.setPadding(new Insets(25, 25, 25, 25));
        Text sceneText = new Text("Login");
        sceneText.setFont(Font.font("Tahoma", FontWeight.BOLD, 28));
        loginGridPane.add(sceneText, 0, 2, 2, 1);

        Label titleFirstUsername = new Label("Enter username and password for white player:");
        titleFirstUsername.setStyle("-fx-text-fill: BLACK");
        loginGridPane.add(titleFirstUsername, 0, 3);
        Label firstUserName = new Label("Username:");
        firstUserName.setStyle("-fx-text-fill: BLACK");
        loginGridPane.add(firstUserName, 0, 4);

        TextField firstUserNameTextField = new TextField();
        loginGridPane.add(firstUserNameTextField, 1, 4);

        Label firstPassword = new Label("Password:");
        firstPassword.setStyle("-fx-text-fill: BLACK");
        loginGridPane.add(firstPassword, 0, 5);

        PasswordField firstPasswordField = new PasswordField();
        loginGridPane.add(firstPasswordField, 1, 5);

        Label titleSecondUsername = new Label("Enter username and password for black player:");
        titleSecondUsername.setStyle("-fx-text-fill: BLACK");
        loginGridPane.add(titleSecondUsername, 0, 7);
        Label secondUserName = new Label("Username:");
        secondUserName.setStyle("-fx-text-fill: BLACK");
        loginGridPane.add(secondUserName, 0, 8);

        TextField secondUserNameTextField = new TextField();
        loginGridPane.add(secondUserNameTextField, 1, 8);

        Label secondPassword = new Label("Password:");
        secondPassword.setStyle("-fx-text-fill: BLACK");
        loginGridPane.add(secondPassword, 0, 9);

        PasswordField secondPasswordField = new PasswordField();
        loginGridPane.add(secondPasswordField, 1, 9);

        Button button = new Button("login");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(button);
        loginGridPane.add(hbBtn, 1, 10);

        Button loginBackButton = new Button("Back");
        HBox hBox = new HBox(10);
        hbBtn.setAlignment(Pos.TOP_LEFT);
        hBox.getChildren().add(loginBackButton);
        loginGridPane.add(hBox, 0, 0);


//TODO debug clearing field
        loginBackButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                window.setScene(mainScene);
                loginError.setText("");
                firstUserNameTextField.clear();
                firstPasswordField.clear();
                secondUserNameTextField.setText("");
                secondPasswordField.setText("");
            }
        });
        loginGridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#1d4350 , #a43931)");

        button.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (firstUserNameTextField.getText().trim().isEmpty() || secondUserNameTextField.getText().trim().isEmpty() ||
                        secondPasswordField.getText().trim().isEmpty() || firstPasswordField.getText().trim().isEmpty()) {
                    loginError.setStyle("-fx-text-fill: red");
                    loginError.setText("first fill the labels");
                } else {
                    if (Player.getPlayerByUsername(firstUserNameTextField.getText()) != null && Player.getPlayerByUsername(secondUserNameTextField.getText()) != null) {
                        if (Player.validateUsernameAndPassword(firstUserNameTextField.getText(), firstPasswordField.getText()) &&
                                Player.validateUsernameAndPassword(secondUserNameTextField.getText(), secondPasswordField.getText())) {
                            Player.setFirstPlayerName(firstUserNameTextField.getText());
                            Player.setSecondPlayerName(secondUserNameTextField.getText());
                            window.setScene(startingScene);
                            window.setMinWidth(300);
                            window.setMinHeight(300);
                            firstUserNameTextField.clear();
                            firstPasswordField.clear();
                        } else {
                            loginError.setStyle("-fx-text-fill: red;");
                            loginError.setText("wrong username or password");
                        }
                    } else {
                        loginError.setStyle("-fx-text-fill: red;");
                        loginError.setText("first register player with this username");
                    }
                }
            }

        });
        loginGridPane.add(loginError, 0, 10);
        loginScene = new Scene(loginGridPane, 600, 600);

        GridPane signUpGridPane = new GridPane();
        signUpGridPane.setAlignment(Pos.CENTER);
        signUpGridPane.setHgap(10);
        signUpGridPane.setVgap(10);
        signUpGridPane.setPadding(new Insets(25, 25, 25, 25));
        Text signUpSceneText = new Text("Register");
        signUpSceneText.setFont(Font.font("Tahoma", FontWeight.BOLD, 28));
        signUpGridPane.add(signUpSceneText, 0, 2, 2, 1);

        Label userNameSU = new Label("Username:");
        userNameSU.setStyle("-fx-text-fill: BLACK");
        signUpGridPane.add(userNameSU, 0, 3);

        TextField userNameTextFieldSU = new TextField();
        signUpGridPane.add(userNameTextFieldSU, 1, 3);

        Label passwordSU = new Label("Password:");
        passwordSU.setStyle("-fx-text-fill: BLACK");
        signUpGridPane.add(passwordSU, 0, 4);

        PasswordField passwordFieldSU = new PasswordField();
        signUpGridPane.add(passwordFieldSU, 1, 4);

        Button buttonSU = new Button("Sign up");
        HBox hbBtnSU = new HBox(10);
        hbBtnSU.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtnSU.getChildren().add(buttonSU);
        signUpGridPane.add(hbBtnSU, 1, 6);

        Button SUBackButton = new Button("Back");
        HBox SUBox = new HBox(10);
        SUBox.setAlignment(Pos.TOP_LEFT);
        SUBox.getChildren().add(SUBackButton);
        signUpGridPane.add(SUBackButton, 0, 0);

        SUBackButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                window.setScene(mainScene);
                SUError.setText("");
                userNameTextFieldSU.clear();
                passwordFieldSU.clear();
            }
        });

        registerScene = new Scene(signUpGridPane, 600, 600);
        buttonSU.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (userNameTextFieldSU.getText().trim().isEmpty() || passwordFieldSU.getText().trim().isEmpty()) {
                    SUError.setStyle("-fx-text-fill: red");
                    SUError.setText("first fill labels");
                } else {

                    if (Player.getPlayerByUsername(userNameTextFieldSU.getText()) == null) {
                        new Player(userNameTextFieldSU.getText(), passwordFieldSU.getText());
                        userNameTextFieldSU.clear();
                        passwordFieldSU.clear();
                        SUError.setStyle("-fx-text-fill: #151c80");
                        SUError.setText(("you have been successfully registered"));
                    } else {
                        SUError.setStyle("-fx-text-fill: red;");
                        SUError.setText("There is a player registered with this username");
                    }
                }
            }
        });
        signUpGridPane.add(SUError, 0, 6);
        signUpGridPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%,#1d4350 , #a43931)");
    }

    public static void main(String[] args) {
        launch(args);
    }


}