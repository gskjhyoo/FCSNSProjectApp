package edu.skku.httphumanict.fcsnsprojectver001.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HTTP 요청 응답을 관장하는 Util 클래스
 */
public class UtilHttp {
	public static final String TAG = UtilHttp.class.getSimpleName();
	private static UtilHttp instance = new UtilHttp();
	private UtilHttp() {
		// null
	}// end of construct
	public static UtilHttp getInstance(){
		return instance;
	}

	public String getURL(String _strUrlSpec) throws IOException{
		URL url = new URL(_strUrlSpec);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"), con.getContentLength());
		StringBuilder strBuilder = new StringBuilder();
		String strBuf;

		while((strBuf = br.readLine()) != null){
			strBuilder.append(strBuf);
		}
		br.close();
		return strBuilder.toString();
	}

	public String postURL(String _strUrlSpec, String _strBody) throws IOException{
		URL url = new URL(_strUrlSpec);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Accept", "application/json");
		con.setDoInput(true);
		con.setDoOutput(true);

		if(_strBody != null && !_strBody.isEmpty()){
			OutputStream os = con.getOutputStream();
			os.write(_strBody.getBytes("UTF-8"));
			os.flush();
			os.close();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"), con.getContentLength());
		StringBuilder strBuilder = new StringBuilder();
		String strBuf;

		while((strBuf = br.readLine()) != null){
			strBuilder.append(strBuf);
		}
		br.close();
		return strBuilder.toString();
	}

	public String putURL(String _strUrlSpec, String _strBody) throws IOException{
		URL url = new URL(_strUrlSpec);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("PUT");
		con.setRequestProperty("Accept", "application/json");
		con.setDoInput(true);
		con.setDoOutput(true);

		if(_strBody != null && !_strBody.isEmpty()){
			OutputStream os = con.getOutputStream();
			os.write(_strBody.getBytes("UTF-8"));
			os.flush();
			os.close();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"), con.getContentLength());
		StringBuilder strBuilder = new StringBuilder();
		String strBuf;

		while((strBuf = br.readLine()) != null){
			strBuilder.append(strBuf);
		}
		br.close();
		return strBuilder.toString();
	}

	public String deleteURL(String _strUrlSpec, String _strBody) throws IOException{
		URL url = new URL(_strUrlSpec);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("DELETE");
		con.setRequestProperty("Accept", "application/json");
		con.setDoInput(true);
		con.setDoOutput(true);

		if(_strBody != null && !_strBody.isEmpty()){
			OutputStream os = con.getOutputStream();
			os.write(_strBody.getBytes("UTF-8"));
			os.flush();
			os.close();
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"), con.getContentLength());
		StringBuilder strBuilder = new StringBuilder();
		String strBuf;

		while((strBuf = br.readLine()) != null){
			strBuilder.append(strBuf);
		}
		br.close();
		return strBuilder.toString();
	}


}// end of class
