package Project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

public class hotelManager {
	public static void listEmployee(Staff[] staffs) {
		for (int i = 0; i < staffs.length; i++)
			if (staffs[i] != null)
				staffs[i].display();
	}

	public hotelManager() {

	}

	public static void statistic(Room[] rooms, Reservation[] res, Staff[] staff, Customer[] cus) {
		int day_to_stay = 0;

		int total_Income = 0;
		int total_salary = 0;
		int profit = 0;

		int best_customer_day = 0;
		int best_customer_id = 0;

		int most_room_day = 0;
		int most_reserve_id = 0;

		double occupancy_rate = 0;

		for (int i = 0; i < Reservation.count; ++i) {
			day_to_stay = ((res[i].getDeparturedate().getDay() - res[i].getArrivaldate().getDay())
					+ ((res[i].getDeparturedate().getMonth() - res[i].getArrivaldate().getMonth()) * 30)
					+ ((res[i].getDeparturedate().getYear() - res[i].getArrivaldate().getYear()) * 365));

			total_Income = rooms[i].getPrice() * day_to_stay;

//          ***********-istisna olan durumlar için-**********
			for (int l = 0; l < Reservation.count; ++l) {
				if (i != l && res[i].getRoomID() == res[l].getRoomID()) {
					most_room_day += ((res[l].getDeparturedate().getDay() - res[l].getArrivaldate().getDay())
							+ ((res[l].getDeparturedate().getMonth() - res[l].getArrivaldate().getMonth()) * 30)
							+ ((res[l].getDeparturedate().getYear() - res[l].getArrivaldate().getYear()) * 365));
				}

				if (i != l && res[i].getCustomerID() == res[l].getCustomerID()) {
					best_customer_day += ((res[l].getDeparturedate().getDay() - res[l].getArrivaldate().getDay())
							+ ((res[l].getDeparturedate().getMonth() - res[l].getArrivaldate().getMonth()) * 30)
							+ ((res[l].getDeparturedate().getYear() - res[l].getArrivaldate().getYear()) * 365));
				}
			}

			// most reserved room
			if (day_to_stay > most_room_day) {
				most_room_day = day_to_stay;
				most_reserve_id = res[i].getRoomID();
			}

			// best customer day and id
			if (day_to_stay > best_customer_day) {
				best_customer_day = day_to_stay;
				best_customer_id = res[i].getCustomerID();
			}

		}

		for (int j = 0; j < 30; ++j) {
			if (staff[j] != null) {
				total_salary = staff[j].Salary * 12;
			} else
				break;
		}

		profit = total_Income - total_salary - 120000;

		System.out.println("\n1.The most reserved room = Room #" + most_reserve_id);
		System.out.println();
		System.out.println("2.The best customer = " + cus[best_customer_id].getName() + " "
				+ cus[best_customer_id].getSurname() + "     " + best_customer_day + " days");
		System.out.println();
		System.out.println("3.Income = " + total_Income);
		System.out.println("  Salary = " + total_salary);
		System.out.println("  Constant expenses = 120,000");
		System.out.println("  Profit = " + total_Income + " - " + total_salary + " -" + " 120,000" + " = " + profit);
		System.out.println();
		System.out.println("4.Monhtly occupancy rate ");
		for (int k = 1; k <= 12; k++) {
			occupancy_rate = 0;

			for (int m = 0; m < Reservation.count; m++) {
				if (res[m].getArrivaldate().getMonth() == k && res[m].getDeparturedate().getMonth() == k) {
					occupancy_rate += (res[m].getDeparturedate().getDay() - res[m].getArrivaldate().getDay());
				}

				else if (res[m].getArrivaldate().getMonth() == k && res[m].getDeparturedate().getMonth() > k) {
					occupancy_rate += 30 - res[m].getArrivaldate().getDay();
				}

				else if (res[m].getArrivaldate().getMonth() < k && res[m].getDeparturedate().getMonth() == k) {
					occupancy_rate += res[m].getDeparturedate().getDay();
				}

				else if (res[m].getArrivaldate().getMonth() < k && res[m].getDeparturedate().getMonth() > k) {
					occupancy_rate += 30;
				}
				System.out.print(k + ". month %" + (occupancy_rate / (30.0 * Room.count)) * 100 + "     ");
			}
		}
	}

