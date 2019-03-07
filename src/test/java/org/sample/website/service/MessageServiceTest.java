package org.sample.website.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.website.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageService messageService;

    @Test
    public void leaveMessage() throws Exception {
        Message message = new Message("正好六个字吗", "凑满2个字凑满2个字凑满2个字凑满2个字");
        assertNull(message.getId());
        messageService.leaveMessage(message);
        assertNotNull(message.getId());
    }

    @Test
    public void listMessage() throws Exception {
        List<Message> list = messageService.listMessage(0, 1);
        assertNotNull(list);
        assertTrue(!list.isEmpty());
    }
}