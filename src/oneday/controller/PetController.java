package oneday.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import oneday.pojo.Pet;
import oneday.pojo.Setting;
import oneday.pojo.User;
import oneday.service.OnedayService;

@Controller
public class PetController {
	@Resource
	private OnedayService onedayServiceImpl;

	@RequestMapping(value = "pet")
	@ResponseBody
	public Pet getPet(@RequestBody Pet pet,HttpSession session) {
		return onedayServiceImpl.getPet(pet.getPhone());
	}
	
	@RequestMapping(value = "pet/petName")
	@ResponseBody
	public boolean setPetName(@RequestBody Pet pet,HttpSession session) {
		return onedayServiceImpl.updPetName(pet.getPetname(), pet.getPhone());
	}
	
	@RequestMapping(value = "pet/petWeight")
	@ResponseBody
	public boolean setPetWeight(@RequestBody Pet pet,HttpSession session) {
		return onedayServiceImpl.updPetWeight(pet.getWeight(), pet.getPhone());
	}
	
	@RequestMapping(value = "pet/petIntimacy")
	@ResponseBody
	public boolean setPetIntimacy(@RequestBody Pet pet,HttpSession session) {
		return onedayServiceImpl.updPetIntimacy(pet.getIntimacy(), pet.getPhone());
	}
	
	@RequestMapping(value = "pet/petColor")
	@ResponseBody
	public boolean Color(@RequestBody Pet pet,HttpSession session) {
		return onedayServiceImpl.updPetColor(pet.getColor(), pet.getPhone());
	}
}
