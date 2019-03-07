package org.sample.website.service.impl;

import org.sample.website.dao.MessageMapper;
import org.sample.website.entity.Message;
import org.sample.website.service.MessageService;
import org.sample.website.util.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;

    @Autowired
    public MessageServiceImpl(MessageMapper mapper) {
        messageMapper = mapper;
    }

    @Override
    public void leaveMessage(Message message) {
        messageMapper.saveMessage(message);
    }

    @Override
    public List<Message> listMessage(int offset, int limit) {
        return messageMapper.listMessage(new RowBounds(offset, limit));
    }
}
