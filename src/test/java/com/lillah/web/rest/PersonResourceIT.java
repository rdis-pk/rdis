package com.lillah.web.rest;

import com.lillah.Cov001App;
import com.lillah.domain.Person;
import com.lillah.repository.PersonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.lillah.domain.enumeration.SupportStatus;
/**
 * Integration tests for the {@link PersonResource} REST controller.
 */
@SpringBootTest(classes = Cov001App.class)

@AutoConfigureMockMvc
@WithMockUser
public class PersonResourceIT {

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FATHER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FATHER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_HUSBAND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_HUSBAND_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_MOBILE = "AAAAAAAAAA";
    private static final String UPDATED_MOBILE = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_CONTACT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_CONTACT_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_CNIC = "AAAAAAAAAA";
    private static final String UPDATED_CNIC = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_HOME_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_AREA = "AAAAAAAAAA";
    private static final String UPDATED_AREA = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_POSTCODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AA";
    private static final String UPDATED_COUNTRY = "BB";

    private static final SupportStatus DEFAULT_STATUS = SupportStatus.PENDING;
    private static final SupportStatus UPDATED_STATUS = SupportStatus.IN_PROGRESS;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPersonMockMvc;

    private Person person;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Person createEntity(EntityManager em) {
        Person person = new Person()
            .fullName(DEFAULT_FULL_NAME)
            .fatherName(DEFAULT_FATHER_NAME)
            .husbandName(DEFAULT_HUSBAND_NAME)
            .email(DEFAULT_EMAIL)
            .mobile(DEFAULT_MOBILE)
            .otherContactDetails(DEFAULT_OTHER_CONTACT_DETAILS)
            .cnic(DEFAULT_CNIC)
            .homeAddress(DEFAULT_HOME_ADDRESS)
            .area(DEFAULT_AREA)
            .city(DEFAULT_CITY)
            .postcode(DEFAULT_POSTCODE)
            .country(DEFAULT_COUNTRY)
            .status(DEFAULT_STATUS);
        return person;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Person createUpdatedEntity(EntityManager em) {
        Person person = new Person()
            .fullName(UPDATED_FULL_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .husbandName(UPDATED_HUSBAND_NAME)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .otherContactDetails(UPDATED_OTHER_CONTACT_DETAILS)
            .cnic(UPDATED_CNIC)
            .homeAddress(UPDATED_HOME_ADDRESS)
            .area(UPDATED_AREA)
            .city(UPDATED_CITY)
            .postcode(UPDATED_POSTCODE)
            .country(UPDATED_COUNTRY)
            .status(UPDATED_STATUS);
        return person;
    }

    @BeforeEach
    public void initTest() {
        person = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerson() throws Exception {
        int databaseSizeBeforeCreate = personRepository.findAll().size();

        // Create the Person
        restPersonMockMvc.perform(post("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isCreated());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeCreate + 1);
        Person testPerson = personList.get(personList.size() - 1);
        assertThat(testPerson.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testPerson.getFatherName()).isEqualTo(DEFAULT_FATHER_NAME);
        assertThat(testPerson.getHusbandName()).isEqualTo(DEFAULT_HUSBAND_NAME);
        assertThat(testPerson.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testPerson.getMobile()).isEqualTo(DEFAULT_MOBILE);
        assertThat(testPerson.getOtherContactDetails()).isEqualTo(DEFAULT_OTHER_CONTACT_DETAILS);
        assertThat(testPerson.getCnic()).isEqualTo(DEFAULT_CNIC);
        assertThat(testPerson.getHomeAddress()).isEqualTo(DEFAULT_HOME_ADDRESS);
        assertThat(testPerson.getArea()).isEqualTo(DEFAULT_AREA);
        assertThat(testPerson.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testPerson.getPostcode()).isEqualTo(DEFAULT_POSTCODE);
        assertThat(testPerson.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testPerson.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createPersonWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = personRepository.findAll().size();

        // Create the Person with an existing ID
        person.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPersonMockMvc.perform(post("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFullNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setFullName(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobileIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setMobile(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCnicIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setCnic(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCityIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setCity(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPostcodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setPostcode(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCountryIsRequired() throws Exception {
        int databaseSizeBeforeTest = personRepository.findAll().size();
        // set the field null
        person.setCountry(null);

        // Create the Person, which fails.

        restPersonMockMvc.perform(post("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPeople() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get all the personList
        restPersonMockMvc.perform(get("/api/people?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(person.getId().intValue())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].fatherName").value(hasItem(DEFAULT_FATHER_NAME)))
            .andExpect(jsonPath("$.[*].husbandName").value(hasItem(DEFAULT_HUSBAND_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].mobile").value(hasItem(DEFAULT_MOBILE)))
            .andExpect(jsonPath("$.[*].otherContactDetails").value(hasItem(DEFAULT_OTHER_CONTACT_DETAILS)))
            .andExpect(jsonPath("$.[*].cnic").value(hasItem(DEFAULT_CNIC)))
            .andExpect(jsonPath("$.[*].homeAddress").value(hasItem(DEFAULT_HOME_ADDRESS)))
            .andExpect(jsonPath("$.[*].area").value(hasItem(DEFAULT_AREA)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].postcode").value(hasItem(DEFAULT_POSTCODE)))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getPerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        // Get the person
        restPersonMockMvc.perform(get("/api/people/{id}", person.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(person.getId().intValue()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.fatherName").value(DEFAULT_FATHER_NAME))
            .andExpect(jsonPath("$.husbandName").value(DEFAULT_HUSBAND_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.mobile").value(DEFAULT_MOBILE))
            .andExpect(jsonPath("$.otherContactDetails").value(DEFAULT_OTHER_CONTACT_DETAILS))
            .andExpect(jsonPath("$.cnic").value(DEFAULT_CNIC))
            .andExpect(jsonPath("$.homeAddress").value(DEFAULT_HOME_ADDRESS))
            .andExpect(jsonPath("$.area").value(DEFAULT_AREA))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.postcode").value(DEFAULT_POSTCODE))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPerson() throws Exception {
        // Get the person
        restPersonMockMvc.perform(get("/api/people/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        int databaseSizeBeforeUpdate = personRepository.findAll().size();

        // Update the person
        Person updatedPerson = personRepository.findById(person.getId()).get();
        // Disconnect from session so that the updates on updatedPerson are not directly saved in db
        em.detach(updatedPerson);
        updatedPerson
            .fullName(UPDATED_FULL_NAME)
            .fatherName(UPDATED_FATHER_NAME)
            .husbandName(UPDATED_HUSBAND_NAME)
            .email(UPDATED_EMAIL)
            .mobile(UPDATED_MOBILE)
            .otherContactDetails(UPDATED_OTHER_CONTACT_DETAILS)
            .cnic(UPDATED_CNIC)
            .homeAddress(UPDATED_HOME_ADDRESS)
            .area(UPDATED_AREA)
            .city(UPDATED_CITY)
            .postcode(UPDATED_POSTCODE)
            .country(UPDATED_COUNTRY)
            .status(UPDATED_STATUS);

        restPersonMockMvc.perform(put("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedPerson)))
            .andExpect(status().isOk());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeUpdate);
        Person testPerson = personList.get(personList.size() - 1);
        assertThat(testPerson.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testPerson.getFatherName()).isEqualTo(UPDATED_FATHER_NAME);
        assertThat(testPerson.getHusbandName()).isEqualTo(UPDATED_HUSBAND_NAME);
        assertThat(testPerson.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testPerson.getMobile()).isEqualTo(UPDATED_MOBILE);
        assertThat(testPerson.getOtherContactDetails()).isEqualTo(UPDATED_OTHER_CONTACT_DETAILS);
        assertThat(testPerson.getCnic()).isEqualTo(UPDATED_CNIC);
        assertThat(testPerson.getHomeAddress()).isEqualTo(UPDATED_HOME_ADDRESS);
        assertThat(testPerson.getArea()).isEqualTo(UPDATED_AREA);
        assertThat(testPerson.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testPerson.getPostcode()).isEqualTo(UPDATED_POSTCODE);
        assertThat(testPerson.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testPerson.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingPerson() throws Exception {
        int databaseSizeBeforeUpdate = personRepository.findAll().size();

        // Create the Person

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPersonMockMvc.perform(put("/api/people")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(person)))
            .andExpect(status().isBadRequest());

        // Validate the Person in the database
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerson() throws Exception {
        // Initialize the database
        personRepository.saveAndFlush(person);

        int databaseSizeBeforeDelete = personRepository.findAll().size();

        // Delete the person
        restPersonMockMvc.perform(delete("/api/people/{id}", person.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Person> personList = personRepository.findAll();
        assertThat(personList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
