package com.example.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btn00;

    @FXML
    private Button btn01;

    @FXML
    private Button btn02;

    @FXML
    private Button btn10;

    @FXML
    private Button btn11;

    @FXML
    private Button btn12;

    @FXML
    private Button btn20;

    @FXML
    private Button btn21;

    @FXML
    private Button btn22;
    @FXML
    private Text who;

    private boolean vis[][] = new boolean[3][3];
    private char grid[][] = new char[3][3];
    private int turn;
    @FXML
    void onClickBtn00(ActionEvent event) {
        ToggleButton(btn00, 0, 0);
    }

    @FXML
    void onClickBtn01(ActionEvent event) {
        ToggleButton(btn01, 0, 1);
    }

    @FXML
    void onClickBtn02(ActionEvent event) {
        ToggleButton(btn02, 0, 2);
    }

    @FXML
    void onClickBtn10(ActionEvent event) {
        ToggleButton(btn10, 1, 0);
    }

    @FXML
    void onClickBtn11(ActionEvent event) {
        ToggleButton(btn11, 1, 1);
    }

    @FXML
    void onClickBtn12(ActionEvent event) {
        ToggleButton(btn12, 1, 2);
    }

    @FXML
    void onClickBtn20(ActionEvent event) {
        ToggleButton(btn20, 2, 0);
    }

    @FXML
    void onClickBtn21(ActionEvent event) {
        ToggleButton(btn21, 2, 1);
    }

    @FXML
    void onClickBtn22(ActionEvent event) {
        ToggleButton(btn22, 2, 2);
    }

    ImageView setIcon(String name) {
        ImageView imgView = new ImageView();
        try {
            FileInputStream file = new FileInputStream("C:\\Users\\Eleus Ahammad\\IdeaProjects\\TicTacToe\\src\\icons\\" + name);
            Image img = new Image(file);
            imgView = new ImageView(img);
        }
        catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }
        return imgView;
    }

    void ToggleButton(Button btn, int x, int y) {
        if (turn%2 == 0 && !vis[x][y]) {
            btn.setGraphic(setIcon("XX.png"));
            vis[x][y] = true;
            grid[x][y] = 'x';
            turn++;
            changeGameState("Player 1 wins", "Player 2's turn");
        }
        if (turn%2 == 1 && !vis[x][y]) {
            btn.setGraphic(setIcon("OO.png"));
            vis[x][y] = true;
            grid[x][y] = 'o';
            turn++;
            changeGameState("Player 2 wins", "Player 1's turn");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn00.setGraphic(setIcon("W.png"));
        btn01.setGraphic(setIcon("W.png"));
        btn02.setGraphic(setIcon("W.png"));
        btn10.setGraphic(setIcon("W.png"));
        btn11.setGraphic(setIcon("W.png"));
        btn12.setGraphic(setIcon("W.png"));
        btn20.setGraphic(setIcon("W.png"));
        btn21.setGraphic(setIcon("W.png"));
        btn22.setGraphic(setIcon("W.png"));

        who.setText("Player 1's turn");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                vis[i][j] = false;
                grid[i][j] = ' ';
            }
        }
        turn = 0;
    }

    boolean checkResult() {
        for (int i = 0; i < 3; i++) {
            int row = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'x') row++;
            }
            if (row == 3) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            int row = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 'o') row++;
            }
            if (row == 3) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            int row = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[j][i] == 'o') row++;
            }
            if (row == 3) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            int row = 0;
            for (int j = 0; j < 3; j++) {
                if (grid[j][i] == 'x') row++;
            }
            if (row == 3) {
                return true;
            }
        }
        int row = 0;
        for (int i = 0;i < 3; i++) {
            if (grid[i][i] == 'x') row++;
        }
        if (row == 3) return true;
        row = 0;
        for (int i = 0;i < 3; i++) {
            if (grid[i][i] == 'o') row++;
        }
        if (row == 3) return true;
        row = 0;
        for (int i = 0;i < 3; i++) {
            if (grid[i][3 - i - 1] == 'x') row++;
        }
        if (row == 3) return true;
        row = 0;
        for (int i = 0;i < 3; i++) {
            if (grid[i][3 - i - 1] == 'o') row++;
        }
        if (row == 3) return true;
        return false;
    }
    void allVisited() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                vis[i][j] = true;
            }
        }
    }
    void changeGameState(String msg1, String msg2) {
        if (checkResult()) {
            who.setText(msg1);
            allVisited();
        }
        else if (turn == 9) {
            who.setText("It's a draw");
        }
        else {
            who.setText(msg2);
        }
    }
}