package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать пакет exception и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws IllegalDateParametersException {
        final String pattern = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        LocalDate to = LocalDate.parse(dateTo, formatter);
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        if (to.compareTo(from) < 0) {
            throw new IllegalDateParametersException();
        }
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            int salaryOfWorker = 0;
            for (String information: data) {
                String[] workerData = information.split(" ");
                LocalDate day = LocalDate.parse(workerData[0], formatter);
                if (day.compareTo(to) <= 0
                        && day.compareTo(from) >= 0
                        && workerData[1].equals(names[i])) {
                    salaryOfWorker += Integer.parseInt(workerData[2])
                            * Integer.parseInt(workerData[3]);
                }
            }
            salary[i] = salaryOfWorker;
        }
        StringBuilder report = new StringBuilder();
        report.append("Отчёт за период ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salary[i]).append("\n");
        }
        return report.toString().trim();
    }
}
