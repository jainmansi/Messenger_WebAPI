package org.firstRESTapi.messenger.service;

import java.util.ArrayList;
import java.util.List;

import org.firstRESTapi.messenger.model.Message;

public class MessageService {

	public List<Message> getAllMessages() {
		Message m1 = new Message(1L, "First Book", "Mansi Jain");
		Message m2 = new Message(2L, "Second Book", "Mansi Jain");
		
		List<Message> list = new ArrayList<>();
		list.add(m1);
		list.add(m2);
		
		return list;
		
	}
}
