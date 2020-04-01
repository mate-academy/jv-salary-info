package core.basesyntax;

import core.basesyntax.exception.IllegalDateParametersException;
import java.time.LocalDate;

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
            throws Exception {
        LocalDate fromData = convertationToData(dateFrom);
        LocalDate toData = convertationToData(dateTo);
        if (toData.isBefore(fromData) || toData.isEqual(fromData)) {
            throw new IllegalDateParametersException("Wrong parameters");
        }
        payrollAndWriteInNames(names, data, fromData, toData);
        StringBuilder report = new StringBuilder("Отчёт за период ");
        report.append(dateFrom).append(" - ").append(dateTo).append("\n");
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]);
            if (i != names.length - 1) {
                report.append("\n");
            }
        }
        return report.toString();
    }

    private void payrollAndWriteInNames(String[] names, String[] data,
                                        LocalDate fromData, LocalDate toData) {
        for (int i = 0; i < names.length; i++) {
            int sum = 0;
            for (String worker : data) {
                String[] splitWorker = worker.split(" ");
                if (splitWorker[1].equals(names[i])) {
                    if (checkData(fromData, toData, splitWorker[0])) {
                        sum += Integer.parseInt(splitWorker[2])
                                * Integer.parseInt(splitWorker[3]);
                    }
                }
            }
            if (sum != 0) {
                names[i] += " - " + sum;
            }
        }
    }

    private boolean checkData(LocalDate fromData, LocalDate toData, String s) {
        LocalDate data = convertationToData(s);
        return data.isAfter(fromData) && data.isBefore(toData)
                || data.isEqual(fromData) || data.isEqual(toData);
    }

    private LocalDate convertationToData(String date) {
        String[] splitDate = date.split("\\.");
        return LocalDate.of(Integer.parseInt(splitDate[2]),
                Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]));
    }
}
