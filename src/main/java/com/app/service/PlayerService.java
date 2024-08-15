package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.PlayerDTOAdmin;

public interface PlayerService {

	List<PlayerDTOAdmin> getAllPlayers();

	Optional<PlayerDTOAdmin> getPlayerById(Long id);

	PlayerDTOAdmin addOrUpdatePlayer(PlayerDTOAdmin playerDTO);

	void deletePlayer(Long id);

	PlayerDTOAdmin approvePlayer(Long id);

	PlayerDTOAdmin rejectPlayer(Long id);

	PlayerDTOAdmin updatePlayer(PlayerDTOAdmin playerDTO);
}