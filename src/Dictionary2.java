import java.io.*;

public class Dictionary2 extends Dictionary {
    @Override
    public void load(String fileName) throws IOException {
        if (!fileName.endsWith(".txt")) throw new IOException("Некорректное расширение файла. Должно быть .txt");
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
            return;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] pair = line.split(":");
            if (pair.length == 2
                    && pair[0].matches("\\d{5}")
                    && pair[1].matches("[" + valueAlphabet + "]+")) {
                dictionary.put(pair[0], pair[1]);
            }
        }
        reader.close();
    }
    @Override
    public void put(String key, String value) {
        if (key.matches("\\d{5}")
                && value.matches("[" + valueAlphabet + "]+")) {
            dictionary.put(key, value);
        }
    }
}
