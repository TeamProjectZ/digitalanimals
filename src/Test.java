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
				MySqlFoodDao foodDao = new MySqlFoodDao(factory, factory.getContext());
//				MySqlTypeDao typeDao = new MySqlTypeDao(factory, factory.getContext());
				MySqlFoodStoreDao foodStoreDao = new MySqlFoodStoreDao(factory, factory.getContext());
				Food food = foodDao.getByPK(3);

				ConsoleHelpe.writeMessage(food.toString());
				FoodStore foodStore = foodStoreDao.getByPK(1);
				foodStore.setValue(100);
				foodStore.setFood(food);
				ConsoleHelpe.writeMessage(foodStore.toString());
				foodStoreDao.update(foodStore);



			} catch (PersistException e) {
				e.printStackTrace();
			}


		}
}
