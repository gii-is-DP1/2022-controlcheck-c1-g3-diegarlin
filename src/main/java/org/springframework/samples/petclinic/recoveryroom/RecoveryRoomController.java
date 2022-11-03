package org.springframework.samples.petclinic.recoveryroom;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.core.RecordExtractor;
import org.springframework.samples.petclinic.web.WelcomeController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecoveryRoomController {
    

    private static final String CREATE_OR_UPDATE_ROOM = "recoveryroom/createOrUpdateRecoveryRoomForm";

    private static final String WELCOME = "welcome";

    private RecoveryRoomService service;

    @Autowired
    public RecoveryRoomController(RecoveryRoomService recoveryRoomService){
        service = recoveryRoomService;
    }

    @GetMapping(value = "/recoveryroom/create")
	public String initCreationForm(ModelMap model) {
		RecoveryRoom recoveryRoom = new RecoveryRoom();
		model.put("recoveryRoom", recoveryRoom);
        model.addAttribute("types", service.getAllRecoveryRoomTypes());

		return CREATE_OR_UPDATE_ROOM;
	}

    @PostMapping(value = "/recoveryroom/create")
	public String initCreationForm(@Valid RecoveryRoom room1, BindingResult br, ModelMap map) {
		if(br.hasErrors()){
            map.addAttribute("recoveryRoom", room1);
            map.addAttribute("types", service.getAllRecoveryRoomTypes());
            return CREATE_OR_UPDATE_ROOM;
        }else{
            try {
                this.service.save(room1);
            } catch (DuplicatedRoomNameException e) {
                br.rejectValue("name", "error", "El nombre de la habitación está duplicado");
                map.addAttribute("recoveryRoom", room1);
                map.addAttribute("types", service.getAllRecoveryRoomTypes());
                return CREATE_OR_UPDATE_ROOM;
            }
           
        }
		return WELCOME;
	}
}
