package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo);
        int[] fromDate = dateToIntArray(dateFrom);
        int[] toDate = dateToIntArray(dateTo);
        for (String employeeName : names) {
            int totalSalary = 0;
            for (String employeeData : data) {
                String[] splittedData = employeeData.split(" ");
                int[] workDate = dateToIntArray(splittedData[0]);
                if (employeeName.equals(splittedData[1]) && dateFitsPeriod(workDate, fromDate,
                        toDate)) {
                    totalSalary += Integer.parseInt(splittedData[2])
                            * Integer.parseInt(splittedData[3]);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(employeeName).append(" - ")
                    .append(totalSalary);
        }
        return salaryInfo.toString();
    }

    private int[] dateToIntArray(String date) {
        int day = Integer.parseInt(date.substring(0, date.indexOf('.')));
        int month = Integer.parseInt(date.substring(date.indexOf('.') + 1, date.lastIndexOf('.')));
        int year = Integer.parseInt(date.substring(date.lastIndexOf('.') + 1));
        return new int[] {day, month, year};
    }

    private boolean dateFitsPeriod(int[] dateChecked, int[] dateFrom, int[] dateTo) {
        return !(dateChecked[2] < dateFrom[2] || dateChecked[2] > dateTo[2]
                || (dateChecked[2] == dateFrom[2] && dateChecked[1] < dateFrom[1])
                || (dateChecked[2] == dateTo[2] && dateChecked[1] > dateTo[1])
                || (dateChecked[2] == dateFrom[2] && dateChecked[1] == dateFrom[1]
                        && dateChecked[0] < dateFrom[0])
                || (dateChecked[2] == dateTo[2] && dateChecked[1] == dateTo[1]
                        && dateChecked[0] > dateTo[0]));
    }
}
