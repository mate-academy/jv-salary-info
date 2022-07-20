package core.basesyntax;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
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
                        salary = salary + parseInt(salaryFromArray)
                                * parseInt(hourFromArray);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
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
