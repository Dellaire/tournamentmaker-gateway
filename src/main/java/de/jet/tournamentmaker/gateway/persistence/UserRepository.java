package de.jet.tournamentmaker.gateway.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.jet.tournamentmaker.gateway.model.TournamentUser;

public interface UserRepository extends MongoRepository<TournamentUser, String>
{
	public TournamentUser findByUsername(String username);
}
