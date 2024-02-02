package com.crud;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    	Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		int flag=1;
		int choice=0;
		do {
			System.out.println("1 : Create");
			System.out.println("2 : Read");
			System.out.println("3 : Update");
			System.out.println("4 : Delete");
			System.out.println("5 : Exit");
			System.out.print("Enter ur choice : ");
			choice = sc.nextInt();
			if(choice==1) {
				System.out.print("Enter the Employee ID : ");
				int id = sc.nextInt();
				System.out.print("Enter the Employee Name : ");
				String name = sc.next();
				System.out.print("Enter the Employee Age : ");
				int age = sc.nextInt();
				System.out.print("Enter the Employee Gender : ");
				String gen = sc.next();
				employee mEmployee = new employee(id,name,age,gen);
				try {
					session.persist(mEmployee);
					System.out.println("Data inserted!!!");
				} catch (Exception e) {
					System.out.println("Employee ID already exits !!!");
				}
			}
			else if(choice==2) {
				System.out.print("Enter the ID to be read : ");
				int id = sc.nextInt();
				try {
					employee dp = session.get(employee.class,id);
					System.out.println("Employee_ID     : "+dp.getId());
					System.out.println("Employee Name   : "+dp.getName());
					System.out.println("Employee Age    : "+dp.getAge());
					System.out.println("Employee Gender : "+dp.getGender());
					
				} catch (Exception e) {
					System.out.println("Ur id doesn't exists!!");
				}
				
			}
			else if(choice ==3) {
				System.out.print("Enter the id : ");
				int id = sc.nextInt();
				System.out.print("Enter the New Name : ");
				String new_nameString = sc.next();
				try {
					employee dp = session.get(employee.class,id);
					dp.setName(new_nameString);
					System.out.println("Data Updated !!!");
				} catch (Exception e) {
					System.out.println("Given ID doesn't exits ");
				}
				
			}
			else if(choice==4) {
				System.out.print("Enter the ID to be delete : ");
				int id = sc.nextInt();
				try {
					employee dp = session.get(employee.class,id);
					session.delete(dp);
					System.out.println("Data deleted !!!");
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Given ID doesn't !!!");
				}
			}
			else if(choice==5) {
				flag=0;
				break;
			}
			else {
				System.out.println("Enter a valid input !!!");
			}
		}while(flag==1);
		tx.commit();
		factory.close();
		sc.close();
    }
}
