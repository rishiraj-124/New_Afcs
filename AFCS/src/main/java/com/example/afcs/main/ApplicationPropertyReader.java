package com.example.afcs.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource("classpath:provider.properties")
public class ApplicationPropertyReader {
	
	@Value("${ENCODING}")
	private List<String> encodingList;
	
	@Value("${HOST}")
	private List<String> hostList;
	
	@Value("${PORT}")
	private List<String> portList;
	
	@Value("${UNAME}")
	private List<String> unameList;
	
	@Value("${PASSWORD}")
	private List<String> passwordList;
	
	@Value("${SMTP_AUTH_KEY}")
	private String smtpAuthKey;

	@Value("${SMTP_AUTH_VALUE}")
	private String smtpAuthValue;

	@Value("${SMTP_CONNTOT_KEY}")
	private String smtpConnTotKey;

	@Value("${SMTP_CONNTOT_VAL}")
	private String smtpConnTotVal;

	@Value("${SMTP_TOT_KEY}")
	private String smtpTotKey;

	@Value("${SMTP_TOT_VAL}")
	private String smtpTotVal;

	@Value("${SMTP_WRTTOT_KEY}")
	private String smtpWrtTotKey;

	@Value("${SMTP_WRTTOT_VALUE}")
	private String smtpWrtTotVal;

	public String getSmtpConnTotKey() {
		return smtpConnTotKey;
	}

	public String getSmtpConnTotVal() {
		return smtpConnTotVal;
	}

	public String getSmtpTotKey() {
		return smtpTotKey;
	}

	public String getSmtpTotVal() {
		return smtpTotVal;
	}

	public String getSmtpWrtTotKey() {
		return smtpWrtTotKey;
	}

	public String getSmtpWrtTotVal() {
		return smtpWrtTotVal;
	}

	public String getSmtpStrTlsKey() {
		return smtpStrTlsKey;
	}

	public String getSmtpStrTlsVal() {
		return smtpStrTlsVal;
	}

	@Value("${SMTP_STRTLS_KEY}")
	private String smtpStrTlsKey;

	@Value("${SMTP_STRTLS_VAL}")
	private String smtpStrTlsVal;

	public String getSmtpAuthKey() {
		return smtpAuthKey;
	}

	public String getSmtpAuthValue() {
		return smtpAuthValue;
	}

	public List<String> getEncodingList() {
		return encodingList;
	}

	public List<String> getHostList() {
		return hostList;
	}

	public List<String> getPortList() {
		return portList;
	}

	public List<String> getUnameList() {
		return unameList;
	}

	public List<String> getPasswordList() {
		return passwordList;
	}


}
