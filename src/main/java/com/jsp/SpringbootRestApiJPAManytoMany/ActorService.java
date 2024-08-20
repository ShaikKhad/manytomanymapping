package com.jsp.SpringbootRestApiJPAManytoMany;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public Actor saveActor(Actor actor) {
        return actorRepository.save(actor);
    }

    public Page<Actor> getActors(Pageable pageable) {
        return actorRepository.findAll(pageable);
    }

    public Optional<Actor> getActorById(Long id) {
        return actorRepository.findById(id);
    }

    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }

    public Page<Actor> getActorsByName(String name, Pageable pageable) {
        return actorRepository.findByNameContaining(name, pageable);
    }
}
