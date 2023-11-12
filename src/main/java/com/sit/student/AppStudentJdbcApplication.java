package com.sit.student;

import com.sit.student.dao.ClassesDao;
import com.sit.student.dao.DisciplineDao;
import com.sit.student.dao.StudentDao;
import com.sit.student.domain.Classes;
import com.sit.student.domain.Discipline;
import com.sit.student.domain.Student;
import com.sit.student.dto.DisciplineDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class AppStudentJdbcApplication {
    static Scanner in = new Scanner(System.in);
    static int choice = 0;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(AppStudentJdbcApplication.class);

        StudentDao studentDao = context.getBean(StudentDao.class);
        DisciplineDao disciplineDao = context.getBean(DisciplineDao.class);
        ClassesDao classesDao = context.getBean(ClassesDao.class);
        boolean flag = false;
        do {
            try {
                System.out.println("1. Новый студент");
                System.out.println("2. Список студентов");
                System.out.println("3. Список дисциплин для...");
                System.out.println("4. Регистрация дисциплины");
                System.out.println("5. Новый урок");
                System.out.println("6. Список проведённых занятий для...");
                System.out.println("7. Выход");
                System.out.print("Ввод: ");
                choice = Integer.parseInt(in.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("Введите Фамилию: ");
                        String sname = in.nextLine();
                        System.out.println("Введите Имя: ");
                        String fname = in.nextLine();
                        System.out.println("Введите Отчество: ");
                        String pname = in.nextLine();
                        Student student = new Student(sname, fname, pname);
                        studentDao.insert(student);
                        break;

                    case 2:
                        studentDao.getAll().forEach(System.out::println);
                        break;

                    case 3:
                        studentDao.getAll().forEach(System.out::println);
                        System.out.println("Укажите Ид студента, для которого необходимо вывести список дисциплин: ");
                        int idStudentDis = Integer.parseInt(in.nextLine());
                        disciplineDao.getByIdStudent(idStudentDis).forEach(System.out::println);
                        break;

                    case 4:
                        studentDao.getAll().forEach(System.out::println);
                        System.out.println("Укажите Ид студента, для которого регистрируете дисциплину: ");
                        int idStudInDis = Integer.parseInt(in.nextLine());
                        System.out.println("Введите наименование дисциплины: ");
                        String disName = in.nextLine();
                        System.out.println("Введите количество часов: ");
                        int hourMax = Integer.parseInt(in.nextLine());

                        Discipline discipline = new Discipline(idStudInDis, disName, hourMax);
                        disciplineDao.insert(discipline);
                        System.out.println("Успешно зарегистрировано");
                        break;

                    case 5:
                        studentDao.getAll().forEach(System.out::println);
                        System.out.println("Укажите Ид студента, для которого регистрируете дисциплину: ");
                        int idClassStudent = Integer.parseInt(in.nextLine());
                        List<DisciplineDto> disciplines = disciplineDao.getByIdStudent(idClassStudent);
                        disciplines.forEach(System.out::println);

                        System.out.println("Укажите Ид дисциплины, для регистрации занятия: ");
                        int idDiscipline = Integer.parseInt(in.nextLine());

                        System.out.println("Введите наименование урока: ");
                        String className = in.nextLine();
                        Classes classes = new Classes(idDiscipline, className, new java.util.Date(System.currentTimeMillis()));
                        classesDao.insert(classes);

                        System.out.println("Успешно зарегистрировано");
                        break;

                    case 6:
                        studentDao.getAll().forEach(System.out::println);
                        System.out.println("Укажите Ид студента: ");
                        int idListClassStudent = Integer.parseInt(in.nextLine());
                        disciplineDao.getByIdStudent(idListClassStudent).forEach(System.out::println);

                        System.out.println("Укажите Ид дисциплины для вывода списка проведённых занятий: ");
                        int idListDiscipline = Integer.parseInt(in.nextLine());
                        classesDao.getAllByIdDiscipline(idListDiscipline).forEach(System.out::println);

                        break;

                    case 7:
                        flag = true;
                        break;
                    default:
                        if (flag == false) System.err.println("Некорректные данные. Повторите ввод!\n");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Некорректные данные!");
            }

        } while (!flag);

    }

}
