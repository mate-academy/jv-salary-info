package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_DAY = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_PAYMENT = 3;
    private static final int DEFAULT_SALARY = 0;
    private static final String SPLITTER = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        String[] dataOfEmployee;
        LocalDate startOfPayment = LocalDate.parse(dateFrom, formatter);
        LocalDate endOfPayment = LocalDate.parse(dateTo, formatter);
        LocalDate dayOfWork;
        for (String name: names) {
            int salary = DEFAULT_SALARY;
            for (String employeeData : data) {
                dataOfEmployee = employeeData.split(SPLITTER);
                if (dataOfEmployee[INDEX_OF_NAME].equals(name)) {
                    dayOfWork = LocalDate.parse(dataOfEmployee[INDEX_OF_DAY], formatter);
                    if ((dayOfWork.isAfter(startOfPayment) || dayOfWork.isEqual(startOfPayment))
                            && (dayOfWork.isBefore(endOfPayment)
                            || dayOfWork.isEqual(endOfPayment))) {
                        salary += Integer.parseInt(dataOfEmployee[INDEX_OF_HOURS])
                                * Integer.parseInt(dataOfEmployee[INDEX_OF_PAYMENT]);
                    }

                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
