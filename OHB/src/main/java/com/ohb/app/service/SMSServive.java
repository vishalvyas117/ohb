package com.ohb.app.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;

import com.ohb.app.model.User;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

public class SMSServive {

	public static final String ACCOUNT_SID = "ACd23b4ce1723ab74895754cb383d3f153";
	public static final String AUTH_TOKEN = "2bb44ff43be5487e8c10cdca5be08fa9";
	public static final String TWILIO_NUMBER = "+12139960664";
	
	@Value("${app.sms.username}")
	private static String username;
	@Value("${app.sms.password}")
	private static String password;
	
	@Value("${app.sms.senderId}")
	private static String senderId;

	public static char[] generateOTP(int len) {
		System.out.println("Generating password using random() : ");
		System.out.print("Your new password is : ");

		// A strong password has Cap_chars, Lower_chars,
		// numeric value and symbols. So we are using all of
		// them to generate our password
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "!@#$%^&*_=+-/.?<>)";
		String values = "";
		if (len == 4) {
			values = numbers;
		} else {
			values = Capital_chars + Small_chars + numbers + symbols;
		}
		// Using random method
		Random rndm_method = new Random();

		char[] password = new char[len];

		for (int i = 0; i < len; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			password[i] = values.charAt(rndm_method.nextInt(values.length()));

		}
		return password;
	}

	public static void sendSMS(String number, String Body) {
		try {
			TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

			// Build a filter for the MessageList
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Body", Body));
			params.add(new BasicNameValuePair("sid", "OHB"));

			params.add(new BasicNameValuePair("To", number)); // Add real number
																// here
			params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

			MessageFactory messageFactory = client.getAccount().getMessageFactory();
			Message message = messageFactory.create(params);
			System.out.println(message.getSid());
		} catch (TwilioRestException e) {
			System.out.println(e.getErrorMessage());
		}
	}

	public static void sendMessage(String message, String number) {
		try {

			final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public void checkClientTrusted(final X509Certificate[] chain, final String authType) {
				}

				@Override
				public void checkServerTrusted(final X509Certificate[] chain, final String authType) {
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };

			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
			URL url = new URL(getURLPath(number,message));
		    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("GET");
			/*conn.setRequestProperty("username", username);
			conn.setRequestProperty("password", password);
			conn.setRequestProperty("sender_id", senderId);
			conn.setRequestProperty("route", "T");
			conn.setRequestProperty("phonenumber", number);
			conn.setRequestProperty("message", message);*/
			
			
			int responseCode = conn.getResponseCode();
			
			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static String getURLPath(String number,String message) {
		String twar = getURL() + "http://smsc.biz/httpapi/send?username="+username+"&password="+password+"&sender_id="+senderId+"&route=T&phonenumber="+number+"&message="+message+"%20-%20SMSC%20Platform";
		return twar;
	}

	private static String getURL() {

		return "http://smsc.biz/";
	}
}
