//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-146 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.04.04 at 06:25:02 PM GMT+05:30 
//


package com.photon.phresco.bby;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="synonym" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="primaryTerm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="listId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *                   &lt;element name="directionality" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="exactMatch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="term" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "synonym"
})
@XmlRootElement(name = "synonyms")
public class Synonyms {

    protected List<Synonyms.Synonym> synonym;

    /**
     * Gets the value of the synonym property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the synonym property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSynonym().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Synonyms.Synonym }
     * 
     * 
     */
    public List<Synonyms.Synonym> getSynonym() {
        if (synonym == null) {
            synonym = new ArrayList<Synonyms.Synonym>();
        }
        return this.synonym;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="primaryTerm" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="listId" type="{http://www.w3.org/2001/XMLSchema}long"/>
     *         &lt;element name="directionality" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="exactMatch" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="term" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "primaryTerm",
        "listId",
        "directionality",
        "exactMatch",
        "term"
    })
    public static class Synonym {

        @XmlElement(required = true)
        protected String primaryTerm;
        protected long listId;
        @XmlElement(required = true)
        protected String directionality;
        @XmlElement(required = true)
        protected String exactMatch;
        @XmlElement(required = true)
        protected String term;

        /**
         * Gets the value of the primaryTerm property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPrimaryTerm() {
            return primaryTerm;
        }

        /**
         * Sets the value of the primaryTerm property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPrimaryTerm(String value) {
            this.primaryTerm = value;
        }

        /**
         * Gets the value of the listId property.
         * 
         */
        public long getListId() {
            return listId;
        }

        /**
         * Sets the value of the listId property.
         * 
         */
        public void setListId(long value) {
            this.listId = value;
        }

        /**
         * Gets the value of the directionality property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDirectionality() {
            return directionality;
        }

        /**
         * Sets the value of the directionality property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDirectionality(String value) {
            this.directionality = value;
        }

        /**
         * Gets the value of the exactMatch property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getExactMatch() {
            return exactMatch;
        }

        /**
         * Sets the value of the exactMatch property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setExactMatch(String value) {
            this.exactMatch = value;
        }

        /**
         * Gets the value of the term property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTerm() {
            return term;
        }

        /**
         * Sets the value of the term property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTerm(String value) {
            this.term = value;
        }

    }

}
