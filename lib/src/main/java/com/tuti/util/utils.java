package com.tuti.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

public class Utils {

	 public static String getDate(){
	        DateFormat dateFormat = new SimpleDateFormat("ddMMyyHHmmss", Locale.US);
	        Date date = new Date();
	        return dateFormat.format(date);
	    }

	 public static String getUuid() {
		 return UUID.randomUUID().toString();
	 }


	static final String ABC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static Random rnd = new Random();

	public static String generateRandomStringFromAlphabet(String alphabet,int length){
		StringBuilder sb = new StringBuilder(length);
		for(int i = 0; i < length; i++)
			sb.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
		return sb.toString();
	}

	public static String generateRandomAlphanumericString(int length){
		return generateRandomStringFromAlphabet(ABC,length);
	}

	public static String generateRandomNumericString(int length){
		return generateRandomStringFromAlphabet("0123456789",length);
	}

	public static String generateARandomName(){
		return NamesForUnitTests.names[rnd.nextInt(NamesForUnitTests.names.length)];
	}
}
