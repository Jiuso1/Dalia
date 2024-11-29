import java.util.Scanner;

public class CmdExecutor {

    //Based on source: https://stackoverflow.com/questions/5711084/java-runtime-getruntime-getting-output-from-executing-a-command-line-program
    public String execCmd(String cmd) throws java.io.IOException {
        String error = "";//Error output.
        String normal = "";//Normal output.
        String output = "";//Full output.
        String nextString = "";//Temporal string to save each element.
        Process commandProcess = Runtime.getRuntime().exec(cmd);//Command execution process.
        Scanner errorScanner = new java.util.Scanner(commandProcess.getErrorStream());//Error output scanner.
        Scanner normalScanner = new java.util.Scanner(commandProcess.getInputStream());//Normal output scanner.

        while (errorScanner.hasNext()) {//While we haven't scanned all error strings:
            nextString = errorScanner.next();//nextString values the current errorScanner word.
            if (nextString.contains(".")) {//If nextString contains a dot:
                error += nextString + "\n";//It's saved in error output with an end line.
            } else {//If nextString doesn't contain a dot:
                error += nextString + " ";//It's saved in error output with a space.
            }
        }

        while (normalScanner.hasNext()) {//While we haven't scanned all normal strings:
            nextString = normalScanner.next();//nextString values the current normalScanner word.
            if (nextString.contains(".")) {//If nextString contains a dot:
                normal += nextString + "\n";//It's saved in normal output with an end line.
            } else {//If nextString doesn't contain a dot:
                normal += nextString + " ";//It's saved in normal output with a space.
            }
        }

        //output string is the union of normal and error output:
        output += normal;
        output += error;

        return output;//output is returned.
    }

}
