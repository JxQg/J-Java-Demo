package top.jxqggg.demo.area.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Jiang
 * @since 2023-06-06 01:40:50
 */
@Getter
@Setter
@TableName("area_city")
public class AreaCity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("pid")
    private Integer pid;

    @TableField("deep")
    private Integer deep;

    @TableField("name")
    private String name;

    @TableField("pinyin_prefix")
    private String pinyinPrefix;

    @TableField("pinyin")
    private String pinyin;

    @TableField("ext_id")
    private String extId;

    @TableField("ext_name")
    private String extName;

    @TableField("geo")
    private String geo;
}
