package com.example.ldaptest.service;


import com.example.ldaptest.model.User;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class LdapService {

    private final LdapTemplate ldapTemplate;

    public LdapService(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public List<String> getAllPersonNames() {
        return ldapTemplate.search(
                query().where("objectclass").is("inetOrgPerson"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attrs)
                            throws NamingException {
                        return (String) attrs.get("cn").get();
                    }
                });
    }

    public void create(User user){
        Attribute objectClass = new BasicAttribute("objectClass");
        {
            objectClass.add("inetOrgPerson");

        }
        Attributes userAttributes = new BasicAttributes();
        userAttributes.put(objectClass);
        userAttributes.put("cn", user.getCn());
        userAttributes.put("sn", user.getSn());
        userAttributes.put("uid", user.getUid());
        userAttributes.put("userPassword", user.getUserPassword().getBytes());
        ldapTemplate.bind(bindDN(user.getCn()), null, userAttributes);
    }

    public static javax.naming.Name bindDN(String _x){
        Name name = new DistinguishedName("cn=" + _x + ",ou=users");
        return name;
    }

    public boolean authenticate(User user) {
        return ldapTemplate.authenticate("cn="+user.getCn()+",ou=users", "(cn=" + user.getCn() + ")", user.getUserPassword());
    }
}
