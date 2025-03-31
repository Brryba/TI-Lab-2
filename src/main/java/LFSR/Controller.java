package LFSR;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
    @FXML
    private MenuItem menuOpen;
    @FXML
    private MenuItem menuSave;
    @FXML
    private TextField registerStart;
    @FXML
    private Button startCipher;
    @FXML
    private TextArea plainBits;
    @FXML
    private TextArea keyBits;
    @FXML
    private TextArea outputBits;

    private byte[] plainBytesArray;

    private byte[] keyBitsArray;

    private byte[] outputBytesArray;

    public final static int REGISTER_LENGTH = 35;

    public final static int DISPLAY_MAX_SIZE = 10;

    @FXML
    public void openFile() {
        plainBytesArray = FileUtil.readFile();
        if (plainBytesArray !=null) {
            plainBits.setText(Parser.parseStringToBinary(plainBytesArray));
        } else {
            showError("Empty File");
        }
    }

    @FXML
    public void saveFile() {
        FileUtil.writeFile(plainBytesArray);
    }

    @FXML
    public void cipher() {
        keyBitsArray = Parser.parseBinaryToBitArray(registerStart.getText());
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }
}