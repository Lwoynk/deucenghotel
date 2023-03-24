package Project;

public class Simulation {
	
	public String StartDate;
	public String EndDate;
	private static int count = 0;
	int total_sim_day;
	int[] Customer_Number;
	double[] Satisfaction;
	
	public Simulation(String start,String End)
	{
		StartDate=start;
		EndDate=End;
		//Finding the number of days to simulate
		total_sim_day = getDayOfYear(EndDate) - getDayOfYear(StartDate) +1;
		
		//Array assignment according to the number of days	
		Customer_Number = new int[total_sim_day];
		Satisfaction = new double[total_sim_day];
	}
	//Function to find the day of the year from the given date (for 2024)
	
	
	//Method that converts the given number of days to date string in "dd-mm" format
	
	public String getDateString(int dayOfYear) {
        int[] daysInMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; // şubat ayını 29 gün kabul ederek
        int totalDays = 0;
        int month = 0;
        int dayOfMonth = 0;
        boolean found = false;
        
        for (int i = 0; i < daysInMonth.length; i++) {
            if (totalDays + daysInMonth[i] >= dayOfYear) {
                month = i + 1;
                dayOfMonth = dayOfYear - totalDays;
                found = true;
                break;
            }
            totalDays += daysInMonth[i];
        }
        
        if (!found) { // toplam gün sayısı yılın sonuna kadar yetmediyse
            month = 12;
            dayOfMonth = dayOfYear - totalDays;
        }
        
        String dayString = String.format("%02d", dayOfMonth); // iki haneli gün sayısı
        String monthString = String.format("%02d", month); // iki haneli ay sayısı
        
        return dayString + "-" + monthString;
    }
	
	
	
	
	//Method to find the number of customers on the current day
	public void countCustomer(Customer[] customers)	

	  {
		int countCostumer =0;	

	     for(int i=0;i<customers.length;i++)
	     {
	    	 if(customers[i]!=null)
	    	 {
	    		 countCostumer +=1; 
	    	 }
	     }
	 	for(int i =0; i<total_sim_day; i++)
		{
	 		Customer_Number[count] = countCostumer;
	 		
	 		if (countCostumer <=3) {Satisfaction[count] = 100;}
	 		else {Satisfaction[count] = 3/countCostumer*100;}	
		    count+=1;
		    
		}
	  }
	
	public void Simulation_Write()

	{
		System.out.println("Simulation;" + StartDate + ";" + EndDate);
		System.out.print(" Day:   ");
		for(int d =0; d< total_sim_day; d++)
		{
			System.out.print(getDateString(getDayOfYear(StartDate)+d) +"   ");
		}
		System.out.print("\n Customer:   ");
		for(int d2 =0; d2< total_sim_day; d2++)
		{
			System.out.print(Customer_Number[d2] +"   ");
		}
		System.out.print("\n Satisfaction:   ");

		double total_satisf =0.0;
		for(int d3 =0; d3< total_sim_day; d3++)
		{
			System.out.print(Satisfaction[d3] +"   ");
			 total_satisf += Satisfaction[d3];
		}
		double avg_satisf = (total_satisf/total_sim_day);
		System.out.println("\n Average Satisfaction =" + avg_satisf +"%");
	}

	public int getDayOfYear(String date) {
	    int year, month, day;

	    String[] tokens = new String[3];
	    tokens = date.split("\\.");
	    day = Integer.parseInt(tokens[0]);
	    month = Integer.parseInt(tokens[1]);
	    year = Integer.parseInt(tokens[2]);

	    int[] daysInMonth = {31,29,31,30,31,30,31,31,30,31,30,31};

	    int numDays = day;

	    for (int i = 0; i < month - 1; i++) {
	        numDays += daysInMonth[i];
	    }

	    return numDays;
	}
	
	
	
	
}