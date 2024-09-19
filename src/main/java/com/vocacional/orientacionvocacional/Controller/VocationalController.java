package com.vocacional.orientacionvocacional.Controller;
import com.vocacional.orientacionvocacional.model.Question;
import com.vocacional.orientacionvocacional.model.VocationalTest;
import com.vocacional.orientacionvocacional.model.entity.Pregunta;
import com.vocacional.orientacionvocacional.repository.PreguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/vocational-test")
@CrossOrigin(origins = "http://localhost:4200")
public class VocationalController {
    @Autowired
    private PreguntaRepository preguntaRepository;

    @PostMapping("/submit")
    public ResponseEntity<ApiResponse> submitVocationalTest(@RequestBody VocationalTest test){
        String result = calculateTestResult(test);
        ApiResponse apiResponse = new ApiResponse(result);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/questions")
    public List<Pregunta> getQuestions(){
        List<Pregunta> preguntas = preguntaRepository.findAll();
        System.out.println("Preguntas enviadas: " + preguntas);
        return preguntas;
    }

    private String calculateTestResult(VocationalTest test){
        Map<String, Integer> areaScores = new HashMap<>();

        for (Question question: test.getQuestions()){
            if (question.getSelectedOption().getScore()==1){
                String area = question.getArea();
                areaScores.put(area, areaScores.getOrDefault(area, 0)+1);
            }
        }

        String recommendedArea = "";
        int maxScore = 0;

        for (Map.Entry<String, Integer> entry : areaScores.entrySet()){
            if(entry.getValue()>maxScore){
                maxScore = entry.getValue();
                recommendedArea = entry.getKey();
            }
        }

        return "Area recomendada: "+ recommendedArea;
    }
}

