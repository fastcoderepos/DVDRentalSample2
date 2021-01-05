package com.fastcode.dvdrentalsample2.restcontrollers.core;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fastcode.dvdrentalsample2.application.core.category.CategoryAppService;
import com.fastcode.dvdrentalsample2.application.core.film.FilmAppService;
import com.fastcode.dvdrentalsample2.application.core.filmcategory.FilmCategoryAppService;
import com.fastcode.dvdrentalsample2.application.core.filmcategory.dto.*;
import com.fastcode.dvdrentalsample2.commons.logging.LoggingHelper;
import com.fastcode.dvdrentalsample2.commons.search.SearchUtils;
import com.fastcode.dvdrentalsample2.domain.core.address.AddressEntity;
import com.fastcode.dvdrentalsample2.domain.core.address.IAddressRepository;
import com.fastcode.dvdrentalsample2.domain.core.category.CategoryEntity;
import com.fastcode.dvdrentalsample2.domain.core.category.ICategoryRepository;
import com.fastcode.dvdrentalsample2.domain.core.city.CityEntity;
import com.fastcode.dvdrentalsample2.domain.core.city.ICityRepository;
import com.fastcode.dvdrentalsample2.domain.core.country.CountryEntity;
import com.fastcode.dvdrentalsample2.domain.core.country.ICountryRepository;
import com.fastcode.dvdrentalsample2.domain.core.customer.CustomerEntity;
import com.fastcode.dvdrentalsample2.domain.core.customer.ICustomerRepository;
import com.fastcode.dvdrentalsample2.domain.core.film.FilmEntity;
import com.fastcode.dvdrentalsample2.domain.core.film.IFilmRepository;
import com.fastcode.dvdrentalsample2.domain.core.filmcategory.FilmCategoryEntity;
import com.fastcode.dvdrentalsample2.domain.core.filmcategory.FilmCategoryId;
import com.fastcode.dvdrentalsample2.domain.core.filmcategory.IFilmCategoryRepository;
import com.fastcode.dvdrentalsample2.domain.core.inventory.IInventoryRepository;
import com.fastcode.dvdrentalsample2.domain.core.inventory.InventoryEntity;
import com.fastcode.dvdrentalsample2.domain.core.language.ILanguageRepository;
import com.fastcode.dvdrentalsample2.domain.core.language.LanguageEntity;
import com.fastcode.dvdrentalsample2.domain.core.rental.IRentalRepository;
import com.fastcode.dvdrentalsample2.domain.core.rental.RentalEntity;
import com.fastcode.dvdrentalsample2.domain.core.staff.IStaffRepository;
import com.fastcode.dvdrentalsample2.domain.core.staff.StaffEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.core.env.Environment;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=test")
public class FilmCategoryControllerTest {

    @Autowired
    protected SortHandlerMethodArgumentResolver sortArgumentResolver;

    @Autowired
    @Qualifier("filmCategoryRepository")
    protected IFilmCategoryRepository filmCategory_repository;

    @Autowired
    @Qualifier("categoryRepository")
    protected ICategoryRepository categoryRepository;

    @Autowired
    @Qualifier("filmRepository")
    protected IFilmRepository filmRepository;

    @Autowired
    @Qualifier("languageRepository")
    protected ILanguageRepository languageRepository;

    @Autowired
    @Qualifier("rentalRepository")
    protected IRentalRepository rentalRepository;

    @Autowired
    @Qualifier("addressRepository")
    protected IAddressRepository addressRepository;

    @Autowired
    @Qualifier("customerRepository")
    protected ICustomerRepository customerRepository;

    @Autowired
    @Qualifier("staffRepository")
    protected IStaffRepository staffRepository;

    @Autowired
    @Qualifier("countryRepository")
    protected ICountryRepository countryRepository;

    @Autowired
    @Qualifier("cityRepository")
    protected ICityRepository cityRepository;

    @Autowired
    @Qualifier("inventoryRepository")
    protected IInventoryRepository inventoryRepository;

