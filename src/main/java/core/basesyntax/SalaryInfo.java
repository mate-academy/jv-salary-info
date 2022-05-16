package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    private static final int DAY = 0;
    private static final int MONTH = 1;
    private static final int YEAR = 2;
    private static final int WORK_DAY = 0;
    private static final int EMP_NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = parseToLocalDate(dateFrom);
        LocalDate to = parseToLocalDate(dateTo);
        int[] salary = new int[names.length];
        for (int i = 0; i < data.length; i++) {
            String[] splittedData = data[i].split(" ");
            for (int j = 0; j < names.length; j++) {
                LocalDate dateOfWork = parseToLocalDate(splittedData[WORK_DAY]);
                if (dateOfWork.compareTo(from) >= 0 && dateOfWork.compareTo(to) <= 0
                        && splittedData[EMP_NAME].equals(names[j])) {
                    salary[j] += Integer.parseInt(splittedData[WORK_HOURS])
                            * Integer.parseInt(splittedData[PER_HOUR]);
                }
            }
        }
        return getReport(names, salary, dateFrom, dateTo);
    }

    private LocalDate parseToLocalDate(String date) {
        String[] splitDate = date.split("\\.");
        LocalDate localDate =
                LocalDate.of(Integer.parseInt(splitDate[YEAR]),
                        Integer.parseInt(splitDate[MONTH]),
                        Integer.parseInt(splitDate[DAY]));
        return localDate;
    }

    private String getReport(String[] names, int[] salary, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo + "\n");
        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(names[i] + " - " + salary[i] + "\n");
        }
        return salaryInfo.toString().trim();
    }
}
