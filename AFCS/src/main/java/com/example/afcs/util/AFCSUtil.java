package com.example.afcs.util;


import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.afcs.bean.ValidationBean;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AFCSUtil {

	static ObjectMapper mapper = new ObjectMapper();
	static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	static Validator validator = factory.getValidator();

	private static final Logger log = LoggerFactory.getLogger(AFCSUtil.class);
	
	private AFCSUtil() {
		super();
	}

	public static <T extends ValidationBean> T jsonStringToObject(
			Object jsonString, Class<T> classObject) {
		Object resultObject = null;

		if (jsonString != null) {
			try {
				mapper.configure(
						DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);
				resultObject = mapper.convertValue(jsonString, classObject);

			} catch (RuntimeException e) {
				log.error("Err Msg:{}" , e);
			}
		}

		return (T) resultObject;
	}

	public static <T extends ValidationBean> T jsonStringToObject(
			String jsonString, Class<T> classObject) {
		Object resultObject = null;

		if (jsonString != null) {
			try {

				mapper.configure(
						DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);

				resultObject = mapper.readValue(jsonString, classObject);
			} catch (IOException e) {
				log.error("Err Msg:{}" , e);
			}
		}

		return (T) resultObject;
	}

	public static Set<ConstraintViolation<ValidationBean>> validateBean(
			ValidationBean validateBean) {
		return validator.validate(validateBean);
	}

	/*
	 * public static TransitAPIResponse
	 * parseBeanErrors(Set<ConstraintViolation<ValidationBean>> validationErrors,
	 * TransitAPIResponse response) {
	 * 
	 * Iterator<ConstraintViolation<ValidationBean>> itr = validationErrors
	 * .iterator();
	 * 
	 * response.setResSatus(ErrorCodes.REQUIRED_PARAMETERS_MISSING.getCode());
	 * 
	 * StringBuilder builder = new StringBuilder(
	 * ErrorCodes.REQUIRED_PARAMETERS_MISSING.getMessage());
	 * 
	 * builder.append(":");
	 * 
	 * while (itr.hasNext()) { builder.append(itr.next().getMessage()).append(",");
	 * 
	 * } response.setResMessage(builder.toString());
	 * 
	 * return response; }
	 */

	public static String getVoucherPrintableDate(Date voucherExpDate) {

		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

		return formatter.format(voucherExpDate);

	}
	
	public static Date getVoucherValidity(){
		Calendar date = Calendar.getInstance();
	    date.setTime(new Date());
	    date.add(Calendar.YEAR,1);
	    
	    return date.getTime();
	}
	
	public static String getFormatedDateStr(Date voucherExpDate) {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy: hh-mm-ss");

		return formatter.format(voucherExpDate);

	}

	public static String getSerialNumber(String voucherIdStr) {

		int serialNoLength = 13;

		int noOfPadding = serialNoLength - voucherIdStr.length();

		StringBuilder sb = new StringBuilder("NG");

		for (int i = 2; i < noOfPadding; i++) {
			sb.append(0);
		}

		sb.append(voucherIdStr);

		return sb.toString();
	}
	
	public static int getVoucherIdBySerialNumber(String serialNumberStr) {
		
		String prefix = "NG";
		String serialNumberNew = null;
		
		try{
			if(null != serialNumberStr && serialNumberStr.trim().startsWith(prefix)){
				serialNumberNew = serialNumberStr.trim().substring(prefix.length());			
			}else{
				serialNumberNew = serialNumberStr;
			}
			
			return Integer.parseInt(serialNumberNew);
			
			
		}catch(NumberFormatException nfe){
			throw new RuntimeException("Provide valid serial number");
		}
		
	}
	
	public static String getAlphaNumericString(int n) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    }
	
	public static String getRandomNum(int n) {   // chose a Character random from this String 
        String AlphaNumericString = "0123456789"; 

        	// create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(n); 

        for (int i = 0; i < n; i++) { 

        		// generate a random number between 
        		// 0 to AlphaNumericString variable length 
        	int index = (int)(AlphaNumericString.length() * Math.random()); 

        	// add Character one by one in end of sb 
        	sb.append(AlphaNumericString.charAt(index)); 
        } 

        return sb.toString(); 
}	

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
	public static String generateNewToken() {
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}

	/*
	 * public static String getSHATwoStr(String plainStr) {
	 * 
	 * return DigestUtils.sha256Hex(plainStr); }
	 */
	
}
