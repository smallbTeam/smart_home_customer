/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.atat.message.service;

import com.atat.util.StringUtil;
import com.atat.util.httpClient.URLUtil;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author ligw
 * @version $Id shortMessageServiceImpl.java, v 0.1 2017-07-15 16:00 ligw Exp $$
 */
public interface ShortMessageService {

    /**
     * 发送短信 返回发送状态
     * @param mobelPhone
     * @param msmContent
     * @return
     */
    public  Integer sendShortMessage(String mobelPhone, String msmContent);
}
