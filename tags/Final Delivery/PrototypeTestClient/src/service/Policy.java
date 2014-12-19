
package service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for policy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="policy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ruleStrings" type="{}ruleString" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="securityLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "policy", propOrder = {
    "id",
    "name",
    "ruleStrings",
    "securityLevel"
})
public class Policy {

    protected int id;
    protected String name;
    @XmlElement(nillable = true)
    protected List<RuleString> ruleStrings;
    protected int securityLevel;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the ruleStrings property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ruleStrings property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRuleStrings().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RuleString }
     * 
     * 
     */
    public List<RuleString> getRuleStrings() {
        if (ruleStrings == null) {
            ruleStrings = new ArrayList<RuleString>();
        }
        return this.ruleStrings;
    }

    /**
     * Gets the value of the securityLevel property.
     * 
     */
    public int getSecurityLevel() {
        return securityLevel;
    }

    /**
     * Sets the value of the securityLevel property.
     * 
     */
    public void setSecurityLevel(int value) {
        this.securityLevel = value;
    }

}
