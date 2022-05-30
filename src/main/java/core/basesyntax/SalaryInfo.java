package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final int DAY_START_WORK_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_OF_WORK_IN_DAY_INDEX = 2;
    static final int SALARY_IN_HOUR_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryWorker = new StringBuilder();
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMATTER);
        String name;
        salaryWorker.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String nameWorkers : names) {
            name = nameWorkers;
            int moneyEarned = 0;
            for (String personData : data) {
                String[] getSalaryInfo = personData.split(" ");
                LocalDate localDateStartWork =
                        LocalDate.parse(getSalaryInfo[DAY_START_WORK_INDEX], DATE_FORMATTER);
                String nameWorker = getSalaryInfo[NAME_INDEX].toString();
                int hoursOfWorkInDay = Integer.parseInt(getSalaryInfo[HOURS_OF_WORK_IN_DAY_INDEX]);
                int salaryInHour = Integer.parseInt(getSalaryInfo[SALARY_IN_HOUR_INDEX]);
                if (name.equals(nameWorker)
                        && (localDateStartWork.isAfter(localDateFrom)
                        || localDateStartWork.equals(localDateFrom))
                        && (localDateStartWork.isBefore(localDateTo)
                        || localDateStartWork.equals(localDateTo))) {
                    moneyEarned += hoursOfWorkInDay * salaryInHour;
                }
            }
            salaryWorker.append(System.lineSeparator())
                    .append(name).append(" - ").append(moneyEarned);
        }
        return salaryWorker.toString();
    }
}
