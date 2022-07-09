package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;

public class SalaryInfo {
    private int salary;
    private final String newLine = System.lineSeparator();
    private StringBuilder stringBuilder = new StringBuilder("Report for period ");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        stringBuilder.append(dateFrom).append(" - ").append(dateTo).append(newLine);
        for (String name : names) {
            for (String periodFromData : data) {
                int indexDate = periodFromData.indexOf(" ");
                int indexName = periodFromData.substring(indexDate + 1)
                        .indexOf(" ") + indexDate + 1;
                int indexHour = periodFromData.substring(indexName + 1)
                        .indexOf(" ") + indexName + 1;
                LocalDate workday = getLocalDate(periodFromData.substring(0, indexDate));
                String nameUser = periodFromData.substring(indexDate + 1, indexName);
                String hourFromArray = periodFromData.substring(indexName + 1, indexHour);
                String salaryFromArray = periodFromData.substring(indexHour + 1);
                if ((getLocalDate(dateFrom).isBefore(workday)
                        || getLocalDate(dateFrom).isEqual(workday))
                        && (getLocalDate(dateTo).isAfter(workday)
                        || getLocalDate(dateTo).isEqual(workday))) {
                    if (name.equals(nameUser)) {
                        this.salary = salary + (parseInt(salaryFromArray)
                                * parseInt(hourFromArray));
                    }
                }
            }
            stringBuilder.append(name).append(" - ").append(salary).append(newLine);
            this.salary = 0;
        }
        return stringBuilder.toString();
    }

    public LocalDate getLocalDate(String dateString) {
        int separator1 = dateString.indexOf(".");
        int separator2 = dateString.substring(separator1 + 1).indexOf(".") + separator1 + 1;
        int day = parseInt(dateString.substring(0, separator1));
        int month = parseInt(dateString.substring(separator1 + 1, separator2));
        int year = parseInt(dateString.substring(separator2 + 1));
        return LocalDate.of(year, month, day);
    }
}
