package com.nbd.nbd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nbd.nbd.model.Event;
import com.nbd.nbd.repository.EventRepository;

@RestController
public class EventController {
    @Autowired
    EventRepository eventRepository;

    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck() {
        return "{ \"isWorking\" : true }";
    }

    @GetMapping("/events")
    public List<Event> getEvents() {
        Iterable<Event> result = eventRepository.findAll();
        List<Event> eventList = new ArrayList<Event>();
        result.forEach(eventList::add);
        return eventList;
    }

    @GetMapping("/event/{eventid}")
    public Optional<Event> getEvent(@PathVariable String eventid) {
        Optional<Event> event = eventRepository.findById(eventid);
        return event;
    }

    @PutMapping("/event/edit/{eventid}")
    public Optional<Event> updateEvent(@RequestBody Event newEvent, @PathVariable String eventid) {
        Optional<Event> optionalEmp = eventRepository.findById(eventid);
        if (optionalEmp.isPresent()) {
            Event event = optionalEmp.get();
            event.setAttacktype1_txt(newEvent.getAttacktype1_txt());
            event.setCity(newEvent.getCity());
            event.setCorp1(newEvent.getCorp1());
            event.setCountry_txt(newEvent.getCountry_txt());
            event.setCrit1(newEvent.isCrit1());
            event.setCrit2(newEvent.isCrit2());
            event.setCrit3(newEvent.isCrit3());
            event.setEventid(newEvent.getEventid());
            event.setGname(newEvent.getGname());
            event.setIday(newEvent.getIday());
            event.setImonth(newEvent.getImonth());
            event.setIyear(newEvent.getIyear());
            event.setNatlty1_txt(newEvent.getNatlty1_txt());
            event.setNkill(newEvent.getNkill());
            event.setNwound(newEvent.getNwound());
            event.setProvstate(newEvent.getProvstate());
            event.setRegion_txt(newEvent.getRegion_txt());
            event.setSuccess(newEvent.isSuccess());
            event.setSuicide(newEvent.isSuicide());
            event.setSummary(newEvent.getSummary());
            event.setTarget1(newEvent.getTarget1());
            event.setTargsubtype1_txt(newEvent.getTargsubtype1_txt());
            event.setTargtype1_txt(newEvent.getTargtype1_txt());
            event.setWeaptype1_txt(newEvent.getWeaptype1_txt());
            eventRepository.save(event);
        }
        return optionalEmp;
    }

    @DeleteMapping(value = "/event/delete/{eventid}", produces = "application/json; charset=utf-8")
    public String deleteEvent(@PathVariable String eventid) {
        Boolean result = eventRepository.existsById(eventid);
        eventRepository.deleteById(eventid);
        return "{ \"success\" : " + (result ? "true" : "false") + " }";
    }

    @PostMapping("/event/add")
    public Event addEvent(@RequestBody Event newEvent) {
        int year = newEvent.getIyear();
        int month = newEvent.getImonth();
        int day = newEvent.getIday();
        String mon = String.format("%02d", month);
        String da = String.format("%02d", day);

        String idk = year + mon + da + "0001";
        Optional<Event> even = null;
        int i = 1;
        do {
            i++;
            even = eventRepository.findById(idk);
            if (even.isPresent())
            {
                idk = idk.substring(0, 11) + i;
            }
            else break;
        } while (even.isPresent());

        Event event = new Event(idk, newEvent.getIyear(), newEvent.getImonth(), newEvent.getIday(),
                newEvent.getCountry_txt(), newEvent.getRegion_txt(), newEvent.getProvstate(), newEvent.getCity(),
                newEvent.getSummary(), newEvent.isCrit1(), newEvent.isCrit2(), newEvent.isCrit3(),
                newEvent.isSuccess(), newEvent.isSuicide(), newEvent.getAttacktype1_txt(),
                newEvent.getTargtype1_txt(), newEvent.getTargsubtype1_txt(), newEvent.getCorp1(),
                newEvent.getTarget1(), newEvent.getNatlty1_txt(), newEvent.getGname(), newEvent.getWeaptype1_txt(),
                newEvent.getNkill(), newEvent.getNwound());
        eventRepository.save(event);
        return event;
    }
}