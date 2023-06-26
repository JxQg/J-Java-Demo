package top.jxqggg.demo.service.hutool;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author JiangQiang 2022/12/16 16:39
 */
@Log4j2
public class ListDemo {

    public static void main(String[] args) throws Exception {
//        ListDTO listDTO = new ListDTO("1", "1", 1, 1);
//        ListDTO listDTO1 = new ListDTO("2", "2", 1, 2);
//        ListDTO listDTO2 = new ListDTO("3", "3", 2, 3);
//        ListDTO listDTO3 = new ListDTO("4", "4", 2, 4);
//        List<ListDTO> listDTOS = Arrays.asList(listDTO, listDTO1, listDTO2, listDTO3);
//        System.out.println(listDTOS.size());
//        ListDTO listDTO4 = listDTOS.stream().min((l1, l2) -> {
//            if (l1.getPriorityType().equals(l2.getPriorityType())) {
//                return l2.getEntryId().compareTo(l1.getEntryId());
//            } else {
//                return l1.getPriorityType().compareTo(l2.getPriorityType());
//            }
//        }).orElse(null);
//        System.out.println(listDTO4);

        ListDTO1 listDTO = new ListDTO1(3L, true);
        ListDTO1 listDTO1 = new ListDTO1(2L, false);
        ListDTO1 listDTO2 = new ListDTO1(1L, false);
        ListDTO1 listDTO3 = new ListDTO1(1L, true);
        List<ListDTO1> listDTOS = Arrays.asList(listDTO, listDTO1, listDTO2, listDTO3);
        List<ListDTO1> employeeDepartmentRelationSortList = listDTOS.stream()
                .sorted(Comparator.comparing(ListDTO1::getId).thenComparing(ListDTO1::getDefaultValue).reversed()).collect(Collectors.toList());
        System.out.println(JSONUtil.toJsonPrettyStr(employeeDepartmentRelationSortList));

//        for (ListDTO dto : listDTOS) {
//            try {
//                throw new Exception("id:" + dto.getId() + "name:" + dto.getName());
//            } catch (Exception e) {
//                log.info(dto.getId() + "name:" + dto.getName(), e);
//            }
//        }
//        List<String> list = Arrays.asList("1", "2", "3", null);
//        System.out.println(JSONUtil.toJsonStr(listDTOS));
//        throw new Exception(list.toString());

//        Map<Integer, String> failMessageByIndex = new HashMap<>(0);
//        failMessageByIndex.put(1, "2");
//        System.out.println(failMessageByIndex.get(1));
//
//        List<List<String>> strListList = new ArrayList<>(5);
//        List<String> strList = new ArrayList<>();
//        int index = 1;
//        for (int i = 0; i < 500; i++) {
//            strList.add(i + "");
//            if (index == 200) {
//                index = 1;
//                strListList.add(strList);
//                strList = new ArrayList<>(200);
//            } else {
//                index++;
//            }
//        }
//        if (strList.size() > 0) {
//            strListList.add(strList);
//        }
//        System.out.println(JSONUtil.toJsonStr(strListList));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static
    class ListDTO {
        private String id;
        private String name;
        private Integer priorityType;
        private Integer entryId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static
    class ListDTO1 {
        private Long id;
        private Boolean defaultValue;
    }
}
