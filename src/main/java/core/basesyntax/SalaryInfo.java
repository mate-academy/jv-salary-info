package core.basesyntax;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
        public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
            // Форматтер для работы с датами
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            // Преобразуем строки дат в LocalDate
            LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
            LocalDate toDate = LocalDate.parse(dateTo, formatter);

            // Строим отчет
            StringBuilder report = new StringBuilder();
            report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

            // Обрабатываем каждого сотрудника
            for (String name : names) {
                int totalSalary = 0;  // Общая зарплата сотрудника за период

                // Проходим по каждому записанному рабочему дню
                for (String record : data) {
                    // Разделяем запись на части
                    String[] recordParts = record.split(" ");

                    // Преобразуем дату работы в LocalDate
                    LocalDate recordDate = LocalDate.parse(recordParts[0], formatter);
                    String employeeName = recordParts[1];
                    int hoursWorked = Integer.parseInt(recordParts[2]);
                    int hourlyRate = Integer.parseInt(recordParts[3]);

                    // Проверяем, соответствует ли запись сотруднику и входит ли дата в диапазон
                    if (employeeName.equals(name) && !recordDate.isBefore(fromDate) && !recordDate.isAfter(toDate)) {
                        totalSalary += hoursWorked * hourlyRate;  // Рассчитываем заработок за день
                    }
                }

                // Добавляем информацию о зарплате сотрудника в отчет
                report.append(name).append(" - ").append(totalSalary).append(System.lineSeparator());
            }

            // Возвращаем итоговый отчет
            return report.toString().trim();  // Убираем лишние символы новой строки
        }
    }
