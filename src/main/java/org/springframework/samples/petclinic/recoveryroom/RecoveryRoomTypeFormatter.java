package org.springframework.samples.petclinic.recoveryroom;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class RecoveryRoomTypeFormatter implements Formatter<RecoveryRoomType>{

    private RecoveryRoomService service;

    @Autowired
	public RecoveryRoomTypeFormatter(RecoveryRoomService recoveryRoomService) {
		service = recoveryRoomService;
	}

    @Override
    public String print(RecoveryRoomType object, Locale locale) {
        return object.getName();
    }

    @Override
    public RecoveryRoomType parse(String text, Locale locale) throws ParseException {
        List<RecoveryRoomType> findRoomType = this.service.getAllRecoveryRoomTypes();
		for (RecoveryRoomType type : findRoomType) {
			if (type.getName().equals(text)) {
				return type;
			}
		}
		throw new ParseException("type not found: " + text, 0);
    }
    
}
