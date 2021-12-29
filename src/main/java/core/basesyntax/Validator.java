package core.basesyntax;

public class Validator {
    public static boolean isNotNull(String... data) {
        for (String date : data) {
            if (date == null) {
                throw new DataException("String is null");
            }
        }
        return true;
    }

    public static boolean isDateContainsOnlyNumbers(String... data) {
        for (String date : data) {
            String[] separatedDate = date.split("\\.");
            try {
                for (String partOfDate : separatedDate) {
                    int number = Integer.parseInt(partOfDate);
                }
            } catch (NumberFormatException e) {
                throw new DataException("Incorrect date");
            }
        }
        return true;
    }

    public static boolean isNumbers(String... data) {
        for (String str : data) {
            try {
                if (str == null) {
                    throw new NullPointerException();
                }
                int number = Integer.parseInt(str);
            } catch (NumberFormatException e) {
                throw new DataException("Incorrect number");
            } catch (NullPointerException e) {
                throw new DataException("Number is null");
            }
        }
        return true;
    }

    public static boolean isCorrectDateLength(String... data) {
        for (String date : data) {
            if (date.length() != 10) {
                throw new DataException("Invalid date");
            }
        }
        return true;
    }
    
    public static boolean isValidDate(String... data) {
        boolean isValid = true;
        for (String date : data) {
            isValid = isValid && isDateContainsOnlyNumbers(date)
                    && isCorrectDateLength(date) && isNotNull(date);
        }
        return isValid;
    }

}
