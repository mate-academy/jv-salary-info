package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String nameOne = names[0];
        String nameTwo = names[1];
        String nameTree = names[2];
        int salaryNameOne = 0;
        int salaryNameTwo = 0;
        int salaryNameTree = 0;

        for (String onedata : data) {
            String[] a = onedata.split(" ");
            if (a[1].equals(nameOne)) {
                salaryNameOne = salaryNameOne + (Integer.parseInt(a[2]) * Integer.parseInt(a[3]));
            }
        }

        for (String twodata : data) {
            String[] a = twodata.split(" ");
            if (a[1].equals(nameTwo)) {
                salaryNameTwo = salaryNameTwo + (Integer.parseInt(a[2]) * Integer.parseInt(a[3]));
            }
        }

        for (String treedata : data) {
            String[] a = treedata.split(" ");
            if (a[1].equals(nameTree)) {
                salaryNameTree = salaryNameTree + (Integer.parseInt(a[2]) * Integer.parseInt(a[3]));
            }
        }

        StringBuilder sb = new StringBuilder("Report for period ");

        sb.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator())
                .append(nameOne).append(" - ").append(salaryNameOne).append(System.lineSeparator())
                .append(nameTwo).append(" - ").append(salaryNameTwo).append(System.lineSeparator())
                .append(nameTree).append(" - ").append(salaryNameTree);
        return sb.toString();
    }
}
