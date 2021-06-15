package com.ctfcervice.authentication.controllers;

import com.ctfcervice.authentication.controllers.interfaces.IRequest;
import com.ctfcervice.authentication.models.Teams;
import com.ctfcervice.authentication.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/team")
public class TeamController implements IRequest<Teams> {

    @Autowired
    TeamService teamService;

    public Iterable<Teams> getAll(){
        return null;
    }

    public Optional<Teams> getById(@PathVariable long id){
        return null;
    }

    /**
     * Creates a new Team
     * @param object
     * @return
     */
    @Override
    public ResponseEntity<?> postObject(Teams object) {
        teamService.createTeam(object);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateObject(long id, Teams object) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteObject(long id) {
        return null;
    }


}
