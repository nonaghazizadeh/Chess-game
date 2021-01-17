package Others;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScoreBoard {
    public static ObservableList<Player> getPlayerList() {
        ObservableList data = FXCollections.observableArrayList();
        data.addAll(Player.getAllPlayers());
        return data;
    }

    public static TableColumn<Player, String> getUserNameColumn() {
        TableColumn<Player, String> userNameCol = new TableColumn<>("UserName");
        PropertyValueFactory<Player, String> usernameCellValueFactory = new PropertyValueFactory<>("username");
        userNameCol.setCellValueFactory(usernameCellValueFactory);
        return userNameCol;
    }

    public static TableColumn<Player, Integer> getWinColumn() {
        TableColumn<Player, Integer> winCol = new TableColumn<>("Win");
        PropertyValueFactory<Player, Integer> winCellValueFactory = new PropertyValueFactory<>("wins");
        winCol.setCellValueFactory(winCellValueFactory);
        return winCol;
    }

    public static TableColumn<Player, Integer> getLosesColumn() {
        TableColumn<Player, Integer> losesCol = new TableColumn<>("Loses");
        PropertyValueFactory<Player, Integer> losesCellValueFactory = new PropertyValueFactory<>("loses");
        losesCol.setCellValueFactory(losesCellValueFactory);
        return losesCol;
    }

    public static TableColumn<Player, Integer> getScoreColumn() {
        TableColumn<Player, Integer> scoreCol = new TableColumn<>("Score");
        PropertyValueFactory<Player, Integer> scoreCellValueFactory = new PropertyValueFactory<>("score");
        scoreCol.setCellValueFactory(scoreCellValueFactory);
        return scoreCol;
    }


}