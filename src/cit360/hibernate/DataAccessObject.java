package cit360.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DataAccessObject {
    // Session Variables from Hibernate
    SessionFactory mySessionFactory = null;
    Session mySession = null;

    // Instance of DataAccessObject
    private static DataAccessObject singleInstance = null;

    private DataAccessObject() {
        mySessionFactory = HibernateUtils.getSessionFactory();
    }

    // Create a Singleton (Single Instance Only
    public static DataAccessObject getInstance() {
        if (singleInstance == null) {
            singleInstance = new DataAccessObject();
        }

        return singleInstance;
    }

    // Retrieve a list of all employees
    public List<Employee> getEmployees() {
        try {
            mySession = mySessionFactory.openSession();
            mySession.getTransaction().begin();
            String sql = "from cit360.hibernate.Employee";
            List<Employee> employees = (List<Employee>) mySession.createQuery(sql).getResultList();
            mySession.getTransaction().commit();

            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            return null;
        } finally {
            mySession.close();
        }
    }


    // Retrieve a single employee
    public Employee getEmployeeById(int id) {
        try {
            mySession = mySessionFactory.openSession();
            mySession.getTransaction().begin();
            String sql = "SELECT id, name, position FROM cit360.hibernate.Employee WHERE id = " + id;
            Employee employee = (Employee) mySession.createQuery(sql).getSingleResult();
            mySession.getTransaction().commit();

            return employee;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

            return null;
        } finally {
            mySession.close();
        }
    }

    // Add a new employee
    public void addSingleEmployee(String name, String position) {
        try {
            mySession = mySessionFactory.openSession();
            mySession.getTransaction().begin();
            String sql = "INSERT INTO employees (name, position) VALUES (:name, :position)";
            Query myQuery = mySession.createSQLQuery(sql);
            myQuery.setParameter("name", name);
            myQuery.setParameter("position", position);
            myQuery.executeUpdate();

            mySession.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            mySession.close();
        }
    }
}
