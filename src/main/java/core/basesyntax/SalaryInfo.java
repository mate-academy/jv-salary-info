package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        int employSalary;
        String[] parsedDateTo = dateTo.split("\\.");
        String[] parsedDateFrom = dateFrom.split("\\.");
        int startWorkMonth = Integer.valueOf(parsedDateFrom[1]);
        int startWorkDay = Integer.valueOf(parsedDateFrom[0]);
        int finishWorkMonth = Integer.valueOf(parsedDateTo[1]);
        int finishWorkDay = Integer.valueOf(parsedDateTo[0]);
        int actualCurrentDay;
        int actualCurrentMonth;
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        String[] parsedEmployData;
        String[] parsedEmployBusyDate;
        for (String employName : names) {
            employSalary = 0;
            for (String employData : data) {
                parsedEmployData = employData.split(" ");
                if (employName.equals(parsedEmployData[1])) {
                    parsedEmployBusyDate = parsedEmployData[0].split("\\.");
                    actualCurrentDay = Integer.parseInt(parsedEmployBusyDate[0]);
                    actualCurrentMonth = Integer.parseInt(parsedEmployBusyDate[1]);
                    if (startWorkMonth == finishWorkMonth) {
                        if (startWorkDay <= actualCurrentDay && actualCurrentDay <= finishWorkDay) {
                            employSalary += Integer.parseInt(parsedEmployData[2])
                                    * Integer.valueOf(parsedEmployData[3]);
                        }
                    } else {
                        if ((startWorkMonth == actualCurrentMonth) &&
                                (startWorkDay <= actualCurrentDay)) {
                            employSalary += Integer.parseInt(parsedEmployData[2])
                                    * Integer.valueOf(parsedEmployData[3]);
                        }
                        if ((finishWorkMonth == actualCurrentMonth) &&
                                (finishWorkDay >= actualCurrentDay)) {
                            employSalary += Integer.parseInt(parsedEmployData[2])
                                    * Integer.valueOf(parsedEmployData[3]);
                        }
                    }
                }
            }
            report.append("\r\n").append(employName).append(" - ").append(employSalary);
        }
        return report.toString();
    }
}

