package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        LocalDate dateFromLocal = getLocalDateFromString(dateFrom);
        LocalDate dateToLocal = getLocalDateFromString(dateTo);
        for (String line: data) {
            String[] parsedData = line.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (parsedData[1].equals(names[i])) {
                    LocalDate dateOfWork = getLocalDateFromString(parsedData[0]);
                    if ((dateOfWork.isAfter(dateFromLocal) | dateOfWork.equals(dateFromLocal))) {
                        if (dateOfWork.equals(dateToLocal) | dateOfWork.isBefore(dateToLocal)) {
                            int salaryForThatDay = Integer.parseInt(parsedData[2])
                                    * Integer.parseInt(parsedData[3]);
                            salaries[i] += salaryForThatDay;
                        }
                    }
                }
            }
        }
        StringBuilder bufferResult = new StringBuilder("Report for period ").append(dateFrom)
                        .append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            bufferResult.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salaries[i]);
        }
        return new String(bufferResult);
    }

    public LocalDate getLocalDateFromString(String data) {
        String[] parsedData;
        parsedData = data.split("\\.");
        return LocalDate.of(Integer.parseInt(parsedData[2]),
                Integer.parseInt(parsedData[1]), Integer.parseInt(parsedData[0]));
    }
}
