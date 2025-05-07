package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOURS_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int money = 0;
            for (String dataInf : data) {
                String[] dataInfo = dataInf.split(" ");
                LocalDate startDate = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate dateToProcess = LocalDate.parse(dataInfo[DATE_INDEX], FORMATTER);
                LocalDate finishDate = LocalDate.parse(dateTo, FORMATTER);
                if (name.equals(dataInfo[NAME_INDEX]) && (dateToProcess.equals(startDate)
                        || dateToProcess.equals(finishDate) || dateToProcess.isAfter(startDate)
                        && dateToProcess.isBefore(finishDate))) {
                    money += Integer.parseInt(dataInfo[HOURS_INDEX])
                            * Integer.parseInt(dataInfo[SALARY_PER_HOURS_INDEX]);
                }
            }
            report.append(name)
                    .append(" - ")
                    .append(money)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