    @SpyBean
    @Qualifier("filmCategoryAppService")
    protected FilmCategoryAppService filmCategoryAppService;

    @SpyBean
    @Qualifier("categoryAppService")
    protected CategoryAppService categoryAppService;

    @SpyBean
    @Qualifier("filmAppService")
    protected FilmAppService filmAppService;

    @SpyBean
    protected LoggingHelper logHelper;

    @SpyBean
    protected Environment env;

    @Mock
    protected Logger loggerMock;

    protected FilmCategoryEntity filmCategory;

    protected MockMvc mvc;

    @Autowired
    EntityManagerFactory emf;

    static EntityManagerFactory emfs;

    static int relationCount = 10;

    int countCategory = 10;

    int countLanguage = 10;

    int countRental = 10;

    int countAddress = 10;

    int countFilm = 10;

    int countCustomer = 10;

    int countStaff = 10;

    int countCountry = 10;

    int countCity = 10;

    int countInventory = 10;

    @PostConstruct
    public void init() {
        emfs = emf;
    }

    @AfterClass
    public static void cleanup() {
        EntityManager em = emfs.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.FILM_CATEGORY").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.CATEGORY").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.FILM").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.LANGUAGE").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.RENTAL").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.ADDRESS").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.CUSTOMER").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.STAFF").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.COUNTRY").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.CITY").executeUpdate();
        em.createNativeQuery("truncate table dvdrental.INVENTORY").executeUpdate();
        em.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
        em.getTransaction().commit();
    }

    public CategoryEntity createCategoryEntity() {
        if (countCategory > 60) {
            countCategory = 10;
        }

        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryId(relationCount);
        categoryEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countCategory + "-09-01 05:25:22"));
        categoryEntity.setName(String.valueOf(relationCount));
        categoryEntity.setVersiono(0L);
        relationCount++;
        if (!categoryRepository.findAll().contains(categoryEntity)) {
            categoryEntity = categoryRepository.save(categoryEntity);
        }
        countCategory++;
        return categoryEntity;
    }

    public LanguageEntity createLanguageEntity() {
        if (countLanguage > 60) {
            countLanguage = 10;
        }

        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLanguageId(relationCount);
        languageEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countLanguage + "-09-01 05:25:22"));
        languageEntity.setName(String.valueOf(relationCount));
        languageEntity.setVersiono(0L);
        relationCount++;
        if (!languageRepository.findAll().contains(languageEntity)) {
            languageEntity = languageRepository.save(languageEntity);
        }
        countLanguage++;
        return languageEntity;
    }

    public RentalEntity createRentalEntity() {
        if (countRental > 60) {
            countRental = 10;
        }

        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countRental + "-09-01 05:25:22"));
        rentalEntity.setRentalDate(SearchUtils.stringToLocalDateTime("19" + countRental + "-09-01 05:25:22"));
        rentalEntity.setRentalId(relationCount);
        rentalEntity.setReturnDate(SearchUtils.stringToLocalDateTime("19" + countRental + "-09-01 05:25:22"));
        rentalEntity.setVersiono(0L);
        relationCount++;
        CustomerEntity customer = createCustomerEntity();
        rentalEntity.setCustomer(customer);
        StaffEntity staff = createStaffEntity();
        rentalEntity.setStaff(staff);
        InventoryEntity inventory = createInventoryEntity();
        rentalEntity.setInventory(inventory);
        if (!rentalRepository.findAll().contains(rentalEntity)) {
            rentalEntity = rentalRepository.save(rentalEntity);
        }
        countRental++;
        return rentalEntity;
    }

    public AddressEntity createAddressEntity() {
        if (countAddress > 60) {
            countAddress = 10;
        }

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress(String.valueOf(relationCount));
        addressEntity.setAddress2(String.valueOf(relationCount));
        addressEntity.setAddressId(relationCount);
        addressEntity.setDistrict(String.valueOf(relationCount));
        addressEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countAddress + "-09-01 05:25:22"));
        addressEntity.setPhone(String.valueOf(relationCount));
        addressEntity.setPostalCode(String.valueOf(relationCount));
        addressEntity.setVersiono(0L);
        relationCount++;
        CityEntity city = createCityEntity();
        addressEntity.setCity(city);
        if (!addressRepository.findAll().contains(addressEntity)) {
            addressEntity = addressRepository.save(addressEntity);
        }
        countAddress++;
        return addressEntity;
    }

    public FilmEntity createFilmEntity() {
        if (countFilm > 60) {
            countFilm = 10;
        }

        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setDescription(String.valueOf(relationCount));
        filmEntity.setFilmId(relationCount);
        filmEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countFilm + "-09-01 05:25:22"));
        filmEntity.setLength((short) relationCount);
        filmEntity.setRating(String.valueOf(relationCount));
        filmEntity.setReleaseYear(relationCount);
        filmEntity.setRentalDuration((short) relationCount);
        filmEntity.setRentalRate(BigDecimal.valueOf(relationCount));
        filmEntity.setReplacementCost(BigDecimal.valueOf(relationCount));
        filmEntity.setTitle(String.valueOf(relationCount));
        filmEntity.setVersiono(0L);
        relationCount++;
        LanguageEntity language = createLanguageEntity();
        filmEntity.setLanguage(language);
        if (!filmRepository.findAll().contains(filmEntity)) {
            filmEntity = filmRepository.save(filmEntity);
        }
        countFilm++;
        return filmEntity;
    }

    public CustomerEntity createCustomerEntity() {
        if (countCustomer > 60) {
            countCustomer = 10;
        }

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setActive(relationCount);
        customerEntity.setActivebool(false);
        customerEntity.setCreateDate(SearchUtils.stringToLocalDate("19" + countCustomer + "-09-01"));
        customerEntity.setCustomerId(relationCount);
        customerEntity.setEmail(String.valueOf(relationCount));
        customerEntity.setFirstName(String.valueOf(relationCount));
        customerEntity.setLastName(String.valueOf(relationCount));
        customerEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countCustomer + "-09-01 05:25:22"));
        customerEntity.setStoreId((short) relationCount);
        customerEntity.setVersiono(0L);
        relationCount++;
        AddressEntity address = createAddressEntity();
        customerEntity.setAddress(address);
        if (!customerRepository.findAll().contains(customerEntity)) {
            customerEntity = customerRepository.save(customerEntity);
        }
        countCustomer++;
        return customerEntity;
    }

    public StaffEntity createStaffEntity() {
        if (countStaff > 60) {
            countStaff = 10;
        }

        StaffEntity staffEntity = new StaffEntity();
        staffEntity.setActive(false);
        staffEntity.setEmail(String.valueOf(relationCount));
        staffEntity.setFirstName(String.valueOf(relationCount));
        staffEntity.setLastName(String.valueOf(relationCount));
        staffEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countStaff + "-09-01 05:25:22"));
        staffEntity.setPassword(String.valueOf(relationCount));
        staffEntity.setStaffId(relationCount);
        staffEntity.setStoreId((short) relationCount);
        staffEntity.setUsername(String.valueOf(relationCount));
        staffEntity.setVersiono(0L);
        relationCount++;
        AddressEntity address = createAddressEntity();
        staffEntity.setAddress(address);
        if (!staffRepository.findAll().contains(staffEntity)) {
            staffEntity = staffRepository.save(staffEntity);
        }
        countStaff++;
        return staffEntity;
    }

    public CountryEntity createCountryEntity() {
        if (countCountry > 60) {
            countCountry = 10;
        }

        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountry(String.valueOf(relationCount));
        countryEntity.setCountryId(relationCount);
        countryEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countCountry + "-09-01 05:25:22"));
        countryEntity.setVersiono(0L);
        relationCount++;
        if (!countryRepository.findAll().contains(countryEntity)) {
            countryEntity = countryRepository.save(countryEntity);
        }
        countCountry++;
        return countryEntity;
    }

    public CityEntity createCityEntity() {
        if (countCity > 60) {
            countCity = 10;
        }

        CityEntity cityEntity = new CityEntity();
        cityEntity.setCity(String.valueOf(relationCount));
        cityEntity.setCityId(relationCount);
        cityEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countCity + "-09-01 05:25:22"));
        cityEntity.setVersiono(0L);
        relationCount++;
        CountryEntity country = createCountryEntity();
        cityEntity.setCountry(country);
        if (!cityRepository.findAll().contains(cityEntity)) {
            cityEntity = cityRepository.save(cityEntity);
        }
        countCity++;
        return cityEntity;
    }

    public InventoryEntity createInventoryEntity() {
        if (countInventory > 60) {
            countInventory = 10;
        }

        InventoryEntity inventoryEntity = new InventoryEntity();
        inventoryEntity.setInventoryId(relationCount);
        inventoryEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("19" + countInventory + "-09-01 05:25:22"));
        inventoryEntity.setStoreId((short) relationCount);
        inventoryEntity.setVersiono(0L);
        relationCount++;
        FilmEntity film = createFilmEntity();
        inventoryEntity.setFilm(film);
        if (!inventoryRepository.findAll().contains(inventoryEntity)) {
            inventoryEntity = inventoryRepository.save(inventoryEntity);
        }
        countInventory++;
        return inventoryEntity;
    }

    public FilmCategoryEntity createEntity() {
        CategoryEntity category = createCategoryEntity();
        FilmEntity film = createFilmEntity();

        FilmCategoryEntity filmCategoryEntity = new FilmCategoryEntity();
        filmCategoryEntity.setCategoryId((short) 1);
        filmCategoryEntity.setFilmId((short) 1);
        filmCategoryEntity.setLastUpdate(SearchUtils.stringToLocalDateTime("1996-09-01 09:15:22"));
        filmCategoryEntity.setVersiono(0L);
        filmCategoryEntity.setCategory(category);
        filmCategoryEntity.setCategoryId(Integer.parseInt(category.getCategoryId().toString()));
        filmCategoryEntity.setFilm(film);
        filmCategoryEntity.setFilmId(Integer.parseInt(film.getFilmId().toString()));

        return filmCategoryEntity;
    }

    public CreateFilmCategoryInput createFilmCategoryInput() {
        CreateFilmCategoryInput filmCategoryInput = new CreateFilmCategoryInput();
        filmCategoryInput.setCategoryId((short) 5);
        filmCategoryInput.setFilmId((short) 5);
        filmCategoryInput.setLastUpdate(SearchUtils.stringToLocalDateTime("1996-08-10 05:25:22"));

        return filmCategoryInput;
    }

    public FilmCategoryEntity createNewEntity() {
        FilmCategoryEntity filmCategory = new FilmCategoryEntity();
        filmCategory.setCategoryId((short) 3);
        filmCategory.setFilmId((short) 3);
        filmCategory.setLastUpdate(SearchUtils.stringToLocalDateTime("1996-08-11 05:35:22"));

        return filmCategory;
    }

    public FilmCategoryEntity createUpdateEntity() {
        FilmCategoryEntity filmCategory = new FilmCategoryEntity();
        filmCategory.setCategoryId((short) 4);
        filmCategory.setFilmId((short) 4);
        filmCategory.setLastUpdate(SearchUtils.stringToLocalDateTime("1996-09-09 05:45:22"));

        return filmCategory;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        final FilmCategoryController filmCategoryController = new FilmCategoryController(
            filmCategoryAppService,
            categoryAppService,
            filmAppService,
            logHelper,
            env
        );
        when(logHelper.getLogger()).thenReturn(loggerMock);
        doNothing().when(loggerMock).error(anyString());

        this.mvc =
            MockMvcBuilders
                .standaloneSetup(filmCategoryController)
                .setCustomArgumentResolvers(sortArgumentResolver)
                .setControllerAdvice()
                .build();
    }

    @Before
    public void initTest() {
        filmCategory = createEntity();
        List<FilmCategoryEntity> list = filmCategory_repository.findAll();
        if (!list.contains(filmCategory)) {
            filmCategory = filmCategory_repository.save(filmCategory);
        }
    }

    @Test
    public void FindById_IdIsValid_ReturnStatusOk() throws Exception {
        mvc
            .perform(
                get(
                    "/filmCategory/categoryId=" +
                    filmCategory.getCategoryId() +
                    ",filmId=" +
                    filmCategory.getFilmId() +
                    "/"
                )
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());
    }

    @Test
    public void FindById_IdIsNotValid_ReturnStatusNotFound() {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(get("/filmCategory/categoryId=999,filmId=999").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("Not found"));
    }

    @Test
    public void CreateFilmCategory_FilmCategoryDoesNotExist_ReturnStatusOk() throws Exception {
        CreateFilmCategoryInput filmCategoryInput = createFilmCategoryInput();

        CategoryEntity category = createCategoryEntity();

        filmCategoryInput.setCategoryId(Integer.parseInt(category.getCategoryId().toString()));

        FilmEntity film = createFilmEntity();

        filmCategoryInput.setFilmId(Integer.parseInt(film.getFilmId().toString()));

        ObjectWriter ow = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .writer()
            .withDefaultPrettyPrinter();

        String json = ow.writeValueAsString(filmCategoryInput);

        mvc
            .perform(post("/filmCategory").contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk());
    }

    @Test
    public void DeleteFilmCategory_IdIsNotValid_ThrowEntityNotFoundException() {
        doReturn(null).when(filmCategoryAppService).findById(new FilmCategoryId((short) 999, (short) 999));
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(
                            delete("/filmCategory/categoryId=999,filmId=999").contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
            )
            .hasCause(
                new EntityNotFoundException("There does not exist a filmCategory with a id=categoryId=999,filmId=999")
            );
    }

    @Test
    public void Delete_IdIsValid_ReturnStatusNoContent() throws Exception {
        FilmCategoryEntity entity = createNewEntity();
        entity.setVersiono(0L);
        CategoryEntity category = createCategoryEntity();
        entity.setCategoryId(Integer.parseInt(category.getCategoryId().toString()));
        entity.setCategory(category);
        FilmEntity film = createFilmEntity();
        entity.setFilmId(Integer.parseInt(film.getFilmId().toString()));
        entity.setFilm(film);
        entity = filmCategory_repository.save(entity);

        FindFilmCategoryByIdOutput output = new FindFilmCategoryByIdOutput();
        output.setCategoryId(entity.getCategoryId());
        output.setFilmId(entity.getFilmId());
        output.setLastUpdate(entity.getLastUpdate());

        //    Mockito.when(filmCategoryAppService.findById(new FilmCategoryId(entity.getCategoryId(), entity.getFilmId()))).thenReturn(output);
        Mockito
            .doReturn(output)
            .when(filmCategoryAppService)
            .findById(new FilmCategoryId(entity.getCategoryId(), entity.getFilmId()));

        mvc
            .perform(
                delete("/filmCategory/categoryId=" + entity.getCategoryId() + ",filmId=" + entity.getFilmId() + "/")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isNoContent());
    }

    @Test
    public void UpdateFilmCategory_FilmCategoryDoesNotExist_ReturnStatusNotFound() throws Exception {
        doReturn(null).when(filmCategoryAppService).findById(new FilmCategoryId((short) 999, (short) 999));

        UpdateFilmCategoryInput filmCategory = new UpdateFilmCategoryInput();
        filmCategory.setCategoryId((short) 999);
        filmCategory.setFilmId((short) 999);
        filmCategory.setLastUpdate(SearchUtils.stringToLocalDateTime("1996-09-28 07:15:22"));

        ObjectWriter ow = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .writer()
            .withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(filmCategory);

        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(
                            put("/filmCategory/categoryId=999,filmId=999")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                        )
                        .andExpect(status().isOk())
            )
            .hasCause(
                new EntityNotFoundException(
                    "Unable to update. FilmCategory with id=categoryId=999,filmId=999 not found."
                )
            );
    }

    @Test
    public void UpdateFilmCategory_FilmCategoryExists_ReturnStatusOk() throws Exception {
        FilmCategoryEntity entity = createUpdateEntity();
        entity.setVersiono(0L);

        CategoryEntity category = createCategoryEntity();
        entity.setCategoryId(Integer.parseInt(category.getCategoryId().toString()));
        entity.setCategory(category);
        FilmEntity film = createFilmEntity();
        entity.setFilmId(Integer.parseInt(film.getFilmId().toString()));
        entity.setFilm(film);
        entity = filmCategory_repository.save(entity);
        FindFilmCategoryByIdOutput output = new FindFilmCategoryByIdOutput();
        output.setCategoryId(entity.getCategoryId());
        output.setFilmId(entity.getFilmId());
        output.setLastUpdate(entity.getLastUpdate());
        output.setVersiono(entity.getVersiono());

        Mockito
            .when(filmCategoryAppService.findById(new FilmCategoryId(entity.getCategoryId(), entity.getFilmId())))
            .thenReturn(output);

        UpdateFilmCategoryInput filmCategoryInput = new UpdateFilmCategoryInput();
        filmCategoryInput.setCategoryId(entity.getCategoryId());
        filmCategoryInput.setFilmId(entity.getFilmId());
        filmCategoryInput.setLastUpdate(entity.getLastUpdate());

        ObjectWriter ow = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .writer()
            .withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(filmCategoryInput);

        mvc
            .perform(
                put("/filmCategory/categoryId=" + entity.getCategoryId() + ",filmId=" + entity.getFilmId() + "/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
            )
            .andExpect(status().isOk());

        FilmCategoryEntity de = createUpdateEntity();
        de.setCategoryId(entity.getCategoryId());
        de.setFilmId(entity.getFilmId());
        filmCategory_repository.delete(de);
    }

    @Test
    public void FindAll_SearchIsNotNullAndPropertyIsValid_ReturnStatusOk() throws Exception {
        mvc
            .perform(
                get("/filmCategory?search=categoryId[equals]=1&limit=10&offset=1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());
    }

    @Test
    public void FindAll_SearchIsNotNullAndPropertyIsNotValid_ThrowException() throws Exception {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(
                            get("/filmCategory?search=filmCategorycategoryId[equals]=1&limit=10&offset=1")
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
            )
            .hasCause(new Exception("Wrong URL Format: Property filmCategorycategoryId not found!"));
    }

    @Test
    public void GetCategory_IdIsNotEmptyAndIdIsNotValid_ThrowException() {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(get("/filmCategory/categoryId999/category").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("Invalid id=categoryId999"));
    }

    @Test
    public void GetCategory_IdIsNotEmptyAndIdDoesNotExist_ReturnNotFound() {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(
                            get("/filmCategory/categoryId=999,filmId=999/category")
                                .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("Not found"));
    }

    @Test
    public void GetCategory_searchIsNotEmptyAndPropertyIsValid_ReturnList() throws Exception {
        mvc
            .perform(
                get(
                    "/filmCategory/categoryId=" +
                    filmCategory.getCategoryId() +
                    ",filmId=" +
                    filmCategory.getFilmId() +
                    "/category"
                )
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());
    }

    @Test
    public void GetFilm_IdIsNotEmptyAndIdIsNotValid_ThrowException() {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(get("/filmCategory/categoryId999/film").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("Invalid id=categoryId999"));
    }

    @Test
    public void GetFilm_IdIsNotEmptyAndIdDoesNotExist_ReturnNotFound() {
        org.assertj.core.api.Assertions
            .assertThatThrownBy(
                () ->
                    mvc
                        .perform(
                            get("/filmCategory/categoryId=999,filmId=999/film").contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
            )
            .hasCause(new EntityNotFoundException("Not found"));
    }

    @Test
    public void GetFilm_searchIsNotEmptyAndPropertyIsValid_ReturnList() throws Exception {
        mvc
            .perform(
                get(
                    "/filmCategory/categoryId=" +
                    filmCategory.getCategoryId() +
                    ",filmId=" +
                    filmCategory.getFilmId() +
                    "/film"
                )
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());
    }
}
