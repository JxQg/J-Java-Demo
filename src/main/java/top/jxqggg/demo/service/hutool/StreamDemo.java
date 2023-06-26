package top.jxqggg.demo.service.hutool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : JiangQiang
 * @date :  2022/3/9
 **/
public class StreamDemo {

    public static void main(String[] args) {
        List<DemoDTO> demoDTOList = new ArrayList<>();
        demoDTOList.add(new DemoDTO(1L, "1", 1, false));
        demoDTOList.add(new DemoDTO(2L, "2", 1, false));
        demoDTOList.add(new DemoDTO(3L, "3", 1, false));
        Optional<DemoDTO> any = demoDTOList.stream().filter(DemoDTO::getEnable).findAny();
        if (!any.isPresent()){
            System.out.println("不存在");
        }
        if (any.isPresent()){
            System.out.println("存在");
        }


//        Map<Long, DemoDTO> demoDTOByIdMap = demoDTOList.stream().collect(Collectors.toMap(DemoDTO::getId, Function.identity()));
//        List<Long> idList = demoDTOList.stream().map(DemoDTO::getId).collect(Collectors.toList());
//        int totalCount = demoDTOList.stream().mapToInt(DemoDTO::getCount).sum();
//        DemoDTO demoDTO = demoDTOByIdMap.get(1L);
//        System.out.println(demoDTO);
//        System.out.println(idList);
//        System.out.println(totalCount);
//        System.out.println(idList.size());
//        System.out.println(demoDTOByIdMap);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    static class DemoDTO {
        private Long id;
        private String name;
        private Integer count;
        private Boolean enable;
    }
}
