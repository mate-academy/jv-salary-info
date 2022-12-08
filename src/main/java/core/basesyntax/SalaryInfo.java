package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        try {
            int salary = 0;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Report for period ")
                    .append(dateFrom)
                    .append(" - ")
                    .append(dateTo);
            LocalDate newDateFrom = getParseDate(dateFrom);
            LocalDate newDateTo = getParseDate(dateTo);

            if (!dateFrom.equals(dateTo)) {
                newDateTo = newDateTo.plusDays(1);
            }

            for (int i = 0; i < names.length; i++) {
                for (int j = 0; j < data.length; j++) {
                    String[] tempArray = data[j].split(" ");
                    LocalDate employeeDate = getParseDate(tempArray[0]);
                    if (names[i].equals(tempArray[1])) {
                        if (employeeDate.isAfter(newDateFrom)
                                && employeeDate.isBefore(newDateTo)) {
                            salary += Integer.parseInt(tempArray[2])
                                    * Integer.parseInt(tempArray[3]);
                        }
                    }
                }
                stringBuilder.append("\n")
                        .append(names[i])
                        .append(" - ")
                        .append(salary);
                salary = 0;
            }
            return stringBuilder.toString();
        } catch (ParseException exp) {
            throw new RuntimeException("Wrong date", exp);
        }
    }

    private LocalDate getParseDate(String dateFrom) throws ParseException {
        return LocalDate.parse(dateFrom, formatter);
    }
}
