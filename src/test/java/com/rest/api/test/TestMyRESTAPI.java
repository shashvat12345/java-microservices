package com.rest.api.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TestMyRESTAPI {

	@SuppressWarnings("deprecation")
	DefaultHttpClient httpClient = null;
	HttpResponse response = null;
	String serviceURL = null;

	public TestMyRESTAPI(String serviceURL) {
		httpClient = new DefaultHttpClient();
		this.serviceURL = serviceURL;
	}

	public String executeGETMethod() {
		StringBuffer sb = new StringBuffer();
		try {

			HttpGet getRequest = new HttpGet(serviceURL);
			getRequest.addHeader("accept", "application/json");

			response = httpClient.execute(getRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}

			httpClient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		TestMyRESTAPI tmr = new TestMyRESTAPI("http://localhost:2222/add?addend1=1111&addend2=1123");
		String response = tmr.executeGETMethod();
		System.out.println("===================================");
		System.out.println(response);
		System.out.println("===================================");
	}
}