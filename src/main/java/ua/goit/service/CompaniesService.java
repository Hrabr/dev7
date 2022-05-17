package ua.goit.service;


import ua.goit.base_service.CompanyBase;
import ua.goit.convert.ConverterCompanies;
import ua.goit.dao.CompanyDao;
import ua.goit.dto.CompanyDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CompaniesService {
    private final CompanyBase companiesBase;

    public CompaniesService(CompanyBase companiesBase) {
        this.companiesBase = companiesBase;
    }

    public List<CompanyDto> getAll() {
        ConverterCompanies converterCompanies = new ConverterCompanies();
        List<CompanyDao> all = companiesBase.getAll();
        return all.stream().map(c -> converterCompanies.from(c)).collect(Collectors.toList());
    }

    public Optional<CompanyDao> get(int id) {
        return companiesBase.get(id);
    }

    public void update(CompanyDao companies) {
        companiesBase.update(companies);
    }

    public void create(CompanyDao companies) {

        companiesBase.create(companies);
    }

    public void delete(CompanyDao companies) {
        companiesBase.delete(companies);
    }

    public void updateNullProject(int id) {
        companiesBase.updateNullProject(id);
    }

    public void updateCompany(CompanyDao dao) {

        Optional<CompanyDao> company = companiesBase.get(dao.getId());
        CompanyDao companyDao = company.get();
        companyDao.setName_companies(dao.getName_companies());
        companyDao.setCountry_companies(dao.getCountry_companies());
        companiesBase.update(companyDao);
    }
}
