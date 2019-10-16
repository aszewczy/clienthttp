package pl.szewczyk.ksp2.c5.clienthttp.client;


import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.szewczyk.ksp2.c5.clienthttp.api.Dog;

import java.util.stream.Stream;

@Controller
public class DogClient {

    //Wysyłanie żądań któ©e mają w sobie nagłówki lub Body

  private  RestTemplate restTemplate;


    public DogClient() {
        restTemplate = new RestTemplate();

    }

   // @EventListener(ApplicationReadyEvent.class) //wywołujemy nasze API - jak przekazać nagłówki żeby wywołać nasze REST API
    private void getDogs(){

        MultiValueMap<String,String> headers = new HttpHeaders();
        headers.add("amount","2");

        //ResponseEntity - typ opakowujący w ramach któ©ego możemy przeczytać info np : status code - czy operacja sie powiodla, Body...

        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Dog[]> exhange = restTemplate.exchange("http://localhost:8080/dogs",
                HttpMethod.GET,
                httpEntity,
                Dog[].class);

        Stream.of(exhange.getBody()).forEach(System.out::println);
    }



    // @EventListener(ApplicationReadyEvent.class) //wywołujemy nasze API - jak przekazać nagłówki żeby wywołać nasze REST API
    private void addDog(){

       //nagłówki tu nie potrzebne

         Dog dog = new Dog("Apsik","Owczarek");

        //ResponseEntity - typ opakowujący w ramach któ©ego możemy przeczytać info np : status code - czy operacja sie powiodla, Body...

        HttpEntity httpEntity = new HttpEntity(dog); //teraz przekazujemy psa a nie nagłówki
        restTemplate.exchange("http://localhost:8080/dogs",
                HttpMethod.POST,
                httpEntity,
                void.class); //typ zwracany

//Api może terz przyjmować i header i body na raz
// SpringBoot automatycznie parsuje POJO na JSON
    }


}
