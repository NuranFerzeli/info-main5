package com.firstnews.info.controller;

import com.firstnews.info.entity.Tedbir;
import com.firstnews.info.entity.TedbirImages;
import com.firstnews.info.model.TedbirImagesModel;
import com.firstnews.info.model.TedbirModel;
import com.firstnews.info.repo.TedbirImagesRepository;
import com.firstnews.info.repo.TedbirRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TedbirController {
    private TedbirRepository tedbirRepository;
    private TedbirImagesRepository tedbirImagesRepository;
    public TedbirController(TedbirRepository tedbirRepository,  TedbirImagesRepository tedbirImagesRepository1){
        this.tedbirRepository=tedbirRepository;
        this.tedbirImagesRepository = tedbirImagesRepository1;
    }
    @RequestMapping(method = RequestMethod.GET , value = "/assembly")
    public ModelAndView findTedbir(@RequestParam(value = "name", required=false) String name ,@RequestParam(value = "id", required=false) Long id,
                                    HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("tedbir");
        TedbirModel tedbirModel = null;
        Tedbir tedbir =new Tedbir();
        List<TedbirModel> tedbirModels = new ArrayList<>();
        List<Tedbir> tedbirs = new ArrayList<>();
        String name1= (String) session.getAttribute("tedbirName");
        TedbirImagesModel tedbirImagesModel = null;
        List<TedbirImagesModel> tedbirImagesModels = new ArrayList<>();
        List<TedbirImages> tedbirImages = new ArrayList<>();
        if(!(name1==null)) {
            tedbirs.addAll(tedbirRepository.getByAssembleName(name1));
            System.out.println("Ifdeyem");
            for (Tedbir t : tedbirs) {
                if (t.getStatus_id() == 1) {
                        tedbirModel = new TedbirModel();
                        tedbirModel.setAssemblyName(t.getAssembleName());
                        tedbirModel.setAssembleText(t.getText());
                        tedbirModel.setImageName(t.getImagePath());
                        System.out.println(t.getId() + " image id ");
                        session.setAttribute("id",t.getId());
                    }


                }
            System.out.println(session.getAttribute("id") + "Tedbirin id si");
            Long tId= (Long) session.getAttribute("id");
            tedbirImages.addAll(tedbirImagesRepository.getByTedbirId(tId));
            System.out.println(tedbirImages);
            for (TedbirImages tedbirImages1 : tedbirImages) {


                    tedbirImagesModel = new TedbirImagesModel();
                    tedbirImagesModel.setImageName(tedbirImages1.getImageName());
                tedbirImagesModels.add(tedbirImagesModel);
            }

            modelAndView.addObject("tedbirName", tedbirModel.getAssemblyName());
            modelAndView.addObject("tedbirText", tedbirModel.getAssembleText());
            modelAndView.addObject("tedbirMainImage", tedbirModel.getImageName());
            modelAndView.addObject("tedbirImagesModels", tedbirImagesModels);
            System.out.println(tedbirImagesModels);
            }
        if (!(id == null)) {
            for (Tedbir t : tedbirs) {
            if (t.getStatus_id() == 1) {
                    tedbirs.addAll(tedbirRepository.getById(id));
                    if (tedbir.getStatus_id() == 1) {
                        tedbirModel = new TedbirModel();
                        tedbirModel.setAssemblyName(tedbir.getAssembleName());
                        tedbirModel.setAssembleText(tedbir.getText());
                        tedbirImages.addAll(tedbirImagesRepository.getByTedbirId(tedbirModel.getId()));
                        for (TedbirImages tedbirImages1 : tedbirImages) {
                            tedbirImagesModel = new TedbirImagesModel();
                            for (int i = 0; i < tedbirImages.size(); i++) {
                                tedbirImagesModel.setImageName(tedbirImages1.getImageName());
                                tedbirImagesModels.add(tedbirImagesModel);
                            }
                        }
                    }
                }

            }
            modelAndView.addObject("tedbirName", tedbirModel.getAssemblyName());
            modelAndView.addObject("tedbirName", tedbirModel.getAssemblyName());

            modelAndView.addObject("tedbirText", tedbirModel.getAssembleText());
            modelAndView.addObject("tedbirImagesModels", tedbirImagesModels);
            System.out.println(tedbirImagesModels.toArray());
        }
        return modelAndView;

    }


    @RequestMapping(method = RequestMethod.GET , value = "/tedbirler")
    public ModelAndView getAssembly(){
        String key="tedbir";
        TedbirModel tedbirModel = null;
        List<Tedbir> tedbir=new ArrayList<>();
        tedbir= tedbirRepository.findAll();

        for (Tedbir t:tedbir) {
            tedbirModel = new TedbirModel();
            tedbirModel.setAssemblyName(t.getAssembleName());
            tedbirModel.setId(t.getId());
            tedbirModel.setAssembleDate(t.getCdate());
        }
        ModelAndView modelAndView = new ModelAndView("admin");
        return modelAndView;
    }
}