	public static int Textreader(int iteration, String dosyaAdi, Room[] rooms, Staff[] staff, Customer[] customers,
			Reservation[] reservations, Hotel hotel) throws IOException {
		boolean flag = true;
		File f = new File(dosyaAdi);
		Scanner sc = new Scanner(f);

		for (int i = 0; i < iteration; i++) // to continue from last line,it has no use if textreader ran for the first
											// time;
		{
			sc.nextLine();
		}

		while (sc.hasNextLine()) {

			String[] lineArr = sc.nextLine().trim().split(";");
			switch (lineArr[0]) {
			case "listEmployees":
				listEmployee(staff);
				break;
			case "listCustomers":
				listCustomer(customers);
				break;
			case "listRooms":
				listRooms(rooms);
				break;
			case "listReservations":
				listReservation(reservations, customers);
				break;
			case "searchCustomer":
				searchCustomer(customers, lineArr[1]);
				break;
			case "searchRoom":
				String startdate = lineArr[1];
				String enddate = lineArr[2];
				searchRoom(rooms, reservations, startdate, enddate);
				break;
			case "deleteEmployee":
				fireStaff(staff, Integer.parseInt(lineArr[1]));
				break;
			case "addRoom":
				int addNum = Integer.parseInt(lineArr[1]);
				String Type = lineArr[2];
				boolean AirCond = Boolean.valueOf(lineArr[3]);
				boolean Balcony = Boolean.valueOf(lineArr[4]);
				int price = Integer.parseInt(lineArr[5]);
				addRoom(rooms, addNum, Type, AirCond, Balcony, price);
				break;
			case "addEmployee":
				String Name = lineArr[1] + " " + lineArr[2];
				String Gender = lineArr[3];
				String BirthDate = lineArr[4];
				String Apt = lineArr[5];
				String District = lineArr[6];
				String Province = lineArr[7];
				String phone = lineArr[8];
				String Task = lineArr[9];
				int Salary = Integer.parseInt(lineArr[10]);
				Adress adress = new Adress(Apt, District, Province);
				addStaff(staff, Name, Gender, BirthDate, phone, Task, Salary, adress);
				break;
			case "addCustomer":
				String cusName = lineArr[1];
				String cusSurname = lineArr[2];
				String cusGender = lineArr[3];
				String cusBirthDate = lineArr[4];
				String cusApt = lineArr[5];
				String cusDistrict = lineArr[6];
				String cusProvince = lineArr[7];
				String cusphone = lineArr[8];
				addCustomer(customers, cusName, cusSurname, cusGender, cusBirthDate, cusApt, cusDistrict, cusProvince,
						cusphone);
				break;
			case "addReservation":
				int cusID = Integer.parseInt(lineArr[1]);
				int roomID = Integer.parseInt(lineArr[2]);
				String resStDay = lineArr[3];
				String resEnDay = lineArr[4];
				addReservation(rooms, reservations, cusID, roomID, resStDay, resEnDay);
				break;
			case "simulation":
				Simulation sims = new Simulation(lineArr[1], lineArr[2]);
				sims.countCustomer(customers);
				sims.Simulation_Write();
				break;
			case "statistics":
				statistic(rooms, reservations, staff, customers);
				break;

			}

			iteration++;
			if (flag) {
				while (true) {

					Scanner scn = new Scanner(System.in);
					System.out.println(
							"Do you want to continue (type in 1),run the rest at once (type in 2) or return to the menu (type in 0)");
					String answer = scn.next();
					if (answer.equals("1"))
						break;
					else if (answer.equals("0"))
						return iteration;
					else if (answer.equals("2")) {
						flag = false;
						break;
					}
					System.out.println("Please write 1,2<< or 0,nothing else is accepted");

				}
			}

		}
		if (!sc.hasNextLine())
			System.out.println("Commands ran out of line.");
		return iteration;
	}

