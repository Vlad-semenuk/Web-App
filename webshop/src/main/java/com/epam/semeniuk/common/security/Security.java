package com.epam.semeniuk.common.security;

import javax.xml.bind.annotation.XmlType;
import java.util.List;
@XmlType(propOrder = {"url", "role"})
public class Security {
    private String role;
    private List<String> url;

    public Security(){}

    public Security(String role, List<String> url) {
        this.role = role;
        this.url = url;
    }

    public String getRole () {
        return role;
    }


    public void setRole (String role){
        this.role = role;
    }

    public List<String> getUrl () {
        return url;
    }


    public void setUrl (List < String > url) {
        this.url = url;
    }

    @Override
    public String toString () {
        return "Security{" +
                "role='" + role + '\'' +
                ", url=" + url +
                '}';
    }
}
