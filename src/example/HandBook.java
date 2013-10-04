package example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: 045B
 * Date: 03.10.13
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class HandBook {

    private final static String FILE_PATH = "d:/current/handBook.txt";
    private final static String NOT_FOUND = "Theme not found!";
    private final static String BAD_FILE = "Bad file";
    private final static String CANNOT_OPEN_FILE = "Cannot open file";

    public String getInfo(String theme) {

        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE_PATH)));
            String line;
            String beginStr = transformToStartElement(theme);
            String endStr = transformToEndElement(theme);
            boolean hasEnd = false;
            while ((line = reader.readLine()) != null) {
                if (beginStr.equals(line)) {
                    while ((line = reader.readLine()) != null) {
                        if (endStr.equals(line)) {
                            hasEnd = true;
                            break;
                        }
                        result.append(line).append('\n');
                    }
                    break;
                }
            }
            if (result.length() == 0) {
                result = new StringBuilder(NOT_FOUND);
            }
            if (hasEnd == false) {
                result = new StringBuilder(BAD_FILE);
            }
            reader.close();
        } catch (IOException ioe) {
            result = new StringBuilder(CANNOT_OPEN_FILE);
        }
//        System.out.println(result);
        return result.toString();
    }

    private String transformToStartElement (String text) {
        return new StringBuilder()
                .append('<')
                .append(text)
                .append('>').toString();
    }

    private String transformToEndElement (String text) {
        return new StringBuilder()
                .append("</")
                .append(text)
                .append('>').toString();
    }

}