	public static void searchCustomer(Customer[] cus, String pattern) {

		String pat = " ";

		int pos = 0;

		for (int i = 0; i < pattern.length(); i++)

		{
			if (pattern.charAt(i) == '*')

			{
				pat = "*";

				pos = i;

				break;

			}

			else if (pattern.charAt(i) == '?')

			{
				pat = "?";

				pos = i;

				break;

			}

		}

		int k = 1;

		if (pat == "*") {

			for (int i = 0; i < 3; i++)

			{
				String sub = cus[i].getName();

				String subs = sub.substring(0, pos);

				if (pattern.substring(0, pos).equals(cus[i].getName().substring(0, pos)))

					System.out.println("Customer #" + (i + 1) + " " + cus[i].display());

				sub = " ";
			}
		} else if (pat == "?") {
			for (int i = 0; i < 3; i++)

			{
				String sub = cus[i].getName();
				if (sub.length() == pattern.length()
						&& pattern.substring(0, pos).equals(cus[i].getName().substring(0, pos))) {
					System.out.println("Customer #" + (i + 1) + " " + cus[i].display());
				}
			}
		}

	}

	public static void addRoom(Room[] rooms, int numberToAdd, String Type, boolean AirCond, boolean Balcon, int Price)

	{
		for (int k = 0; k < numberToAdd; k++) {
			int i = 0;
			while (i < 30) {
				if (rooms[i] == null) {
					Room room = new Room(i, Type, AirCond, Balcon, Price);
					rooms[i] = room;
					break;
				} else if (i == 29 && rooms[29] != null) {
					System.out.println("Sorry we're out of space to build new rooms so we only built " + k + " rooms");
				} else if (i == 29 && k == 0 && rooms[29] != null) {
					System.out.println("There is no space to build more rooms");
				}
				i++;

			}
		}

	}

	public static void addStaff(Staff[] staff, String name, String gender, String birthDate, String phoneNo,
			String task, int salary, Adress address)

	{
		int i = 0;
		while (i < 30) {
			if (staff[i] == null) {
				Staff staf = new Staff(task, name, birthDate, gender, phoneNo, salary, address);
				staff[i] = staf;
				break;
			} else if (i == 29 && staff[29] != null) {
				System.out.println("Sorry we've no capacity to hire staff");
				break;
			}
			i++;
		}
	}

	public static void fireStaff(Staff[] staff, int index) {
		staff[index] = null;

	}

	public static void listRooms(Room[] rooms) {
		for (int i = 0; i < rooms.length; i++) {
			if (rooms[i] != null)
				rooms[i].display();

		}
	}

