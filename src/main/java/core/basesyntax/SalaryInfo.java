package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder resultString = new StringBuilder();
        LocalDate formattedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate formattedDateTo = LocalDate.parse(dateTo, FORMATTER);

        if (formattedDateFrom.isAfter(formattedDateTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        resultString
                .append("Отчёт за период ")
                .append(formattedDateFrom.format(FORMATTER))
                .append(" - ")
                .append(formattedDateTo.format(FORMATTER))
                .append("\n");

        for (String name : names) {
            int sumOfMoney = 0;
            resultString
                    .append(name)
                    .append(" - ");

            for (String personInfo : data) {
                String[] substringPersonInfo = personInfo.split(" ");
                LocalDate personDate = LocalDate.parse(substringPersonInfo[0], FORMATTER);

                if (!(personDate.isBefore(formattedDateFrom) || personDate.isAfter(formattedDateTo))
                        && substringPersonInfo[1].equals(name)) {
                    sumOfMoney += Integer.parseInt(substringPersonInfo[2])
                            * Integer.parseInt(substringPersonInfo[3]);
                }
            }
            resultString
                    .append(sumOfMoney)
                    .append("\n");
        }
        resultString.deleteCharAt(resultString.length() - 1);
        return resultString.toString();
    }
}
