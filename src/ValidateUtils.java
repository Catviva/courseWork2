public class ValidateUtils {

    public static String checkString(String str) throws IncorrectArgumentException {
        if (str == null || str.isEmpty() || str.isBlank()) {
            throw new IncorrectArgumentException("Некорректный ввод");
        } else {
            return str;
        }
    }
}
