package blackjack.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WinStatistics implements GameStatisticsManager{
	public final String fileName="WinStatistics.txt";
	private File myFile;
//	private final static String filename="WinStatistics.txt";

	
	/**
	 * @param content
	 * @param filename
	 * @throws  
	 */
	
	public WinStatistics() {
		try {
			myFile = new File("files/"+fileName);
			myFile.createNewFile();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	throw e;
	    }
	}
	
	
	//Denne har jeg kun laget for at fillagringen skal være mulig å teste uten at dette påvirker "WinStatistics.txt"
	public WinStatistics(String filename) {
		try {
			myFile = new File("files/"+filename);
			myFile.createNewFile();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	throw e;
	    }
	}
	
	
	
	@Override
	public void addRound(String content) {
		try {
			FileWriter wr=new FileWriter(myFile,true);
			wr.write(content+"\n");
			wr.flush();
			wr.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private String readFromFile() {
		String str="";
		try {
			Scanner sc=new Scanner(myFile);
			while (sc.hasNextLine()) {
				str+=sc.nextLine();
			}
			sc.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	@Override
	public String getStatistics() {
		String str=readFromFile();
		int nrWins=0;
		int nrRounds=0;
		for(int i=0;i<str.length();i++) {
			if (str.charAt(i)=='w')
				nrWins++;
			nrRounds++;
		}
		return String.format("%d/%d", nrWins,nrRounds);
	}
	
	@Override
	public void clearStatistics() {
		try {
			FileWriter wr=new FileWriter(myFile);
			wr.write("");
			wr.flush();
			wr.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	




	
	
	
	
}
