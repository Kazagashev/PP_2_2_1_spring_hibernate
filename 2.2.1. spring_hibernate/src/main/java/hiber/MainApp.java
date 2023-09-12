package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      carService.add(new Car("Lada",2114));
      carService.add(new Car("Toyota",2));
      carService.add(new Car("Honda",3));
      carService.add(new Car("Nissan",4));

      List<Car> car = carService.listCars();

      userService.add(new User("Пётр", "Петровчи", "user1@mail.ru",car.get(0)));
      userService.add(new User("Евгений", "Евгенич", "user2@mail.ru",car.get(1)));
      userService.add(new User("Сергей", "Сергеич", "user3@mail.ru",car.get(2)));
      userService.add(new User("Дим", "Дичмыч", "user4@mail.ru",car.get(3)));


      List<User> users = userService.getUserByCarModelAndSeries("Toyota",2);
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
