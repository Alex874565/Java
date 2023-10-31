package Database;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface ValidateFields {
    static final Pattern EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    static final Pattern PASS_REGEX = Pattern.compile("^(?=\\P{Ll}*\\p{Ll})(?=\\P{N}*\\p{N})[\\s\\S]{8,}$");


    default boolean validateEmail(String emailStr) {
            Matcher matcher = EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.matches();
    }

    default boolean validatePass(String pass){
        Matcher matcher = PASS_REGEX.matcher(pass);
        return matcher.matches();
    }
}
