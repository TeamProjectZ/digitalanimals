import command.CommandExecutor;
import command.Operation;
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

			CommandExecutor.execute(Operation.REGISTER);

		}

}
