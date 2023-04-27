package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String WHITE_SPACE = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, dateTimeFormatter);;
        LocalDate lastDate = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        int salaryForEmployee;
        for (int i = 0; i < names.length; i++) {
            builder.append(System.lineSeparator()).append(names[i]).append(" - ");
            salaryForEmployee = 0;
            for (int j = 0; j < data.length; j++) {
                String[] splitedData = data[j].split(WHITE_SPACE);
                LocalDate exactDate = LocalDate.parse(splitedData[0], dateTimeFormatter);;
                if (exactDate.isAfter(firstDate.minusDays(1))
                        && exactDate.isBefore(lastDate.plusDays(1))
                        && names[i].equals(splitedData[1])) {
                    salaryForEmployee += (Integer.parseInt(splitedData[2])
                            * Integer.parseInt(splitedData[3]));
                }
            }
            builder.append(salaryForEmployee);
        }
        return builder.toString();
    }
}
