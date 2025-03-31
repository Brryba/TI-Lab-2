package LFSR;

import javafx.stage.FileChooser;

import java.io.*;

public class FileUtil {
    public static byte[] readFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll();
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            try (InputStream is = new FileInputStream(file)) {
                return is.readAllBytes();
            } catch (FileNotFoundException e) {
                System.err.println("File not found");
                return null;
            } catch (IOException e) {
                System.err.println("I/O Error");
            }

        }
        return null;
    }

    public static void writeFile(byte[] plainBytesArray) {
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
