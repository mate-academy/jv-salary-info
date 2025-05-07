package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] dateFromSeparated = dateFrom.split("\\.");
        LocalDate dateFromReady = LocalDate.of(Integer.parseInt(dateFromSeparated[2]),
                Integer.parseInt(dateFromSeparated[1]),
                Integer.parseInt(dateFromSeparated[0]));
        String[] dateToSeparated = dateTo.split("\\.");
        LocalDate dateToReady = LocalDate.of(Integer.parseInt(dateToSeparated[2]),
                Integer.parseInt(dateToSeparated[1]),
                Integer.parseInt(dateToSeparated[0]));
        String[] dataSeparated;
        String[] dataSeparatedDate;
        int salaryInLoop;
        int userSalary = 0;
        StringBuilder result = new StringBuilder();
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (int i = 0; i < names.length; i++) {
            salaryInLoop = 0;
            for (int j = 0; j < data.length; j++) {
                dataSeparated = data[j].split(" ");
                dataSeparatedDate = dataSeparated[0].split("\\.");
                LocalDate dateUser = LocalDate.of(Integer.parseInt(dataSeparatedDate[2]),
                        Integer.parseInt(dataSeparatedDate[1]),
                        Integer.parseInt(dataSeparatedDate[0]));
                if ((dateUser.isAfter(dateFromReady) || dateUser.isEqual(dateFromReady))
                        && (dateUser.isBefore(dateToReady) || dateUser.isEqual(dateToReady))
                        && dataSeparated[1].equals(names[i])) {
                    salaryInLoop += Integer.parseInt(dataSeparated[2])
                            * Integer.parseInt(dataSeparated[3]);
                }
                userSalary = salaryInLoop;
            }
            result.append(System.lineSeparator()).append(names[i]).append(" - ").append(userSalary);
        }

        return result.toString();
    }
}
