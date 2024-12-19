package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder outputString = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String dataEmployeeName : names) {
            int totalEmployeeSalary = 0;
            for (String employeeRecord : data) {
                String[] splittedEmployeeRecord = employeeRecord.split(" ");
                String recordEmployeeName = splittedEmployeeRecord[1];
                if (!dataEmployeeName.equals(recordEmployeeName)) {
                    continue;
                }

                String recordDate = splittedEmployeeRecord[0];
                String employeeHoursOfWork = splittedEmployeeRecord[2];
                String employeeSalaryPerHour = splittedEmployeeRecord[3];

                String[] splittedDateFrom = dateFrom.split("\\.");
                String[] splittedDateTo = dateTo.split("\\.");
                String[] splittedRecordDate = recordDate.split("\\.");
                if ((Integer.parseInt(splittedRecordDate[2])
                                <= Integer.parseInt(splittedDateFrom[2])
                            && (Integer.parseInt(splittedRecordDate[1])
                                <= Integer.parseInt(splittedDateFrom[1])
                            && (Integer.parseInt(splittedRecordDate[0])
                                <= Integer.parseInt(splittedDateFrom[0]))))
                        || (Integer.parseInt(splittedRecordDate[2])
                                >= Integer.parseInt(splittedDateTo[2])
                            && (Integer.parseInt(splittedRecordDate[1])
                                >= Integer.parseInt(splittedDateTo[1])
                            && (Integer.parseInt(splittedRecordDate[0])
                                > Integer.parseInt(splittedDateTo[0]))))) {
                    continue;
                }
                totalEmployeeSalary += Integer.parseInt(employeeHoursOfWork)
                        * Integer.parseInt(employeeSalaryPerHour);
            }
            outputString.append(System.lineSeparator())
                        .append(dataEmployeeName)
                        .append(" - ")
                        .append(totalEmployeeSalary);

        }
        return outputString.toString();
    }
}
