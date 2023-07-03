package com.example.workshop22.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshop22.models.Rsvp;
import com.example.workshop22.repositories.RsvpRepository;

@RestController
@RequestMapping("/api")
public class RsvpController {

    @Autowired
    private RsvpRepository rsvpRepository;

    @GetMapping("/rsvps")
    public ResponseEntity<List<Rsvp>> getRsvp() {

        List<Rsvp> rsvpList = new ArrayList<Rsvp>();
        rsvpList = rsvpRepository.findAll();

        if (rsvpList.isEmpty()) {
            return new ResponseEntity<List<Rsvp>>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<List<Rsvp>>(rsvpList, HttpStatus.OK);
        }
    }

    @GetMapping("/rsvp")
    public ResponseEntity<List<Rsvp>> getName(@RequestParam("q") String name) {
        try {
            List<Rsvp> rsvps = new ArrayList<Rsvp>();

            if (name == null) {
                rsvpRepository.findAll().forEach(rsvps::add);
            } else {
                rsvpRepository.findByNameContaining(name).forEach((rsvps::add));
            }

            if (rsvps.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(rsvps, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rsvps/count")
    public int countUser() {
        Integer count = rsvpRepository.count();
        return count != null ? count : 0;
    }

    @PostMapping("/rsvps/register")
    public ResponseEntity<Rsvp> register(@RequestBody Rsvp rsvp) {
        try {
            Rsvp _rsvp = rsvpRepository.findByName(rsvp.getName());
            if (_rsvp != null) {
                _rsvp.setName(rsvp.getName());
                _rsvp.setEmail(rsvp.getEmail());
                _rsvp.setPhone(rsvp.getPhone());
                _rsvp.setDate(rsvp.getDate());
                _rsvp.setComment(rsvp.getComment());
                rsvpRepository.updateByName(_rsvp);
                return new ResponseEntity<>(rsvp, HttpStatus.OK);
            } else {
                rsvpRepository.save(rsvp);
                return new ResponseEntity<>(rsvp, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/rsvp/{email}")
    public ResponseEntity<Rsvp> updateByEmail(@PathVariable("email") String email, @RequestBody Rsvp rsvp) {
        Rsvp _rsvp = rsvpRepository.findByEmail(email);
        if (_rsvp != null) {
            _rsvp.setName(rsvp.getName());
            _rsvp.setEmail(rsvp.getEmail());
            _rsvp.setPhone(rsvp.getPhone());
            _rsvp.setDate(rsvp.getDate());
            _rsvp.setComment(rsvp.getComment());
            rsvpRepository.updateByEmail(_rsvp);
            return ResponseEntity.ok(_rsvp);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

// @PostMapping("/rsvps/register")
// public ResponseEntity<Rsvp> register(@RequestBody Rsvp rsvp) {
// try {
// Rsvp rsvpExist = rsvpRepository.findByName(rsvp.getName());
// if (rsvpExist != null) {
// rsvpExist.setEmail(rsvp.getEmail());
// rsvpExist.setPhone(rsvp.getPhone());
// rsvpExist.setDate(rsvp.getDate());
// rsvpExist.setComment(rsvp.getComment());
// rsvpRepository.update(rsvpExist);
// return new ResponseEntity<>(rsvpExist, HttpStatus.OK);
// } else {
// rsvpRepository.register(rsvp);
// return new ResponseEntity<>(rsvp, HttpStatus.CREATED);
// }
// } catch (Exception e) {
// return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
// }
// }
// };
