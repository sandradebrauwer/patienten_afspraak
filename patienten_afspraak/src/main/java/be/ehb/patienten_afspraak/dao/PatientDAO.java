package be.ehb.patienten_afspraak.dao;

import be.ehb.patienten_afspraak.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientDAO extends CrudRepository<Patient, Long> {

}
