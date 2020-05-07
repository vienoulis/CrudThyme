package ru.vienoulis.crud_thymeleaf.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LongAdapter extends XmlAdapter {
    @Override
    public String unmarshal(Object v) throws Exception {
        if (v == null){
            return "";
        }
        return v.toString();
    }

    @Override
    public Long marshal(Object v) throws Exception {
        if (v == null){
            return 0L;
        }
        return Long.parseLong(v.toString());
    }
}
