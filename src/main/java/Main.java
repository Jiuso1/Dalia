import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String args[]) {
        String sentence = "";
        CmdExecutor cmdExecutor = new CmdExecutor();
        File listFile = new File("src/main/resources/list.txt");
        File outFile = new File("src/main/resources/out.mp3");
        String absoluteListPath = listFile.getAbsolutePath();
        String absoluteOutPath = outFile.getAbsolutePath();
        String command = "ffmpeg -f concat -safe 0 -i " + absoluteListPath + " -c copy " + absoluteOutPath;//We use FFmpeg to concatenate audios. Source of the command: https://trac.ffmpeg.org/wiki/Concatenate

        System.out.println("Enter a sentence:");
        sentence = System.console().readLine();
        sentence = sentence.replace(' ', '-');
        sentence = '-' + sentence + '-';
        System.out.println("Sentence speech: " + sentence);
        System.out.println("Generating speech...");
        for (int i = 1; i < sentence.length(); i++) {
            System.out.println("Searching diphone " + sentence.charAt(i - 1) + sentence.charAt(i));
        }

        /*if (outFile.exists()) {//If the output file already exists:
            outFile.delete();//The output file is removed.
        }

        try {
            cmdExecutor.execCmd(command);//Command is executed to create the output file.
            System.out.println("Speech generation completed.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }*/
    }
}
