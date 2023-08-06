package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] employeeData;
        String[] employeeDataDate;
        String[] dateFromDivide = dateFrom.split("\\.");
        String[] dateToDivide = dateTo.split("\\.");
        int[] employeeSalary = new int[names.length];

        for (String employee : data) {
            employeeData = employee.split(" ");
            employeeDataDate = employeeData[0].split("\\.");

            if (dateCompare(employeeDataDate, dateFromDivide)
                    && dateCompare(dateToDivide, employeeDataDate)) {
                for (int numberOfName = 0; numberOfName < names.length; numberOfName++) {
                    if (names[numberOfName].equals(employeeData[1])) {
                        employeeSalary[numberOfName] +=
                                (Integer.parseInt(employeeData[2])
                                        * Integer.parseInt(employeeData[3]));
                    }
                }
            }
        }

        StringBuilder builder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (int numberOfName = 0; numberOfName < names.length; numberOfName++) {
            builder.append(System.lineSeparator())
                    .append(names[numberOfName]).append(" - ")
                    .append(employeeSalary[numberOfName]);
        }

        return builder.toString();
    }

    private boolean dateCompare(String[] dateA, String[] dateB) {
        int dateADay = Integer.parseInt(dateA[0]);
        int dateBDay = Integer.parseInt(dateB[0]);
        int dateAMonth = Integer.parseInt(dateA[1]);
        int dateBMonth = Integer.parseInt(dateB[1]);
        int dateAYear = Integer.parseInt(dateA[2]);
        int dateBYear = Integer.parseInt(dateB[2]);

        if (dateAYear == dateBYear) {
            if (dateAMonth == dateBMonth) {
                return dateADay >= dateBDay;
            } else {
                return dateAMonth >= dateBMonth;
            }
        } else {
            return dateAYear >= dateBYear;
        }
    }
}
