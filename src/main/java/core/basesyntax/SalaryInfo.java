package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)  {
        Employee[] employees = new Employee[names.length];
            for (int i = 0; i < names.length; i++) {
                Employee employee = new Employee();
                for (int j = 0; j < data.length; j++) {
                    if (names[i].contains(getNameWithData(data[j]))) {
                        if (containDate(data[j], dateFrom, dateTo)) {
                            employee.add_info(getNameWithData(data[j]),
                                    getSalaryWithData(data[j]), getHourWithData(data[j]));
                        } else {
                            employee.add_info(getNameWithData(data[j]));
                        }
                    }
                }
                employees[i] = employee;
            }
        return printData(employees, dateFrom, dateTo);
    }

    private boolean IsDateValid(String dateFrom, String dateTo) {
        int[] dateFrom1 = new int[3];
        dateFrom1 = getIntWithChar(dateFrom.toCharArray());
        int[] dateTo1 = new int[3];
        dateTo1 = getIntWithChar(dateTo.toCharArray());
        if(dateFrom1[2] <= dateTo1[2] && dateFrom1[1] <= dateTo1[1] && dateFrom1[0] <= dateTo1[0]) {
            return true;
        } else {
            return false;
        }
    }

    private boolean containDate(String data, String dateFrom, String dateTo) {
        /*char[] charDate = getDateWithData(data).toCharArray();
        char[] charFrom = dateFrom.toCharArray();
        char[] charTo = dateTo.toCharArray();*/
        int[] valueDate = new int[3];
        int[] valueFrom = new int[3];
        int[] valueTo = new int[3];
        valueDate = getIntWithChar(getDateWithData(data).toCharArray());
        valueFrom = getIntWithChar(dateFrom.toCharArray());
        valueTo = getIntWithChar(dateTo.toCharArray());
        if (valueTo[2] >= valueDate[2] && valueDate[2] >= valueFrom[2]) {
            if (((valueTo[1] * 30) + valueTo[0]) >= ((valueDate[1] * 30) + valueDate[0])
                    && ((valueDate[1] * 30) + valueDate[0])
                    >= ((valueFrom[1] * 30) + valueFrom[0])) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private String getDateWithData(String data) {
        String[] strings = data.split(" ");
        return strings[0];
    }

    private int[] getIntWithChar(char[] arr) {
        int[] result = new int[3];
        result[0] = ((arr[0] - '0') * 10) + (arr[1] - '0');
        result[1] = ((arr[3] - '0') * 10) + (arr[4] - '0');
        result[2] = ((arr[6] - '0') * 1000) + ((arr[7] - '0') * 100)
                + ((arr[8] - '0') * 10) + (arr[9] - '0');
        return result;
    }

    private String getNameWithData(String data) {
        String[] strings = data.split(" ");
        return strings[1];
    }

    private int getSalaryWithData(String data) {
        String[] strings = data.split(" ");
        return Integer.parseInt(strings[3]);
    }

    private int getHourWithData(String data) {
        String[] strings = data.split(" ");
        return Integer.parseInt(strings[2]);
    }

    private String printData(Employee[] employee, String dateFrom, String dateTo) {
        String string = new String("Report for period " + dateFrom + " - " + dateTo + "\n");
        if (employee.length == 0) {
            return string;
        } else {
            for (int i = 0; i < employee.length; i++) {
                if (i == employee.length - 1) {
                    string += (employee[i].getName() + " - " + employee[i].getSalary());
                    break;
                }
                string += (employee[i].getName() + " - " + employee[i].getSalary() + "\n");
            }
            return string;
        }
    }
}
