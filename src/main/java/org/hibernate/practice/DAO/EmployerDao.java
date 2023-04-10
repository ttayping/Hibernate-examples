package org.hibernate.practice.DAO;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.practice.MODEL.Employer;
import org.hibernate.practice.UTIL.HibernateUtil;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class EmployerDao {
    public void addEmployer(Employer employer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.save(employer);
            transaction.commit();
        } catch (Exception exception) {
            if (transaction != null) {
                transaction.rollback();
            }
            exception.printStackTrace();
        } finally {
            session.close();
        }
    }

    public Employer getEmployerByID(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employer employer = null;
        try {
            employer = session.get(Employer.class, id);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return employer;
    }


    public void updateEmployer(Employer employer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(employer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteEmployer(Employer employer) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(employer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
        } finally {
            session.close();
        }
    }

    public List<Employer> selectAll() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Employer", Employer.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    public void updateSalaryManual(int experienceFrom, int experienceTill, double bonusSalary) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Query query = session.createQuery("UPDATE Employer SET salary=(salary+:bonusSalary) WHERE experience BETWEEN :experienceFrom AND :experienceTill");
            query.setParameter("bonusSalary", bonusSalary);
            query.setParameter("experienceFrom", experienceFrom);
            query.setParameter("experienceTill", experienceTill);
            int rowAffected = query.executeUpdate();

            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateSalary(int experienceFrom, int experienceTill, double bonusSalary) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<Employer> employers = selectAll();
            employers.forEach(employer -> {
                if (employer.getExperience() > experienceFrom && employer.getExperience() < experienceTill) {
                    employer.setSalary(employer.getSalary() + bonusSalary);
                    session.update(employer);
                }
            });
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Employer> showEmployeeBySalaryRange(Double salary) {
        List<Employer> selectedEmployers = null;
        selectAll().forEach(employer -> {
            if (employer.getSalary() > salary) {
                selectedEmployers.add(employer);
            }
        });
        return selectedEmployers;
    }

    public Double calculateAverageSalary() {
        Double sumSalary = 0D;
        Double count = 0D;

//        List<Employer> employerList = this.selectAll();
//
//        for (Employer employer : employerList) {
//            sumSalary += employer.getSalary();
//            count++;
//        }

        selectAll().forEach(employer -> {
            sumSalary.valueOf(sumSalary + employer.getSalary());
            count.valueOf(count + 1);
        });

        return sumSalary / count;
    }


    public List<Employer> underAverageSalaryEmployee() {
        List<Employer> selectedEmployee = new ArrayList<>();

        selectAll().forEach(employer -> {
            if (employer.getSalary() < calculateAverageSalary()) {
                selectedEmployee.add(employer);
            }
        });
        return selectedEmployee;
    }

}
