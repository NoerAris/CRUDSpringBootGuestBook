package com.guestbook.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.guestbook.exception.RecordNotFoundException;
import com.guestbook.model.GuestEntity;
import com.guestbook.service.GuestService;

@Controller
@RequestMapping("/")
public class GuestMvcController 
{
	@Autowired
	GuestService service;

	@RequestMapping
	public String getAllGuest(Model model) 
	{
		List<GuestEntity> list = service.getAllGuest();

		model.addAttribute("guest_all", list);
		return "list-guest";
	}

	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editGuestById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
            if (id.isPresent()) {
                GuestEntity entity = service.getGuestById(id.get());
                model.addAttribute("guest", entity);
                return "edit-guest";
            } else {
                model.addAttribute("guest", new GuestEntity());
                return "add-guest";
            }
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteGuestById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
            service.deleteGuestById(id);
            return "redirect:/";
	}

	@RequestMapping(path = "/createGuest", method = RequestMethod.POST)
	public String createOrUpdateGuest(GuestEntity guest) {
            service.createOrUpdateGuest(guest);
            return "redirect:/";
	}
}
