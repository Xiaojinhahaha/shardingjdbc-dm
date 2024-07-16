package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description: 取模分表测试DO
 *
 * @author xiaoj
 * @version 1.0.0
 * @date 2024/07/16 14:32:38
 */
@TableName("T_TEST")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Integer num;

    private String name;
}
