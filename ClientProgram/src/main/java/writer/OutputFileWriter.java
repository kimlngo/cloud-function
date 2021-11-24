package writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFileWriter {
    public void writeData(String filePath, String data) {
        try (FileWriter writer = new FileWriter(filePath, true);
                BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(String.format("%s\n", data));

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
