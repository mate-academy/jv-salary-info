package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        String[][] words = new String[data.length][4];

        for (int i = 0; i < data.length; i++) {
            words[i] = data[i].split(" ");
        }

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)

        for (int i = 0; i < names.length; i++) {
            int totalSalary = 0;

            for (int j = 0; j < words.length; j++) {
                if (names[i].equals(words[j][1]) && isDateInRange(words[j][0], dateFrom, dateTo)) {
                    int hours = Integer.parseInt(words[j][2]);
                    int salaryPerHour = Integer.parseInt(words[j][3]);
                    int salary = hours * salaryPerHour;
                    totalSalary += salary;
                }
            }
            if (i == names.length - 1) {
                report.append(names[i])
                        .append(" - ")
                        .append(totalSalary);
            } else {
                report.append(names[i])
                        .append(" - ")
                        .append(totalSalary)
                        .append(System.lineSeparator());
            }
        }

        return report.toString();
    }

    private static boolean isDateInRange(String dateString, String dateFrom, String dateTo) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateString, dateFormatter);
        LocalDate fromDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate toDate = LocalDate.parse(dateTo, dateFormatter);

        return (date.isEqual(fromDate) || date.isAfter(fromDate))
                && (date.isEqual(toDate) || date.isBefore(toDate));
    }
}
