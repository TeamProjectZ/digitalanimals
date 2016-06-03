import database.dao.PersistException;
import database.dao.entity.*;
import database.dao.mysql.*;
import helper.ConsoleHelpe;

import java.time.LocalDateTime;
import java.util.List;

/** 
 * Project: DigitalAnimals
 * Tester.java
 */

/**
 * @author Bondarenko Anton Michailovich
 * @version 1.0
 *
 */
public class Test {
	public static LocalDateTime timePoint = LocalDateTime.now();
		public static void main(String[] args){
//			GregorianCalendar cc = new GregorianCalendar();
//			System.out.println("Hello World");
//			System.out.println(timePoint);
//			cc.setGregorianChange(date);
//			System.out.println(cc.isSet());

			try {
				MySqlDaoFactory factory = new MySqlDaoFactory();
				MySqlHouseDao houseDao = new MySqlHouseDao(factory, factory.getContext());
				MySqlTypeDao typeDao = new MySqlTypeDao(factory, factory.getContext());

				House house = houseDao.create();

				house.setPrice(100);
				house.setSize(1);
				house.setType(typeDao.getByPK(10));

				houseDao.update(house);
				House home = houseDao.getByPK(3);

				houseDao.delete(home);



			} catch (PersistException e) {
				e.printStackTrace();
			}


		}
}
