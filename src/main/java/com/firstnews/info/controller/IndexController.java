package com.firstnews.info.controller;


import com.firstnews.info.entity.Tedbir;
import com.firstnews.info.model.TedbirModel;
import com.firstnews.info.repo.TedbirRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {
    private final TedbirRepository tedbirRepository;

    public IndexController(TedbirRepository tedbirRepository){
        this.tedbirRepository=tedbirRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ModelAndView getIndex(){
        TedbirModel tedbirModel;
        List<Tedbir> tedbirs= tedbirRepository.findAll();

        List<TedbirModel> tedbirModels = new ArrayList<>();

        for (Tedbir tedbir : tedbirs) {
            if (tedbir.getStatus_id() == 1) {
                tedbirModel = new TedbirModel();
                tedbirModel.setId(tedbir.getId());
                tedbirModel.setAssemblyName(tedbir.getAssembleName());
                tedbirModel.setImageName(tedbir.getImagePath());
                tedbirModel.setAssembleDate(tedbir.getCdate());
                tedbirModels.add(tedbirModel);
            }
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("tedbirs", tedbirModels);
        return modelAndView;
    }
}
