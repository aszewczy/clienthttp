package pl.szewczyk.ksp2.c5.clienthttp.client;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class AnimalController {


    public AnimalController() {

    }

    private AnimalFact getCatFact(){
        RestTemplate restTemplate = new RestTemplate();
        AnimalFact animalFact = restTemplate.getForObject("https://cat-fact.herokuapp.com/facts/random", AnimalFact.class);


//    for(AnimalFact animalFact:forObject){  //Wyświetlenie całęj Tablicy AnimalFact[].class
//        System.out.println(animalFact);
//    }

        //Klasa kóra pozwala przechowywać pojedynczą gałąż jsona, żeby nie tworzyć struktury modelu dla małego jsona
        //jak jest bardzo proste pojedyncze API
        JsonNode image = restTemplate.getForObject("https://aws.random.cat/meow", JsonNode.class).get("file");
       // System.out.println(animalFact);
        //System.out.println(image.toString());//obrazek

        animalFact.setSrc(image.asText());

        return animalFact;


    }

    @GetMapping("/animal-fact")
    public String get(Model model){
        model.addAttribute("catFact", getCatFact());
        return "AnimalView";
    }
}
