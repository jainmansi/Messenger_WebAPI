package org.firstRESTapi.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.firstRESTapi.messenger.database.DatabaseClass;
import org.firstRESTapi.messenger.exception.DataNotFoundException;
import org.firstRESTapi.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages = DatabaseClass.getMessages();

	public MessageService() {
		messages.put(1L, new Message(1, "First Book", "Mansi"));
		messages.put(2L, new Message(2, "Second Book", "Mansi"));
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	public Message getMessage(long id) {
		Message message = messages.get(id);
		if(message == null)
			throw new DataNotFoundException("Message with id" + id + "not found");
		return message;
	}

	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getId() <= 0) {
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}

	public Message removeMessage(Long id) {
		return messages.remove(id);
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;
	}

	public List<Message> getAllMessagesPaginated(int start, int size) {
		ArrayList<Message> allMessages = new ArrayList<>(messages.values());
		if (start + size > allMessages.size()) {
			return new ArrayList<>();
		}
		return allMessages.subList(start, start + size);
	}

}
