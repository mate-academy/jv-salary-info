package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            LocalDate dateFromFormat = getParsedDate(dateFrom);
            LocalDate dateToFormat = getParsedDate(dateTo);
            String userInfo = "";
            StringBuilder strBld = new StringBuilder();//to append result
            String result = "";

            strBld.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

            for (int i = 0; i < names.length; i++) {
                int totalSalary = 0;
                for (int j = 0; j < data.length; j++) {
                    String[] arrDataSplit = data[j].split(" ");
                    LocalDate userDate = getParsedDate(arrDataSplit[0]);
                    if (names[i].equals(arrDataSplit[1]) && (
                            userDate.isAfter(dateFromFormat) &&
                                    userDate.isBefore(dateToFormat) ||
                                    userDate.equals(dateFromFormat) ||
                                    userDate.equals(dateToFormat))) {
                        totalSalary += (Integer.parseInt(arrDataSplit[2]))
                                * (Integer.parseInt(arrDataSplit[3]));
                    }
                    userInfo = (names[i] + " - " + totalSalary);
                }
                strBld.append(System.lineSeparator()).append(userInfo);
            }

            result = strBld.toString();
            System.out.println(result);
            return result;
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Wrong date format", e);
        }
    }

    private LocalDate getParsedDate(String dateForm) throws DateTimeParseException {
        DateTimeFormatter myDateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");//set format we need
        return LocalDate.parse(dateForm, myDateFormat); //dateForm - get this, myDateFormat - make this
    }
}
