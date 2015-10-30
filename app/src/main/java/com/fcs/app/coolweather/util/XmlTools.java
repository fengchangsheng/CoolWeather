package com.fcs.app.coolweather.util;
import com.fcs.app.coolweather.model.City;
import com.fcs.app.coolweather.model.County;
import com.fcs.app.coolweather.model.Province;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlTools {

	public static List<Province> parseXml(String xmlString){
		List<Province> list = null;
		Province province = null;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xmlString));
			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					list = new ArrayList<Province>();
					break;
					
				case XmlPullParser.START_TAG:
					if("province".equals(parser.getName())){
						province = new Province();
						String id = parser.getAttributeValue(null,"id");
						String name = parser.getAttributeValue(null,"name");
						province.setProvinceCode(id);
						province.setProvinceName(name);
					}
//					else if("city".equals(parser.getName())){
////						String value = parser.nextText();
////						province.setProvinceName(value);
//					}
//					else if("county".equals(parser.getName())){
//					}
					break;
					
				case XmlPullParser.END_TAG:
					if("province".equals(parser.getName())){
						list.add(province);
						province = null;
					}
					break;
				}
				eventType = parser.next();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<City> parseXmlCity(String xmlString,int pid){
		List<City> list = null;
		City city = null;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xmlString));
			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch (eventType) {
					case XmlPullParser.START_DOCUMENT:
						list = new ArrayList<City>();
						break;

					case XmlPullParser.START_TAG:
						if("city".equals(parser.getName())){
							city = new City();
							String id = parser.getAttributeValue(null,"id");
							String name = parser.getAttributeValue(null,"name");
							city.setCityCode(id);
							city.setCityName(name);
							city.setProvinceId(pid);
						}
						break;

					case XmlPullParser.END_TAG:
						if("province".equals(parser.getName())){
							list.add(city);
							city = null;
						}
						break;
				}
				eventType = parser.next();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<County> parseXmlCounty(String xmlString,int pid){
		List<County> list = null;
		County county = null;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(new StringReader(xmlString));
			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch (eventType) {
					case XmlPullParser.START_DOCUMENT:
						list = new ArrayList<County>();
						break;

					case XmlPullParser.START_TAG:
						if("city".equals(parser.getName())){
							county = new County();
							String id = parser.getAttributeValue(null,"id");
							String name = parser.getAttributeValue(null,"name");
							county.setCountyCode(id);
							county.setCountyName(name);
							county.setCityId(pid);
						}
						break;

					case XmlPullParser.END_TAG:
						if("province".equals(parser.getName())){
							list.add(county);
							county = null;
						}
						break;
				}
				eventType = parser.next();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static List<Province> parseXml(InputStream inStream){
		List<Province> list = null;
		Province province = null;
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser parser = factory.newPullParser();
			parser.setInput(inStream,"UTF-8");
			int eventType = parser.getEventType();
			while(eventType != XmlPullParser.END_DOCUMENT){
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					list = new ArrayList<Province>();
					break;
					
				case XmlPullParser.START_TAG:
					if("province".equals(parser.getName())){
						province = new Province();
					}else if("city".equals(parser.getName())){
						String value = parser.nextText();
						province.setProvinceName(value);
					}else if("county".equals(parser.getName())){
						
					}
					break;
					
				case XmlPullParser.END_TAG:
					if("province".equals(parser.getName())){
						list.add(province);
						province = null;
					}
					break;
				}
				eventType = parser.next();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
