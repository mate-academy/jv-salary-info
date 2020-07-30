package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        LocalDate parsedDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parsedDateTo = LocalDate.parse(dateTo, FORMATTER);

        if (parsedDateFrom.isAfter(parsedDateTo)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }

        StringBuilder builder = new StringBuilder();
        builder.append("Отчёт за период ")
                .append(dateFrom).append(" - ")
                .append(dateTo).append("\n");

        for (String name : names) {
            builder.append(name).append(" - ");
            int result = 0;
            for (String dataString : data) {
                String[] parsedData = dataString.split(" ");

                LocalDate currentDate = LocalDate.parse(parsedData[0], FORMATTER);
                if ((parsedData[1].equals(name)) && (currentDate.isAfter(parsedDateFrom)
                        || currentDate.isEqual(parsedDateFrom))
                        && (currentDate.isBefore(parsedDateTo)
                        || currentDate.isEqual(parsedDateTo))) {
                    result += Integer.parseInt(parsedData[2]) * Integer.parseInt(parsedData[3]);
                }

            }
            builder.append(result).append("\n");
        }
        return builder.toString().trim();
    }
}
