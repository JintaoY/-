package net.seehope.hxfenxiao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.seehope.hxfenxiao.pojo.Code;

/**
 * 通用 Mapper 代码生成器
 *
 * @author mapper-generator
 */
public interface CodeMapper extends tk.mybatis.mapper.common.Mapper<Code> {
    void insertIntoCodes(List<Code> list);

    Integer updateByPackcode(@Param("packcode") String packcode, @Param("id") String id);
}
