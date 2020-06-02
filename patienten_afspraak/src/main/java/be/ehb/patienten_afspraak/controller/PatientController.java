package be.ehb.patienten_afspraak.controller;

import be.ehb.patienten_afspraak.dao.AfspraakDAO;
import be.ehb.patienten_afspraak.dao.PatientDAO;
import be.ehb.patienten_afspraak.model.Afspraak;
import be.ehb.patienten_afspraak.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class PatientController {

    private PatientDAO patientDAO;
    private AfspraakDAO afspraakDAO;

    @Autowired
    public PatientController(PatientDAO patientDAO, AfspraakDAO afspraakDAO) {
        this.patientDAO = patientDAO;
        this.afspraakDAO = afspraakDAO;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public Iterable<Afspraak> getAllAfspraak(){
        return afspraakDAO.SortedByDate();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public void NewPatient(@RequestParam(value = "id") Long id,
                           @RequestParam(value = "firstname") String firstname,
                           @RequestParam(value = "lastname") String lastname,
                           @RequestParam(value = "birthday") String birthdayTxt) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-mm-dd");
        Date birthday = sdf.parse(birthdayTxt);

        Patient patient = new Patient(id, lastname, firstname, birthday);
        patientDAO.save(patient);
    }

    @RequestMapping(value = "/newAfspraak", method = RequestMethod.POST)
    @ResponseBody
    public void NewAfspraak(@RequestParam(value = "timestamp") String ts,
                            @RequestParam(value = "id") double id,
                            @RequestParam(value = "patientid") double patientId){

        Patient patient = patientDAO.findById((long) patientId).get();
        Afspraak afspraak = new Afspraak(id, LocalDateTime.parse(ts));
        afspraak.setPatient(patient);
        afspraakDAO.save(afspraak);
    }

    @RequestMapping(value = "/deleteAfspraak/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    //Pathvariabele is enkel voor 1 value (id) zie hierboven
    public HttpStatus delete(@PathVariable(value = "id") Integer id){
        if(afspraakDAO.findById(id).isPresent()){
            Afspraak afspraak = afspraakDAO.findById(id).get();
            afspraakDAO.delete(afspraak);
            return HttpStatus.OK;
        } else return HttpStatus.NOT_FOUND;
    }

    @RequestMapping(value = "/updateAfspraak/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public HttpStatus update(@PathVariable(value = "id") Integer id){
        if (afspraakDAO.findById(id).isPresent()){
            Afspraak afspraak = afspraakDAO.findById(id).get();
            afspraakDAO.save(afspraak);
            return HttpStatus.OK;
        } else return HttpStatus.NOT_FOUND;
    }

}
