package org.sample.website.dao;

import org.apache.ibatis.annotations.Mapper;
import org.sample.website.entity.Message;
import org.sample.website.util.RowBounds;

import java.util.List;

@Mapper
public interface MessageMapper {

    void saveMessage(Message message);

    List<Message> listMessage(RowBounds rowBounds);
}
