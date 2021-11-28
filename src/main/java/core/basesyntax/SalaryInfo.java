package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();

        sb.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            sb.append(names[i]).append(" - ");
            for (int j = 0; j < data.length; j++) {
                try {
                    LocalDate start = LocalDate.parse(dateFrom, dateTimeFormatter);
                    LocalDate end = LocalDate.parse(dateTo, dateTimeFormatter);
                    LocalDate dateOfArray =
                            LocalDate.parse(data[j].split(" ", 4)[0], dateTimeFormatter);
                    if ((dateOfArray.isAfter(start) || start.equals(dateOfArray))
                            && (dateOfArray.isBefore(end) || end.equals(dateOfArray))) {
                        if (names[i].equals(data[j].split(" ", 4)[1])) {
                            sum = sum + (Integer.parseInt(data[j].split(" ", 4)[2]))
                                    * (Integer.parseInt(data[j].split(" ", 4)[3]));
                        }
                    }
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Wrong date",e);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Wrong date",e);
                }
            }
            sb.append(sum).append(System.lineSeparator());
        }
        sb.setLength(sb.length() - 2);
        System.out.println(sb);
        return sb.toString();
    }
}
