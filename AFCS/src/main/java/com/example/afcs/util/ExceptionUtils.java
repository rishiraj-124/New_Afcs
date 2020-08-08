/**
 * 
 */
package com.example.afcs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rishiraj
 *
 */
public class ExceptionUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUtils.class);
	/**
	 * This method is used to get the Class details, where exception has occurred. We can give the pattern of java package to match.
	 * @param stackTraceElements
	 * @param packageName
	 * @return - String[] as ClassName at 0, FileName at 1,MethodName at 2 and LineNumber at 3 index.
	 */
	ExceptionUtils(){
		//default constructor
	}
	public static String[] getExceptionGeneratedClassDetails(StackTraceElement[] stackTraceElements, String packageName)
	{
		String[] stackTraceElemnt=null;
		String packageNameNew=packageName;
		if(stackTraceElements == null || stackTraceElements.length < 1)
			return stackTraceElemnt;
		
		String[] exceptionGeneratedClassDetails = null;
		packageNameNew = (packageNameNew == null)?"com.ng":packageNameNew;
		
		try{
			
			for(StackTraceElement stackTraceElement : stackTraceElements)
			{
				
					if(stackTraceElement.getClassName().startsWith(packageNameNew))
					{
						exceptionGeneratedClassDetails = new String[4];
						exceptionGeneratedClassDetails[0] = stackTraceElement.getClassName();
						exceptionGeneratedClassDetails[1] = stackTraceElement.getFileName();
						exceptionGeneratedClassDetails[2] = stackTraceElement.getMethodName();
						exceptionGeneratedClassDetails[3] = Integer.toString(stackTraceElement.getLineNumber()) ;
						
						break;
					}
				}		
			
			
		}catch (Exception e) {
			LOGGER.info(""+e);
		}
		
		return exceptionGeneratedClassDetails;
	}
}
