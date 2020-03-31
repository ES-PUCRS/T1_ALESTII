import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;
import java.lang.String;
import java.io.File;

import java.time.LocalDateTime;
import java.time.Duration;


public class FileManager {

    public static String load(String file, String space) throws FileNotFoundException {

        LocalDateTime beginProg = LocalDateTime.now();

        String path = Paths.get("").toAbsolutePath().toString();
        path = path.replaceAll("src", "");
        char separator = path.charAt(path.length() - 1);
        path = path + "resources" + separator;

        File filepath = new File(path + file);
        Scanner data = new Scanner(filepath);
        String line = space;

        while (data.hasNext()) {
            line += data.nextLine() + space;
        }
    
        data.close();
        return save(path, file, line, beginProg);
    }
    
    public static String save(String path ,String file, String line, LocalDateTime beginProg) throws FileNotFoundException {
        File filepath = new File(path + "remake_" + file);
        PrintWriter data = new PrintWriter(filepath);
        data.print(line);
        data.close();
        closeTime(beginProg);
        return line;
    }


    public static void closeTime(LocalDateTime beginProg){
        StringBuilder str = new StringBuilder();
        LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(beginProg, now);

            str.append("Program runned on ")
               .append(duration.toDays())
               .append(" Days, ")
               .append(duration.toHours())
               .append(" hours, ")
               .append(duration.toMinutes())
               .append(" Minutes, ")
               .append(duration.toSeconds())
               .append(" Seconds, ")
               .append(duration.toMillis())
               .append(" Milli. ");
        System.out.println("\n" + str);
    }
}