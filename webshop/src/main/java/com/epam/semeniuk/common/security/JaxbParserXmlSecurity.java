package com.epam.semeniuk.common.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static com.epam.semeniuk.constant.Paths.SECURITY_FILE_PATH;

public class JaxbParserXmlSecurity {

    private static final Logger LOG = LogManager.getLogger(JaxbParserXmlSecurity.class);

    public SecurityConfig getSecurityConfig() {
        SecurityConfig securityConfig = null;
        try {
            JAXBContext context = JAXBContext.newInstance(SecurityConfig.class);
            securityConfig = (SecurityConfig) context.createUnmarshaller().unmarshal(new FileReader(SECURITY_FILE_PATH));

        } catch (JAXBException | FileNotFoundException e) {
            LOG.error(e);
        }
        return securityConfig;
    }

    public void setSecurityConfig(SecurityConfig securityConfig) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(SecurityConfig.class);
        Marshaller mar = context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(securityConfig, new File(SECURITY_FILE_PATH));
    }

}
