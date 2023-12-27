import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main
{
  private static final String FILENAME = "car_data.txt";
  private static final String NAME = "rented_data.txt";
  private static final String FILE = "rentercart_data.txt";
  private static final String SECURITYDEPOSIT = "config.txt";
  private static final String EMAIL = "userdata.txt";
  private static Map<String, String> emailAndPasswords;
  private static int Securitydepositforbike;
  private static int Securitydepositforcar;

  public static void main (String[]args)
  {
    Scanner scanner = new Scanner (System.in);

      ArrayList < Map < String, Object >> cars = loadCarDataFromFile ();
      Map < String, ArrayList < Map < String, Object >>> renterToVehicles =loadcartDataFromFile ();
      Map < String, ArrayList < Map < String, Object >>> rentedvehicles =loadRentedDataFromFile ();
        emailAndPasswords = loadEmailDataFromFile();
      
    String semail = "girijadevi@gmail.com";
    String spassword = "Siet@i2022";
      System.out.println("Enter the EmailId: ");
    String email = scanner.nextLine ();

      System.out.println("Enter the password: ");
    String password = scanner.nextLine ();
      
    Boolean isValid =  (isCredentialsValid(email, password)) ;
    if (semail.equals (email))
      {
	if (spassword.equals (password))
	  {
	    System.out.println ("**************Welcome Admin***************");
	    System.out.println ();
	    System.out.println ("******************************************");
	    System.out.println ("       1.Add a vehicle(bike/car)          ");
	    System.out.println ("       2.Update the renter Name           ");
	    System.out.println ("       3.Delete the Vehicle               ");
	    System.out.println ("       4.Due for Vehicle                  ");
	    System.out.println ("       5.Search a Vehicle                 ");
	    System.out.println ("       6.View list of all Vehicles        ");
	    System.out.println ("       7.Security Deposit                 ");
	     System.out.println("       8.Add new User                     ");
	    System.out.println ("******************************************");
	    System.out.println ("Do You want to modify:(yes/no)");
	    String modify = scanner.nextLine ();
	    while (modify.equals ("yes"))
	      {
		System.out.println ("Enter the Number to modify :");
		String number = scanner.nextLine ();
		switch (number)
		  {
		  case "1":
		    {
		      Map < String, Object > carDetails = new HashMap <> ();
		      System.out.print ("Enter type whether car /bike: ");
		      carDetails.put ("type", scanner.nextLine ());

		      System.out.print ("Enter car name: ");
		      carDetails.put ("model", scanner.nextLine ());

		      System.out.print ("Enter number plate: ");
		      carDetails.put ("numberPlate", scanner.nextLine ());

		      System.out.print ("Enter rented price: ");
		      carDetails.put ("rentedPrice",Double.parseDouble (scanner.nextLine ()));

		      System.out.print ("Enter renter name: ");
		      carDetails.put ("renterName", scanner.nextLine ());
		      System.out.print ("Enter kmrunned : ");
		      carDetails.put ("kmrunned", scanner.nextLine ());

		      cars.add (carDetails);
		      saveCarDataToFile (cars);
		      break;
		    }
		    case "2":
		    {
		      System.out.println ("Enter the name of vehicle");
		      String updateCarName = scanner.nextLine ();
		      boolean updated = false;
		    for (Map < String, Object > car:cars)
			{
			  if (car.get ("model").equals (updateCarName))
			    {
			      System.out.print ("Enter new renter name: ");
			      String newRenterName = scanner.nextLine ();
			      car.put ("renterName", newRenterName);
			      updated = true;
			      break;
			    }
			}
		      if (updated)
			{
			  saveCarDataToFile (cars);
			  System.out.println ("\nRenter name updated for car '" +updateCarName + "'.");
			}
		      else
			{
			  System.out.println("\nCar not found for updating renter name.");
			}
		      break;
		    }
		  case "3":
		    {
		      System.out.print ("\nEnter a vehicle name to delete: ");
		      String deleteCarName = scanner.nextLine ();
		      boolean deleted = false;
		    for (Map < String, Object > car:cars)
			{
			  if (car.get ("model").equals (deleteCarName))
			    {
			      cars.remove (car);
			      deleted = true;
			      break;
			    }
			}

		      if (deleted)
			{
			  saveCarDataToFile (cars);
			  System.out.println ("Car " + deleteCarName + " deleted successfully.");
			}
		      else
			{
			  System.out.println ("Car not found for deletion.");
			}

		      break;
		    }
		  case "4":
		    {
		      System.out.println ();
		      System.out.println
			("*************Due for Service*****************");
		      System.out.println ("bike:");
		    for (Map < String, Object > car:cars)
			{
			  if (car.get ("type").equals ("bike"))
			    {
			      int n =Integer.valueOf ((String) car.get ("kmrunned"));
			      if (n >= 1500)
				{
				  System.out.println (car.get ("model"));
				}
			    }
			}
		      System.out.println ("car:");
		    for (Map < String, Object > car:cars)
			{
			  if (car.get ("type").equals ("car"))
			    {
			      int n =Integer.valueOf ((String) car.get ("kmrunned"));
			      if (n >= 3000)
				{
				  System.out.println (car.get ("model"));
				}
			    }
			}
		      System.out.println("*********************************************");
		      break;
		    }
		  case "5":
		    {
		      System.
			out.println ("Enter the vehicle name to search:");
		      String vehiclename = scanner.nextLine ();
		      boolean found = false;
		    for (Map < String, Object > car:cars)
			{
			  if (car.get ("model").equals (vehiclename))
			    {
			      found = true;
			      System.out.println ("type                : " +car.get ("type"));
			      System.out.println ("Car Name            : " +car.get ("model"));
			      System.out.println ("Number Plate        : " +car.get ("numberPlate"));
			      System.out.println ("Rented Price        : " +car.get ("rentedPrice"));
			      System.out.println ("Renter Name         : " +car.get ("renterName"));
			      System.out.println ("KM ran by vehicle   : " +car.get ("kmrunned"));
			      System.out.println ();
			    }
			}

		      if (found)
			System.out.println ("Car  found.");
		      else
			System.out.println ("Car not found.");

		      break;
		    }
		  case "6":
		    {
		      System.out.println ("List Of Vehicles");
		    for (Map < String, Object > car:cars)
			{
			  System.out.println ("type                : " +car.get ("type"));
			  System.out.println ("Car Name            : " +car.get ("model"));
			  System.out.println ("Number Plate        : " +car.get ("numberPlate"));
			  System.out.println ("Rented Price        : " +car.get ("rentedPrice"));
			  System.out.println ("Renter Name         : " +car.get ("renterName"));
			  System.out.println ("KM ran by vehicle   : " +car.get ("kmrunned"));
			  System.out.println ();
			}
		      break;
		    }
		  case "7":
		    {
               loadConfigurableIntegers();

        
                  System.out.println("Security deposit for bike: " + Securitydepositforbike);
                  System.out.println("Security deposit for car: " + Securitydepositforcar);

                  System.out.println("Entry the new Security deposit for bike:");
                  int bike=scanner.nextInt();
                   System.out.println("Entry the new Security deposit for Car:");
                  int car=scanner.nextInt();
                  
        changeIntegersByAdmin(bike, car);

        
        loadConfigurableIntegers();
        System.out.println("Updated  Security deposit for bike:" + Securitydepositforbike);
        System.out.println("Updated  Security deposit for car:" +Securitydepositforcar);
            break;
		    }
          case "8":
          {
              addNewUserData();
              saveEmailToFile(); 
              break;
          }
		  }

		System.out.println ();
		System.out.println ("Do You want to modify again:(yes/no)");
		modify = scanner.nextLine ();
	  }
	  
	  }
	else
	  {
	    System.out.println ("please check the password");
	  }
      }


    else if (isValid)
      {
	System.out.println ("**************Welcome Client***********");
	System.out.println ("");
	System.out.println ("***************************************");
	System.out.println ("        1.Add vehicle to cart          ");
	System.out.println ("        2.Checkout cart                ");
	System.out.println ("        3.Remove cart                  ");
	System.out.println ("        4.View a list of all Vehicles  ");
	System.out.println ("        5.rent the vehicle             ");
	System.out.println ("        6.close the rent               ");
	System.out.println ("***************************************");
	System.out.println("Enter your name: ");
		    String name=scanner.nextLine();
	System.out.println ("Do You want to modify or view:(yes/no)");
	String view = scanner.nextLine ();
	
	while (view.equals ("yes"))
	  {
	    System.out.println ("Enter the Number to modify :");
	    String n = scanner.nextLine ();
	    switch (n)
	      {
	  case "1":
		   {
		  

		  if (!renterToVehicles.containsKey (name))
		    {
		      renterToVehicles.put (name, new ArrayList <> ());
		    }


		  Map < String, Object > carDetails = new HashMap <> ();

		  System.out.print ("Enter  vehicle model : ");
		  String cname=scanner.nextLine();
		  carDetails.put ("model", cname);
		  
             for (Map < String, Object > car:cars)
			{
			    if(car.get("model").equals(cname)){
			
			carDetails.put ("numberPlate", car.get ("numberPlate"));
			carDetails.put ("rentedPrice",car.get ("rentedPrice"));
			    }
			}
			

		  renterToVehicles.get (name).add (carDetails);
		  saveRenterDataToFile (renterToVehicles);
		  break;
	      	}

	 case "2":
	    	{

		     for (Map.Entry < String, ArrayList < Map < String, Object >>> entry:renterToVehicles.entrySet())
		    {
		      String renterName = entry.getKey ();
		      if (renterName.equals (name))
			{
			  System.out.println ("\nRenter Name: " + renterName);
			  System.out.println ("Cars in Cart:");

			  ArrayList < Map < String, Object >> vehicle =entry.getValue ();
			  for (int i = 0; i < vehicle.size (); i++)
			    {
			      Map < String, Object > car = vehicle.get (i);
			      System.out.println ("Index: " + i);
			      System.out.println ("Car Name: " +car.get ("model"));
			      System.out.println ("Number Plate: " + car.get ("numberPlate"));
			      System.out.println ("Rented Price: " + car.get ("rentedPrice"));
			      System.out.println ();
			    }
			}
		    }
		       break;
		    }
	 case "3":
		      {
		    
		 for (Map.Entry < String, ArrayList < Map < String, Object >>> entry:renterToVehicles.entrySet())
		    {
		      String renterName = entry.getKey ();
		      if(name.equals(renterName)){
		      System.out.println ("\nRenter Name: " + renterName);
		      System.out.println ("Cars in Cart:");

		      ArrayList < Map < String, Object >> vehicles =
			entry.getValue ();
		      for (int i = 0; i < vehicles.size (); i++)
			{
			  Map < String, Object > car = vehicles.get (i);
			  System.out.println ("Index: " + i);
			  System.out.println ("Car Name: " +car.get ("model"));
			  System.out.println ("Number Plate: " +car.get ("numberPlate"));
			  System.out.println ("Rented Price: " +car.get ("rentedPrice"));
			  System.out.println ();
			}

		      System.out.println("Enter the index of the car to remove from the cart : ");
		      int deleteIndex =Integer.parseInt (scanner.nextLine ());

		      if (deleteIndex >= 0 && deleteIndex < vehicles.size ())
			{
			  vehicles.remove (deleteIndex);
			  System.out.println ("Car removed from cart.");
			}

		      System.out.println ();
		    }
		  saveRenterDataToFile (renterToVehicles);
		    }
		  break;
	     	}
	 case "4":
		   {
		  System.out.println("Enter the vehicle you want to view:(bike/car)");
		  String rentvehicle = scanner.nextLine ();
		  System.out.println ("*******List of " + rentvehicle +" that are available*******");
		  System.out.println ();
		  for (Map < String, Object > car:cars)
		    {
		      if (car.get ("type").equals (rentvehicle))
			{
			  if (car.get ("renterName").equals ("null")
			      || ((String) car.get ("renterName")).isEmpty ())
			    {
			      System.out.println (car.get ("model"));
			    }
			}
		    }
		  System.out.println ();
		  System.out.println("************************************************************");
		  System.out.println("Enter the model of vehicle to get information:");
		  String modelname = scanner.nextLine ();
		  System.out.println ();
		for (Map < String, Object > car:cars)
		    {
		      if (car.get ("model").equals (modelname))
			{
			  System.out.println (rentvehicle + " Name: " +car.get ("model"));
			  System.out.println ("Number Plate: " +car.get ("numberPlate"));
			  System.out.println ("Rent Price: " +car.get ("rentedPrice"));
			  System.out.println ("KM ran by vehicle: " +car.get ("kmrunned"));
			  System.out.println ();
			}
		    }
		  break;
		    }
	 case "5":
		    {
		        Boolean h=false;
		  
		  if (!rentedvehicles.containsKey (name))
		    {
		      rentedvehicles.put (name, new ArrayList <> ());
		    }


		  Map < String, Object > vehicleDetails = new HashMap <> ();
		  System.out.println ("Enter the type of vehicle:(car/bike)");
		  String typeofvehicle=scanner.nextLine();
		  vehicleDetails.put ("type", typeofvehicle);

           
		  System.out.println("Enter vehicle name you want to rent: ");
		  String vehiclemodel=scanner.nextLine();
		  
		  vehicleDetails.put ("model", vehiclemodel);
          
          for (Map < String, Object > car:cars)
			{
			    if(car.get("model").equals(vehiclemodel)){
			
			vehicleDetails.put ("numberPlate", car.get ("numberPlate"));
			vehicleDetails.put ("rentedPrice",car.get ("rentedPrice"));
			    
			   	  if (car.get ("renterName").equals ("null")
			      || ((String) car.get ("renterName")).isEmpty ())
			      {
			          h=true;
			      }
			}
			}
			if(h==true){

		  System.out.print ("Enter security deposit: ");
		  vehicleDetails.put ("SecurityDeposit", scanner.nextLine ());


		  System.out.print ("Enter caution deposit: ");
		  vehicleDetails.put ("CautionDeposit", scanner.nextLine ());
		  System.out.println();
                System.out.print("CautionDeposit  will be refunded on returning the vehicle"+" ");
		      System.out.println("The Amount will be reduced there is any damage or loss of vehicle  "); 
		      
		    
		  rentedvehicles.get (name).add (vehicleDetails);
		  
		for (Map.Entry < String, ArrayList < Map < String, Object >>> entry:rentedvehicles.entrySet())
		    {
		      String renterName = entry.getKey ();
		      if (renterName.equals (name))
			{
			  ArrayList < Map < String, Object >> rent =entry.getValue ();
			  for (int i = 0; i < rent.size (); i++)
			    {
			      Map < String, Object > vehicle = rent.get (i);
			      int security =Integer.valueOf ((String) vehicle.get ("SecurityDeposit"));
			      int caution =Integer.valueOf ((String) vehicle.get ("CautionDeposit"));
			      Boolean b1=(security>=Securitydepositforbike&&vehicle.get("type").equals("bike"));
			      Boolean b2=(security>=Securitydepositforcar&&vehicle.get("type").equals("car"));
			     
			      if (b1==true||b2==true)
				{
				  if (caution >= 30000 )
				    {
				      System.out.println ("Index: " + i);
				      System.out.println ("Car Name: " +vehicle.get ("model"));
				      System.out.println ("Number Plate: " +vehicle.get("numberPlate"));
				      System.out.println ("Rented Price: " +vehicle.get("rentedPrice"));
				      System.out.println ("Security Deposit: " +vehicle.get ("SecurityDeposit"));
				      System.out.println ("CautionDeposit: " +vehicle.get("CautionDeposit"));
				      System.out.println ();
				      saveRentedDataToFile (rentedvehicles);
				      String CarName=(String) vehicle.get ("model");
				      for (Map < String, Object > car:cars)
		              	{
			  if (car.get ("model").equals (CarName))
			    {
			      
			      car.put ("renterName", renterName);
			     saveCarDataToFile (cars);
			      break;
			    }
			}
				      System.out.println (vehicle.get ("model") +" has successfully rented");
				      
				    }
				  else
				     System.out.println("Your Caution Deposit is less than 30000");
				}
			      else
				  System.out. println("Your SecurityDeposit is less than 3000");
				

			    }
			}
		    }
			}
			else{
			System.out.println("****The vehicle is already rented please rent the another vehicle****");
			System.out.println();
		    }
		  break;
		}
		case "6":
		    {
		      String k="",carname="";  
		      for (Map.Entry < String, ArrayList < Map < String, Object >>> entry:rentedvehicles.entrySet())
		    {
		      
		      String renterName = entry.getKey ();
		      if(name.equals(renterName)){
		      System.out.println ("\nRenter Name: " + renterName);
		      System.out.println ("Cars in Cart:");

		      ArrayList < Map < String, Object >> vehicles =entry.getValue ();
		      for (int i = 0; i < vehicles.size (); i++)
			{
			  Map < String, Object > car = vehicles.get (i);
			  System.out.println ("Index: " + i);
			  System.out.println ("Car Name: " +car.get ("model"));
			  System.out.println ("Number Plate: " +car.get ("numberPlate"));
			  System.out.println ("Rented Price: " +car.get ("rentedPrice"));
			  System.out.println ("Security Deposit: " +car.get ("SecurityDeposit"));
			  System.out.println ("CautionDeposit: " +car.get("CautionDeposit"));
			  System.out.println ();
			}

		      System.out.println("Enter the index to close the rent : ");
		        k=scanner.nextLine();
		         Map < String, Object > car = vehicles.get (Integer.parseInt(k));
		         carname=(String) car.get("model");

		    for (Map < String, Object > carss:cars)
			{
			  if (carss.get ("model").equals (carname))
			    {
			      
			      String newRenterName = null;
			      carss.put ("renterName", newRenterName);
			      break;
			    }
			}
		    
			  saveCarDataToFile (cars);
			  
		      int deleteIndex =Integer.parseInt (k);

		      if (deleteIndex >= 0 && deleteIndex < vehicles.size ())
			{
			  vehicles.remove (deleteIndex);
			   saveRentedDataToFile (rentedvehicles);
			  System.out.println ("rent is closed");
			  
			}

		     
			  saveCarDataToFile(cars);
		      }
		     // saveCarDataToFile(cars);
		      
		  saveRenterDataToFile (rentedvehicles);
		  
		    }
		        break;
		    }

	      }
	    System.out.println ("Do You want to modify or view Again :(yes/no)");
	    view = scanner.nextLine ();
	  }
      }
      else
      {
          System.out.println("Invalid credentials");
      }
  }

// VEHICLES 
  private static ArrayList < Map < String, Object >> loadCarDataFromFile ()
  {
    ArrayList < Map < String, Object >> cars = new ArrayList <> ();
    try (BufferedReader br = new BufferedReader (new FileReader (FILENAME)))
    {
      String line;
      while ((line = br.readLine ()) != null)
	{
	  String[]parts = line.split (",");
	  Map < String, Object > carDetails = new HashMap <> ();

	  carDetails.put ("type", parts[0]);
	  carDetails.put ("model", parts[1]);
	  carDetails.put ("numberPlate", parts[2]);
	  carDetails.put ("rentedPrice", Double.parseDouble (parts[3]));
	  carDetails.put ("renterName", parts[4]);
	  carDetails.put ("kmrunned", parts[5]);
	  cars.add (carDetails);
	}
    }
    catch (IOException | NumberFormatException e)
    {
      
      e.printStackTrace ();
    }
    return cars;
  }

  private static void saveCarDataToFile (ArrayList < Map < String,Object >> cars)
  {
    try (PrintWriter writer = new PrintWriter (new FileWriter (FILENAME)))
    {
    for (Map < String, Object > car:cars)
	{
	  writer.println (car.get ("type") + "," +
			  car.get ("model") + "," +
			  car.get ("numberPlate") + "," +
			  car.get ("rentedPrice") + "," +
			  car.get ("renterName") + "," +
			  car.get ("kmrunned"));
	}
    }
    catch (IOException e)
    {

      e.printStackTrace ();
    }
  }
  //CART VEHICLES
  private static Map < String, ArrayList < Map < String,Object >>> loadcartDataFromFile ()
  {
    Map < String, ArrayList < Map < String, Object >>> renterToVehicles =new HashMap <> ();

    try (BufferedReader br = new BufferedReader (new FileReader (FILE)))
    {
      String line;
      while ((line = br.readLine ()) != null)
	{
	  String[]parts = line.split (";");
	  String renterName = parts[0];

	  ArrayList < Map < String, Object >> cars = new ArrayList <> ();
	  for (int i = 1; i < parts.length; i++)
	    {
	      String[]carDetails = parts[i].split (",");
	      Map < String, Object > car = new HashMap <> ();
	      car.put ("model", carDetails[0]);
	      car.put ("numberPlate", carDetails[1]);
	      car.put ("rentedPrice", Double.parseDouble (carDetails[2]));
	      cars.add (car);
	    }

	  renterToVehicles.put (renterName, cars);
	}
    } catch (IOException | NumberFormatException e)
    {
      e.printStackTrace ();
    }
    return renterToVehicles;
  }

  private static void saveRenterDataToFile (Map < String,ArrayList < Map < String,Object >>> renterToVehicles)
  {
    try (PrintWriter writer = new PrintWriter (new FileWriter (FILE)))
    {
    for (Map.Entry < String, ArrayList < Map < String, Object >>> entry:renterToVehicles.entrySet())
	{
	  StringBuilder line = new StringBuilder (entry.getKey ());
	for (Map < String, Object > car:entry.getValue ())
	    {
	      line.append (";").append (car.get ("model")).
		append (",").append (car.get ("numberPlate")).append (",").
		append (car.get ("rentedPrice"));
	    }
	  writer.println (line);
	}
    }
    catch (IOException e)
    {
      
      e.printStackTrace ();
    }
  }
  
  // RENTED VEHICLESS
  private static Map < String, ArrayList < Map < String,Object >>> loadRentedDataFromFile ()
  {
    Map < String, ArrayList < Map < String, Object >>> rentedvehicles =new HashMap <> ();

    try (BufferedReader br = new BufferedReader (new FileReader (NAME)))
    {
      String line;
      while ((line = br.readLine ()) != null)
	{
	  String[]parts = line.split (";");
	  String renterName = parts[0];

	  ArrayList < Map < String, Object >> rented = new ArrayList <> ();
	  for (int i = 1; i < parts.length; i++)
	    {
	      String[]carDetails = parts[i].split (",");
	      Map < String, Object > vehicle = new HashMap <> ();
	      vehicle.put ("type", carDetails[0]);
	      vehicle.put ("model", carDetails[1]);
	      vehicle.put ("numberPlate", carDetails[2]);
	      vehicle.put ("rentedPrice", Double.parseDouble (carDetails[3]));
	      vehicle.put ("SecurityDeposit", carDetails[4]);
	      vehicle.put ("CautionDeposit", carDetails[5]);
	      rented.add (vehicle);
	    }

	  rentedvehicles.put (renterName, rented);
	}
    } catch (IOException | NumberFormatException e)
    {
      e.printStackTrace ();
    }
    return rentedvehicles;
  }

  private static void saveRentedDataToFile (Map < String,ArrayList < Map < String,Object >>> rentedvehicles)
  {
    try (PrintWriter writer = new PrintWriter (new FileWriter (NAME)))
    {
    for (Map.Entry < String, ArrayList < Map < String, Object >>> entry:rentedvehicles.entrySet())
	{
	  StringBuilder line = new StringBuilder (entry.getKey ());
	for (Map < String, Object > car:entry.getValue ())
	    {
	      line.append (";").append (car.get ("type")).
		append (",").append (car.get ("model")).
		append (",").append (car.get ("numberPlate")).
		append (",").append (car.get("rentedPrice")).
		append (",").append (car.get ("SecurityDeposit")).
		append (",").append (car.get ("CautionDeposit"));
	    }
	  writer.println (line);
	}
    }
    catch (IOException e)
    {
      
      e.printStackTrace ();
    }
  }
  
  //SECURITYDEPOSIT
   private static void loadConfigurableIntegers() {
        try (BufferedReader br = new BufferedReader(new FileReader(SECURITYDEPOSIT))) {
            String line;
            if ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 2) {
                    Securitydepositforbike = Integer.parseInt(values[0]);
                   Securitydepositforcar = Integer.parseInt(values[1]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static void changeIntegersByAdmin(int newValue1, int newValue2) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(SECURITYDEPOSIT))) {
            bw.write(newValue1 + "," + newValue2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //email
    private static Map<String, String> loadEmailDataFromFile() {
        Map<String, String> data = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(EMAIL))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String email = parts[0];
                    String password = parts[1];
                    data.put(email, password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void saveEmailToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(EMAIL))) {
            for (Map.Entry<String, String> entry : emailAndPasswords.entrySet()) {
                bw.write(entry.getKey() + "," + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addNewUserData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter new email and password (format: email,password):");
        String input = scanner.nextLine().trim();

        String[] newData = input.split(",");
        if (newData.length != 2) {
            System.out.println("Invalid input format. Please enter email and password separated by comma.");
            addNewUserData();
            return;
        }

        String email = newData[0];
        String password = newData[1];

        emailAndPasswords.put(email, password);
    }
    private static boolean isCredentialsValid(String email, String password) {
        String storedPassword = emailAndPasswords.get(email);
        return storedPassword != null && storedPassword.equals(password);
    }
}
