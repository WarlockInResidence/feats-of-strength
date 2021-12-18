package tech.dumpsterfire.featsofstrength.Controllers;

import tech.dumpsterfire.featsofstrength.Entities.Team;
import tech.dumpsterfire.featsofstrength.Repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    // Create Team - Post
    @PostMapping
    public ResponseEntity<Team> create(@RequestBody Team team) {
        Team savedTeam = teamRepository.save(team);
        URI teamName = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTeam.getId()).toUri();

        return ResponseEntity.created(teamName).body(savedTeam);
    }

    // Read Team - Get
    @GetMapping("/{id}")
    public ResponseEntity<Team> getById(@PathVariable Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalTeam.get());
    }

    // Update Team - Put
    @PutMapping("/{id}")
    public ResponseEntity<Team> update(@PathVariable Long id, Team team) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        team.setId(optionalTeam.get().getId());
        teamRepository.save(team);

        return ResponseEntity.noContent().build();
    }

    // Delete Team - Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Team> delete(@PathVariable Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        teamRepository.delete(optionalTeam.get());

        return ResponseEntity.noContent().build();
    }

    // List - Get all the items
    @GetMapping
    public ResponseEntity<List<Team>> getAll() {
        return ResponseEntity.ok(teamRepository.findAll());
    }
}

