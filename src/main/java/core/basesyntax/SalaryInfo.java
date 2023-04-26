package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] dataOne;
        int [] namesSalary = new int[names.length];
        for (String personData : data) {
            dataOne = personData.split(" ");
            if (compareDate(getLocalDate(dataOne[0]),getLocalDate(dateFrom),getLocalDate(dateTo))) {
                for (int i = 0; i < names.length; i++) {
                    if (dataOne[1].equals(names[i])) {
                        namesSalary[i] += Integer.parseInt(dataOne[3])
                                * Integer.parseInt(dataOne[2]);
                    }
                }
            }
        }
        return getResultNameAndSalary(names,namesSalary,dateFrom,dateTo).toString();
    }

    public StringBuilder getResultNameAndSalary(String[] names,
                                                int[] namesSalary,String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (int i = 0; i < names.length; i++) {
            result.append(names[i]).append(" - ").append(namesSalary[i]);
            if (i < names.length - 1) {
                result.append(System.lineSeparator());
            }
        }
        return result;
    }

    public LocalDate getLocalDate(String date) {
        LocalDate localDate;
        String[] dayMonthYear;
        dayMonthYear = date.split("\\.");
        localDate = LocalDate.of(Integer.parseInt(dayMonthYear[2]),
                Integer.parseInt(dayMonthYear[1]), Integer.parseInt(dayMonthYear[0]));
        return localDate;
    }

    public boolean compareDate(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return date.isAfter(dateFrom) && date.isBefore(dateTo)
                || date.equals(dateFrom) && date.isBefore(dateTo)
                || date.isAfter(dateFrom) && date.equals(dateTo);
    }
}
