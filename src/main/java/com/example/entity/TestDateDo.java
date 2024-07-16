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
 * description: 自定义时间算法测试do
 *
 * @author xiaoj
 * @version 1.0.0
 * @date 2024/07/16 15:05:02
 */
@TableName("T_TEST_DATE")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDateDo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String  createDate;

    private String name;
}
