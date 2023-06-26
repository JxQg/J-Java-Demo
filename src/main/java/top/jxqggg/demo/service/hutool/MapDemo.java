package top.jxqggg.demo.service.hutool;

import cn.hutool.json.JSONUtil;
import lombok.*;

import java.util.Date;

/**
 * TODO
 *
 * @author JiangQiang 2022/12/19 11:14
 */
public class MapDemo {

    public static void main(String[] args) {
//        DemoDTO demoDTO1 = new DemoDTO("1", null);
//        DemoDTO demoDTO2 = new DemoDTO("2", null);
//        DemoDTO demoDTO3 = new DemoDTO("3", null);
//        List<DemoDTO> demoDTOList = Arrays.asList(demoDTO1, demoDTO2, demoDTO3);
//        Map<String, String> collect = demoDTOList.stream().collect(Collectors.toMap(DemoDTO::getId, DemoDTO::getPostionId));
        MessageSyncDTO messageSyncDTO = MessageSyncDTO.builder().currentSyncDate(new Date()).build();
        System.out.println(JSONUtil.toJsonStr(messageSyncDTO));
//        System.out.println(collect);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static
    class DemoDTO {
        String id;
        String postionId;
    }

    @Data
    @Builder
    static class MessageSyncDTO {
        private Date currentSyncDate;
    }

}
