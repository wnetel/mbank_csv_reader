import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date; 
import java.util.GregorianCalendar; 
import java.util.TimeZone;

public class mbank_reader {

  public static void main(String[] args) {

	mbank_reader obj = new mbank_reader();
	obj.importdata();
  }

  public void importdata() {

	String csvFile = "Agnieszka_mbank.csv";
	BufferedReader br = null;
	String line = "";
	String dataSplitBy = "-";
	String cvsSplitBy = ";";
	int counter =0;
	

	
	
	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
		counter++;
			if (counter > 38) {
		        // use comma as separator
				String[] country = line.split(cvsSplitBy);
				String date[] = country[0].split(dataSplitBy);
				int clength = country.length; 
			
				if (clength == 8) {			
					System.out.println("liczba elementów listy" + clength);
					System.out.println("Data operacji " + country[0] + ",\n"
                                 + "Operacja" + country[2] + "\n"
								 + "Tytuł" + country[3] + "\n"
								 + "Odbiorca" + country[4] + "\n"
								 + "Nr konta" + country[5] + "\n"
								 + "Kwota" + country[6] + "\n"
								 + "Rok" + date[0] + "\n"
								 + "Misiąc" + date[1] + "\n"
								 + "Dzien tygodnia: " + getDayFromDate(country[0])+ "\n"
								 + "Dzien" + date[2] + "\n" + "\n");
				} else {
					System.out.println("liczba elementów listy" + clength);
					System.out.println("Data operacji " + country[0] + ",\n"
                                 + "Operacja" + country[2] + ",\n"
								 + "Tytuł" + country[3] + country[4] + ",\n"
								 + "Odbiorca" + country[5] + ",\n"
								 + "Nr konta" + country[6] + ",\n"
								 + "Kwota" + country[7] + "\n" + "\n");
				}
			}
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	System.out.println("Done");
  }
  
  public String getDayFromDate(String datestring) {
	
	String[] days = {"poniedzialek", "wtorek", "środa", "czwartek", "piątek", "sobota", "niedziel"};
	String day = ""; 

	
	try {	
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse(datestring);
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("PST"));
		cal.setTime(date);
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		day = (days[dayOfWeek - 1]);
		}
			catch(Exception e) {
			e.printStackTrace();
			}
		
		return day; 
		

	}
  

}