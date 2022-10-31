package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] arrayFrom = getIntArray(dateFrom.split("\\D"));
        int[] arrayTo = getIntArray(dateTo.split("\\D"));
        StringBuilder sb = new StringBuilder("Report for period ");
        sb.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            sb.append(names[i]).append(" - ");
            int sumName = 0;
            for (int j = 0; j < data.length; j++) {

                String[] words = data[j].split(names[i]);
                int[] actualDate = getIntArray(words[0].split("\\D"));

                if (words.length > 1) {
                    int[] salary = getIntArray(words[1].split("\\D"));
                    if (arrayTo[1] == arrayFrom[1]) {
                        if (actualDate[0] >= arrayFrom[0] && actualDate[0] <= arrayTo[0]) {
                            sumName = replaceSalary(sumName, salary);
                        }
                    } else {
                        if (actualDate[1] == arrayFrom[1] && actualDate[0] >= arrayFrom[0]) {
                            sumName = replaceSalary(sumName, salary);
                        } else if (actualDate[1] == arrayTo[1] && actualDate[0] <= arrayTo[0]) {
                            sumName = replaceSalary(sumName, salary);
                        }
                    }
                }
            }
            if (i == names.length - 1) {
                sb.append(sumName);
            } else {
                sb.append(sumName).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public int[] getIntArray(String[] arrayString) {
        int[] arrayInt = new int[arrayString.length];
        for (int i = 0; i < arrayString.length; i++) {
            if (!arrayString[i].equals("")) {
                arrayInt[i] = Integer.parseInt(arrayString[i]);
            }
        }
        return arrayInt;
    }

    public int replaceSalary(int sumName, int[] salary) {
        return sumName + (salary[1]) * (salary[2]);
    }
}
