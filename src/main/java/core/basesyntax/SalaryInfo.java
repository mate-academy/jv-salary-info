package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public static final String DATE_FORMAT = "dd.MM.yyyy";

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String[] names;
    private int[] salarySum;
    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null) {
            System.out.println("Error! Input parameters are wrong");
            return "";
        }
        try {
            setDateFrom(dateFrom);
            setDateTo(dateTo);
        } catch (DateTimeParseException e) {
            System.out.println("Error! Input parameters are wrong. " + e);
            return "";
        }
        setNames(names);
        setZeroSalarySum(this.names.length);

        for (String line : data) {
            SalaryPerDay salaryPerDay;
            try {
                salaryPerDay = new SalaryPerDay(line);
            } catch (DateTimeParseException | NumberFormatException | SalaryPerDayException e) {
                System.out.printf("Error! Input parameters (%s) are wrong. %s\n", line, e);
                break;
            }

            if (salaryPerDay.getDate().isBefore(this.dateFrom)
                    || salaryPerDay.getDate().isAfter(this.dateTo)) {
                continue;
            }

            for (int i = 0; i < this.names.length; i++) {
                if (salaryPerDay.getName().equals(this.names[i])) {
                    salarySum[i] += salaryPerDay.getCost();
                    break;
                }
            }
        }
        return getInfo();
    }

    private void setNames(String[] names) {
        this.names = names;
    }

    private void setZeroSalarySum(int dimension) {
        this.salarySum = new int[dimension];
        for (int i = 0; i < dimension; i++) {
            this.salarySum[i] = 0;
        }
    }

    private void setDateFrom(String date) throws DateTimeParseException {
        this.dateFrom = LocalDate.parse(date, dateFormatter);
    }

    private void setDateTo(String date) throws DateTimeParseException {
        this.dateTo = LocalDate.parse(date, dateFormatter);
    }

    private String getInfo() {
        StringBuilder salaryInfo = new StringBuilder();

        salaryInfo.append(String.format("Report for period %s - %s",
                dateFormatter.format(dateFrom), dateFormatter.format(dateTo)));

        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(String.format("%s%s - %d",
                    System.lineSeparator(), names[i], salarySum[i]));
        }

        return salaryInfo.toString();
    }
}
