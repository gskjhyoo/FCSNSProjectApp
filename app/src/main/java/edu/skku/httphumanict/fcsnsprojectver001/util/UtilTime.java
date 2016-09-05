package edu.skku.httphumanict.fcsnsprojectver001.util;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilTime {
	
	/// Time Format
	/** yyyy-MM-dd*/
	static SimpleDateFormat SDFyyyyMMdd;
	/** yyyy-MM-dd HH:mm:ss*/
	static SimpleDateFormat SDFyyyyMMdd_HHmmss;
	/** HH:mm:ss*/
	static SimpleDateFormat SDFHHmmss;
	
	/** javascript server time format*/
	static SimpleDateFormat SDFServerFormat;
	
	static {
		SDFyyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		SDFyyyyMMdd_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SDFHHmmss = new  SimpleDateFormat("HH:mm:ss");
		SDFServerFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	}
	
	public static java.util.Date getDateFromServer(String _strTimeServerFormat) throws ParseException {
		return SDFServerFormat.parse(_strTimeServerFormat);
	}
	
	public static Date getDateFrom(String _stryyyy_MM_dd) throws ParseException{
		return new Date(SDFyyyyMMdd.parse(_stryyyy_MM_dd).getTime());
	}
	public static Date getDateTimeFrom(String _stryyyy_MM_dd__HH$mm$ss) throws ParseException{
		return new Date(SDFyyyyMMdd_HHmmss
				.parse(_stryyyy_MM_dd__HH$mm$ss).getTime());
	}
	public static Timestamp getTimestampFrom(String _stryyyy_MM_dd__HH$mm$ss) throws ParseException{
		return new Timestamp(SDFyyyyMMdd_HHmmss.parse(_stryyyy_MM_dd__HH$mm$ss).getTime());
	}
	
	public static Timestamp getTimeStampFrom(String _stryyyy_MM_dd__HH$mm$ss) throws ParseException{
		return new Timestamp(SDFyyyyMMdd_HHmmss.parse(_stryyyy_MM_dd__HH$mm$ss).getTime());
	}
	public static Time getTimeFrom(String _strHHmmss) throws ParseException{
		return new Time(SDFHHmmss.parse(_strHHmmss).getTime());
	}
	
	public static String genStringDateTimeFrom(Date _cDateTime){
		return SDFyyyyMMdd_HHmmss.format(_cDateTime);
	}
	
	public static String genStringDateTimeFrom(Timestamp _cTimestamp){
		if(_cTimestamp != null)
			return SDFyyyyMMdd_HHmmss.format(new java.util.Date(_cTimestamp.getTime()));
		else 
			return null;
	}
	
	public static String genStringDateTimePres() {
		return UtilTime.genStringDateTimeFrom(new Date(new  java.util.Date().getTime()));
	}
	public static Date genDateFromTimeStamp(Timestamp _cTS) throws ParseException{
		return UtilTime.getDateTimeFrom(_cTS.toString());
	}
	public static Timestamp genTimestampPres() throws ParseException{
		return getTimestampFrom(genStringDateTimePres());
	}
	
	
	public static void main(String[] args) throws ParseException {
//		System.out.println(UtilTime.genStringDateTimePres());
		Timestamp ts = new Timestamp(new java.util.Date().getTime());
		System.out.println(UtilTime.genStringDateTimeFrom(genDateFromTimeStamp(ts)));
		/*Date date = new Date(ts.getTime());*/
//		System.out.println(UtilTime.genStringDateTimeFrom(date));
	}
}// end of class
