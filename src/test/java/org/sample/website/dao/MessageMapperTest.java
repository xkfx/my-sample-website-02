package org.sample.website.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.website.entity.Message;
import org.sample.website.util.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageMapperTest {

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void selectMessage() throws Exception {
        Message message = new Message("test" + (int) (Math.random() * 10), "test");
        assertNull(message.getId());
        messageMapper.saveMessage(message);
        List<Message> list = messageMapper.listMessage(new RowBounds(0, 1));
        assertNotNull(list);
        assertTrue(!list.isEmpty());
        assertEquals(message.getNickname(), list.get(0).getNickname());
        assertNotNull(message.getId()); // 判断是否获取了自增主键
    }

}