package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.PlayerDTOAdmin;
import com.app.service.PlayerService;

@RestController
@RequestMapping("/players")
@CrossOrigin
public class PlayerController {
	
	
	@Autowired
	private  PlayerService playerService;
	
	
	@GetMapping
    public List<PlayerDTOAdmin> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTOAdmin> getPlayerById(@PathVariable Long id) {
        Optional<PlayerDTOAdmin> PlayerDTOAdmin = playerService.getPlayerById(id);
        return PlayerDTOAdmin.map(ResponseEntity::ok)
           
        		.orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PlayerDTOAdmin> addOrUpdatePlayer(@RequestBody PlayerDTOAdmin PlayerDTOAdmin) {
        PlayerDTOAdmin savedPlayerDTOAdmin = playerService.addOrUpdatePlayer(PlayerDTOAdmin);
        return new ResponseEntity<>(savedPlayerDTOAdmin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerDTOAdmin> UpdatePlayer(@RequestBody PlayerDTOAdmin playerDTOAdmin) {
    	System.out.println(playerDTOAdmin);
    	
        PlayerDTOAdmin savedPlayerDTOAdmin = playerService.updatePlayer(playerDTOAdmin);
        return new ResponseEntity<>(savedPlayerDTOAdmin, HttpStatus.CREATED);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<PlayerDTOAdmin> approvePlayer(@PathVariable Long id) {
        PlayerDTOAdmin PlayerDTOAdmin = playerService.approvePlayer(id);
        if (PlayerDTOAdmin != null) {
            return ResponseEntity.ok(PlayerDTOAdmin);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<PlayerDTOAdmin> rejectPlayer(@PathVariable Long id) {
        PlayerDTOAdmin PlayerDTOAdmin = playerService.rejectPlayer(id);
        if (PlayerDTOAdmin != null) {
            return ResponseEntity.ok(PlayerDTOAdmin);
        }
        return ResponseEntity.notFound().build();
    }

  
}
