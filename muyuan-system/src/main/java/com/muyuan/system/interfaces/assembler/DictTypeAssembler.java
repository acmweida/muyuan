package com.muyuan.system.interfaces.assembler;

import com.muyuan.system.application.vo.DictTypeVO;
import com.muyuan.system.domain.model.DictType;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class DictTypeAssembler {

    public static DictTypeVO buildDictDataVO(DictType dictType) {
        DictTypeVO temp = new DictTypeVO();
        BeanUtils.copyProperties(dictType,temp);
        return temp;
    }

    public static List<DictTypeVO> buildDictDataVO(List<DictType> dictTypes) {
        List<DictTypeVO> list = new ArrayList<>();
        for (DictType dictData: dictTypes) {
            if (null != dictData) {
                DictTypeVO temp = new DictTypeVO();
                BeanUtils.copyProperties(dictData,temp);
                list.add(temp);
            }
        }

        return list;
    }
}
