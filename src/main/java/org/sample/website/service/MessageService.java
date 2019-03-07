package org.sample.website.service;

import org.sample.website.entity.Message;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

public interface MessageService {

    void leaveMessage(@Valid Message message); // 校验实体

    List<Message> listMessage(@Min(value = 0, message = "offset不能小于0") int offset,
                              @Min(value = 1, message = "limit至少为1") int limit);
}
