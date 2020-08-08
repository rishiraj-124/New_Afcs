/**
 * 
 */
package com.example.afcs.util;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.commons.codec.binary.Hex;
import com.example.afcs.bean.ValidationBean;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author rishiraj
 *
 */
public class KeyEncryptionUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(KeyEncryptionUtils.class);
	private static final String CBCPKCS5PADDIND="/CBC/PKCS5Padding";
	static RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger("bef837aac6fb66d5ec198218460c5cc503e654c1c37f37788f576d7f5a1bbf092b307dbdc9a1502d7302e458aa2c32a9c01e3790b76460b27c622cbb09c6643bfa240a96aacfebb4129ed9a9ae1e9ef8f140532d3a81b9e292236d29af1ef6dc7f6d2020de5b8479eb6ed9e363a3104f9864c0c47575e2e20e0ecdf94c1bb9cf2bb266772ff7222b7f505900755f7b8ff13c9bb908c86378db7ee31769a4012da5088b5a197b794cc11248e064cb8a3a3feede8955134dcdc80e0db43ba892c5e6e558eb77d7c321a013c44112507929004dcebcd5aee54237456206b8474f81bb0a25941c2770dad91f7e66b6b9abc0e1aa3cdfc1b99a1b7a6ac62bd8216361",16),
			new BigInteger("010001",16));
	static RSAPrivateCrtKeySpec privKeySpec = new RSAPrivateCrtKeySpec(new BigInteger("bef837aac6fb66d5ec198218460c5cc503e654c1c37f37788f576d7f5a1bbf092b307dbdc9a1502d7302e458aa2c32a9c01e3790b76460b27c622cbb09c6643bfa240a96aacfebb4129ed9a9ae1e9ef8f140532d3a81b9e292236d29af1ef6dc7f6d2020de5b8479eb6ed9e363a3104f9864c0c47575e2e20e0ecdf94c1bb9cf2bb266772ff7222b7f505900755f7b8ff13c9bb908c86378db7ee31769a4012da5088b5a197b794cc11248e064cb8a3a3feede8955134dcdc80e0db43ba892c5e6e558eb77d7c321a013c44112507929004dcebcd5aee54237456206b8474f81bb0a25941c2770dad91f7e66b6b9abc0e1aa3cdfc1b99a1b7a6ac62bd8216361",16),
			new BigInteger("010001",16),
			new BigInteger("7cfb201b816c8929da01fbb57d2a9c10487416e654d6ce6bf58be75aa093661c4f0ac6504e44679fc7148e7344b3f6584826be80d4b539dfb62dde4e6ea04b7b41431336f6056ba20749b4f42ac359915cfa97351384512e6a2c0b466b98dd4103985c9d8648e8d38df4975f0d176c165b8b1ce9b661f7a1b0479a1fdce28c6058b175f9baa319c4ad45f691b2acd9514f389d37896bb9b8a99da20c80f679524100153dc2ac41ef892d89ddff509c3cce3a2e71a7011caeb8ca8dd18638874665320fa8ae2887deed7b35007a14d6d82c29461fe00d64b108ae9dfe1f7a678c9c54e36919b1879068b08325d59637632aad7780b32c1164886a09ee1082b641",16),
			new BigInteger("fc34e38ad52e18c56a38c005f51f1aafa75055cbcc6a86e2a3ec6234ee1280df980dbaebb7036e2cf8a585b46aa588c33ee2943cb157121face9f5155065cb8beb5515d63f2a898944d02d379d63276b472961b261e7ebcd0e92674b4801b74f511f69f2ea22f2394e0127987f8974d532f35c05b2ab12768630fdaeb86e80f9",16),
			new BigInteger("c1d789be87a704e655f79cd924132903cf8e06ab9e759609102f00ac07ea222b620f754c1b7d7100f44e41aadff5e3af5856d0fcfad017b3c78356f97672ded2265806ceaf736e40e6f6f644a41ceff8171d927fbd9256b6fa8a38bf67a667c478f5f92f20e55332a088cd6351b7f270bd9104afb6a210942a5a0917ac2ef7a9",16),
			new BigInteger("54d6019868bf39cbe64032af8e55a9275ef391f89cc954981625e49f035a2c098a5f4bfa1034d231c46e013cc006f0ab249318e4158f41647048be8d2ddf2009efde4e1b24624da3640756be776dac0bd503217edc9a23e2753d600b9dac1e1a4b22730428281b2c9585a3e913358c9c4ea837d31ed796bd51c4570117beec19",16),
			new BigInteger("8f9c4a36d8758b11c35b21df55f4c40c48b76685bb6e4e6c048b6e2a05eb16c9a16ae6722e63af570fa8f2c9d007725a344e8da2ff87302847862ce7c133b59568470b341194a97878253baafb135d4e268208498f7f5db34ec71ce7e1b8cd642861c3ed80a7a60dfa15a06516e3fb403f91b3312353ca889b1ffd6086767d91",16),
			new BigInteger("80a14de92a8eff59b1b7f62d6e2ce9e7def4342b5682693dc4e0ce6edd1205375e87ccef68bd74e8533bf8a4081d52894a24b274f94c411015465c3da789a624ba32a1535417f13bd6fcc8601c62373fd164c309e1dcf6a38b7aa2e65ac174de9d09ec8a3189281a3a1d1cbddddfa86c097cfdfcedb4152df294afcfbc389e33",16));
	 
	static PublicKey pubKey; 
	static ObjectMapper mapper = new ObjectMapper();
	static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    static Validator validator = factory.getValidator();
	static{
	try {
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		pubKey = keyFactory.generatePublic(pubKeySpec);
	} catch (NoSuchAlgorithmException e) {
		LOGGER.info(""+e);
	} catch (InvalidKeySpecException ex) {
		LOGGER.info(""+ex);
	}
	}
	
	private KeyEncryptionUtils(){}
	

    
	public static String stringToHex(String str) 
	{ 
        char[] chars = str.toCharArray();
        
        StringBuilder strBuffer = new StringBuilder();
        
        for (int i = 0; i < chars.length; i++) 
        {
        	strBuffer.append(Integer.toHexString((int) chars[i]));
        }
        
        return strBuffer.toString();
    }
	
	/*
	 * public static String encryptUsingKey(String key, String dataToEncrypt) {
	 * StringBuilder ciphertext = new StringBuilder(); try{ SecureRandom random =
	 * new SecureRandom(); byte [] initializationVector = new byte [16];
	 * random.nextBytes( initializationVector );
	 * 
	 * SecretKey mySecretKey = new SecretKeySpec(Hex.decodeHex(key.toCharArray()),
	 * "AES");
	 * 
	 * byte [] plainDataArray = dataToEncrypt.getBytes();
	 * 
	 * Cipher cipher = Cipher.getInstance( mySecretKey.getAlgorithm() +
	 * CBCPKCS5PADDIND ); cipher.init( Cipher.ENCRYPT_MODE, mySecretKey, new
	 * IvParameterSpec( initializationVector ) );
	 * 
	 * ciphertext.append( Hex.encodeHexString(initializationVector ) ); byte []
	 * encrypted = cipher.doFinal( plainDataArray );
	 * 
	 * 
	 * 
	 * 
	 * ciphertext.append( Hex.encodeHexString( encrypted ) ); } catch(Exception e){
	 * LOGGER.info(""+e); } return ciphertext.toString(); }
	 */
	
	public static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}
	
	/*
	 * public static String encryptUsingKeyForTopUp(String key, String
	 * dataToEncrypt) { StringBuilder ciphertext = new StringBuilder(); try{ byte []
	 * initializationVector = new byte [16];
	 * 
	 * Arrays.fill( initializationVector, (byte) 0 );
	 * 
	 * SecretKey mySecretKey = new SecretKeySpec(Hex.decodeHex(key.toCharArray()),
	 * "AES");
	 * 
	 * byte [] plainDataArray = Hex.decodeHex(dataToEncrypt.toCharArray());
	 * 
	 * Cipher cipher = Cipher.getInstance( mySecretKey.getAlgorithm() +
	 * "/CBC/NoPadding" ); cipher.init( Cipher.ENCRYPT_MODE, mySecretKey, new
	 * IvParameterSpec( initializationVector ) );
	 * 
	 * byte [] encrypted = cipher.doFinal( plainDataArray );
	 * 
	 * 
	 * 
	 * ciphertext.append( Hex.encodeHexString( encrypted ) ); }catch(Exception e){
	 * LOGGER.info(""+e); }
	 * 
	 * return ciphertext.toString(); }
	 */
	
	/*
	 * public static String encryptUsingKey(String key, Object objectToEncrypt) {
	 * StringBuilder ciphertext = new StringBuilder(); try{ if(objectToEncrypt ==
	 * null) return null;
	 * 
	 * mapper.setSerializationInclusion(Include.NON_NULL);
	 * 
	 * String dataToEncrypt = mapper.writeValueAsString(objectToEncrypt);
	 * 
	 * SecureRandom random = new SecureRandom(); byte [] initializationVector = new
	 * byte [16]; random.nextBytes( initializationVector );
	 * 
	 * SecretKey mySecretKey = new SecretKeySpec(Hex.decodeHex(key.toCharArray()),
	 * "AES");
	 * 
	 * byte [] plainDataArray = dataToEncrypt.getBytes();
	 * 
	 * Cipher cipher = Cipher.getInstance( mySecretKey.getAlgorithm() +
	 * CBCPKCS5PADDIND ); cipher.init( Cipher.ENCRYPT_MODE, mySecretKey, new
	 * IvParameterSpec( initializationVector ) ); byte [] encrypted =
	 * cipher.doFinal( plainDataArray );
	 * 
	 * 
	 * ciphertext.append( Hex.encodeHexString(initializationVector ) );
	 * ciphertext.append( Hex.encodeHexString( encrypted ) ); } catch(Exception e){
	 * LOGGER.info(""+e); } return ciphertext.toString(); }
	 */
	
	/*
	 * public static String decryptUsingKey(String key, Object dataToDecrypt) { try{
	 * if(dataToDecrypt instanceof String) { String encryptedData =
	 * (String)dataToDecrypt;
	 * 
	 * 
	 * byte [] initializationVector = Hex.decodeHex(encryptedData.substring(0,
	 * 32).toCharArray()); byte [] encryptedString = Hex.decodeHex(
	 * encryptedData.substring(32).toCharArray());
	 * 
	 * 
	 * SecretKey mySecretKey = new SecretKeySpec(Hex.decodeHex(key.toCharArray()),
	 * "AES");
	 * 
	 * Cipher cipher = Cipher.getInstance( mySecretKey.getAlgorithm() +
	 * CBCPKCS5PADDIND ); cipher.init( Cipher.DECRYPT_MODE, mySecretKey, new
	 * IvParameterSpec( initializationVector ) );
	 * 
	 * byte[] decryptedData = cipher.doFinal( encryptedString );
	 * 
	 * return new String( decryptedData ); } }catch(Exception e){ LOGGER.info(""+e);
	 * }
	 * 
	 * return null; }
	 */
	
	
	public static String decryptForJavaScriptUsingKey(String decryptionKey, Object dataToDecrypt)
	{
		try{
		if(dataToDecrypt instanceof String)
		{
			String encryptedData = (String)dataToDecrypt;
			
			encryptedData  = encryptedData.replace(" ", "+");
			
			byte[] cipherData = Base64.getDecoder().decode(encryptedData);
			byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);	
			
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			final byte[][] keyAndIV = GenerateKeyAndIV(32, 16, 1, saltData, decryptionKey.getBytes(StandardCharsets.UTF_8), md5);
			SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
			IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

			byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
			Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
			aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
			byte[] decryptedData = aesCBC.doFinal(encrypted);
			String decryptedText = new String(decryptedData, StandardCharsets.UTF_8);
			
			return decryptedText;
		}
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.info(""+e);
		}
		
		return null;
	}
	
	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) {

	    int digestLength = md.getDigestLength();
	    int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
	    byte[] generatedData = new byte[requiredLength];
	    int generatedLength = 0;

	    try {
	        md.reset();

	        // Repeat process until sufficient data has been generated
	        while (generatedLength < keyLength + ivLength) {

	            // Digest data (last digest if available, password data, salt if available)
	            if (generatedLength > 0)
	                md.update(generatedData, generatedLength - digestLength, digestLength);
	            md.update(password);
	            if (salt != null)
	                md.update(salt, 0, 8);
	            md.digest(generatedData, generatedLength, digestLength);

	            // additional rounds
	            for (int i = 1; i < iterations; i++) {
	                md.update(generatedData, generatedLength, digestLength);
	                md.digest(generatedData, generatedLength, digestLength);
	            }

	            generatedLength += digestLength;
	        }

	        // Copy key and IV into separate byte arrays
	        byte[][] result = new byte[2][];
	        result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
	        if (ivLength > 0)
	            result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

	        return result;

	    } catch (DigestException e) {
	        throw new RuntimeException(e);

	    } finally {
	        // Clean out temporary data
	        Arrays.fill(generatedData, (byte)0);
	    }
	}
	
	public static <T extends ValidationBean> T jsonStringToObject(Object jsonString, Class<T> classObject)
	{
		Object resultObject = new Object();
		
		if(jsonString != null)
		{
			try
			{
			
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				resultObject = mapper.convertValue(jsonString, classObject);
				
			}catch(RuntimeException e)
			{
				LOGGER.info(""+e);
			}
		}
		
		
	  return (T)resultObject;
	}
	
    
    public static <T extends ValidationBean> T jsonStringToObject(String jsonString, Class<T> classObject)
	{
		Object resultObject;
		
		if(jsonString != null)
		{
			try
			{
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				resultObject = mapper.readValue(jsonString, classObject);
				
				return (T)resultObject;
			}catch(IOException e)
			{
				LOGGER.info(""+e);
			}
		}
		
		
	  return null;
	}
    
    public static <T extends ValidationBean> T[] jsonStringToObjectArray(String jsonString, Class<T[]> classObject)
    {
    	Object resultObject = null;
    	
    	if(jsonString != null)
    	{
    		try
    		{
    			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    			resultObject = mapper.readValue(jsonString, classObject);
    			
    			return (T[]) resultObject;
    			
    		}catch(Exception e)
    		{
    			LOGGER.info(""+e);
    		}
    	}
    	
    	
      return (T[]) resultObject;
    }
    
	public static Set<ConstraintViolation<ValidationBean>> validateBean(ValidationBean validateBean)
	{
		return validator.validate(validateBean);
	}
	
	public static boolean checkDateInRange(Date startDate, Date endDate, Date dateToCheck) 
	{
			return startDate.compareTo(dateToCheck) * dateToCheck.compareTo(endDate) >= 0;
	}
	
	public static Date getCurrentDateWithoutHours() 
	{
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	public static String dateToString(Date dateObject, String patterToParse) 
	{
		DateFormat df = new SimpleDateFormat(patterToParse);
		
		return df.format(dateObject);
	}
	
	public static Date stringToDate(String dateObject, String patterToParse) 
	{
		try {
		DateFormat df = new SimpleDateFormat(patterToParse);
			return df.parse(dateObject);
		} catch (ParseException e) {
			LOGGER.info(""+e);
		}
		return null;
	}

	public static Date getDateForSpecificInterval(Date dateObject, int invervalInDays) 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateObject);
		
		calendar.add(Calendar.DATE, invervalInDays);
		
		return calendar.getTime();
	}
	
	public static String replaceArgumentParameters(String message, String[] params) 
	{
		String argument = "arg";
		int index1;
		int index2;
		String messageNew=message;
		for (int i = 0; i < params.length; i++) {
			String arg = argument + String.valueOf(i);
			index2 = messageNew.indexOf("<" + arg + ">");
			while (index2 > -1) {
				index1 = index2 + arg.length() + 2;
				messageNew = messageNew.substring(0, index2) + params[i]
						+ messageNew.substring(index1, messageNew.length());
				index2 = messageNew.indexOf("<" + arg + ">");
			}
		}
		return messageNew;
	}
	
	/**
	 * date - ddmmyyyy
	 * time - hhMMSS
	 */
	public static Date getDateFromString(String date, String time) 
	{
		if(date == null || date.isEmpty())
			return null;
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(0, 2)));
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(2, 4)) -1);
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(4)));
		
		if(time != null)
		{
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0,2)));
			calendar.set(Calendar.MINUTE, Integer.parseInt(time.substring(2,4)));
			calendar.set(Calendar.SECOND, Integer.parseInt(time.substring(4)));
		}
		
		return calendar.getTime();
	}
	
	public static void main(String... str) throws Exception
	{
		//empty
	}
	
	/*
	 * public static String generateKeyForCardApplication(String cardUId, String
	 * cardConstant, String cardApplicationId, String keyType, String encryptionKey)
	 * { String finalData = null;
	 * 
	 * try { String toBeHased = cardUId + cardConstant + cardApplicationId +
	 * keyType;
	 * 
	 * MessageDigest digest = MessageDigest.getInstance("SHA-256"); byte[] hash =
	 * digest.digest(toBeHased.getBytes(StandardCharsets.UTF_8));
	 * 
	 * String hashedString = Hex.encodeHexString( hash );
	 * 
	 * finalData = encryptUsingKeyForTopUp(encryptionKey , hashedString);
	 * 
	 * if(finalData != null && finalData.length() > 32) return
	 * finalData.substring(0, 32);
	 * 
	 * } catch (Exception e) { LOGGER.info(""+e); }
	 * 
	 * return finalData; }
	 */
	
	public static String generateHexFromInt(String amount, boolean paddingRequired, int totalLength) 
	{
		StringBuilder finalData = new StringBuilder();
		
        try {
        	String hex = Long.toHexString(Integer.parseInt(amount));
        	
        	finalData.append(hex);
        	
        	if(paddingRequired)
        	{	
        		int counter = totalLength - hex.length();
        		
        		for(int i=0; i<counter; i++)
        		{
        			finalData.insert(0,"0");
        		}
        	}
        } catch (Exception e) {
        	LOGGER.info(""+e);
		}
		
		return finalData.toString();
	}
	
	public static String getBuildVersion(String buildVersion, boolean paddingRequired, int totalLength) 
	{
		StringBuilder finalData = new StringBuilder();
		
        try {
        	int decimalIndex = buildVersion.indexOf('.');
        	
        	String buildVersionData = buildVersion.substring(0, decimalIndex) + buildVersion.substring(decimalIndex + 1);
        	
        	finalData.append(buildVersionData);
        	
        	if(paddingRequired)
        	{	
        		int counter = totalLength - buildVersionData.length();
        		
        		for(int i=0; i<counter; i++)
        		{
        			finalData.insert(0,"0");
        		}
        	}
        } catch (Exception e) {
        	LOGGER.info(""+e);
		}
		
		return finalData.toString();
	}
	
	public static String getValidationMessage(Set<ConstraintViolation<ValidationBean>> validationError){
		 Iterator<ConstraintViolation<ValidationBean>>  itr =  validationError.iterator();	         
	         StringBuilder builder = new StringBuilder();
	         int counter = 1;
	         while(itr.hasNext())
	         {
	          builder.append(counter+". "+itr.next().getMessage()+" ");
	          
	          counter++;
	         }
	         return builder.toString();
	}

	/*
	 * public static boolean verifyCardInternalNumber(String message, String
	 * extNum){ try{ //Verify the signature Signature signature1 =
	 * Signature.getInstance("SHA256withRSA"); signature1.initVerify(pubKey);
	 * signature1.update(Hex.decodeHex(extNum.toCharArray())); return
	 * signature1.verify(Hex.decodeHex(message.toCharArray())); } catch(Exception
	 * e){ LOGGER.info(""+e); return false; } }
	 */
	
	public static String paddedData(String data, int length)
	{
		try{
			int paddingRequired = length - data.length();
			String dataNew=data;
			for(int i =0; i < paddingRequired; i++)
			{
				dataNew = "0".concat(dataNew);
			}
			
			return dataNew;
		} catch(Exception e){
			LOGGER.info(""+e);
			
		}
		
		return null;
	}
	
	public static <T extends ValidationBean> T[] jsonStringToObjectArray(Object jsonString, Class<T[]> classObject)
    {
    	Object resultObject = null;
    	
    	if(jsonString != null)
    	{
    		try
    		{
    			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    			resultObject = mapper.convertValue(jsonString, classObject);
    			
    			
    			
    		}catch(Exception e)
    		{
    			LOGGER.info(""+e);
    		}
    	}
    	
    	
      return (T[]) resultObject;
    }
	
	
	
	
	
	
	
	
	
	
	
	
}
