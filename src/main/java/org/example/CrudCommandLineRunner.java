package org.example;

import org.example.persistence.entity.StudentEntity;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class CrudCommandLineRunner implements CommandLineRunner {

    private final StudentService studentService;

    @Autowired
    public CrudCommandLineRunner(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Listar estudiantes");
            System.out.println("2. Crear estudiante");
            System.out.println("3. Actualizar estudiante");
            System.out.println("4. Eliminar estudiante");
            System.out.println("5. Salir");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("Estudiantes:");
                    studentService.getAllStudents().forEach(student ->
                            System.out.println(student.getStudentId() + " - " + student.getName() + " " + student.getLastname())
                    );
                    break;

                case 2:
                    System.out.println("Ingrese el nombre:");
                    String name = scanner.nextLine();
                    System.out.println("Ingrese el apellido:");
                    String lastname = scanner.nextLine();

                    StudentEntity newStudent = new StudentEntity();
                    newStudent.setName(name);
                    newStudent.setLastname(lastname);
                    studentService.save(newStudent);
                    System.out.println("Estudiante creado con éxito.");
                    break;

                case 3:
                    System.out.println("Ingrese el ID del estudiante a actualizar:");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();

                    Optional<StudentEntity> studentToUpdate = studentService.getStudentById(updateId);
                    if (studentToUpdate.isPresent()) {
                        StudentEntity student = studentToUpdate.get();
                        System.out.println("Ingrese el nuevo nombre:");
                        student.setName(scanner.nextLine());
                        System.out.println("Ingrese el nuevo apellido:");
                        student.setLastname(scanner.nextLine());
                        studentService.save(student);
                        System.out.println("Estudiante actualizado con éxito.");
                    } else {
                        System.out.println("Estudiante no encontrado.");
                    }
                    break;

                case 4:
                    System.out.println("Ingrese el ID del estudiante a eliminar:");
                    int deleteId = scanner.nextInt();
                    studentService.delete(deleteId);
                    System.out.println("Estudiante eliminado con éxito.");
                    break;

                case 5:
                    running = false;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }

        scanner.close();
    }
}
