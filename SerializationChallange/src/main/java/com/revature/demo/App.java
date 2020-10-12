package com.revature.demo;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
	public static void main(String... args) {
		DemoClass jsonObject = new DemoClass();
		DemoClass jaxbObject = new DemoClass();
		jsonObject.setB(true);
		jsonObject.setI(7);
		jsonObject.setS("Hi");
		
		jaxbObject.setB(false);
		jaxbObject.setI(17);
		jaxbObject.setS("Bye");
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(DemoClass.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(jaxbObject,System.out);
			
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader read = new StringReader("<DemoClass>\r\n"
					+ "    <b>false</b>\r\n"
					+ "    <i>17</i>\r\n"
					+ "    <s>Bye</s>\r\n"
					+ "</DemoClass>");
			DemoClass unmarshalled = (DemoClass) unmarshaller.unmarshal(read);
			System.out.println(unmarshalled.getI() + " " + unmarshalled.getS());
			
			
			
			
			FormModel form = new FormModel();
			form.username = "liam";
			form.password = "nunes";
			form.food = "chinese";
			String[] temp = new String[2];
			
			temp[0] = "English";
			temp[1] = "Spanish";
			form.languages = temp;
			
			
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				System.out.println( mapper.writeValueAsString(form));
			
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 


	}
	

}
