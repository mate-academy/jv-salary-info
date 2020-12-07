package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0;i < names.length;i++) {
            report.append("\n" + names[i] + " - ");
            int totalSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String [] oneEntry = data[j].split(" ");
                if (names[i].equals(oneEntry[1]) && (LocalDate.parse(oneEntry[0],FORMATTER)
                        .compareTo(LocalDate.parse(dateTo,FORMATTER))) <= 0
                        && (LocalDate.parse(oneEntry[0],FORMATTER)
                        .compareTo(LocalDate.parse(dateFrom,FORMATTER))) > 0) {
                    totalSalary += parseInt(oneEntry[2]) * parseInt(oneEntry[3]);
                }
            }
            report.append(totalSalary);
        }
        return report.toString();
    }
}
