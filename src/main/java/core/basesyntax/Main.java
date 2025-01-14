package core.basesyntax;

public class Main {
    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();

        // Przykładowe daty w formie stringów
        String dateFrom = "01.01.2024";
        String dateTo = "31.12.2024";

        // Przykładowe dane
        String[] names = {"Jan", "Anna"};
        String[] data = {"10.10.2024 Jan 8 20.5", "15.03.2024 Anna 6 19"};

        // Generowanie raportu
        String report = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTo);
        System.out.println(report);
    }
}
