import java.util.regex.Pattern;
/**
 *
 * @author Markus
 */
public class InputCheck
{

    private static final Pattern patternIP = Pattern.compile(
            "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    private static final Pattern patternPort = Pattern.compile(
            "\\d+");
    private static final Pattern patternUser = Pattern.compile(
            "^(?=.*[a-zA-Z])[a-zA-Z0-9]+$");
    private static final Pattern patternPassword = Pattern.compile(
            "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$");

    //Call with inputType as IP, Port, User, Password or Message
    public static boolean isValid(String inputType, String inputValue)
    {
        boolean valid = false;
        switch (inputType)
        {
            case "IP":
                valid = checkIP(inputValue);
                break;
            case "Port":
                valid = checkPort(inputValue);
                break;
            case "User":
                valid = checkUser(inputValue);
                break;
            case "Password":
                valid = checkPassword(inputValue);
                break;
            case "Message":
                valid = checkMessage(inputValue);
                break;
        }
        return valid;
    }

    private static boolean checkIP(String input)
    {
        boolean valid = false;
        int length = input.length();
        if (length <= 15 && length > 0)
        {
            valid = patternIP.matcher(input).matches();
        }
        return valid;
    }

    private static boolean checkPort(String input)
    {
        boolean valid = false;
        if (input.length() == 4)
        {
            valid = patternPort.matcher(input).matches();
        }
        return valid;
    }

    private static boolean checkUser(String input)
    {
        boolean valid = false;
        int length = input.length();
        if (length <= 12 && length > 6)
        {
            valid = patternUser.matcher(input).matches();
        }
        return valid;
    }

    private static boolean checkPassword(String input)
    {
        boolean valid = false;
        int length = input.length();
        if (length <= 16 && length > 8)
        {
            valid = patternPassword.matcher(input).matches();
        }
        return valid;
    }

    private static boolean checkMessage(String input)
    {
        boolean valid = false;
        int length = input.length();
        if(length > 0) valid = true;
        return valid;
    }
}
