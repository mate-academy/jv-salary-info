package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ");
        int[] salary = new int[names.length];
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate dateBegin = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);

        result.append(dateFrom + " - " + dateTo);

        for (String tempDataString : data) {
            LocalDate date = LocalDate.parse(getParameter(tempDataString), formatter);
            if ((date.isAfter(dateBegin) || date.equals(dateBegin))
                    && (date.isBefore(dateEnd) || date.equals(dateEnd))) {
                tempDataString = removeParameterFromText(tempDataString);
                String name = getParameter(tempDataString);
                for (int j = 0; j < names.length; j++) {
                    if (names[j].equals(name)) {
                        tempDataString = removeParameterFromText(tempDataString);
                        int hour = Integer.parseInt(getParameter(tempDataString));
                        tempDataString = removeParameterFromText(tempDataString);
                        int salaryHour = Integer.parseInt(tempDataString);
                        salary[j] = salary[j] + (salaryHour * hour);
                    }
                }
            }
        }
        for (int i = 0; i < names.length; i++) {
            result.append("\n" + names[i] + " - " + salary[i]);
        }
        return result.toString();
    }

    private String getParameter(String parameter) {
        return parameter.substring(0, parameter.indexOf(' ')).strip();
    }

    private String removeParameterFromText(String oldText) {
        return oldText.replaceFirst(oldText.substring(0, oldText.indexOf(' ')), "").strip();
    }

}
