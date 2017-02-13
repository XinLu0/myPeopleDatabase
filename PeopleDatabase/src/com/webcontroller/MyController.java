package com.webcontroller;

import java.util.ArrayList;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.database.DocumentController;
import com.database.MongoDocument;
import com.database.Sex;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;



/**
 * 
 * @author Xintong Lu
 * Web Controller
 * This class is used to bind the request to related behavor
 */
@Controller
public class MyController {
	DocumentController dc = new DocumentController("peopleDB","people");
	/**
	 * react for updating and inserting users' information
	 * @param name
	 * @param a
	 * @param s
	 * @return
	 */
	@RequestMapping(value = "/database/display", method = RequestMethod.POST)
	public ModelAndView postSubmit(@RequestParam("name") String name, @RequestParam("age") String a,@RequestParam("sex") String s)
	{
		ModelAndView model  =new ModelAndView("result");
		int age = Integer.parseInt(a);
		Sex sex;
		if (s.equalsIgnoreCase("male")) sex = Sex.male;
		else if(s.equalsIgnoreCase("female")) sex = Sex.female;
		else sex = null;
		MongoDocument md = new MongoDocument(name.trim(),age,sex);
		dc.updateDocument(md);
		model.addObject("result",new MongoDocument(name.trim(),age,sex));
		return model;
		
	}
	/**
	 * react for search post request
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/database/search", method = RequestMethod.POST)
	public ModelAndView postSearch(@RequestParam("sname") String name)
	{
		String result =dc.getDocument(name);
		ModelAndView model = new ModelAndView("result");
		model.addObject("result", result);
		return model;
	}
	/**
	 * react for display all users post request
	 * @return
	 */
	@RequestMapping(value = "/database/getall", method = RequestMethod.POST)
	public ModelAndView postSearchAll()
	{
		ModelAndView model = new ModelAndView("result");
		StringBuffer bf = new StringBuffer();
		ArrayList<String> stringList = dc.getAllDocument();
		for(String s: stringList )
		{
			bf.append(s);
			bf.append("<br>");
		}
		model.addObject("result",bf);
		return model;
		
	}
	/**
	 * react for remove post request
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/database/remove", method = RequestMethod.POST)
	public ModelAndView postremove(@RequestParam("rname") String name)
	{
		dc.removeDocument(name);
		ModelAndView model = new ModelAndView("drop");
		
		return model;
		
	}
	/**
	 * react for get request
	 * @return
	 */
	@RequestMapping(value = "/database",method = RequestMethod.GET)
	public String displayMessage(){

		return "default";
	}
	

}
