package Timetable.service;

import Timetable.model.Auditorium;
import Timetable.repositories.AuditoriumRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditoriumService {
    private final AuditoriumRepository auditoriumRepository;

    @Autowired
    public AuditoriumService(AuditoriumRepository auditoriumRepository) {
        this.auditoriumRepository = auditoriumRepository;
    }

    public Auditorium save(Auditorium auditorium) {
        return auditoriumRepository.save(auditorium);
    }

    public ObservableList<Auditorium> getAuditoriums() {
        return FXCollections.observableArrayList(auditoriumRepository.findAll());
    }

    public ObservableList<Auditorium> searchAuditoriums(String text) {
        return FXCollections.observableArrayList(auditoriumRepository.findByNameIgnoreCaseContaining(text));
    }

    public ObservableList<Auditorium> getAvailableAuditoriums(LocalDateTime beginTime, LocalDateTime endTime) {
        return FXCollections.observableArrayList(auditoriumRepository.findAvailableAuditorium(beginTime, endTime));
    }

    public Auditorium getAuditoriumByName(String name){
        return searchAuditoriums(name).get(0);
    }
}
