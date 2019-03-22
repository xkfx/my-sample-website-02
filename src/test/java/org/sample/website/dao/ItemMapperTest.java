package org.sample.website.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.website.entity.ItemDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemMapperTest {

    @Autowired
    private ItemMapper mapper;

    @Test
    public void listItem() throws Exception {
        List<ItemDO> list = mapper.listItem(0, 2);
        ItemDO itemDO = list.get(1);
        assertEquals("test_item01", itemDO.getName());
    }

}