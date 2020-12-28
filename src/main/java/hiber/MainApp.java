package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
//import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
//    CarService carService = context.getBean(CarService.class);

      User user1 = new User("FirstName1", "LastName1", "fl1@m.org");
      User user2 = new User("FirstName2", "LastName2", "fl2@m.org");
      User user3 = new User("FirstName3", "LastName3", "fl3@m.org");
      User user4 = new User("FirstName4", "LastName4", "fl4@m.org");
      Car car1 = new Car("model1", 1);
      Car car2 = new Car("model2", 2);
      Car car3 = new Car("model3", 3);
      Car car4 = new Car("model4", 4);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);
//      carService.add(car1);
//      carService.add(car2);
//      carService.add(car3);
//      carService.add(car4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      List<User> userCar = userService.ownerSomeCar("model1", 1);
      userCar.stream().forEach(System.out::println);

      context.close();
   }
} // #1
