package com.joseph.Nexus.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.models.Contract;
import com.joseph.Nexus.repos.BusinessRepo;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BusinessService.class})
@ExtendWith(SpringExtension.class)
class BusinessServiceTest {
    @MockBean
    private BusinessRepo businessRepo;

    @Autowired
    private BusinessService businessService;

    /**
     * Method under test: {@link BusinessService#getAllBusinesses()}
     */
    @Test
    void testGetAllBusinesses() {
        ArrayList<Business> businessList = new ArrayList<>();
        when(businessRepo.findAll()).thenReturn(businessList);
        List<Business> actualAllBusinesses = businessService.getAllBusinesses();
        assertSame(businessList, actualAllBusinesses);
        assertTrue(actualAllBusinesses.isEmpty());
        verify(businessRepo).findAll();
    }

    /**
     * Method under test: {@link BusinessService#getBusinessById(int)}
     */
    @Test
    void testGetBusinessById() {
        Contract contract = new Contract();
        contract.setClient_name("Dr Jane Doe");
        contract.setContractId(123);
        contract.setPending(true);

        Business business = new Business();
        business.setBusinessId(123);
        business.setBusiness_name("Business name");
        business.setContract(contract);
        business.setDescription("The characteristics of someone or something");
        business.setEmail("jane.doe@example.org");
        business.setPending(true);
        business.setPhone_number(10);
        Optional<Business> ofResult = Optional.of(business);
        when(businessRepo.findById((Integer) any())).thenReturn(ofResult);
        Optional<Business> actualBusinessById = businessService.getBusinessById(123);
        assertSame(ofResult, actualBusinessById);
        assertTrue(actualBusinessById.isPresent());
        verify(businessRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link BusinessService#addBusiness(Business)}
     */
    @Test
    void testAddBusiness() {
        Contract contract = new Contract();
        contract.setClient_name("Dr Jane Doe");
        contract.setContractId(123);
        contract.setPending(true);

        Business business = new Business();
        business.setBusinessId(123);
        business.setBusiness_name("Business name");
        business.setContract(contract);
        business.setDescription("The characteristics of someone or something");
        business.setEmail("jane.doe@example.org");
        business.setPending(true);
        business.setPhone_number(10);
        when(businessRepo.save((Business) any())).thenReturn(business);

        Contract contract1 = new Contract();
        contract1.setClient_name("Dr Jane Doe");
        contract1.setContractId(123);
        contract1.setPending(true);

        Business business1 = new Business();
        business1.setBusinessId(123);
        business1.setBusiness_name("Business name");
        business1.setContract(contract1);
        business1.setDescription("The characteristics of someone or something");
        business1.setEmail("jane.doe@example.org");
        business1.setPending(true);
        business1.setPhone_number(10);
        assertSame(business, businessService.addBusiness(business1));
        verify(businessRepo).save((Business) any());
    }

    /**
     * Method under test: {@link BusinessService#updateBusiness(int, Business)}
     */
    @Test
    void testUpdateBusiness() {
        Contract contract = new Contract();
        contract.setClient_name("Dr Jane Doe");
        contract.setContractId(123);
        contract.setPending(true);

        Business business = new Business();
        business.setBusinessId(123);
        business.setBusiness_name("Business name");
        business.setContract(contract);
        business.setDescription("The characteristics of someone or something");
        business.setEmail("jane.doe@example.org");
        business.setPending(true);
        business.setPhone_number(10);
        Optional<Business> ofResult = Optional.of(business);

        Contract contract1 = new Contract();
        contract1.setClient_name("Dr Jane Doe");
        contract1.setContractId(123);
        contract1.setPending(true);

        Business business1 = new Business();
        business1.setBusinessId(123);
        business1.setBusiness_name("Business name");
        business1.setContract(contract1);
        business1.setDescription("The characteristics of someone or something");
        business1.setEmail("jane.doe@example.org");
        business1.setPending(true);
        business1.setPhone_number(10);
        when(businessRepo.save((Business) any())).thenReturn(business1);
        when(businessRepo.findById((Integer) any())).thenReturn(ofResult);

        Contract contract2 = new Contract();
        contract2.setClient_name("Dr Jane Doe");
        contract2.setContractId(123);
        contract2.setPending(true);

        Business business2 = new Business();
        business2.setBusinessId(123);
        business2.setBusiness_name("Business name");
        business2.setContract(contract2);
        business2.setDescription("The characteristics of someone or something");
        business2.setEmail("jane.doe@example.org");
        business2.setPending(true);
        business2.setPhone_number(10);
        businessService.updateBusiness(123, business2);
        verify(businessRepo).save((Business) any());
        verify(businessRepo).findById((Integer) any());
        assertEquals(123, business2.getBusinessId());
    }

    /**
     * Method under test: {@link BusinessService#updateBusiness(int, Business)}
     */
    @Test
    void testUpdateBusiness2() {
        Contract contract = new Contract();
        contract.setClient_name("Dr Jane Doe");
        contract.setContractId(123);
        contract.setPending(true);

        Business business = new Business();
        business.setBusinessId(123);
        business.setBusiness_name("Business name");
        business.setContract(contract);
        business.setDescription("The characteristics of someone or something");
        business.setEmail("jane.doe@example.org");
        business.setPending(true);
        business.setPhone_number(10);
        when(businessRepo.save((Business) any())).thenReturn(business);
        when(businessRepo.findById((Integer) any())).thenReturn(Optional.empty());

        Contract contract1 = new Contract();
        contract1.setClient_name("Dr Jane Doe");
        contract1.setContractId(123);
        contract1.setPending(true);

        Business business1 = new Business();
        business1.setBusinessId(123);
        business1.setBusiness_name("Business name");
        business1.setContract(contract1);
        business1.setDescription("The characteristics of someone or something");
        business1.setEmail("jane.doe@example.org");
        business1.setPending(true);
        business1.setPhone_number(10);
        businessService.updateBusiness(123, business1);
        verify(businessRepo).findById((Integer) any());
        assertEquals(123, business1.getBusinessId());
        assertTrue(business1.isPending());
        assertEquals(10, business1.getPhone_number());
        assertEquals("jane.doe@example.org", business1.getEmail());
        assertEquals("The characteristics of someone or something", business1.getDescription());
        assertEquals(contract, business1.getContract());
        assertEquals("Business name", business1.getBusiness_name());
        assertTrue(businessService.getAllBusinesses().isEmpty());
    }

    /**
     * Method under test: {@link BusinessService#deleteBusiness(int)}
     */
    @Test
    void testDeleteBusiness() {
        Contract contract = new Contract();
        contract.setClient_name("Dr Jane Doe");
        contract.setContractId(123);
        contract.setPending(true);

        Business business = new Business();
        business.setBusinessId(123);
        business.setBusiness_name("Business name");
        business.setContract(contract);
        business.setDescription("The characteristics of someone or something");
        business.setEmail("jane.doe@example.org");
        business.setPending(true);
        business.setPhone_number(10);
        Optional<Business> ofResult = Optional.of(business);
        doNothing().when(businessRepo).deleteById((Integer) any());
        when(businessRepo.findById((Integer) any())).thenReturn(ofResult);
        businessService.deleteBusiness(123);
        verify(businessRepo).findById((Integer) any());
        verify(businessRepo).deleteById((Integer) any());
        assertTrue(businessService.getAllBusinesses().isEmpty());
    }

    /**
     * Method under test: {@link BusinessService#deleteBusiness(int)}
     */
    @Test
    void testDeleteBusiness2() {
        doNothing().when(businessRepo).deleteById((Integer) any());
        when(businessRepo.findById((Integer) any())).thenReturn(Optional.empty());
        businessService.deleteBusiness(123);
        verify(businessRepo).findById((Integer) any());
        assertTrue(businessService.getAllBusinesses().isEmpty());
    }
}

