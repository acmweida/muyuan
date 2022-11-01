package com.muyuan.manager.system.dto.assembler;

import com.muyuan.config.api.dto.DictDataDTO;
import com.muyuan.manager.system.dto.vo.DictDataVO;
import com.muyuan.manager.system.domains.model.DictData;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class DictDataAssembler {


    public static List<DictDataVO> buildDictDataVO2(List<DictDataDTO> dictDatas) {
        List<DictDataVO> list = new ArrayList<>();
        for (DictDataDTO dictData : dictDatas) {
            if (null != dictData) {
                DictDataVO temp = new DictDataVO();
                BeanUtils.copyProperties(dictData, temp);
                list.add(temp);
            }
        }

        return list;
    }

}
