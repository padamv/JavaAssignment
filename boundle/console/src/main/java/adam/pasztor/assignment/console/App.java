package adam.pasztor.assignment.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import adam.pasztor.assignment.api.model.Entry;
import adam.pasztor.assignment.api.model.Finished;
import adam.pasztor.assignment.api.service.EntryManagementService;
import adam.pasztor.assignment.api.service.FinishedManagementService;
import adam.pasztor.assignment.persist.EntryDAOJSON;
import adam.pasztor.assignment.persist.FinishedDAOJSON;
import adam.pasztor.assignment.service.dao.EntryDAO;
import adam.pasztor.assignment.service.dao.FinishedDAO;
import adam.pasztor.assignment.service.impl.EntryManagementServiceImpl;
import adam.pasztor.assignment.service.impl.FinishedManagementServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
	//managers
	
    private static EntryManagementService entryManager;
    private static FinishedManagementService finishedManager;
    
    //files passed to DAOs, DAOs passed to managers
    
    static{
    	EntryDAO entryDAO= new EntryDAOJSON("resources/entries.json");
    	FinishedDAO finishedDAO= new FinishedDAOJSON("resources/finisheds.json");
    	entryManager= new EntryManagementServiceImpl(entryDAO);
    	finishedManager=new FinishedManagementServiceImpl(finishedDAO);
    }
    
    public static void main( String[] args ) throws IOException { 

    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 			//Read from standard input
        boolean run=true;
        
        System.out.println("Options:");
        System.out.println();
        System.out.println("list entries: Lists the entries.");
        System.out.println("insert entry: Adds anew entry to the list.");
        System.out.println("list finisheds: Lists the history ofthe returned books.");
        System.out.println("exit: Exit");
        System.out.println();
        
        while (run){
        
        	String line=br.readLine();
        	
        	if ("exit".equals(line)){
        		break;
        	}
        	
        	if ("list entries".equals(line)){
        		listEntries();
        	}
        	
        	if ("insert entry".equals(line)){
        		addEntry();
        	}
        	
        	if("list finisheds".equals(line)){
        		listFinisheds();
        	}
        }
    }
    
        
    private static void printHorisontalLine(int length) {
    	for (int i=0; i<length;i++){
        	System.out.print("-");
        }
        System.out.println();
    }
    
    private static void listEntries(){
    	final int tableWidth=54;
    	
    	printHorisontalLine(tableWidth);
    	System.out.println("| Name of borrower |    Date    | Book ID |  Action  |");			//Header
    	printHorisontalLine(tableWidth);
    	DateFormat df = new SimpleDateFormat("YYYY.MM.dd");
    	
    	for (Entry entry: entryManager.listEntries()){
    		System.out.println(String.format("| %1$-16s | %2$10s | %3$7s | %4$8s |", entry.getNameofBorrower(), df.format(entry.getDate()), entry.getBookID(), entry.getMode()));
    		printHorisontalLine(tableWidth);
    	}
    	
    }
    
    private static void addEntry() throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	System.out.println("Name of borrower: ");
    	String nameofBorrower=br.readLine();
    	
    	System.out.println("Date (yyyy-MM-dd): ");
    	String dateStr=br.readLine();
    	
    	System.out.println("Book ID: ");
    	String bookID=br.readLine();
    	
    	System.out.println("Action (BORROW/RETURN): ");
    		
    	String action = br.readLine();
    	Entry.Mode mode=Entry.Mode.valueOf("BORROWED");
    	while(!("BORROW".equals(action))&&!("RETURN".equals(action))){
    		System.out.println("Wrong action! Please give the action again (BORROW/RETURN): ");
    		action = br.readLine();
    	}
    	

    	if ("BORROW".equals(action))
    		mode=Entry.Mode.valueOf("BORROWED");
    	if ("RETURN".equals(action))
    		mode=Entry.Mode.valueOf("RETURNED");

    	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = null;
		try {
			date = df.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Entry entry = new Entry(nameofBorrower, date, bookID, mode);
    	entryManager.addEntry(entry);
    }
        
        private static void listFinisheds(){
        	
        	
        	//Fill finisheds.json
        	
        	for (Entry entryOne : entryManager.listEntries()){
        		if(entryOne.getIsFinished() == false && "BORROWED".equals(entryOne.getMode().toString())){
        			for (Entry entryTwo: entryManager.listEntries()){
        				if (entryTwo.getIsFinished() == false && "RETURNED".equals(entryTwo.getMode().toString()) && entryOne.getNameofBorrower().equals(entryTwo.getNameofBorrower()) && entryOne.getBookID().equals(entryTwo.getBookID()))
        				{
        					boolean isIn=false;
        					
        					Finished finished = new Finished (entryOne.getBookID(), entryOne.getNameofBorrower(), entryOne.getDate(), entryTwo.getDate());
        					
        					for(Finished finihed2 : finishedManager.listFinished()){			//Checks whether the the finished is already in the list
        						if(finished.equals(finihed2))
        							isIn=true;
        					}
        					
        					if (!isIn){
            					finishedManager.addFinished(finished);
        					}

        					entryOne.setIsFinished(true);
        					entryTwo.setIsFinished(true);
        				}
        			}
        		}
        	}
        	
        	final int tableWidth = 56;
        	printHorisontalLine(tableWidth);
        	System.out.println("| Book ID | Name of Borrower |    From    |     To     |");			//Header
        	printHorisontalLine(tableWidth);
        	DateFormat df = new SimpleDateFormat("YYYY.MM.dd");
        	
        	for (Finished finished: finishedManager.listFinished()){
        		System.out.println(String.format("| %1$7s | %2$-16s | %3$10s | %4$10s |",finished.getBookID(), finished.getNameofBorrower(), df.format(finished.getFromDate()), df.format(finished.getToDate())));
        		printHorisontalLine(tableWidth);
        	}
        }
}
