package com.revature.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@XmlRootElement(name = "DemoClass")
public class DemoClass {
	private int i;
	private String s;
	private boolean b;

	
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public boolean isB() {
		return b;
	}
	public void setB(boolean b) {
		this.b = b;
	}

	public static String marshalToXml(DemoClass demo) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(DemoClass.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			StringWriter sw = new StringWriter();
			marshaller.marshal(demo,sw);
			
			return sw.toString();
			//Unmarshaller unmarshaller = context.createUnmarshaller();
			//StringReader read = new StringReader("<DemoClass>\r\n"
			//		+ "    <b>false</b>\r\n"
			//		+ "    <i>17</i>\r\n"
			//		+ "    <s>Bye</s>\r\n"
			//		+ "</DemoClass>");
			//DemoClass unmarshalled = (DemoClass) unmarshaller.unmarshal(read);
			//System.out.println(unmarshalled.getI() + " " + unmarshalled.getS());
			
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ""; 
	}
	
	public static DemoClass unmarshalFromXml(String xml) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(DemoClass.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader read = new StringReader(xml);
			DemoClass unmarshalled = (DemoClass) unmarshaller.unmarshal(read);
			return unmarshalled;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static DemoClass unmarshalFromXml(BufferedReader xml) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(DemoClass.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			DemoClass unmarshalled = (DemoClass) unmarshaller.unmarshal(xml);
			return unmarshalled;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String marshalToJson(DemoClass demoClass) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writeValueAsString(demoClass);
			return jsonString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static DemoClass unmarshalFromJson(String json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			DemoClass unmarshalled = mapper.readValue(json, DemoClass.class);
			return unmarshalled;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static DemoClass unmarshalFromJson(BufferedReader json) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			DemoClass unmarshalled = mapper.readValue(json, DemoClass.class);
			return unmarshalled;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String... args) {
		DemoClass jsonObject = new DemoClass();
		DemoClass jaxbObject = new DemoClass();
		jsonObject.setB(true);
		jsonObject.setI(7);
		jsonObject.setS("Hi");
		
		jaxbObject.setB(false);
		jaxbObject.setI(17);
		jaxbObject.setS("Bye");
		
		String jaxb = marshalToXml(jaxbObject);
		System.out.println(jaxb);
		
		DemoClass unmarshalJax =  unmarshalFromXml(jaxb);
		System.out.println(unmarshalJax.getI()+ " " + unmarshalJax.getS() + " " + unmarshalJax.isB());
		
		String json = marshalToJson(jsonObject);
		System.out.println(json);

		DemoClass unmarshalJSon =  unmarshalFromJson(json);
		System.out.println(unmarshalJSon.getI()+ " " + unmarshalJSon.getS() + " " + unmarshalJSon.isB());
		
		BufferedReader xmlData = new BufferedReader(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n"
				+ "<DemoClass>\r\n"
				+ "    <b>true</b>\r\n"
				+ "    <i>10</i>\r\n"
				+ "    <s>Hello</s>\r\n"
				+ "</DemoClass>"));
		BufferedReader jsonData = new BufferedReader(new StringReader("{\"i\":56,\"s\":\"Hi there!\",\"b\":false}"));
		DemoClass unmarshalJax2 =  unmarshalFromXml(xmlData);
		DemoClass unmarshalJSon2 =  unmarshalFromJson(jsonData);
		System.out.println(unmarshalJax2.getI()+ " " + unmarshalJax2.getS() + " " + unmarshalJax2.isB());
		System.out.println(unmarshalJSon2.getI()+ " " + unmarshalJSon2.getS() + " " + unmarshalJSon2.isB());
	}

}
