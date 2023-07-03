package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String newLine = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormatter = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToFormatter = LocalDate.parse(dateTo, formatter).plusDays(1);
        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String dateFinding: data) {
                String[] dateSplit = dateFinding.split(" ");
                LocalDate dateWork = LocalDate.parse(dateSplit[0], formatter);
                if (names[i].equals(dateSplit[1]) && dateWork.isAfter(dateFromFormatter)
                        && dateWork.isBefore(dateToFormatter)) {
                    salaries[i] += Integer.parseInt(dateSplit[2]) * Integer.parseInt(dateSplit[3]);
                }
            }
        }
        StringBuilder infoAboutWorkers = new StringBuilder();
        infoAboutWorkers.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            infoAboutWorkers.append(newLine)
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return infoAboutWorkers.toString();
    }
}
