package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping(value="/phone")
public class PhoneController {

	//필드
	@Autowired
	private PhoneDao phoneDao;
	
	//생성자
	
	//메소드 g.s
	
	//메소드 일반
	//메소드마다 기능 1개씩 --> 기능마다 url 부여
	
	//http://localhost:8088/phonebook5/phone/list
	//리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("list");
		
		//dao를 통해 리스트를 가져온다.
		List<PersonVo> personList = phoneDao.getPersonList();

		
		//model --> data 를 보내는 방법 --> 담아 놓으면 된다.
		model.addAttribute("pList", personList);
		
		return "list";
	}
		
	//http://localhost:8088/phonebook5/phone/writeForm
	//등록폼
	@RequestMapping(value="/writeForm", method = {RequestMethod.GET , RequestMethod.POST })
	public String writeForm() {
		System.out.println("WriteForm");
		
		return "writeForm";
	}
	
	
	  //http://localhost:8088/phonebook5/phone/writeForm?name=서웅기&hp=?&company=?
	  //등록
	  @RequestMapping(value="/write", method={RequestMethod.GET,RequestMethod.POST} ) 
	  public String write(@RequestParam("name") String name,
	                     @RequestParam("hp") String hp,
	                     @RequestParam("company") String company) {
	  System.out.println("write"); System.out.println(name + hp + company);
	  
	  PersonVo personVo = new PersonVo(name, hp, company);
	  
	  phoneDao.personInsert(personVo);
	  
	  return "redirect:/phone/list"; }
	 
	  //http://localhost:8088/phonebook3/phone/modifyForm?id= //수정폼
	 
	  @RequestMapping(value="/modifyForm", method= {RequestMethod.GET, RequestMethod.POST}) 
	  public String modifyForm(@RequestParam("id") int id,
			  				   Model model) { 
		  System.out.println("modifyForm"); 
	  
		  PersonVo personVo = phoneDao.getPerson(id);
		  
		  model.addAttribute("personVo", personVo);
	  
		  return "modifyForm";
	  }
	  
	  //http://localhost:8088/phonebook3/phone/modifyForm?id=?&name=?&hp=?$company=? 
	  //수정 @ModelAttribute
	  @RequestMapping(value="/modify", method ={RequestMethod.GET,RequestMethod.POST}) 
	  public String modify(@ModelAttribute PersonVo personVo) {
		  System.out.println("modify");
		  phoneDao.personUpdate(personVo);
	 
		return "redirect:/phone/list"; 
	 }
	  
	  /*
	 //수정 @RequestParam
	  @RequestMapping(value="/modify2", method =
	 * {RequestMethod.GET,RequestMethod.POST}) public String
	 * modify2(@RequestParam("person_id") int id,
	 * 
	 * @RequestParam("name") String name,
	 * 
	 * @RequestParam("hp") String hp,
	 * 
	 * @RequestParam("company") String company) { System.out.println("modify2");
	 * 
	 * PersonVo personVo = new PersonVo(id, name, hp, company);
	 * System.out.println(personVo.toString());
	 * 
	 * phoneDao.personUpdate(personVo);
	 * 
	 * return "redirect:/phone/list"; }
	 */
	  //삭제 @PathVariable 
	 @RequestMapping(value="/delete/{person_id}", method = {RequestMethod.GET, RequestMethod.POST}) 
	 public String delete(@PathVariable("person_id") int id) { 
		 System.out.println("delete"); 
	  
		 phoneDao.personDelete(id);
	  
		 return "redirect:/phone/list"; 
	 }
	 
	  /*
	 //삭제2 @RequestParam
	 @RequestMapping(value="/delete", method = {RequestMethod.GET,RequestMethod.POST})
	 public String delete2(@RequestParam("person_id") int id){
		 System.out.println("delete");
	  
		 phoneDao.personDelete(id);
		 return "redirect:/phone/list"; 
	 }
	 */
	
	
	
	
}
