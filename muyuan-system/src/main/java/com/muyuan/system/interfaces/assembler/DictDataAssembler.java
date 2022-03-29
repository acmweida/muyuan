package com.muyuan.system.interfaces.assembler;

import com.muyuan.system.application.vo.DictDataVO;
import com.muyuan.system.domain.model.DictData;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class DictDataAssembler {

    public static List<DictDataVO> buildDictDataVO(List<DictData> dictDatas) {
        List<DictDataVO> list = new ArrayList<>();
        for (DictData dictData: dictDatas) {
            if (null != dictData) {
                DictDataVO temp = new DictDataVO();
                BeanUtils.copyProperties(dictData,temp);
                list.add(temp);
            }
        }

        return list;
    }
}
