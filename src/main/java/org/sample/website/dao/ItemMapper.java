package org.sample.website.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sample.website.entity.ItemDO;

import java.util.List;

@Mapper
public interface ItemMapper {

    List<ItemDO> listItem(@Param("offset") Integer offset,
                          @Param("limit") Integer limit);
}
