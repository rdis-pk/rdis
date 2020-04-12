package com.lillah.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import ch.qos.logback.classic.db.names.ColumnName;
import com.lillah.domain.enumeration.SupportStatus;
import org.checkerframework.common.aliasing.qual.Unique;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 500)
    @Column(name = "full_name", length = 500, nullable = false)
    private String fullName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "husband_name")
    private String husbandName;

    @Column(name = "email")
    private String email;

    @NotNull
    @Size(max = 12)
    @Column(name = "mobile", length = 12, nullable = false)
    private String mobile;

    @Size(max = 100)
    @Column(name = "other_contact_details", length = 100)
    private String otherContactDetails;

    @NotNull
    @Size(max = 13)
    @Column(name = "cnic", length = 13, unique = true, nullable = false)
    private String cnic;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "area")
    private String area;

    @NotNull
    @Size(max = 50)
    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @NotNull
    @Size(max = 10)
    @Column(name = "postcode", length = 10, nullable = false)
    private String postcode;

    @NotNull
    @Size(max = 2)
    @Column(name = "country", length = 2, nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SupportStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public Person fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Person fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getHusbandName() {
        return husbandName;
    }

    public Person husbandName(String husbandName) {
        this.husbandName = husbandName;
        return this;
    }

    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }

    public String getEmail() {
        return email;
    }

    public Person email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public Person mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtherContactDetails() {
        return otherContactDetails;
    }

    public Person otherContactDetails(String otherContactDetails) {
        this.otherContactDetails = otherContactDetails;
        return this;
    }

    public void setOtherContactDetails(String otherContactDetails) {
        this.otherContactDetails = otherContactDetails;
    }

    public String getCnic() {
        return cnic;
    }

    public Person cnic(String cnic) {
        this.cnic = cnic;
        return this;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public Person homeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
        return this;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getArea() {
        return area;
    }

    public Person area(String area) {
        this.area = area;
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public Person city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public Person postcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public Person country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public SupportStatus getStatus() {
        return status;
    }

    public Person status(SupportStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(SupportStatus status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Person)) {
            return false;
        }
        return id != null && id.equals(((Person) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", husbandName='" + getHusbandName() + "'" +
            ", email='" + getEmail() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", otherContactDetails='" + getOtherContactDetails() + "'" +
            ", cnic='" + getCnic() + "'" +
            ", homeAddress='" + getHomeAddress() + "'" +
            ", area='" + getArea() + "'" +
            ", city='" + getCity() + "'" +
            ", postcode='" + getPostcode() + "'" +
            ", country='" + getCountry() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
