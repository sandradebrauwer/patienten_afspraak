package be.ehb.patienten_afspraak.dao;

import be.ehb.patienten_afspraak.model.Afspraak;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface AfspraakDAO extends CrudRepository<Afspraak, Integer> {

    @Query("SELECT a FROM Afspraak a WHERE a.timestamp = current_date")
    public List<Afspraak> SortedByDate();

}
