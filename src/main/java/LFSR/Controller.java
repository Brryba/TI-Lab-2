package LFSR;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

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
    @FXML
    private Label lengthShower;

    private List<Byte> plainBytesList;

    private List<Byte> keyBytesList;

    private List<Byte> cipherBytesList;

    public final static int REGISTER_LENGTH = 35;

    public final static int DISPLAY_MAX_SIZE = 10;

    @FXML
    public void openFile() {
        plainBytesList = FileUtil.readFile();
        if (plainBytesList != null) {
            plainBits.setText(Parser.parseStringToBinary(plainBytesList));
            outputBits.setText("");
        }
    }

    @FXML
    public void saveFile() {
        FileUtil.writeFile(cipherBytesList);
    }

    private void addToShowingArrayIfNeeded(byte nextByte, int position) {
        if (plainBytesList.size() < 2 * DISPLAY_MAX_SIZE || position < DISPLAY_MAX_SIZE
                || position >= plainBytesList.size() - DISPLAY_MAX_SIZE) {
            keyBytesList.add(nextByte);
        }
    }

    @FXML
    public void cipher() {
        if (!isKeyValid(registerStart.getText())) {
            return;
        }
        if (plainBytesList == null || plainBytesList.isEmpty()) {
            showError("Сначала надо выбрать файл для шифрования");
            return;
        }

        KeyGenerator.keyBitsArray = Parser.parseBinaryToBitArray(registerStart.getText());
        keyBytesList = new ArrayList<>();
        cipherBytesList = new ArrayList<>();
        for (int i = 0; i < plainBytesList.size(); i++) {
            byte next = KeyGenerator.generateByteKey();
            addToShowingArrayIfNeeded(next, i);
            cipherBytesList.add((byte) (next ^ plainBytesList.get(i)));
        }


        keyBits.setText(Parser.parseKeyString(keyBytesList));
        outputBits.setText(Parser.parseStringToBinary(cipherBytesList));
    }

    private boolean isKeyValid(String key) {
        if (key.length() != REGISTER_LENGTH) {
            System.out.println(key.length());
            showError("Введите ключ корректной длины");
            return false;
        }
        for (char c : key.toCharArray()) {
            if (c != '1' && c != '0') {
                showError("Ключ должен состоять из 0 и 1");
                return false;
            }
        }
        return true;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    public void showKeyLength() {
        lengthShower.setText("" + registerStart.getText().length());
    }
}