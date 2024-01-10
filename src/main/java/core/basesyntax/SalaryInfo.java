package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String lineSeparator = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (validateInput(names, data, dateFrom, dateTo)) {
            throw new DataFormatException("Data is empty");
        }

        LocalDate[] dataDate = new LocalDate[data.length];
        String[] dataName = new String[data.length];
        String[] dataHours = new String[data.length];
        String[] dataSalaryPerHour = new String[data.length];

        try {
            for (int i = 0; i < data.length; i++) {
                String[] dataSplit = data[i].split(" ");
                dataDate[i] = LocalDate.parse(dataSplit[0], FORMATTER);
                dataName[i] = dataSplit[1];
                dataHours[i] = dataSplit[2];
                dataSalaryPerHour[i] = dataSplit[3];
            }
        } catch (DateTimeParseException e) {
            throw new DataFormatException("Wrong date format");
        }

        LocalDate dateFromLocalDate = parseDateFrom(dateFrom);
        LocalDate dateToLocalDate = parseDateTo(dateTo);

        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int sum = 0;
            for (int i = 0; i < data.length; i++) {
                if (name.equals(dataName[i])
                        && dataDate[i].compareTo(dateFromLocalDate) >= 0
                        && dataDate[i].compareTo(dateToLocalDate) <= 0) {
                    sum += Integer.valueOf(dataHours[i]) * Integer.valueOf(dataSalaryPerHour[i]);
                }
            }
            result.append(lineSeparator)
                    .append(name)
                    .append(" - ")
                    .append(sum);
        }
        return result.toString();
    }

    public boolean validateInput(String[] names, String[] data, String dateFrom, String dateTo) {
        return names.length == 0 || data.length == 0 || dateFrom == null || dateTo == null;
    }

    public LocalDate parseDateFrom(String dateFrom) {
        try {
            return LocalDate.parse(dateFrom, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DataFormatException("Wrong from date format");
        }
    }

    public LocalDate parseDateTo(String dateTo) {
        try {
            return LocalDate.parse(dateTo, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DataFormatException("Wrong to date format");
        }
    }
}
