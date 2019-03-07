package org.sample.website.web;

import org.sample.website.entity.Message;
import org.sample.website.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PageDataController {
    private MessageService messageService;

    @Autowired
    public PageDataController(MessageService service) {
        messageService = service;
    }

    @RequestMapping(value = "api/v1/message_board_vo", method = RequestMethod.GET)
    public ResponseEntity<?> listMessage(int offset, int limit) {
        List<Message> messageList = messageService.listMessage(offset, limit);
        Map<String, Object> nodes = new HashMap<>();
        nodes.put("title", "this is my message board");
        nodes.put("messageList", messageList);
        return new ResponseEntity<>(nodes, HttpStatus.OK);
    }
}
