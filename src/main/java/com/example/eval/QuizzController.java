package com.example.eval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/quizz")
public class QuizzController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;

    @GetMapping("/question/{userId}")
    public ResponseEntity<Map<String, Object>> getCapitalByCountryId(@PathVariable Integer userId) {
        User user = userService.findById(userId).orElse(null);

        List<Country> countries = countryService.findAll();

        if (!countries.isEmpty()) {
            Random random = new Random();
            Country randomCountry;
            Integer randomId;

            if (user != null && countries.size() > 1) {
                do {
                    int randomIndex = random.nextInt(countries.size());
                    randomCountry = countries.get(randomIndex);
                    randomId = randomCountry.getId();
                } while (user.getLastQuestionId() != null && user.getLastQuestionId() == randomId);

                user.setLastQuestionId(randomId);
                userService.save(user);
            } else {
                int randomIndex = random.nextInt(countries.size());
                randomCountry = countries.get(randomIndex);
                randomId = randomCountry.getId();
            }

            Optional<Country> country = countryService.findById(randomId);
            if (country.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("id", randomId);
                response.put("question", "Quelle est la capitale de " + country.get().getName() + " ?");
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("error", "L'id sélectionné aléatoirement ne correspond à aucun élément de la BDD.");
                return ResponseEntity.ok(response);
            }
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Aucun pays enregistré.");
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/response")
    public ResponseEntity<Map<String, Object>> checkAnswer(@RequestParam Integer id, @RequestParam Integer playerId, @RequestParam String answer) {
        User player = userService.findById(playerId)
                .orElseGet(() -> createUser(playerId));

        Optional<Country> country = countryService.findById(id);

        if (country.isPresent()) {
            if (Objects.equals(country.get().getCapital().toUpperCase(), answer.toUpperCase())){
                player.setPoints(player.getPoints() + 1);
                userService.save(player);

                Map<String, Object> response = new HashMap<>();
                response.put("message", "Bien joué");
                response.put("score", player.getPoints());
                response.put("playerId", player.getId());
                return ResponseEntity.ok(response);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "Non");
                response.put("score", player.getPoints());
                response.put("playerId", player.getId());
                return ResponseEntity.ok(response);
            }
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Pays introuvable");
            response.put("playerId", player.getId());
            return ResponseEntity.ok(response);
        }
    }

    private User createUser(Integer playerId) {
        User player = new User();
        player.setId(playerId);
        player.setPoints(0);
        return userService.save(player);
    }
}
