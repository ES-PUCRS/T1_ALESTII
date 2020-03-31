import java.io.FileNotFoundException;

public class app {

    public static final String extension = ".txt";

    public static void main(String args[]) throws FileNotFoundException {
        try{
        	
        	String separator;

        	if (args.length < 2) 
        		separator = ";";
        	else
        		separator = args[1];

           System.out.print(FileManager.load(args[0] + extension, separator));
           
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}