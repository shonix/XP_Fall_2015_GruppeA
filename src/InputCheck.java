import java.util.regex.Pattern;

/**
 * @author Markus
 */

public class InputCheck
{

    private static final Pattern patternIP = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
    private static final Pattern patternPort = Pattern.compile("\\d+");
    private static final Pattern patternUser = Pattern.compile("^(?=.*[a-zA-Z])[a-zA-Z0-9]+$");
    private static final Pattern patternPassword = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$");

 /*
 *         InputCheck chackes the input if it is valid.
 *         IP must follow the IPv4 standard format. (eg. 192.168.1.1)
 *         Port number must be between 2000 - 65000
 *         User name must be between 6 - 12 characters long, and can't contain special characters.
 *         Password must contain both letters and numbers, and must be between 8 - 16 characters long.
 *         Message must be longer then 0.
 */
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

    //must follow standard ip format eg. 192.168.1.1
    private static boolean checkIP(String input)
    {
        boolean valid = false;
        int length = input.length();
        if (length <= 15)
        {
            valid = patternIP.matcher(input).matches();
        }
        return valid;
    }

    //must be 4 long and only contain numbers
    private static boolean checkPort(String input)
    {
        boolean valid = false;
        if (patternPort.matcher(input).matches())
        {
            int value = Integer.parseInt(input);
            if(value >= 2000 && value <= 65000) valid = true;
        }
        return valid;
    }

    //must contain a letter and be 6-12 characters long, and must not contain special characters 
    private static boolean checkUser(String input)
    {
        boolean valid = false;
        int length = input.length();
        if (length <= 12 && length >= 6)
        {
            valid = patternUser.matcher(input).matches();
        }
        return valid;
    }

    //must contain both letters and numbers, must be 8-16 characters long an must not contain special characters
    private static boolean checkPassword(String input)
    {
        boolean valid = false;
        int length = input.length();
        if (length <= 16 && length >= 8)
        {
            valid = patternPassword.matcher(input).matches();
        }
        return valid;
    }

    //must be longer than 0 characters
    private static boolean checkMessage(String input)
    {
        boolean valid = false;
        int length = input.length();
        if (length > 0)
        {
            valid = true;
        }
        return valid;
    }
}
