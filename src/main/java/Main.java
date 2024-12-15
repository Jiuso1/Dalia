import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String args[]) {
        String sentence = "";
        CmdExecutor cmdExecutor = new CmdExecutor();
        File listFile = new File("src/main/resources/list.txt");
        File outFile = new File("src/main/resources/out.wav");
        String absoluteListPath = listFile.getAbsolutePath();
        String absoluteOutPath = outFile.getAbsolutePath();
        String command = "ffmpeg -f concat -safe 0 -i " + absoluteListPath + " -c copy " + absoluteOutPath;//We use FFmpeg to concatenate audios. Source of the command: https://trac.ffmpeg.org/wiki/Concatenate
        ArrayList<String> diphoneList = new ArrayList<>();
        BufferedWriter listFileWriter = null;

        System.out.println("Enter a sentence:");
        sentence = System.console().readLine();
        sentence = sentence.replace(' ', '-');

        sentence = '-' + sentence + '-';
        System.out.println("Sentence speech: " + sentence);
        System.out.println("Generating speech...");
        for (int i = 1; i < sentence.length(); i++) {
            String diphone = String.valueOf(sentence.charAt(i - 1)) + String.valueOf(sentence.charAt(i));//https://stackoverflow.com/questions/8172420/how-to-convert-a-char-to-a-string
            diphoneList.add(diphone);
        }

        if (outFile.exists()) {//If the output file already exists:
            outFile.delete();//The output file is removed.
        }

        try {
            listFileWriter = new BufferedWriter(new FileWriter(absoluteListPath));
            for (int i = 0; i < diphoneList.size(); i++) {
                listFileWriter.write("file 'C:\\Users\\jesus\\Documents\\java\\ph\\Dalia\\src\\main\\resources\\" + diphoneList.get(i).replaceAll("A", "a!") + ".wav" + "'\n");
            }
            listFileWriter.close();
            cmdExecutor.execCmd(command);//Command is executed to create the output file.
            System.out.println("Speech generation completed.");
            System.out.println("Playing out.wav.");
            cmdExecutor.execCmd("explorer " + absoluteOutPath);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
