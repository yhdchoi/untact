package com.yhdc.untact.service;


import com.yhdc.untact.dao.AttrDao;
import com.yhdc.untact.dto.Attr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttrService {
    @Autowired
    private AttrDao attrDao;

    public Attr get(String name) {
        String[] nameBits = name.split("__");
        String relTypeCode = nameBits[0];
        int relId = Integer.parseInt(nameBits[1]);
        String typeCode = nameBits[2];
        String type2Code = nameBits[3];

        return get(relTypeCode, relId, typeCode, type2Code);
    }

    public Attr get(String relTypeCode, int relId, String typeCode, String type2Code) {
        return attrDao.get(relTypeCode, relId, typeCode, type2Code);
    }

    public int setValue(String name, String value, String expireDate) {
        String[] nameBits = name.split("__");
        String relTypeCode = nameBits[0];
        int relId = Integer.parseInt(nameBits[1]);
        String typeCode = nameBits[2];
        String type2Code = nameBits[3];

        return setValue(relTypeCode, relId, typeCode, type2Code, value, expireDate);
    }

    public String getValue(String name) {
        String[] nameBits = name.split("__");
        String relTypeCode = nameBits[0];
        int relId = Integer.parseInt(nameBits[1]);
        String typeCode = nameBits[2];
        String type2Code = nameBits[3];

        return getValue(relTypeCode, relId, typeCode, type2Code);
    }

    public String getValue(String relTypeCode, int relId, String typeCode, String type2Code) {
        String value = attrDao.getValue(relTypeCode, relId, typeCode, type2Code);

        if ( value == null ) {
            return "";
        }

        return value;
    }

    public int remove(String name) {
        String[] nameBits = name.split("__");
        String relTypeCode = nameBits[0];
        int relId = Integer.parseInt(nameBits[1]);
        String typeCode = nameBits[2];
        String type2Code = nameBits[3];

        return remove(relTypeCode, relId, typeCode, type2Code);
    }

    public int remove(String relTypeCode, int relId, String typeCode, String type2Code) {
        return attrDao.remove(relTypeCode, relId, typeCode, type2Code);
    }

    public int setValue(String relTypeCode, int relId, String typeCode, String type2Code, String value, String expireDate) {
        attrDao.setValue(relTypeCode, relId, typeCode, type2Code, value, expireDate);
        Attr attr = get(relTypeCode, relId, typeCode, type2Code);

        if (attr != null) {
            return attr.getId();
        }

        return -1;
    }
}
