package com.taotao.mapper;

import com.taotao.pojo.Doubanmovice;
import com.taotao.pojo.DoubanmoviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoubanmoviceMapper {
    int countByExample(DoubanmoviceExample example);

    int deleteByExample(DoubanmoviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Doubanmovice record);

    int insertSelective(Doubanmovice record);

    List<Doubanmovice> selectByExample(DoubanmoviceExample example);

    Doubanmovice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Doubanmovice record, @Param("example") DoubanmoviceExample example);

    int updateByExample(@Param("record") Doubanmovice record, @Param("example") DoubanmoviceExample example);

    int updateByPrimaryKeySelective(Doubanmovice record);

    int updateByPrimaryKey(Doubanmovice record);
}