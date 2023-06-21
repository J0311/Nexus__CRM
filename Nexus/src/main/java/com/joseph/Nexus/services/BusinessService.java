package com.joseph.Nexus.services;

import com.joseph.Nexus.models.Business;
import com.joseph.Nexus.repos.BusinessRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class BusinessService {
    private BusinessRepo businessRepo = new BusinessRepo() {
        @Override
        public List<Business> findAllByBusinessId(int businessId) {
            return null;
        }

        @Override
        public List<Business> findAll() {
            return null;
        }

        @Override
        public List<Business> findAll(Sort sort) {
            return null;
        }

        @Override
        public List<Business> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public <S extends Business> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Business> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Business> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Business> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> integers) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Business getOne(Integer integer) {
            return null;
        }

        @Override
        public Business getById(Integer integer) {
            return null;
        }

        @Override
        public Business getReferenceById(Integer integer) {
            return null;
        }

        @Override
        public <S extends Business> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Business> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public Page<Business> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Business> S save(S entity) {
            return null;
        }

        @Override
        public Optional<Business> findById(Integer integer) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {

        }

        @Override
        public void delete(Business entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends Business> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Business> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Business> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Business> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Business> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends Business, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }

    @Autowired
    public void setBusinessRepo (BusinessRepo businessRepo){
        this.businessRepo = businessRepo;
    }

    public List<Business> getAllBusinesses() {
        return businessRepo.findAll();
    }

    // Return an Optional
    public Optional<Business> getBusinessById(int businessId) {
        return businessRepo.findById(businessId);
    }

    public Business addBusiness(Business business) {
        return businessRepo.save(business);
    }

    public void updateBusiness(int businessId, Business business) {
        Optional<Business> existingBusiness = businessRepo.findById(businessId);
        if (existingBusiness.isPresent()) {
            business.setBusinessId(businessId);
            businessRepo.save(business);
        }
    }

    public void deleteBusiness(int businessId) {
        Optional<Business> business = businessRepo.findById(businessId);
        if (business.isPresent()) {
            businessRepo.deleteById(businessId);
        }
    }
}
