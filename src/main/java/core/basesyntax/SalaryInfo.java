package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws SalaryInfoException {
        validateInputData(names, data, dateFrom, dateTo);
        int length = names.length;
        String info;
        String report = "Report for period ";
        StringBuilder getResult = new StringBuilder();
        LocalDate dateFromDate = LocalDate.parse(dateFrom.trim(), dateTimeFormatter);
        LocalDate dateToDate = LocalDate.parse(dateTo.trim(), dateTimeFormatter);
        int[] salary = new int[length];
        for (int i = 0; i < length; i++) {
            for (String dat : data) {
                String[] dataSeparater = dat.split(" ");
                String getDate = dataSeparater[0];
                String getName = dataSeparater[1];
                String getQuantity = dataSeparater[2];
                String getSalary = dataSeparater[3];
                LocalDate dayToCheck = LocalDate.parse(getDate.trim(), dateTimeFormatter);
                boolean isTrue = (dayToCheck.isAfter(dateFromDate.minusDays(1))
                        && dayToCheck.isBefore(dateToDate.plusDays(1)));
                if (names[i].equals(getName) && isTrue) {
                    salary[i] += Integer.parseInt(getQuantity) * Integer.parseInt(getSalary);
                }
            }
            info = names[i] + " - " + salary[i];
            getResult.append(System.lineSeparator()).append(info);
        }
        return report + dateFrom + " - " + dateTo + getResult;
    }

    public void validateInputData(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names.length == 0) {
            throw new SalaryInfoException("incorrect data entry, check: name");
        }
        if (data.length == 0) {
            throw new SalaryInfoException("incorrect data entry, check: data");
        }
        if (dateFrom == null) {
            throw new SalaryInfoException("incorrect data entry, check: dateFrom");
        }
        if (dateTo == null) {
            throw new SalaryInfoException("incorrect data entry, check: dateTo");
        }
    }
}
