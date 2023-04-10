package org.hibernate.practice;

import org.hibernate.practice.DAO.EmployerDao;
import org.hibernate.practice.MODEL.Employer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

    EmployerDao employerDao = new EmployerDao();
//    employerDao.addEmployer(new Employer("Mahir", "Ehsanov", 3, 2000.0));
  //  employerDao.addEmployer(new Employer("Vali", "Hajiev", 2, 1200.0));
     //   employerDao.updateSalary(1,6,100);
//        Double maas = employerDao.calculateAverageSalary();
//        System.out.println(maas);
       System.out.println(employerDao.underAverageSalaryEmployee());
//    employerDao.updateSalary(1,5,100);

}
}