	public static void menu(String path, boolean firsttimeflag, Hotel hotel, Room[] rooms, Customer[] customers,
			Staff[] Employees, Reservation[] reservations) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		String menu;
		int iteration = 0; // To execute the commands from the txt
		do {
			System.out.println("********** Information on Hotel Management Structures ********** \n \n \n \n");
			System.out.println(
					"For Hotel Information Press 1:\n(The information about the hotel, such as name, foundation date, address, phone number, and the number) \n \n ");
			System.out.println(
					"For Room Information Press 2: \n (Hotel DEUCENG is small with few rooms (max. 30). It has various types of rooms; including regular, \n deluxe, and suite. Rooms may or may not have some properties such as air-conditioning and a balcony. Each room type has different prices for each day.) \n \n ");
			System.out.println(
					"For Staff Information Press 3: \n (The hotel staff consists of maximum 50 employees to fulfill different tasks. There are 3 types of staff; \n administrator, receptionist and housekeeper. There must be at least one employee for each type to run the hotel. \n The information about the staff, such as name, birth date, gender, address, phone number, job, and salary will be managed by the software.) \n \n ");
			System.out.println(
					"For Customer Information Press 4: \n (The software is used to manage the records of customers, including name, contact address, and phone number.) \n \n ");
			System.out.println(
					"For Reservation Information Press 5: \n (It keeps the reservation details of the customers such as customer-id, room-id, date of arrival, \n and date of departure. It is expected to make a reservation between 01.01.2024 and 31.12.2024.) \n \n ");
			if (firsttimeflag)
				System.out.println("To Run The First Line of Commands Press 6  \n \n ");
			else {
				System.out.println("To Continue Commands Press 7 \n \n");
				System.out.println("To Start Over Commands Press 8 \n \n");
			}
			System.out.println();
			System.out.print("Make Your Choice:");

			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				hotel.display();
				;
				System.out.println("Press any character to return to the menu.");
				menu = scanner.next();
				break;
			case 2:
				listRooms(rooms);
				System.out.println("Press any character to return to the menu.");
				menu = scanner.next();
				break;
			case 3:
				listEmployee(Employees);
				System.out.println("Press any character to return to the menu.");
				menu = scanner.next();

				break;
			case 4:
				listCustomer(customers);
				System.out.println("Press any character to return to the menu.");
				menu = scanner.next();

				break;
			case 5:
				listReservation(reservations, customers);
				System.out.println("Press any character to return to the menu.");
				menu = scanner.next();
				break;
			case 6:

				try {

					iteration = Textreader(0, path, rooms, Employees, customers, reservations, hotel);
					firsttimeflag = false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				firsttimeflag = false;
				System.out.println("Press any character to return to the menu.");
				menu = scanner.next();
				break;
			case 7:
				try {

					iteration = Textreader(iteration, path, rooms, Employees, customers, reservations, hotel);
					firsttimeflag = false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Press any character to return to the menu.");
				menu = scanner.next();
				break;
			case 8:
				iteration = 0;
				rooms = new Room[30];
				customers = new Customer[50];
				reservations = new Reservation[30];
				try {

					iteration = Textreader(0, path, rooms, Employees, customers, reservations, hotel);
					firsttimeflag = false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Press any character to return to the menu.");
				menu = scanner.next();
				break;
			case 9:
				System.out.println("Exiting the program...");

			default:
				System.out.println("Wrong choice! Please choose again.");
			}
		} while (choice != 9);

		scanner.close();
	}

	public static void listCustomer(Customer[] customers) {
		for (int i = 0; i < customers.length; i++)
			if (customers[i] != null)
				System.out.println("Customer #" + (i + 1) + " " + customers[i].display());
	}

	public static void addCustomer(Customer[] customer, String name, String surname, String gender, String birthdate,
			String addresstext, String district, String city, String phone) {
		if (Customer.count == 0)
			customer[0] = new Customer(name, surname, gender, birthdate, addresstext, district, city, phone);
		else
			customer[Customer.count - 1] = new Customer(name, surname, gender, birthdate, addresstext, district, city,
					phone);
	}

	public static void listReservation(Reservation[] res, Customer[] customer) {
		for (int i = 0; i < Reservation.count; i++) {

			String arrivaldate = res[i].getArrivaldate().getDay() + "." + res[i].getArrivaldate().getMonth() + "."
					+ res[i].getArrivaldate().getYear();
			String departuredate = +res[i].getDeparturedate().getDay() + "." + res[i].getDeparturedate().getMonth()
					+ "." + res[i].getDeparturedate().getYear();
			int roomıd = res[i].getRoomID();
			String name = customer[res[i].CustomerID - 1].getName();
			String surname = customer[res[i].CustomerID - 1].getSurname();
			Formatter fmt = new Formatter();
			fmt.format("%5s %1s %-8s %-8s %10s %10s", "Room #", roomıd, name, surname, arrivaldate, departuredate);
			System.out.println(fmt.toString());
		}

	}

	public static void searchRoom(Room[] rooms, Reservation[] res, String startdate, String finaldate) {
		String[] splite = startdate.split("\\.");
		int startday = Integer.parseInt(splite[0]);
		int startmonth = Integer.parseInt(splite[1]);
		String[] spl = finaldate.split("\\.");
		int finishday = Integer.parseInt(spl[0]);
		int finishmonth = Integer.parseInt(spl[1]);
		boolean flag = true;
		boolean flag1 = true;
		for (int j = 0; j < Room.count; j++) {
			for (int i = 0; i < Reservation.count; i++) {
				if (rooms[j].getID() == res[i].RoomID)
					flag1 = false;
			}
			if (flag1 == true)
				rooms[j].display();

			else {
				if (Reservation.count == 0)

					for (int k = 0; k < Room.count; k++)
						rooms[k].display();

				else
					for (int k = 0; k < Reservation.count; k++) {
						flag = true;
						if (res[k].equals(null))
							k = Reservation.count;

						else if (startmonth == finishmonth) {
							for (int m = startday; m < 32; m++) {
								if (res[k].getArrivaldate().getDay() == m
										&& res[k].getArrivaldate().getMonth() == startmonth)
									flag = false;

								else if (res[k].getArrivaldate().getDay() == m
										&& res[k].getArrivaldate().getMonth() == finishmonth)
									flag = false;

							}
						}

						else {
							for (int i = startmonth; i <= finishmonth; i++)

							{
								for (int a = startday; a < 32; a++) {
									if (res[k].getArrivaldate().getDay() == a
											&& res[k].getArrivaldate().getMonth() == i)
										flag = false;
									else if (res[k].getDeparturedate().getDay() == a
											&& res[k].getDeparturedate().getMonth() == i)

										flag = false;

								}
							}
						}

						if (flag == true)

							rooms[res[k].getRoomID() - 1].display();

					}
			}
			flag1 = true;
		}
	}

	public static void addReservation(Room[] rooms, Reservation[] res, int customer_id, int room_id, String startdate,

			String finaldate) { // TODO Auto-generated method stub

		String[] startspl = startdate.split("\\.");

		String[] finishspl = finaldate.split("\\.");

		int[] month = { 1, 3, 5, 7, 10 };

		boolean flag = true;

		for (int i = 0; i < 5; i++)

		{

			if ((Integer.parseInt(startspl[1]) == month[i] && Integer.parseInt(startspl[0]) > 31)
					|| (Integer.parseInt(finishspl[1]) == month[i] && Integer.parseInt(finishspl[0]) > 31))

			{

				System.out.println("We are sorry but there is no such day.");

				flag = false;

				break;

			}

			else if ((Integer.parseInt(startspl[1]) == 2 && Integer.parseInt(startspl[0]) > 29)
					|| (Integer.parseInt(finishspl[1]) == 2 && Integer.parseInt(finishspl[0]) > 29))

			{

				System.out.println("We are sorry but there is no such day.");

				flag = false;

				break;

			}

			else if ((Integer.parseInt(startspl[0]) > 30) || (Integer.parseInt(finishspl[0]) > 30))

			{
				System.out.println("We are sorry but there is no such day.");

				flag = false;

				break;

			}

			else if (Integer.parseInt(startspl[1]) < 0 || Integer.parseInt(startspl[1]) > 12
					|| Integer.parseInt(finishspl[1]) < 0 || Integer.parseInt(finishspl[1]) > 12)

			{
				System.out.println("We are sorry but there is no such day.");

				flag = false;

				break;

			}

			else if (Integer.parseInt(startspl[2]) != 2024 || Integer.parseInt(finishspl[2]) != 2024)

			{

				System.out.println("We are sorry but there is no such day.");

				flag = false;

				break;

			}

		}

		if (Reservation.count == 0 && flag == true)

		{

			String[] startsplit = startdate.split("\\.");

			Date start = new Date(Integer.parseInt(startsplit[0]), Integer.parseInt(startsplit[1]),
					Integer.parseInt(startsplit[2]));

			String[] finishsplit = finaldate.split("\\.");

			Date finish = new Date(Integer.parseInt(finishsplit[0]), Integer.parseInt(finishsplit[1]),
					Integer.parseInt(finishsplit[2]));

			res[0] = new Reservation(customer_id, room_id, start, finish);

			flag = false;

		}

		else if (flag == true)

		{

			boolean flag2 = true;

			for (int i = 0; i < Reservation.count; i++)

			{

				if (room_id == res[i].getRoomID())

				{

					flag2 = false;

				}

			}

			if (flag2 == true && flag == true)

			{

				String[] startsplit = startdate.split("\\.");

				Date start = new Date(Integer.parseInt(startsplit[0]), Integer.parseInt(startsplit[1]),

						Integer.parseInt(startsplit[2]));

				String[] finishsplit = finaldate.split("\\.");

				Date finish = new Date(Integer.parseInt(finishsplit[0]), Integer.parseInt(finishsplit[1]),

						Integer.parseInt(finishsplit[2]));

				res[Reservation.count] = new Reservation(customer_id, room_id, start, finish);

				flag2 = false;

			}

			else if (flag2 == false && flag == true)

			{
				boolean flag1 = true;

				String[] splite = startdate.split("\\.");

				int startday = Integer.parseInt(splite[0]);

				int startmonth = Integer.parseInt(splite[1]);

				String[] spl = finaldate.split("\\.");

				int finishday = Integer.parseInt(spl[0]);

				int finishmonth = Integer.parseInt(spl[1]);

				for (int k = 0; k < Reservation.count; k++) {

					flag1 = true;

					if (room_id == res[k].getRoomID())

					{
						if (res[k].equals(null))

							k = Reservation.count;

						else if (startmonth == finishmonth)

						{
							for (int m = startday; m < 32; m++)

							{

								if (res[k].getArrivaldate().getDay() == m
										&& res[k].getArrivaldate().getMonth() == startmonth)

									flag1 = false;

								else if (res[k].getDeparturedate().getDay() == m
										&& res[k].getDeparturedate().getMonth() == finishmonth)

									flag1 = false;

							}

						}

						else

						{

							for (int i = startmonth; i <= finishmonth; i++)

							{

								for (int j = startday; j < 32; j++)

								{

									if (res[k].getArrivaldate().getDay() == j
											&& res[k].getArrivaldate().getMonth() == i)

									{

										flag1 = false;

									}

									else if (res[k].getDeparturedate().getDay() == j
											&& res[k].getDeparturedate().getMonth() == i)

									{

										flag1 = false;

									}

								}

							}

						}

					}

					if (flag1 == true && flag == true)

					{

						String[] startsplit = startdate.split("\\.");

						Date start = new Date(Integer.parseInt(startsplit[0]), Integer.parseInt(startsplit[1]),
								Integer.parseInt(startsplit[2]));

						String[] finishsplit = finaldate.split("\\.");

						Date finish = new Date(Integer.parseInt(finishsplit[0]), Integer.parseInt(finishsplit[1]),
								Integer.parseInt(finishsplit[2]));

						res[Reservation.count] = new Reservation(customer_id, room_id, start, finish);

					}

					else if (flag1 == false)
						System.out.println("Sorry but we are full that day");

				}

			}

		}

	}

	public static void run() {
		boolean flag = true;
		Staff[] staffs = new Staff[50];
		Customer[] cus = new Customer[3];

		Room[] rooms = new Room[30];
		Reservation[] res = new Reservation[30];

		Hotel hotel = new Hotel("Paradise", new Date(03, 03, 2023), new Adress("Tekirova, 07995", " Kemer", "Antalya"),
				"+90 242 821 50 11", 5,
				new Customer("name", "surname", "gender", "birthdate", "addresstext", "district", "city", "phone"));

		menu("Commands.txt", flag,hotel, rooms, cus, staffs, res);
	}

}