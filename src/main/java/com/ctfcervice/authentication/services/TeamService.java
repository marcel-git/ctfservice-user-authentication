package com.ctfcervice.authentication.services;

import com.ctfcervice.authentication.models.Teams;
import com.ctfcervice.authentication.repository.InTeamRepository;
import com.ctfcervice.authentication.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    InTeamRepository inTeamRepository;


    public void createTeam(Teams object) {

        String inviteCode = object.getTeamName();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(inviteCode.getBytes(StandardCharsets.UTF_8));
            inviteCode = String.format("%040x",new BigInteger(1,digest.digest()));
        }catch (Exception e){
            e.printStackTrace();
        }

        object.setInviteCode(inviteCode.substring(0,10));
        teamRepository.save(object);

    }
}
