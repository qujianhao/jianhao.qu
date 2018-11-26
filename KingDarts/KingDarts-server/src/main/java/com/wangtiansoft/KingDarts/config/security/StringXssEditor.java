package com.wangtiansoft.KingDarts.config.security;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;

/**
 * Created by weitong on 2017/9/4.
 */
public class StringXssEditor extends StringTrimmerEditor {

    public StringXssEditor(boolean emptyAsNull) {
        super(emptyAsNull);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isEmpty(text)){
            setValue(null);
        } else if (text != null) {
            String value = StringEscapeUtils.escapeHtml4(text);
            setValue(value);
        } else {
            super.setAsText(text);
        }
    }
}
