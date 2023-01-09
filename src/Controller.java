import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
public class Controller {
    public Label labelPool;
    public TextField textGetBet;
    public Button btnLower;
    public Label lowerResult1;
    public Label lowerResult2;
    public Button btnRoll;
    public Button btnHigher;
    public Label higherResult1;
    public Label higherResult2;
    public Label resultLabel1;
    public Label resultLabel2;
    public int pool = 1000;

    public int diceMemory;
    public int betMemory;
//method for resetting bet memory
    public void resetBetMemory(){
        betMemory = 0;
    }
    //method to restrict two buttons from being pressed
    public void buttonRestrict(){
        btnHigher.setDisable(true);
        btnLower.setDisable(true);
    }
//method that runs instantly when the gui opens
    public void initialize() {
        //sets cursor to be a hand when hovering over these 3 buttons
        btnLower.setCursor(Cursor.HAND);
        btnHigher.setCursor(Cursor.HAND);
        btnRoll.setCursor(Cursor.HAND);
        //just some formatting for showing the amount of money the user has
        labelPool.setText("pool: " + pool);
        //importing class so the user cannot roll the initial die until you have written in the bet text field.
        BooleanProperty textFieldEmpty = new SimpleBooleanProperty(true);
        btnRoll.disableProperty().bind(textFieldEmpty);
        textGetBet.textProperty().addListener((observable, oldValue, newValue) -> {textFieldEmpty.set(newValue.isEmpty());});
        //restricting higher/lower buttons
        buttonRestrict();
    }

    public void rollDice(ActionEvent actionEvent) {
        //resetting text for every new roll
        higherResult1.setText("");
        higherResult2.setText("");
        lowerResult1.setText("");
        lowerResult2.setText("");
        //setting num as whatever the user enters into the GetBet textfield
        int num = Integer.parseInt(textGetBet.getText());
        //stuff for keeping track of bets/pool
        betMemory +=num;
        pool -= num;
        //updates the pool amount so the user is always informed
        labelPool.setText("pool: " + pool);
        //setting diceroll1 to roll from 1 - 6 randomly
        int diceRoll1 = (int)(Math.random() * 6) + 1;
        //setting diceroll1 to an int i can then use to compare for the higher/lower methods below
        diceMemory = diceRoll1;
        //formatting to show the user what was rolled
        resultLabel1.setText("You rolled a " + diceRoll1);
        //enable higher and lower buttons
        btnHigher.setDisable(false);
        btnLower.setDisable(false);
        //clear bet text field
        textGetBet.clear();



    }
        // bet lower method for clicking lower button
    public void betLower(ActionEvent actionEvent) {
        //same dice roll as above
        int diceRoll = (int)(Math.random() * 6) + 1;
        resultLabel2.setText("You rolled a " + diceRoll);
        //if diceroll is smaller than dice memory you win
        if(diceRoll<diceMemory){
            //showing user they won
            lowerResult1.setText("Correct! ");
            lowerResult2.setText("You Win!");
            //winning pays out 2:1
            pool += betMemory*2;
            //updating pool to show winnings
            labelPool.setText("pool: " + pool);
            //reset bet memory
            resetBetMemory();
            //re disable higher and lower buttons
            btnHigher.setDisable(true);
            btnLower.setDisable(true);

        }
        //if equal or more than dice roll you lose
        else if(diceRoll>=diceMemory){
            //showing user they lost
            lowerResult1.setText("Incorrect! ");
            lowerResult2.setText("You Lose!");
            //updating pool to show loss of points/money
            labelPool.setText("pool: " + pool);
            //re disable higher and lower buttons
            btnHigher.setDisable(true);
            btnLower.setDisable(true);
            //reset bet memory
            resetBetMemory();
        }

    }
        //bet higher method for clicking higher button
    public void betHigher(ActionEvent actionEvent) {
        //same dice roll random stuff
        int diceRoll = (int)(Math.random() * 6) + 1;
        resultLabel2.setText("You rolled a " + diceRoll);
        //if dicerolls is greater than dice memory you win
        if(diceRoll>diceMemory) {
            //showing user the win
            higherResult1.setText("Correct! ");
            higherResult2.setText("You Win!");
            //pays 2:1
            pool += betMemory*2;
            //resetting pool to show updated winnings
            labelPool.setText("pool: " + pool);
            //resetting bet memory
            resetBetMemory();
            //re disabling higher lower buttons
            btnHigher.setDisable(true);
            btnLower.setDisable(true);
        }
        //if diceroll is equal or less than dice memory you lose
        else if(diceRoll<=diceMemory){
            //showing loss
            higherResult1.setText("Incorrect! ");
            higherResult2.setText("You Lose!");
            //updating pool to show loss
            labelPool.setText("pool: " + pool);
            //re disable higher lower buttons
            btnHigher.setDisable(true);
            btnLower.setDisable(true);
            //resetting bet memory
            resetBetMemory();
        }

    }

}
