package com.epam.semeniuk.common.security;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "securities")
public class SecurityConfig {
    private List<Security> constraint = new ArrayList<Security>();

    public List<Security> getConstraint() {
        return constraint;
    }

    @XmlElement(name = "constraint")
    public void setConstraint(List<Security> constraint) {
        this.constraint = constraint;
    }
}
