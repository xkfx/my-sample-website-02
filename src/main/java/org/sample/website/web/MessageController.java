package org.sample.website.web;

import org.sample.website.entity.Message;
import org.sample.website.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/messages")
public class MessageController {


    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService service) {
        messageService = service;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> leaveMessage(Message message) {
        // if (A. checkPermisson('laveM', getSub.). ...From)
        messageService.leaveMessage(message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> listMessage(int offset, int limit) {
        List<Message> messageList = messageService.listMessage(offset, limit);
        return new ResponseEntity<>(messageList, HttpStatus.OK);
    }
}
