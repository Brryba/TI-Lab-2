package LFSR;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.List;

public class Controller {
    @FXML private MenuItem menuOpen;
    @FXML private MenuItem menuSave;
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

    @FXML
    public List<Byte> openFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try (InputStream is = new FileInputStream(file)) {
                plainBytesArray = is.readAllBytes();
            } catch (FileNotFoundException e) {
                System.err.println("File not found");
                return null;
            } catch (IOException e) {
                System.err.println("I/O Error");
            }

        }
        return null;
    }

    public void saveFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.getExtensionFilters().addAll();
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try (OutputStream os = new FileOutputStream(file)) {
                os.write(plainBytesArray);
            } catch (FileNotFoundException e) {
                System.err.println("File not found");
            } catch (IOException e) {
                System.err.println("I/O Error");
            }
        }
    }
}